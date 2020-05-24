package com.loftblog.loftcoin.ui.wallets;

import androidx.lifecycle.ViewModelProvider;

import com.loftblog.loftcoin.BaseComponent;
import com.loftblog.loftcoin.util.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        WalletsModule.class,
        WalletsViewModel.class
}, dependencies = {
        BaseComponent.class
})
abstract class WalletsComponent {

    abstract ViewModelProvider.Factory viewModelFactory();

    abstract WalletsAdapter walletsAdapter();

    abstract TransactionsAdapter transactionsAdapter();

}