//package com.example.parsetagram;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.parse.ParseException;
//import com.parse.ParseUser;
//import com.parse.SignUpCallback;
//
//public class SignUp extends AppCompatActivity {
//    EditText SignUpUsername;
//    EditText SignUpPassword;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
//        SignUpUsername = findViewById(R.id.etSignUpName);
//        SignUpPassword = findViewById(R.id.etSignUpPasword);
//
//        // Create the ParseUser
////        ParseUser User = new ParseUser(); error here
//        //getting text from editText
//        String username = SignUpUsername.getText().toString(); // gets text and changes it to string
//        String password = SignUpPassword.getText().toString();
//
//// Set core properties
////        User.setUsername(username); //setting the username to username inputted by user
////        User.setPassword(password); // setting the password to password inputted by user
//
//
//// Invoke signUpInBackground
//        User.signUpInBackground(new SignUpCallback() {
//            public void done(ParseException e) {
//                if (e == null) {
//                    // Hooray! Let them use the app now.
//                   goFeedActivity();
//                } else {
//                    Toast.makeText(SignUp.this,"Something went wrong",Toast.LENGTH_SHORT).show();
//                    // Sign up didn't succeed. Look at the ParseException
//                    // to figure out what went wrong
//                }
//            }
//        });
//
//
//
////        String username = etUsername.getText().toString();
////        String password = etPassword.getText().toString();
////
////
////        loginUser(username,password); I should use this
//    }
//
//    private void goFeedActivity() {
//            Intent intent = new Intent(SignUp.this,FeedActivity.class);
//            startActivity((intent));
//    }
//}