package com.example.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
    public final String TAG = "LoginActivity";
    public EditText etUsername;
    public EditText etPassword;
    public Button btnLogin;
    public Button btnSignUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        btnLogin = (Button) findViewById(R.id.btnSignIn);

        btnLogin = findViewById(R.id.btnLogIn);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("test", "Button clicked");
                Toast.makeText(LoginActivity.this,"Button was clicked", Toast.LENGTH_SHORT).show();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();


                loginUser(username,password);
                //goMainActivity();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Sign up button was clicked", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(LoginActivity.this,SignUp.class);
                //startActivity(intent);

            }
        });


    }

//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        //insert this line here
//        setContentView(R.layout.activity_login);
//
//        //Pull the button with the id
//        btnLogin = (Button) findViewById(R.id.btnLogin);
//
//        //set onclicklistener
//        btnLogin.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view){
//                Toast.makeText(getApplicationContext(),"Button was Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    public void loginUser(String username, String password) {
        Log.i(TAG, "loginUser: Attempting to login user " + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                Log.d(TAG, "Edey work for here");
                if( e != null) {
                    Log.e(TAG, "error: It doesnt work here", e );
                    Toast.makeText(LoginActivity.this,"failure", Toast.LENGTH_SHORT).show();;
                    return;
                }
//                    goMainActivity(); // and this goes back to main activity...
                      goFeedActivity();
                    Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void goFeedActivity() {
        Intent intent = new Intent(LoginActivity.this,FeedActivity.class);
        startActivity((intent));
    }


}
