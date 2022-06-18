package com.example.parsetagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUp extends AppCompatActivity {
    EditText SignUpUsername;
    EditText SignUpPassword;
    Button BtnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        SignUpUsername = findViewById(R.id.etSignUpName);
        SignUpPassword = findViewById(R.id.etSignUpPasword);
        BtnSubmit = findViewById(R.id.btnSubmit);

        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the ParseUser
                User user = new User();
                //getting text from editText
                String username = SignUpUsername.getText().toString(); // gets text and changes it to string
                String password = SignUpPassword.getText().toString();

                // Set core properties
                user.setUsername(username); //setting the username to username inputted by user
                user.setPassword(password); // setting the password to password inputted by user

                // Invoke signUpInBackground
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            goFeedActivity();
                        } else {
                            Toast.makeText(SignUp.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                        }
                    }
                });
            }
        });

    }
    private void goFeedActivity() {
            Intent intent = new Intent(SignUp.this,FeedActivity.class);
            startActivity((intent));
    }
}