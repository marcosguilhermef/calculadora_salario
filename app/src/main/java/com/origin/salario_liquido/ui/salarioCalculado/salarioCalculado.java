package com.origin.salario_liquido.ui.salarioCalculado;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
import com.origin.salario_liquido.databinding.FragmentSalarioClculadoBinding;

import java.text.DecimalFormat;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link salarioCalculado#newInstance} factory method to
 * create an instance of this fragment.
 */
public class salarioCalculado extends Fragment {


    private FragmentSalarioClculadoBinding databiding;
    private String bruto;
    private String desconto;
    private String dependentes;
    private List<NativeAd> nativeAds;

    public salarioCalculado() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment salarioClculado.
     */
    // TODO: Rename and change types and number of parameters
    public static salarioCalculado newInstance(String param1, String param2) {
        salarioCalculado fragment = new salarioCalculado();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Analytics.ScreenNameSend("Salário Calcudado",this.getClass().getName());


        //bruto    = getArguments().getString("buto");
        //desconto = getArguments().getString("desonto");
        if(getArguments().getString("bruto") != null){
            bruto = getArguments().getString("bruto");
        }else{
            bruto = "0.00";
        }
        if(getArguments().getString("desconto") != null){
            desconto = getArguments().getString("desconto");
        }else{
            desconto = "0.00";
        }
        if(getArguments().getString("dependentes") != null){
            dependentes = getArguments().getString("dependentes");
        }else{
            dependentes = "0";
        }
        Log.i("arg_0", bruto);
        Log.i("arg_1", desconto);
        Log.i("arg_2", dependentes);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        databiding = FragmentSalarioClculadoBinding.inflate(inflater, container, false);
        databiding.bruto1.setText("R$ " + bruto);
        databiding.descontoIrpf.setText("R$ " + descontoIRPF());
        databiding.descontoInss.setText("R$ " + descontoINSS());
        databiding.totalDesconto.setText("R$ " + totalDeconto());
        databiding.totalLiquido.setText("R$ " + totalLiquido());
        databiding.outrosDescontos.setText("R$ " + getDesconto());

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
        nativeAds = Appodeal.getNativeAds(1);
    }
    private void showAdsAppOdeal(){
        databiding.nativeAdViewAppWall.setNativeAd(nativeAds.get(0));
    }

    private String descontoINSS(){
        Float brutoValue = Float.valueOf(bruto);
        DecimalFormat df = new DecimalFormat("#0.00");
        if (brutoValue <= 1212) {
            Double calculo = brutoValue * (0.075);
            return String.valueOf((df.format(calculo)));
        }else if (brutoValue > 1212 && brutoValue <= 2427.35) {
            Double calculo = (1212 * 0.075) + (brutoValue - 1212)*0.09;
            return String.valueOf(df.format(calculo));
        }else if (brutoValue > 2427.35 && brutoValue <= 3641.03) {
            Double calculo = (1212 * 0.075) + (2427.35-1212) * 0.09 + (brutoValue - 2427.35) * 0.12;
            return String.valueOf(df.format(calculo));
        }else if (brutoValue > 3641.03 && brutoValue <= 7087.22) {
                Double calculo = (1212 * 0.075) + (2427.35 - 1212) * 0.09 + (3641.03-2427.35) * 0.12 + (brutoValue - 3641.03)*0.14;
            return String.valueOf(df.format(calculo));
        }else {
            return String.valueOf(828.39);
        }
    }
    private String getDesconto(){
        DecimalFormat df = new DecimalFormat("#0.00");
        Double f = Double.valueOf(desconto);
        return String.valueOf(df.format(f));
    }
    private String descontoIRPF() {
        DecimalFormat df = new DecimalFormat("#0.00");
        Float decontoInss = Float.parseFloat(descontoINSS().replace(",","."));
        Double brutoValue = Double.valueOf(bruto);
        Integer dependentes = Integer.valueOf(this.dependentes);
        brutoValue = brutoValue - decontoInss - (dependentes * 189.59);
        if (brutoValue <= 1903.98) {
            Double calculo = brutoValue * 0;
            return String.valueOf(calculo);
        }else if (brutoValue > 1903.98 && brutoValue < 2826.65) {
            Double calculo = (brutoValue*0.075)-142.80;
            return String.valueOf(df.format(calculo));
        }else if (brutoValue > 2826.66 && brutoValue < 3751.05) {
            Double calculo = (brutoValue * 0.15)-354.80;
            return String.valueOf(df.format(calculo));
        }else if (brutoValue > 3751.06 && brutoValue < 4664.68) {
            Double calculo = (brutoValue * 0.225)- 636.13;
            return String.valueOf(df.format(calculo));
        }else {
            Double calculo = brutoValue * 0.275 - 869.36;
            return String.valueOf(df.format(calculo));
        }
    }
    private String totalDeconto(){
        Float decontoIRPF = Float.valueOf(descontoIRPF().replace(",","."));
        Float decontoInss = Float.valueOf(descontoINSS().replace(",","."));
        DecimalFormat df = new DecimalFormat("#0.00");
        return String.valueOf(df.format(decontoIRPF+decontoInss));
    }
    private  String totalLiquido(){
        Float brutoValue = Float.valueOf(bruto);
        Float decontoIRPF = Float.valueOf(descontoIRPF().replace(",","."));
        Float decontoInss = Float.valueOf(descontoINSS().replace(",","."));
        Float deconto = Float.valueOf(getDesconto().replace(",","."));
        DecimalFormat df = new DecimalFormat("#0.00");
        return String.valueOf(df.format(brutoValue - (decontoIRPF+decontoInss)-deconto));
    }
}