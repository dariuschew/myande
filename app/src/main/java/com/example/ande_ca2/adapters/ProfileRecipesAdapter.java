package com.example.ande_ca2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ande_ca2.R;
import com.example.ande_ca2.models.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfileRecipesAdapter extends RecyclerView.Adapter<ProfileRecipesAdapter.RecipeViewHolder> {
    private List<Recipe> recipeList;

    // Constructor
    public ProfileRecipesAdapter(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_recipe_card_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);

        Picasso.get().load(recipe.getImageUrls().get(0)).into(holder.recipeImage);

        holder.recipeTitle.setText(recipe.getTitle());
        holder.recipeDetails.setText(String.format("%d Ingredients | %s", recipe.getIngredients().size(), recipe.getCookTime()));
        holder.ratingText.setText(String.format("%.1f", recipe.getRating()));
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    static class RecipeViewHolder extends RecyclerView.ViewHolder {
        ImageView recipeImage;
        TextView recipeTitle;
        TextView recipeDetails;
        TextView ratingText;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.recipeImageView);
            recipeTitle = itemView.findViewById(R.id.recipeTitle);
            recipeDetails = itemView.findViewById(R.id.recipeDetails);
            ratingText = itemView.findViewById(R.id.ratingText);
        }
    }
}