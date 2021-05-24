package com.edumbrella.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.edumbrella.foodapp.R;
import com.edumbrella.foodapp.model.Popular;

import java.util.List;

/**
 * Created by Sagar Pandey on 5/23/2021.
 */
public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.ViewHolder> {

    private Context mContext;
    private List<Popular> mPopulars;

    public CartItemsAdapter(Context mContext, List<Popular> mPopulars) {
        this.mContext = mContext;
        this.mPopulars = mPopulars;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.cart_items, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final Popular popular = mPopulars.get(position);

        holder.cartItemName.setText(popular.getTitle());
        holder.cartItemPrice.setText(popular.getPrice());
        holder.cartItemImage.setImageResource(popular.getImage());
        holder.itemCount.setText(""+ popular.getCount());


        holder.cartItemRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(holder.itemCount.getText().toString());
                if(count > 1) {
                    count = count -1 ;
                    holder.itemCount.setText(""+ count);
                }
            }
        });

        holder.cartItemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(holder.itemCount.getText().toString()) + 1;
                holder.itemCount.setText(""+ count);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mPopulars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView cartItemName, cartItemPrice, cartItemRemove, cartItemAdd, itemCount;
        public ImageView cartItemImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cartItemName = itemView.findViewById(R.id.cartItemName);
            cartItemPrice = itemView.findViewById(R.id.cartItemPrice);
            cartItemRemove = itemView.findViewById(R.id.cartItemRemove);
            cartItemAdd = itemView.findViewById(R.id.cartItemAdd);
            itemCount = itemView.findViewById(R.id.itemCount);
            cartItemImage = itemView.findViewById(R.id.cartItemImage);

        }
    }
}
