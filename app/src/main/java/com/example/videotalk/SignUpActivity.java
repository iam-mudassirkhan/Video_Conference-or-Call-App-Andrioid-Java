package com.example.videotalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {
    Button btnSignUp;
    EditText emailBox, passBox, userName;
    TextView haveAccount;
    FirebaseAuth auth;
    ProgressDialog dialog;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        haveAccount = findViewById(R.id.alreadyHaveAccount);
        btnSignUp = findViewById(R.id.signUp);
        userName = findViewById(R.id.userName);
        emailBox = findViewById(R.id.email);
        passBox = findViewById(R.id.pass);
        auth = FirebaseAuth.getInstance();
         database = FirebaseFirestore.getInstance();

         dialog = new ProgressDialog(this);
         dialog.setMessage("Signing Up...");



       btnSignUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialog.show();
               String name,email, password;
               name = userName.getText().toString();
               email = emailBox.getText().toString();
               password = passBox.getText().toString();

               User user = new User();
               user.setEmail(email);
               user.setName(name);
               user.setPassword(password);

               auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()){
                           // success
                           dialog.dismiss();
                           database.collection("Users")
                                   .document(user.email).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                               @Override
                               public void onSuccess(Void aVoid) {
                                   startActivity(new Intent(SignUpActivity.this, DashboardActivity.class));
                               }
                           });
                           Toast.makeText(SignUpActivity.this, "Account is Created Successfully", Toast.LENGTH_SHORT).show();

                       }
                       else {
                           Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                       }

                   }
               });
               userName.setText("");
               emailBox.setText("");
               passBox.setText("");
           }

       });


        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveToLoginActivity = new Intent(SignUpActivity.this, SignIn_Activity.class);
                startActivity(moveToLoginActivity);
            }
        });
    }
}