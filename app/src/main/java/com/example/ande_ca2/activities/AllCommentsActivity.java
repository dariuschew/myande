package com.example.ande_ca2.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ande_ca2.R;
import com.example.ande_ca2.adapters.CommentsAdapter;
import com.example.ande_ca2.models.Comment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AllCommentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_comments);

        // Retrieve comments from the intent
        String commentsJson = getIntent().getStringExtra("comments_json");

// Convert the JSON string back to a list of comments
        List<Comment> comments = convertJsonToCommentsList(commentsJson);


        // Initialize and set up a RecyclerView to display all comments
        RecyclerView allCommentsRecyclerView = findViewById(R.id.allCommentsRecyclerView);
        CommentsAdapter allCommentsAdapter = new CommentsAdapter(comments);
        allCommentsRecyclerView.setAdapter(allCommentsAdapter);
        allCommentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<Comment> convertJsonToCommentsList(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Comment>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
