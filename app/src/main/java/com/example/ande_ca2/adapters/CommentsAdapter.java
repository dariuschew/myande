package com.example.ande_ca2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ande_ca2.R;
import com.example.ande_ca2.models.Comment;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentViewHolder> {

    private List<Comment> commentList;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

    public CommentsAdapter(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = commentList.get(position);

        holder.authorNameTextView.setText(comment.getAuthorName());
        holder.commentTextView.setText(comment.getDescription());
        holder.likeCountTextView.setText(String.valueOf(comment.getLikes()));

        // Formatting the date
        String formattedDate = dateFormat.format(comment.getDatePosted());
        holder.datePostedTextView.setText(formattedDate);

        // Load the author image URL with Picasso
        Picasso.get()
                .load(comment.getAuthorImageUrl())
                .placeholder(R.drawable.default_profile_pic)
                .error(R.drawable.default_profile_pic)
                .into(holder.authorImageView);
    }
    @Override
    public int getItemCount() {
        return commentList != null ? commentList.size() : 0;
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView authorNameTextView, commentTextView, datePostedTextView, likeCountTextView;
        ImageView authorImageView, likeImageView;

        CommentViewHolder(View itemView) {
            super(itemView);
            authorImageView = itemView.findViewById(R.id.profileImageView);
            authorNameTextView = itemView.findViewById(R.id.nameTextView);
            commentTextView = itemView.findViewById(R.id.commentTextView);
            datePostedTextView = itemView.findViewById(R.id.timeTextView);
            likeImageView = itemView.findViewById(R.id.likeImageView);
            likeCountTextView = itemView.findViewById(R.id.likeCountTextView);
        }
    }
}