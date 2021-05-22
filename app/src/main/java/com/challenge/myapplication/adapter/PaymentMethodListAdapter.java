package com.challenge.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.challenge.myapplication.R;
import com.challenge.myapplication.model.ApplicableItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PaymentMethodListAdapter  extends RecyclerView.Adapter<PaymentMethodListAdapter.MyViewHolder> {
    private Context context;
    private List<ApplicableItem> paymentMethodList;
    private ItemClickListener clickListener;

    public PaymentMethodListAdapter(Context context, List<ApplicableItem> paymentMethodList, ItemClickListener clickListener) {
        this.context = context;
        this.paymentMethodList = paymentMethodList;
        this.clickListener = clickListener;
    }

    public void setMovieList(List<ApplicableItem> paymentMethodList) {
        this.paymentMethodList = paymentMethodList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PaymentMethodListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.payment_method_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodListAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(this.paymentMethodList.get(position).getLabel());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onMethodClick(paymentMethodList.get(position));
            }
        });
        Glide.with(context)
                .load(this.paymentMethodList.get(position).getLinks().getLogo())
                .apply(RequestOptions.fitCenterTransform())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(this.paymentMethodList != null) {
            return this.paymentMethodList.size();
        }
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.TVmethod);
            imageView = (ImageView)itemView.findViewById(R.id.IVlogo);
        }
    }


    public interface ItemClickListener{
        public void onMethodClick(ApplicableItem method);
    }
}
