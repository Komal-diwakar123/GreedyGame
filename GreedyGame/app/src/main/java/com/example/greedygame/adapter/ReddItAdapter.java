package com.example.greedygame.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greedygame.R;
import com.example.greedygame.activity.ImagePreviewMode;

import com.example.greedygame.imageloader.NetworkImageView;
import com.example.greedygame.imageloader.ReddItImageNetworkImageView;
import com.example.greedygame.model.ChildrenModel;
import com.example.greedygame.model.ImageDataModel;

import java.util.List;

public class ReddItAdapter extends RecyclerView.Adapter<ReddItAdapter.ViewHolder>{

    private List<ChildrenModel> childrenModels;
    Context context;

    public ReddItAdapter(List<ChildrenModel> childrenModels, Context context){
        this.childrenModels = childrenModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ReddItAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.image_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ChildrenModel childrenModel = childrenModels.get(position);
        if(childrenModel == null || childrenModel.getChildrenDataModel() == null ||
                childrenModel.getChildrenDataModel().getPreviewModel() == null ||
                childrenModel.getChildrenDataModel().getPreviewModel().getImageList() == null ||
                childrenModel.getChildrenDataModel().getPreviewModel().getImageList().isEmpty()
           )
            return;

        for(ImageDataModel imageDataModel : childrenModel.getChildrenDataModel().getPreviewModel().getImageList()){
            if(imageDataModel.getSourceModel() == null ||
                    imageDataModel.getSourceModel().getImageUrl() == null ||
                    imageDataModel.getSourceModel().getImageUrl().isEmpty() || context == null)
                continue;
            int resolutionListSize = imageDataModel.getResolutionList().size();

            /* Because I thought source resolution will be very size density pixel.
            So, I'm showing resolution last image.
            String imageUrl = imageDataModel.getSourceModel().getImageUrl(); */

            String imageUrl = imageDataModel.getResolutionList().get(resolutionListSize-1).getImageUrl();
            String replaced = imageUrl.replace("&amp;", "&");

            holder.imageView.setOnClickListener(view -> {
                Intent intent = new Intent(context, ImagePreviewMode.class);
                intent.putExtra("image_url", replaced);
                context.startActivity(intent);
            });

            holder.imageView.loadImage(replaced, new NetworkImageView.NetworkImageViewListener() {
                @Override
                public void onImageSuccessfullyLoaded() {

                }

                @Override
                public void onImageFailedToLoad() {

                }
            });
            //ImageLoader imageLoader = ImageLoader.getInstance();
            //Glide.with(context).load(replaced).into(holder.imageView);
        }

    }

    @Override
    public int getItemCount() {
        return childrenModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ReddItImageNetworkImageView imageView;

         ViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
