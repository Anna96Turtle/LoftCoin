package com.loftblog.loftcoin.ui.currency;

import androidx.lifecycle.ViewModelProvider;

import com.loftblog.loftcoin.BaseComponent;
import com.loftblog.loftcoin.util.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        CurrencyModule.class,
        ViewModelModule.class
}, dependencies = {
        BaseComponent.class
})
abstract class CurrencyComponent {

    abstract ViewModelProvider.Factory viewModelFactory();

}