package com.origin.salario_liquido.ui.calculadora;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.NativeAd;
import com.appodeal.ads.NativeCallbacks;
import com.origin.salario_liquido.ADS;
import com.origin.salario_liquido.R;
import com.origin.salario_liquido.ads.Analytics;
import com.origin.salario_liquido.databinding.FragmentCalculadoraBinding;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Calculadora#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Calculadora extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentCalculadoraBinding databiding;
    private List<NativeAd> nativeAds;

    public Calculadora() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Calculadora.
     */
    // TODO: Rename and change types and number of parameters
    public static Calculadora newInstance(String param1, String param2) {
        Calculadora fragment = new Calculadora();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        databiding = FragmentCalculadoraBinding.inflate(inflater, container, false);
        Analytics.ScreenNameSend("Calduladora",this.getClass().getName());

        databiding.calcular.setOnClickListener( v-> {
            Bundle args = createBundle();
            Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.navigationToCalcuted,args);
        });

        startAppOdeal();

        Appodeal.setNativeCallbacks(new NativeCallbacks() {
            @Override
            public void onNativeLoaded() {
                Log.i("APPODEAL", "onNativeLoaded");
                nativeAds = Appodeal.getNativeAds(1);
                showAdsAppOdeal();
            }
            @Override
            public void onNativeFailedToLoad() {
                Log.i("APPODEAL", "onNativeFailedToLoad");
            }
            @Override
            public void onNativeShown(NativeAd nativeAd) {
                Log.i("APPODEAL", "onNativeShown");
            }
            @Override
            public void onNativeShowFailed(NativeAd nativeAd) {
                Log.i("APPODEAL", "onNativeShowFailed"+nativeAd.getTitle());
            }
            @Override
            public void onNativeClicked(NativeAd nativeAd) {
                Log.i("APPODEAL", "onNativeClicked"+nativeAd.getTitle());
            }
            @Override
            public void onNativeExpired() {
                Log.i("APPODEAL", "onNativeExpired");
            }
        });

        return databiding.getRoot();
    }

    private void startAppOdeal(){
        if(Appodeal.isInitialized(Appodeal.NATIVE)){
            Appodeal.initialize(getActivity(), getString( R.string.app_key ), Appodeal.NATIVE);
        }
    }

    private void showAdsAppOdeal(){
        databiding.nativeAdViewAppWall.setNativeAd(nativeAds.get(0));
    }

    private Bundle createBundle(){
        Bundle args = new Bundle();
        if(databiding.salarioBruto.getText().toString().isEmpty()){
            args.putString("bruto","0");
        }else{
            args.putString("bruto",databiding.salarioBruto.getText().toString());
        }
        if(databiding.desconto.getText().toString().isEmpty()){
            args.putString("desconto", "0");
        }else{
            args.putString("desconto", databiding.desconto.getText().toString());
        }
        if(databiding.dependentes.getText().toString().isEmpty()){
            args.putString("dependentes", "0");
        }else{
            args.putString("dependentes", databiding.dependentes.getText().toString());
        }
        return args;
    }


    private void initAds(Bundle args){
        Intent intent = new Intent(getContext(), ADS.class);
        intent.putExtras(args);
        startActivity(intent);
    }
}