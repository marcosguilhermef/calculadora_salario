package com.origin.salario_liquido.ads.Admob;

import android.app.Activity;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.origin.salario_liquido.ads.Admob.Native.OnNativeAdLoadedListener;

public abstract class Admob {
    private View showcasa;
    private String unit;
    private Activity activity;
    public AdLoader adLoader;

    /**
     * In interstitial case is not needed pass value to showcase
     *
     * @param unit is the code from ads block
     */
    public Admob(String unit, Activity activity) {
        this.unit = unit;
        this.activity = activity;
    }

    /**
     * @param view receive value of showcase unit ads if applicable
     * @param unit is the code from ads block
     **/
    public Admob(View view, String unit, Activity activity) {
        showcasa = view;
        this.unit = unit;
        this.activity = activity;
    }

    abstract public void build();

    public abstract void build(
            OnNativeAdLoadedListener callBackAdLoad,
            AdListener adListener,
            NativeAdOptions.Builder nativeAdOptions
    );

    abstract protected void load();

    abstract public void show();

    abstract protected Boolean controllerAds();

    public View getShowcasa() {
        return showcasa;
    }

    public void setShowcasa(View showcasa) {
        this.showcasa = showcasa;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public AdLoader getAdLoader() {
        return adLoader;
    }

    public void setAdLoader(AdLoader adLoader) {
        this.adLoader = adLoader;
    }
}
