package com.example.livedatamvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class WeatherViewModel extends ViewModel {

    private final WeatherModel weatherModel = new WeatherModel();
    private final LiveData<String> weatherLiveData = weatherModel.getWeatherLiveData();

    public LiveData<Integer> getWeatherImageLiveData() {
        return Transformations.map(weatherLiveData, weatherState -> {
            switch (weatherState) {
                case "sol":
                    return R.drawable.sun;
                case "nubes":
                    return R.drawable.clouds;
                case "lluvia":
                    return R.drawable.rain;
                case "viento":
                    return R.drawable.wind;
                default:
                    return R.drawable.unknown;
            }
        });
    }
}
