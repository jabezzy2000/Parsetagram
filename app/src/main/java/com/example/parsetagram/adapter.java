package com.example.parsetagram;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    private Context context;
    private List<Post> posts;

    public adapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivProfilePicture;
        TextView tvUsername;
        ImageButton ibLike;
        ImageButton ibComment;
        ImageButton ibSave;
        ImageButton ibSend;
        ImageView ivPoster;
        TextView tvDescription;
        String temp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfilePicture = itemView.findViewById(R.id.ivProfileImage);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ibComment = itemView.findViewById(R.id.ibComment);
            ibLike = itemView.findViewById(R.id.ibLike);
            ibSave = itemView.findViewById(R.id.ibSave);
            ibSend = itemView.findViewById(R.id.ibSend);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            temp = "@";
            itemView.setOnClickListener(this);

        }

        public void bind(Post post) {
            // Bind the post data to the view elements
            tvUsername.setText(new StringBuilder().append("@").append(post.getUser().getUsername()).toString());
            tvDescription.setText(post.getDescription());
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivPoster);
            }
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Post post = posts.get(position);
                Intent intent = new Intent(context, PostDetail.class);
                intent.putExtra("post", post);
                context.startActivity(intent);
            }
        }

    }
}
