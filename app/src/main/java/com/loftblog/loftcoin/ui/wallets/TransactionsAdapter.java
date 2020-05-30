package com.loftblog.loftcoin.ui.wallets;

import android.content.Context;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.loftblog.loftcoin.R;
import com.loftblog.loftcoin.data.Transaction;
import com.loftblog.loftcoin.databinding.LiTransactionBinding;
import com.loftblog.loftcoin.util.PriceFormatter;

import java.util.Objects;

import javax.inject.Inject;

class TransactionsAdapter extends ListAdapter<Transaction, TransactionsAdapter.ViewHolder> {

    private final PriceFormatter priceFormatter;

    private LayoutInflater inflater;

    private int colorPositive = Color.GREEN;
    private int colorNegative = Color.RED;

    @Inject
    TransactionsAdapter(PriceFormatter priceFormatter) {
        super(new DiffUtil.ItemCallback<Transaction>() {
            @Override
            public boolean areItemsTheSame(@NonNull Transaction oldItem, @NonNull Transaction newItem) {
                return Objects.equals(oldItem.uid(), newItem.uid());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Transaction oldItem, @NonNull Transaction newItem) {
                return Objects.equals(oldItem, newItem);
            }
        });
        this.priceFormatter = priceFormatter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LiTransactionBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Transaction transaction = getItem(position);
        holder.binding.amount1.setText(priceFormatter.format(transaction.amount()));
        final double fiatAmount = transaction.amount() * transaction.coin().price();
        holder.binding.amount2.setText(priceFormatter.format(transaction.coin().currencyCode(), fiatAmount));
        holder.binding.timestamp.setText(DateFormat.getDateFormat(inflater.getContext()).format(transaction.createdAt()));

        if (transaction.amount() > 0) holder.binding.amount2.setTextColor(colorPositive);
        else holder.binding.amount2.setTextColor(colorNegative);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        inflater = LayoutInflater.from(recyclerView.getContext());

        Context context = recyclerView.getContext();

        TypedValue typedValue = new TypedValue();

        context.getTheme().resolveAttribute(R.attr.textPositive, typedValue, true);
        colorPositive = typedValue.data;
        context.getTheme().resolveAttribute(R.attr.textNegative, typedValue, true);
        colorNegative = typedValue.data;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final LiTransactionBinding binding;

        ViewHolder(@NonNull LiTransactionBinding binding) {
            super(binding.getRoot());
            binding.getRoot().setClipToOutline(true);
            this.binding = binding;
        }

    }

}