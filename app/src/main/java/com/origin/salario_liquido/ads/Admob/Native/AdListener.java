package com.origin.salario_liquido.ads.Admob.Native;

import android.util.Log;

import com.google.android.gms.ads.LoadAdError;

public class AdListener extends com.google.android.gms.ads.AdListener {
    public AdListener() {

    }

    public void onAdClicked() {
    }

    public void onAdClosed() {
    }

    public void onAdFailedToLoad(LoadAdError erro) {
        Log.i( "error: ", erro.getMessage() );
    }

    public void onAdImpression() {
    }

    public void onAdLoaded() {
    }

    public void onAdOpened() {
    }
}
