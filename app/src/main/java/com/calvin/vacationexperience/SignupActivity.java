package com.calvin.vacationexperience;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    Context context;
    FirebaseAuth auth;
    DatabaseReference db;

    EditText nameText;
    EditText usernameText;
    EditText emailText;
    EditText mobileText;
    EditText passwordText;
    EditText reEnterPasswordText;
    Button signupButton;
    TextView loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        context=this;
        auth=FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance().getReference().child("Users");

        nameText=findViewById(R.id.input_name);
        usernameText=findViewById(R.id.input_username);
        emailText=findViewById(R.id.input_email);
        mobileText=findViewById(R.id.input_mobile);
        passwordText=findViewById(R.id.input_password);
        reEnterPasswordText= findViewById(R.id.input_reEnterPassword);
        signupButton=findViewById(R.id.btn_signup);
        loginLink=findViewById(R.id.link_login) ;

       signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        final String name = nameText.getText().toString();
        final String username = usernameText.getText().toString();
        final String email = emailText.getText().toString();
        final String mobile = mobileText.getText().toString();
        final String password = passwordText.getText().toString();
        final String reEnterPassword = reEnterPasswordText.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess(name,username,email,mobile,password,reEnterPassword);
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess(final String name, final String username, final String email, final String mobile, String password, String reEnterPassword) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser currentUser=auth.getCurrentUser();
                    DatabaseReference userDB=db.child(currentUser.getUid());
                    userDB.child("name").setValue(name);
                    userDB.child("Username").setValue(username);
                    userDB.child("Email").setValue(email);
                    userDB.child("Mobile").setValue(mobile);
                    Intent intent=new Intent(context,MainActivity.class);
                    startActivity(intent);
                    signupButton.setEnabled(true);
                    finish();
                }
                else{
                    onSignupFailed();
                }
            }
        });

        setResult(RESULT_OK, null);

    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = nameText.getText().toString();
        String username = usernameText.getText().toString();
        String email = emailText.getText().toString();
        String mobile = mobileText.getText().toString();
        String password = passwordText.getText().toString();
        String reEnterPassword = reEnterPasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 5) {
            nameText.setError("at least 5 characters");
            valid = false;
        } else {
            nameText.setError(null);
        }

        if (username.isEmpty() || username.length() < 5) {
            usernameText.setError("Enter a Valid Username of at least 5 characters");
            valid = false;
        } else {
            usernameText.setError(null);
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            mobileText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            mobileText.setError(null);
        }

        if (password.isEmpty() || password.length() < 6 || password.length() > 13) {
            passwordText.setError("between 6 and 13 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 6 || reEnterPassword.length() > 13 || !(reEnterPassword.equals(password))) {
            reEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            reEnterPasswordText.setError(null);
        }

        return valid;
    }
}
