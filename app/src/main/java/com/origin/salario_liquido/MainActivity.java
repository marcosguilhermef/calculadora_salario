package com.origin.salario_liquido;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.initializing.ApdInitializationCallback;
import com.appodeal.ads.initializing.ApdInitializationError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.origin.salario_liquido.ads.Analytics;
import com.origin.salario_liquido.databinding.ActivityMainBinding;
import com.facebook.FacebookSdk;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavHostFragment navHostFragment;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);

        /*MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });*/
        Analytics initi = new Analytics(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        appBarConfiguration = new AppBarConfiguration.Builder(getNavController().getGraph()).build();

        NavigationUI.setupActionBarWithNavController(this,getNavController(),appBarConfiguration);

        Appodeal.initialize(this, getString( R.string.app_key ), Appodeal.NATIVE);


        Appodeal.cache(this, Appodeal.NATIVE, 3);

        setContentView(binding.getRoot());
    }

    private NavController getNavController() {
        return navHostFragment.getNavController();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}