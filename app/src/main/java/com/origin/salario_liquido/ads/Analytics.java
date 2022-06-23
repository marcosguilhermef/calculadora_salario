package com.origin.salario_liquido.ads;

import android.app.Activity;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Analytics {
    public static FirebaseAnalytics mFirebaseAnalytics;
    public Analytics(Activity activity){
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(activity);
    }
    public static void ScreenNameSend(String title,String classe){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, title);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, classe);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
    }
    public static void share(String title){
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE, bundle);
    }

    public static void avaliar(String title){
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE, bundle);
    }

    public static void success(String title){
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        mFirebaseAnalytics.logEvent("SUCCESS_GROUP_ADD", bundle);
    }
    public static void error(String erro){
        Bundle bundle = new Bundle();
        bundle.putString("erro",erro);
        mFirebaseAnalytics.logEvent("ERROR_GROUP_ADD", bundle);
    }
}
