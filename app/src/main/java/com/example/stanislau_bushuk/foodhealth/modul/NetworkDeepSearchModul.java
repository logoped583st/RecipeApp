package com.example.stanislau_bushuk.foodhealth.modul;

import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.model.NetWorkModelDeepSearch;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkDeepSearchModul {

    @Provides
    @Singleton
    public NetWorkModelDeepSearch netWorkModelDeepSearch(){
        return new NetWorkModelDeepSearch();
    }
}
