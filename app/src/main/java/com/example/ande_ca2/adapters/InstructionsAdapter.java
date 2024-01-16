package com.example.ande_ca2.adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ande_ca2.R;
import com.example.ande_ca2.models.Instruction;

import java.util.List;

public class InstructionsAdapter extends RecyclerView.Adapter<InstructionsAdapter.InstructionViewHolder> {

    private List<Instruction> instructions;

    public InstructionsAdapter(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    @NonNull
    @Override
    public InstructionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.instruction_item, parent, false);
        return new InstructionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionViewHolder holder, int position) {
        // Use the position parameter to set the step number
        holder.numberTextView.setText(String.valueOf(position + 1));
        holder.stepDescription.setText(instructions.get(position).getDescription());

        // Set up the inner RecyclerView for images
        InstructionImagesAdapter imagesAdapter = new InstructionImagesAdapter(instructions.get(position).getImageUrls());
        holder.imagesRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        holder.imagesRecyclerView.setAdapter(imagesAdapter);
    }

    @Override
    public int getItemCount() {
        return instructions.size();
    }

    class InstructionViewHolder extends RecyclerView.ViewHolder {
        TextView numberTextView;
        TextView stepDescription;
        RecyclerView imagesRecyclerView;

        InstructionViewHolder(View itemView) {
            super(itemView);
            numberTextView = itemView.findViewById(R.id.numberTextView);
            stepDescription = itemView.findViewById(R.id.tv_step_description);
            imagesRecyclerView = itemView.findViewById(R.id.imagesRecyclerView);
        }
    }
}
