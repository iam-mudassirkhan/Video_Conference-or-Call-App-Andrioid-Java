package com.example.videotalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn_Activity extends AppCompatActivity {
private Button createAccount, BtnLogIn;
    EditText emailBox, passBox;
    FirebaseAuth auth;
   ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_);

        createAccount = findViewById(R.id.createAccount);
        BtnLogIn = findViewById(R.id.logIn);
        emailBox = findViewById(R.id.email);
        passBox = findViewById(R.id.pass);
        auth = FirebaseAuth.getInstance();
       dialog = new ProgressDialog(this);
       dialog.setMessage("Please Wait...");

        BtnLogIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dialog.show();
                String email, password;
                email = emailBox.getText().toString();
                password = passBox.getText().toString();

                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            dialog.dismiss();
                            Intent goToMainActivity = new Intent(SignIn_Activity.this, DashboardActivity.class);
                            startActivity(goToMainActivity);
                            Toast.makeText(SignIn_Activity.this, "Logged in", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(SignIn_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });

            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveToLoginActivity = new Intent(SignIn_Activity.this, SignUpActivity.class);
                startActivity(moveToLoginActivity);
            }
        });
    }
}