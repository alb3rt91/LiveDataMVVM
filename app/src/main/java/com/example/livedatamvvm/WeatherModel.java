package com.example.livedatamvvm;

import androidx.lifecycle.LiveData;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class WeatherModel {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> weatherUpdater;

    public LiveData<String> getWeatherLiveData() {
        return new LiveData<String>() {
            private final String[] weatherStates = {"sol", "nubes", "lluvia", "viento"};
            private int index = 0;

            @Override
            protected void onActive() {
                super.onActive();
                weatherUpdater = scheduler.scheduleWithFixedDelay(() -> {
                    postValue(weatherStates[index]); // Emitimos el estado del clima actual
                    index = (index + 1) % weatherStates.length; // Cambiamos al siguiente estado
                }, 0, 1, SECONDS); // Cada segundo actualizamos
            }

            @Override
            protected void onInactive() {
                super.onInactive();
                if (weatherUpdater != null) {
                    weatherUpdater.cancel(true); // Detenemos las actualizaciones si nadie observa
                }
            }
        };
    }
}
