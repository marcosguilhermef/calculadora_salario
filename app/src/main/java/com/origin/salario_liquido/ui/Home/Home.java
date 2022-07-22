package com.origin.salario_liquido.ui.Home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.origin.salario_liquido.R;
import com.origin.salario_liquido.ads.Analytics;
import com.origin.salario_liquido.databinding.FragmentHomeBinding;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    private FragmentHomeBinding homeBinding;


    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Analytics.ScreenNameSend("Home",this.getClass().getName());
        homeBinding = FragmentHomeBinding.inflate(inflater,container,false);
        homeBinding.calcularSalario.cardCalculadora.setOnClickListener(toNavitate());
        homeBinding.duvidasFrequentes.cardDuvidasFrequentes.setOnClickListener(toNavitate());
        homeBinding.avaliarApp.cardAvaliarApp.setOnClickListener(toNavitate());
        homeBinding.compartilharApp.cardCompartilhar.setOnClickListener(toNavitate());
        return homeBinding.getRoot();
    }

    private View.OnClickListener toNavitate(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("ID: ", getResources().getResourceName(view.getId()));
                switch (getResources().getResourceName(view.getId())){
                    case "com.origin.salario_liquido:id/card_calculadora": Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.navigationToCalculator);
                    break;
                    case "com.origin.salario_liquido:id/card_duvidas_frequentes": Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.navigationToAsks);
                    break;
                    case "com.origin.salario_liquido:id/card_avaliar_app": openAvaliation();
                    break;
                    case "com.origin.salario_liquido:id/cardCompartilhar": compartilhar();
                    break;
                }
            }
        };
    }

    private void openAvaliation(){
        Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.origin.salario_liquido"));
        startActivity(viewIntent);
        Analytics.avaliar("Avaliar");
    }

    private void compartilhar(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.origin.salario_liquido");
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
        Analytics.share("Compartilhar");

    }
}