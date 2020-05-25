package com.loftblog.loftcoin;

import android.content.Context;

import com.loftblog.loftcoin.data.CoinsRepo;
import com.loftblog.loftcoin.data.CurrencyRepo;
import com.loftblog.loftcoin.util.ImageLoader;

public interface BaseComponent {
    Context context();
    CoinsRepo coinsRepo();
    CurrencyRepo currencyRepo();
    ImageLoader imageLoader();
}