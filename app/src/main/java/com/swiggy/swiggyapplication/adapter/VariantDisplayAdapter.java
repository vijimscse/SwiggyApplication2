package com.swiggy.swiggyapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.swiggy.swiggyapplication.R;
import com.swiggy.swiggyapplication.responsedto.Variation;

import java.util.List;

/**
 * Created by Accolite on 7/12/2017.
 */

public class VariantDisplayAdapter extends RecyclerView.Adapter<VariantDisplayAdapter.VariantViewHolder> {

    private List<Variation> variationList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void OnItemClicked(String variantID);
    }

    public class VariantViewHolder extends RecyclerView.ViewHolder {
        public TextView name, inStock, price;
        public View mCardView;

        public VariantViewHolder(View view) {
            super(view);

            mCardView = view.findViewById(R.id.recycler_card_view);
            name = (TextView) view.findViewById(R.id.name);
            inStock = (TextView) view.findViewById(R.id.inStock);
            price = (TextView) view.findViewById(R.id.price);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Variation variation = variationList.get(getAdapterPosition());
                    mOnItemClickListener.OnItemClicked(variation.getId());
                }
            });
        }
    }


    public VariantDisplayAdapter(Context context, List<Variation> variationList, OnItemClickListener onItemClickListener) {
        mContext = context;
        this.variationList = variationList;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public VariantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.variation_view, parent, false);

        return new VariantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VariantViewHolder holder, int position) {
        Variation variation = variationList.get(position);

     /*   if (variation.isSelectable()) {
            holder.mCardView.setClickable(true);
        } else {
            holder.mCardView.setClickable(false);
        }*/
        holder.name.setText(variation.getName());
        holder.inStock.setText(String.valueOf(variation.getInStock()));
        holder.price.setText(mContext.getString(R.string.rupee_symbol) + " " + String.valueOf(variation.getPrice()));
    }

    @Override
    public int getItemCount() {
        return variationList.size();
    }
}
