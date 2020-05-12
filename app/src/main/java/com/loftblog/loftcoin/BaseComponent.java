package com.loftblog.loftcoin;

import android.content.Context;

import com.loftblog.loftcoin.data.CoinsRepo;
import com.loftblog.loftcoin.data.CurrencyRepo;

public interface BaseComponent {
    Context context();
    CoinsRepo coinsRepo();
    CurrencyRepo currencyRepo();
}