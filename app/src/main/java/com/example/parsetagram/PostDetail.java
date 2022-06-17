package com.example.parsetagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.Glide;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class PostDetail extends AppCompatActivity {
    RecyclerView rvComments;
    Post post;
    CommentsAdapter adapter;

    @Override
    protected void onRestart(){
        //when we come back from any other activity
        super.onRestart();

        refreshComments();
    }

    private void refreshComments() {
        //load all the comment in this post
        ParseQuery<Comment> query = ParseQuery.getQuery("Comment");
        query.whereEqualTo(Comment.KEY_POST, post);
        query.orderByDescending("createdAt");
        query.include(Comment.KEY_AUTHOR);
        query.findInBackground(new FindCallback<Comment>() {
            @Override
            public void done(List<Comment> objects, ParseException e) {
                if(e!= null) {
                    Log.e("Failed to fetch comments", e.getMessage());
                    return;
                }
                adapter.mComments.clear();
                adapter.mComments.addAll(objects);
                adapter.notifyDataSetChanged();

            }
        });
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        TextView tvUsername = findViewById(R.id.tvUsername);
        TextView tvDate = findViewById(R.id.tvDate);
        ImageView ivPhoto = findViewById(R.id.ivPhoto);
        ImageView ivProfilePicture = findViewById(R.id.ivProfileImage);
        ImageButton ibHeart = findViewById(R.id.ibHeart);
        ImageButton ibComment = findViewById(R.id.ibComment);
        TextView tvLikes = findViewById(R.id.tvLikes);
        CommentsAdapter adapter;


        rvComments = findViewById(R.id.rvComments);
        adapter = new CommentsAdapter();
        rvComments.setLayoutManager(new LinearLayoutManager(this));
        rvComments.setAdapter(adapter);

        Post post = getIntent().getParcelableExtra("post");

        ivProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PostDetail.this,"Profile was clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PostDetail.this,UserProfile.class);
                startActivity(intent);
            }
        });

        ibHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ibHeart.setBackgroundResource(R.drawable.ufi_heart_active);
            }
        });

        ibComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PostDetail.this,ComposeCommentActivity.class);
                i.putExtra("post", post);
                startActivity(i);
            }
        });

        tvUsername.setText(post.getUser().getUsername());
        tvDate.setText(Utilities.getSimpleTime(post.getCreatedAt()));
        ParseFile image = post.getImage();
        ParseFile profileImage = post.getUser().getProfileImage();
        if (image != null) {
            Glide.with(this).load(image.getUrl()).into(ivPhoto);
    }
        if(profileImage != null ) {
            Utilities.roundedImage(this,profileImage.getUrl(),ivProfilePicture,100);
        }
}}