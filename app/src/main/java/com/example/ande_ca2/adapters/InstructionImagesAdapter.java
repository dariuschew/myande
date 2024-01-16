package com.example.ande_ca2.adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ande_ca2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionImagesAdapter extends RecyclerView.Adapter<InstructionImagesAdapter.ImageViewHolder> {

    private List<String> imageUrls; // The image URLs or resource IDs.

    public InstructionImagesAdapter(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    @NonNull
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_instruction_image_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        String imageUrl = imageUrls.get(position);
        // Use Picasso to load the image into the ImageView.
        Picasso.get()
                .load(imageUrl)
                .centerCrop()
                .fit()
                .into(holder.instructionImage);
    }


    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView instructionImage;

        ImageViewHolder(View itemView) {
            super(itemView);
            instructionImage = itemView.findViewById(R.id.instruction_image);
        }
    }
}
