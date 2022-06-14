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
    public static final String TAG = "LoginActivity";
    private EditText etUsername = findViewById(R.id.etUsername);
    private EditText etPassword = findViewById(R.id.etPassword);
    private Button btnLogin = findViewById(R.id.btnLogIn);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: Login Button Clicked");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username,password);

            }
        });



    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "loginUser: Attempting to login user " + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if( e != null) {
                    Log.e(TAG, "error", e );
                    return;
                }
                goMainActivity();
                Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_SHORT);

            }
        });
    }

    private void goMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
