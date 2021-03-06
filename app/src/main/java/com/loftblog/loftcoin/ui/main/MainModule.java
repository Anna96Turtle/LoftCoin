package com.loftblog.loftcoin.ui.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.loftblog.loftcoin.ui.converter.CoinsSheet;
import com.loftblog.loftcoin.ui.converter.ConverterFragment;
import com.loftblog.loftcoin.ui.currency.CurrencyDialog;
import com.loftblog.loftcoin.ui.rates.RatesFragment;
import com.loftblog.loftcoin.ui.wallets.WalletsFragment;
import com.loftblog.loftcoin.util.LoftFragmentFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module
abstract class MainModule {

    @Binds
    abstract FragmentFactory fragmentFactory(LoftFragmentFactory impl);

    @Binds
    @IntoMap
    @ClassKey(RatesFragment.class)
    abstract Fragment ratesFragment(RatesFragment impl);

    @Binds
    @IntoMap
    @ClassKey(WalletsFragment.class)
    abstract Fragment walletsFragment(WalletsFragment impl);

    @Binds
    @IntoMap
    @ClassKey(ConverterFragment.class)
    abstract Fragment converterFragment(ConverterFragment impl);

    @Binds
    @IntoMap
    @ClassKey(CurrencyDialog.class)
    abstract Fragment currencyDialog(CurrencyDialog impl);

    @Binds
    @IntoMap
    @ClassKey(CoinsSheet.class)
    abstract Fragment coinsSheet(CoinsSheet impl);

}