package com.haris.android.weather.data.cache;

import android.content.Context;

import com.haris.android.weather.data.cache.serializer.Serializer;
import com.haris.android.weather.data.entity.WeatherEntity;
import com.haris.android.weather.data.exception.WeatherNotAvailableException;
import com.haris.android.weather.domain.executor.ThreadExecutor;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


@Singleton
public class WeatherCacheImpl implements WeatherCache {

    private static final String SETTINGS_FILE_NAME = "com.haris.android.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

    private static final String DEFAULT_FILE_NAME = "Weather_";
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private final Context context;
    private final File cacheDir;
    private final Serializer serializer;
    private final FileManager fileManager;
    private final ThreadExecutor threadExecutor;


    @Inject
    WeatherCacheImpl(Context context, Serializer serializer,
                     FileManager fileManager, ThreadExecutor executor) {
        if (context == null || serializer == null || fileManager == null || executor == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.context = context.getApplicationContext();
        this.cacheDir = this.context.getCacheDir();
        this.serializer = serializer;
        this.fileManager = fileManager;
        this.threadExecutor = executor;
    }


    @Override
    public Observable<WeatherEntity> get(String lat, String lng) {
        return Observable.create(emitter -> {
            final File WeatherEntityFile = WeatherCacheImpl.this.buildFile(0);
            final String fileContent = WeatherCacheImpl.this.fileManager.readFileContent(WeatherEntityFile);
            final WeatherEntity weatherEntity =
                    WeatherCacheImpl.this.serializer.deserialize(fileContent, WeatherEntity.class);
            if (weatherEntity != null) {
                emitter.onNext(weatherEntity);
                emitter.onComplete();
            } else {
                emitter.onError(new WeatherNotAvailableException());
            }
        });
    }


    @Override
    public void put(WeatherEntity weatherEntity) {

        if (weatherEntity != null) {
            weatherEntity.setWeatherTime(new SimpleDateFormat("dd MMM hh:mm a").format(Calendar.getInstance().getTime()));

            final File WeatherEntty = this.buildFile(0);
            if (!isCached(0)) {
                final String jsonString = this.serializer.serialize(weatherEntity, WeatherEntity.class);
                this.executeAsynchronously(new CacheWriter(this.fileManager, WeatherEntty, jsonString));
                setLastCacheUpdateTimeMillis();
            }
        }

    }

    @Override
    public boolean isCached(int weatherId) {
        final File WeatherEntityFile = this.buildFile(weatherId);
        return this.fileManager.exists(WeatherEntityFile);
    }

    @Override
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);

        if (expired) {
            this.evictAll();
        }

        return expired;
    }

    @Override
    public void evictAll() {
        this.executeAsynchronously(new CacheEvictor(this.fileManager, this.cacheDir));
    }


    private File buildFile(int weatherId) {
        final StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(this.cacheDir.getPath());
        fileNameBuilder.append(File.separator);
        fileNameBuilder.append(DEFAULT_FILE_NAME);
        fileNameBuilder.append(weatherId);

        return new File(fileNameBuilder.toString());
    }


    private void setLastCacheUpdateTimeMillis() {
        final long currentMillis = System.currentTimeMillis();
        this.fileManager.writeToPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis);
    }


    private long getLastCacheUpdateTimeMillis() {
        return this.fileManager.getFromPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE);
    }


    private void executeAsynchronously(Runnable runnable) {
        this.threadExecutor.execute(runnable);
    }


    private static class CacheWriter implements Runnable {
        private final FileManager fileManager;
        private final File fileToWrite;
        private final String fileContent;

        CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
            this.fileManager = fileManager;
            this.fileToWrite = fileToWrite;
            this.fileContent = fileContent;
        }

        @Override
        public void run() {
            this.fileManager.writeToFile(fileToWrite, fileContent);
        }
    }


    private static class CacheEvictor implements Runnable {
        private final FileManager fileManager;
        private final File cacheDir;

        CacheEvictor(FileManager fileManager, File cacheDir) {
            this.fileManager = fileManager;
            this.cacheDir = cacheDir;
        }

        @Override
        public void run() {
            this.fileManager.clearDirectory(this.cacheDir);
        }
    }
}
