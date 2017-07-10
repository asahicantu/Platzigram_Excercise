package com.kororia.fshop.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.kororia.fshop.R;
import com.kororia.fshop.model.Picture;
import com.kororia.fshop.post.view.PictureDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by anahisalgado on 01/09/16.
 */
public class PictureAdapterRecyclerView extends RecyclerView.Adapter<PictureAdapterRecyclerView.PictureViewHolder> implements Filterable {

    public ArrayList<Picture> pictures;
    private int resource;
    private Activity activity;
    private PictureFilter pictureFilter;
    public PictureAdapterRecyclerView(ArrayList<Picture> pictures, int resource, Activity activity) {
        this.pictures = pictures;
        this.resource = resource;
        this.activity = activity;
        this.pictureFilter = new PictureFilter(this, pictures);

    }

    @Override
    public Filter getFilter() {
        return pictureFilter;
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {
        Picture picture = pictures.get(position);
        holder.usernameCard.setText(picture.getUserName());
        holder.timeCard.setText(picture.getTime());
        holder.likeNumberCard.setText(picture.getLike_number());
        holder.imageUrl = picture.getPicture();
        Picasso.with(activity).load(picture.getPicture()).into(holder.pictureCard);
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }


    public class PictureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public String imageUrl;
        public ImageView pictureCard;
        public TextView usernameCard;
        public TextView timeCard;
        public TextView likeNumberCard;

        public PictureViewHolder(CardView view) {
            super(view);
            pictureCard = (ImageView) view.findViewById(R.id.imgUSer);
            usernameCard = (TextView) view.findViewById(R.id.txtUserName);
            timeCard = (TextView) view.findViewById(R.id.txtCreation);
            likeNumberCard = (TextView) view.findViewById(R.id.txtLike);
            pictureCard.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(activity, PictureDetailActivity.class);
            intent.putExtra(PictureDetailActivity.IMAGE_URL, imageUrl);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Explode explode = new Explode();
                explode.setDuration(1000);
                activity.getWindow().setExitTransition(explode);
                activity.startActivity(intent,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(activity, v, activity.getString(R.string.transition_picture)).toBundle());

            } else {
                activity.startActivity(intent);
            }
        }



    }
}


