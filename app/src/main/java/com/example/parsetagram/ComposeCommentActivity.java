package com.example.parsetagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
public class ComposeCommentActivity extends AppCompatActivity {
    Post post;
    Button btSave;
    EditText etBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_comment);
        post = getIntent().getParcelableExtra("post");
        etBody = findViewById(R.id.etBody);
        btSave = findViewById(R.id.btSave);
        btSave.setOnClickListener(new View.OnClickListener() { // setting an onclick listener for when the button is clicked
            @Override
            public void onClick(View v) {
                String body = etBody.getText().toString();
                Comment comment = new Comment();
                comment.setAuthor(ParseUser.getCurrentUser());
                comment.setBody(body);
                comment.setPost(post);
                comment.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e!=null) {
                            Log.e("yikes", e.getMessage() );
                        }
                    }
                });
            }
        });
    }
}