package com.asahicantu.platzigram_exercise.adapter;

import android.widget.Filter;

import com.asahicantu.platzigram_exercise.model.Picture;

import java.util.ArrayList;

/**
 * Created by Asahi on 7/1/2017.
 */

public class PictureFilter extends Filter {
    private final PictureAdapterRecyclerView mAdapter;
    private ArrayList<Picture> pictureList;
    private ArrayList<Picture> filteredList;

    public PictureFilter(PictureAdapterRecyclerView mAdapter, ArrayList<Picture> pictureList) {
        this.mAdapter = mAdapter;
        this.pictureList = new ArrayList<>();
        this.pictureList.addAll(pictureList);
        filteredList = new ArrayList<>();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        filteredList.clear();
        final FilterResults fr = new FilterResults();
        if (constraint.length() == 0) {
            filteredList.addAll(pictureList);
        } else {
            final String filterPattern = constraint.toString().toLowerCase().trim();
            for (final Picture contact : pictureList) {
                if (contact.getUserName().toLowerCase().contains(filterPattern)) {
                    filteredList.add(contact);
                }
            }
        }
        fr.values = filteredList;
        fr.count = filteredList.size();
        return fr;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        mAdapter.pictures.clear();
        mAdapter.pictures.addAll((ArrayList<Picture>) results.values);
        mAdapter.notifyDataSetChanged();
    }
}



/*
    private static final Comparator<Picture> ALPHABETICAL_COMPARATOR = new Comparator<Picture>() {
        @Override
        public int compare(Picture a, Picture b) {
            return  a.getUserName().toUpperCase().compareTo(b.getUserName().toUpperCase());
        }
    };

    private final SortedList.Callback<Picture> mCallback = new SortedList.Callback<Picture>(){

        @Override
        public void onInserted(int position, int count) {
            notifyItemRangeInserted(position,count);
        }

        @Override
        public void onRemoved(int position, int count) {
            notifyItemRemoved(position);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            notifyItemMoved(fromPosition,toPosition);
        }

        @Override
        public int compare(Picture o1, Picture o2) {
            return ALPHABETICAL_COMPARATOR.compare(o1,o2);
        }

        @Override
        public void onChanged(int position, int count) {
            notifyItemChanged(position);
        }

        @Override
        public boolean areContentsTheSame(Picture oldItem, Picture newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areItemsTheSame(Picture item1, Picture item2) {
            return item1.getUserName() == item2.getUserName();
        }
    };

    final SortedList<Picture> list = new SortedList<>(Picture.class, mCallback);*/
