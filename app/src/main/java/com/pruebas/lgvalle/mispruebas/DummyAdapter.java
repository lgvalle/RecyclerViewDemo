package com.pruebas.lgvalle.mispruebas;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.PaletteItem;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

/**
 * Created by lgvalle on 13/07/14.
 */
public class DummyAdapter extends RecyclerView.Adapter<DummyAdapter.ViewHolder> {
    private static final String TAG = "MyAdpater";
    private ArrayList<Item> mDataset;

    public DummyAdapter(ArrayList<Item> myDataset) {
        mDataset = myDataset;
    }

    public DummyAdapter() {
        mDataset = new ArrayList<Item>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DummyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_row, null);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        Item item = mDataset.get(position);
        Log.d(TAG, item.getUrl());
        Picasso.with(holder.mImageView.getContext())
                .load(item.getUrl())
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        holder.mImageView.setImageBitmap(bitmap);
                        holder.mLoadingImageView.setVisibility(View.GONE);
                        holder.updatePalette();
                        Log.d(TAG, "on success");
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        holder.mLoadingImageView.setVisibility(View.GONE);
                        Log.d(TAG, "on error");
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        holder.mLoadingImageView.setVisibility(View.VISIBLE);
                    }
                });
        holder.mImageTitle.setText(item.getName());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();

    }

    public void addFirst(Item item) {
        mDataset.add(0, item);
        notifyItemInserted(0);
    }

    // Provide a reference to the type of views that you are using
    // (custom viewholder)
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private static final int PALETTE_SIZE = 24; /* 24 is default size. You can decrease this value to speed up palette generation */
        private final View mLoadingImageView;
        private final View mLoadingTitleView;
        public ImageView mImageView;
        public TextView mImageTitle;

        public ViewHolder(RelativeLayout v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.imageView);
            mImageTitle = (TextView) v.findViewById(R.id.title);
            mLoadingImageView = v.findViewById(R.id.pbImage);
            mLoadingTitleView = v.findViewById(R.id.pbTitle);
        }

        public void updatePalette() {

            Bitmap bitmap = ((BitmapDrawable)mImageView.getDrawable()).getBitmap();
            Palette.generateAsync(bitmap, PALETTE_SIZE, new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    PaletteItem item = palette.getLightVibrantColor();
                    if (item != null) {
                        mImageTitle.setBackgroundColor(adjustAlpha(item.getRgb(), 0.5f));
                    }

                    item = palette.getDarkVibrantColor();
                    if (item != null) {
                        mImageTitle.setTextColor(item.getRgb());
                    }
                    mLoadingTitleView.setVisibility(View.GONE);
                }

            });
        }

        public int adjustAlpha(int color, float factor) {
            int alpha = Math.round(Color.alpha(color) * factor);
            int red = Color.red(color);
            int green = Color.green(color);
            int blue = Color.blue(color);
            return Color.argb(alpha, red, green, blue);
        }
    }
}
