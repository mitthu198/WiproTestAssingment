package com.wipro.test.mvp.views;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.wipro.test.R;
import com.wipro.test.mvp.model.Rows;

import java.util.ArrayList;
import java.util.List;

public class RowsAdapter extends RecyclerView.Adapter<RowsAdapter.RowsViewholder> {

    private List<Rows> rows;

    public RowsAdapter() {
        rows = new ArrayList<Rows>();
    }

    public void setRows(List<Rows> list) {
        rows.clear();
        rows.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RowsViewholder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fact_item, viewGroup, false);
        RowsViewholder viewholder = new RowsViewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(final RowsViewholder RowsViewholder, int i) {
        Rows rows = this.rows.get(i);
        RowsViewholder.titleView.setText(rows.getTitle());
        RowsViewholder.imageView.setVisibility(View.GONE);

        if (TextUtils.isEmpty(rows.getDescription())) {
            RowsViewholder.descriptionView.setVisibility(View.GONE);
        } else {
            RowsViewholder.descriptionView.setVisibility(View.VISIBLE);
            RowsViewholder.descriptionView.setText(rows.getDescription());
        }

        String imageUrl = rows.getImageHref();
        if (TextUtils.isEmpty(imageUrl)) {
            // Need hile imageView if no Image
            RowsViewholder.imageView.setVisibility(View.GONE);
        } else {
            Glide.with(RowsViewholder.imageView.getContext())
                    .load(rows.getImageHref())
                    .fitCenter()
                    .into(new ViewTarget<ImageView, GlideDrawable>(RowsViewholder.imageView) {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation anim) {
                            RowsViewholder.imageView.setImageDrawable(resource.getCurrent());
                            RowsViewholder.imageView.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onLoadFailed(Exception e, Drawable errorDrawable) {
                            RowsViewholder.imageView.setVisibility(View.GONE);

                        }
                    });

        }
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }


    static class RowsViewholder extends RecyclerView.ViewHolder {

        public TextView titleView;
        public TextView descriptionView;
        public ImageView imageView;

        public RowsViewholder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.titleView);
            descriptionView = (TextView) itemView.findViewById(R.id.descriptionView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
