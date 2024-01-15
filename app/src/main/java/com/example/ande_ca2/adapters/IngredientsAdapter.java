package com.example.ande_ca2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ande_ca2.R;
import com.example.ande_ca2.models.Ingredient;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {

    private List<Ingredient> ingredients;

    public IngredientsAdapter(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        holder.bind(ingredient);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {
        private TextView numberView;
        private TextView ingredientView;
        private ImageView imgView;

        IngredientViewHolder(View itemView) {
            super(itemView);
            numberView = itemView.findViewById(R.id.numberTextView);
            ingredientView = itemView.findViewById(R.id.ingredientTextView);
            imgView = itemView.findViewById(R.id.imgView);
        }

        void bind(Ingredient ingredient) {
            numberView.setText(String.valueOf(getAdapterPosition() + 1));
            ingredientView.setText(ingredient.getName());
            Picasso.get()
                    .load(ingredient.getImgUrl())
                    .into(imgView);
            numberView.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.circle_background));
        }
    }
}