package com.loftblog.loftcoin.ui.rates;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.loftblog.loftcoin.data.Coin;
import com.loftblog.loftcoin.data.CoinsRepo;

import java.util.List;

import javax.inject.Inject;

public class RatesViewModel extends ViewModel {

    private final MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>();

    private final MutableLiveData<Boolean> forceRefresh = new MutableLiveData<>(true);

    private final LiveData<List<Coin>> coins;

    @Inject
    public RatesViewModel(CoinsRepo coinsRepo) {

        final LiveData<CoinsRepo.Query> query = Transformations
                .map(forceRefresh, (r) -> {
                    isRefreshing.postValue(true);
                    return CoinsRepo.Query
                            .builder()
                            .forceUpdate(r)
                            .currency("USD")
                            .build();
                });
        final LiveData<List<Coin>> coins = Transformations.switchMap(query, coinsRepo::listings);
        this.coins = Transformations.map(coins, (c) -> {
            isRefreshing.postValue(false);
            return c;
        });
    }

    @NonNull
    LiveData<List<Coin>> coins() {
        return coins;
    }

    @NonNull
    LiveData<Boolean> isRefreshing() {
        return isRefreshing;
    }

    final void refresh() {
        forceRefresh.postValue(true);
    }

}