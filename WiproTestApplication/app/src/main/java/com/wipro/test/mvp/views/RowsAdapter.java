package com.wipro.test.mvp.views;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
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
    public void onBindViewHolder(final RowsViewholder rowsViewholder, int i) {
        Rows rows = this.rows.get(i);
        rowsViewholder.titleView.setText(rows.getTitle());
        if (TextUtils.isEmpty(rows.getDescription())) {
            rowsViewholder.descriptionView.setVisibility(View.GONE);
        } else {
            rowsViewholder.descriptionView.setVisibility(View.VISIBLE);
            rowsViewholder.descriptionView.setText(rows.getDescription());
        }

        Glide.with(rowsViewholder.imageView.getContext())
                .load(rows.getImageHref())
                .fitCenter()
                .placeholder(R.drawable.place_holder_images)
                .error(R.drawable.error_image)
                .into(rowsViewholder.imageView);


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
