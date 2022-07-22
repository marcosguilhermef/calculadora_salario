package com.origin.salario_liquido.ads.Admob.Native;

import android.app.Activity;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.origin.salario_liquido.ads.Admob.Admob;

public class NativeAds extends Admob {
    public NativeAds(String unit, Activity activity) {
        super( unit, activity );
    }

    public NativeAds(View view, String unit, Activity activity) {
        super( view, unit, activity );
    }

    @Override
    public void build() {
    }

    @Override
    public void build(
            OnNativeAdLoadedListener callBackAdLoad,
            AdListener adListener,
            NativeAdOptions.Builder nativeAdOptions
    ) {
        adLoader = new AdLoader.Builder( this.getActivity().getApplicationContext(), getUnit() )
                .forNativeAd( callBackAdLoad.setAdloader(adLoader) )
                .withAdListener( adListener )
                .withNativeAdOptions( nativeAdOptions.build() )
                .build();

    }

    @Override
    protected void load() {
        getAdLoader().loadAd(new AdRequest.Builder().build());
        return;
    }

    @Override
    public void show() {
        if(controllerAds()){

        }
    }

    @Override
    protected Boolean controllerAds() {
        return true;
    }
}
