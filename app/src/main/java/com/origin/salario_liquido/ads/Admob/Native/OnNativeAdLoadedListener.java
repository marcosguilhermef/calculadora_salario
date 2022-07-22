package com.origin.salario_liquido.ads.Admob.Native;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.nativead.NativeAd;

public class OnNativeAdLoadedListener implements NativeAd.OnNativeAdLoadedListener {
    static AdLoader adLoader;
    static OnNativeAdLoadedListener instance;

    public OnNativeAdLoadedListener(){
        if(instance != null){
            instance = new OnNativeAdLoadedListener();
        }
    }

    @Override
    public void onNativeAdLoaded(@NonNull NativeAd nativeAd) {
        if(adLoader.isLoading()){

        }else{

        }

    }


    public static OnNativeAdLoadedListener setAdloader(AdLoader adLoader){
        OnNativeAdLoadedListener.adLoader = adLoader;
        return instance;
    }
}
