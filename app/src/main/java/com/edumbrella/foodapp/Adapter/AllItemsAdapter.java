package com.edumbrella.foodapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.edumbrella.foodapp.OtherActivities.DescriptionActivity;
import com.edumbrella.foodapp.R;
import com.edumbrella.foodapp.model.Popular;

import java.util.List;

/**
 * Created by Sagar Pandey on 5/24/2021.
 */
public class AllItemsAdapter extends RecyclerView.Adapter<AllItemsAdapter.ViewHolder> {
    private Context mContext;
    private List<Popular> mPopulars;

    public AllItemsAdapter(Context mContext, List<Popular> mPopulars) {
        this.mContext = mContext;
        this.mPopulars = mPopulars;
    }

    @NonNull
    @Override
    public AllItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.all_items, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AllItemsAdapter.ViewHolder holder, int position) {
        final Popular popular = mPopulars.get(position);
        holder.productName.setText(popular.getTitle());
        holder.productPrice.setText(popular.getPrice());
        holder.productImage.setImageResource(popular.getImage());

        holder.cardViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DescriptionActivity.class);
                intent.putExtra("name", popular.getTitle());
                intent.putExtra("price", popular.getPrice());
                intent.putExtra("description", popular.getDescription());
                intent.putExtra("image", popular.getImage());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPopulars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        public TextView productName, productPrice;
        public ImageView productImage;
        public CardView cardViewItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productImage = itemView.findViewById(R.id.productImage);

            cardViewItem = itemView.findViewById(R.id.cardViewItem);

        }
    }
}
