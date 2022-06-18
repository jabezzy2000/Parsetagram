package com.example.parsetagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
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

import java.util.ArrayList;
import java.util.List;

public class PostDetail extends AppCompatActivity {
    RecyclerView rvComments;
    Post post;
    private TextView tvUsername;
    private ImageView ivImage;
    private TextView tvDescription;
    private TextView tvCreatedAt;
    private ImageButton ibLikes;
    private ImageButton ibComment;
    private TextView tvLikeCounts;
    private ImageView ivDetailProfile;
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
                else{
                adapter.mComments.clear();
                adapter.mComments.addAll(objects);
                adapter.notifyDataSetChanged();}
            }
        });
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        tvUsername = findViewById(R.id.tvUsername);
        ivImage = findViewById(R.id.ivPhoto);
        ivDetailProfile = findViewById(R.id.ivProfileImage);
        tvDescription = findViewById(R.id.tvDescription);
        tvCreatedAt = findViewById(R.id.tvDate);
        ibLikes = findViewById(R.id.ibHeart);
        ibComment = findViewById(R.id.ibComment);
        rvComments = findViewById(R.id.rvComments);
        tvLikeCounts = findViewById(R.id.tvLikes);
        rvComments = findViewById(R.id.rvComments);
        adapter = new CommentsAdapter();
        rvComments.setLayoutManager(new LinearLayoutManager(this));
        rvComments.setAdapter(adapter);

        Post post = getIntent().getParcelableExtra("post");

        ivDetailProfile.setOnClickListener(new View.OnClickListener() { //setting on click listener for detail profile
            @Override
            public void onClick(View v) {
                Toast.makeText(PostDetail.this,"Profile was clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PostDetail.this,UserProfile.class);
                startActivity(intent);
            }
        });

        ibLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(post.isLikedByCurrentUser()){
                    post.unlike();
                    ibLikes.setBackgroundResource(R.drawable.ufi_heart);
                }else{
                    post.like();
                    ibLikes.setBackgroundResource(R.drawable.ufi_heart_active);
                }
                tvLikeCounts.setText(post.likeCountDisplayText());
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
        refreshComments();

        tvUsername.setText(post.getUser().getUsername());
        tvCreatedAt.setText(Utilities.getSimpleTime(post.getCreatedAt()));
        ParseFile image = post.getImage();
        ParseFile profileImage = post.getUser().getProfileImage();
        if (image != null) {
            Glide.with(this).load(image.getUrl()).into(ivImage);
            ivImage.setVisibility(View.VISIBLE); // make visible if there
    }
        else{
            ivImage.setVisibility(View.GONE);
        }
        if(profileImage != null ) {
            Utilities.roundedImage(this,profileImage.getUrl(),ivDetailProfile,100);
        }
}}