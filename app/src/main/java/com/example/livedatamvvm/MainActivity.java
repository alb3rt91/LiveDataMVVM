package com.example.livedatamvvm;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private ImageView weatherImageView;
    private WeatherViewModel weatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherImageView = findViewById(R.id.weatherImageView);

        // Configuración del ViewModel
        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

        // Observamos el LiveData del ViewModel
        weatherViewModel.getWeatherImageLiveData().observe(this, imageResId -> {
            weatherImageView.setImageResource(imageResId); // Actualizamos la imagen
        });
    }
}
