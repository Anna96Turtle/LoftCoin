package com.loftblog.loftcoin.ui.wallets;

import android.content.Context;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.loftblog.loftcoin.R;
import com.loftblog.loftcoin.data.Transaction;
import com.loftblog.loftcoin.databinding.LiTransactionBinding;
import com.loftblog.loftcoin.util.PriceFormatter;
import com.loftblog.loftcoin.util.TransactionFormatter;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Objects;

import javax.inject.Inject;

class TransactionsAdapter extends ListAdapter<Transaction, TransactionsAdapter.ViewHolder> {

    private final PriceFormatter priceFormatter;

    private final TransactionFormatter transactionFormatter;

    private LayoutInflater inflater;

    private int colorPositive = Color.GREEN;
    private int colorNegative = Color.RED;

    @Inject
    TransactionsAdapter(PriceFormatter priceFormatter, TransactionFormatter transactionFormatter) {
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
        this.transactionFormatter = transactionFormatter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LiTransactionBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Transaction transaction = getItem(position);
        holder.binding.amount1.setText(transactionFormatter.format(transaction));
        final double fiatAmount = transaction.amount() * transaction.coin().price();
        holder.binding.amount2.setText(priceFormatter.format(transaction.coin().currencyCode(), fiatAmount));

        Format formatter = new SimpleDateFormat("dd MMM yyyy");
        holder.binding.timestamp.setText(formatter.format(transaction.createdAt()).toUpperCase());

        if (transaction.amount() > 0)  {
            holder.binding.amount2.setTextColor(colorPositive);
            holder.binding.itemTransactionAmountArrow.setImageDrawable(ResourcesCompat.getDrawable(inflater.getContext().getResources(), R.drawable.ic_arrow_drop_up_24dp, null));
        }
        else {
            holder.binding.amount2.setTextColor(colorNegative);
            holder.binding.itemTransactionAmountArrow.setImageDrawable(ResourcesCompat.getDrawable(inflater.getContext().getResources(), R.drawable.ic_arrow_drop_down_24dp, null));
        }
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