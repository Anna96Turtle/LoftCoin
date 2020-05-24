package com.loftblog.loftcoin;

import android.content.Context;

import com.loftblog.loftcoin.data.CoinsRepo;
import com.loftblog.loftcoin.data.CurrencyRepo;
import com.loftblog.loftcoin.data.WalletsRepo;
import com.loftblog.loftcoin.util.ImageLoader;
import com.loftblog.loftcoin.util.Notifier;
import com.loftblog.loftcoin.util.RxSchedulers;

public interface BaseComponent {
    Context context();
    CoinsRepo coinsRepo();
    CurrencyRepo currencyRepo();
    WalletsRepo walletsRepo();
    ImageLoader imageLoader();
    RxSchedulers schedulers();
    Notifier notifier();
}