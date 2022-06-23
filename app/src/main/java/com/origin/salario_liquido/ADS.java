package com.origin.salario_liquido;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

/*
 * Main Activity class that loads {@link MainFragment}.
 */
public class ADS extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                calculate(savedInstanceState);
            }
        }, 3000);
    }

    private void calculate(Bundle args){
        NavHostFragment navhost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);//.navigate(R.id.navigationToCalcuted,args);
        navhost.getNavController().navigate(R.id.navigationToCalcuted,args);

    }
}