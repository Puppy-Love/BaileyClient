package com.min.bailey.client.mvp.ui.widget.weather.dynamic;


import lombok.Getter;
import lombok.Setter;

/**
 * @author: Bailey
 * Email: 553258697@qq.com
 * Description:
 */
public class ShortWeatherInfo {

    private String code;
    private String windSpeed;
    private String sunrise;
    private String sunset;
    private String moonrise;
    private String moonset;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getMoonrise() {
        return moonrise;
    }

    public void setMoonrise(String moonrise) {
        this.moonrise = moonrise;
    }

    public String getMoonset() {
        return moonset;
    }

    public void setMoonset(String moonset) {
        this.moonset = moonset;
    }
}
