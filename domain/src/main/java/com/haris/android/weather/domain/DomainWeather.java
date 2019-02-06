package com.haris.android.weather.domain;


public final class DomainWeather {
    public Coord coord;
    public Weather weather[];
    public String base;
    public Main main;
    public long visibility;
    public Wind wind;
    public Clouds clouds;
    public long dt;
    public Sys sys;
    public long id;
    public String name;
    public long cod;

    public String weatherTime;


    public String getWeatherTime() {
        return weatherTime;
    }

    public void setWeatherTime(String weatherTime) {
        this.weatherTime = weatherTime;
    }


    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setVisibility(long visibility) {
        this.visibility = visibility;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCod(long cod) {
        this.cod = cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public long getVisibility() {
        return visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public long getDt() {
        return dt;
    }

    public Sys getSys() {
        return sys;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getCod() {
        return cod;
    }

    public DomainWeather() {
    }

    public DomainWeather(long id) {
        this.id=id;
    }

    public DomainWeather(Coord coord, Weather[] weather, String base, Main main, long visibility, Wind wind, Clouds clouds, long dt, Sys sys, long id, String name, long cod) {
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public static final class Coord {
        public final double lon;
        public final double lat;

        public Coord(double lon, double lat) {
            this.lon = lon;
            this.lat = lat;
        }
    }

    public static final class Weather {
        public final long id;
        public final String main;
        public final String description;
        public final String icon;

        public Weather(long id, String main, String description, String icon) {
            this.id = id;
            this.main = main;
            this.description = description;
            this.icon = icon;
        }
    }

    public static final class Main {
        public double temp;
        public long pressure;
        public long humidity;
        public double temp_min;
        public double temp_max;

        public Main() {
        }

        public Main(double temp) {
            this.temp = temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public void setPressure(long pressure) {
            this.pressure = pressure;
        }

        public void setHumidity(long humidity) {
            this.humidity = humidity;
        }

        public void setTemp_min(double temp_min) {
            this.temp_min = temp_min;
        }

        public void setTemp_max(double temp_max) {
            this.temp_max = temp_max;
        }

        public double getTemp() {
            return temp;
        }

        public long getPressure() {
            return pressure;
        }

        public long getHumidity() {
            return humidity;
        }

        public double getTemp_min() {
            return temp_min;
        }

        public double getTemp_max() {
            return temp_max;
        }

        public Main(double temp, long pressure, long humidity, double temp_min, double temp_max) {
            this.temp = temp;
            this.pressure = pressure;
            this.humidity = humidity;
            this.temp_min = temp_min;
            this.temp_max = temp_max;
        }
    }

    public static final class Wind {
        public final double speed;
        public final long deg;

        public Wind(double speed, long deg) {
            this.speed = speed;
            this.deg = deg;
        }
    }

    public static final class Clouds {
        public final long all;

        public Clouds(long all) {
            this.all = all;
        }
    }

    public static final class Sys {
        public final long type;
        public final long id;
        public final double message;
        public final String country;
        public final long sunrise;
        public final long sunset;

        public Sys(long type, long id, double message, String country, long sunrise, long sunset) {
            this.type = type;
            this.id = id;
            this.message = message;
            this.country = country;
            this.sunrise = sunrise;
            this.sunset = sunset;
        }
    }
}
