package com.example.parsetagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn;
    Button btnPicture;
    EditText etDescription;
    ImageView ivPostImage;
    String TAG = "tag";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        btn = findViewById(R.id.btnLogOut);
        btnPicture = findViewById(R.id.btnPicture);
        etDescription = findViewById(R.id.etDescription);
        ivPostImage = findViewById(R.id.ivPostImage);

        queryPosts();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"logout button was clicked", Toast.LENGTH_SHORT).show();
                logout();
            }
        });
    }

    private void queryPosts() {
        ParseQuery<Post> query = null;
        query.include(Post.KEY_USER);
        query = ParseQuery.getQuery(Post.class);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "error: Somethings gone wrong here", e);
                    return;
                }
                for (Post post : posts) {
                    Log.i(TAG, "done: success" + post.getDescription());

                }

            }
        });
    }

    private void logout() {
        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser();
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }


}