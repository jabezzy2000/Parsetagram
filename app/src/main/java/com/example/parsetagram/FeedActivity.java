package com.example.parsetagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {
    protected adapter adapter;
    protected List<Post> allPosts;
    ImageView ivProfilePicture;
    TextView tvUsername;
    ImageButton ibLike ;
    ImageButton ibComment ;
    ImageButton ibSave ;
    ImageButton ibSend ;
    TextView tvDescription;
    String TAG = "tag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        ivProfilePicture = findViewById(R.id.ivProfileImage);
        tvUsername = findViewById(R.id.tvUsername);
        ibComment = findViewById(R.id.ibComment);
        ibLike = findViewById(R.id.ibLike);
        ibSave =findViewById(R.id.ibSave);
        ibSend = findViewById(R.id.ibSend);
        tvDescription =findViewById(R.id.tvDescription);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        adapter adapter = new adapter(this,)
        allPosts = new ArrayList<>();
        adapter = new adapter(this, allPosts);

        recyclerView.setAdapter(adapter);
        // set the layout manager on the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // query posts from Parstagram
        queryPosts();

    }

    private void queryPosts() {
        // specify what type of data we want to query - Post.class
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        // include data referred by user key
        query.include(Post.KEY_USER);
        // limit query to latest 20 items
        query.setLimit(20);
        // order posts by creation date (newest first)
        query.addDescendingOrder("createdAt");
        // start an asynchronous call for posts
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                // check for errors
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }

                // for debugging purposes let's print every post description to logcat
                for (Post post : posts) {
                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }

                // save received posts to list and notify adapter of new data
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }

}