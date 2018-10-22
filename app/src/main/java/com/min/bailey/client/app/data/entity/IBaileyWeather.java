package com.min.bailey.client.app.data.entity;

import java.util.List;

/**
 * @author: Bailey
 * Email: 553258697@qq.com
 * Description:
 */
public interface IBaileyWeather {

    BaileyWeather.FakeBasic getFakeBasic();
    BaileyWeather.FakeNow getFakeNow();
    BaileyWeather.FakeAqi getFakeAqi();
    List<BaileyWeather.FakeForecastDaily> getFakeForecastDaily();
    List<BaileyWeather.FakeForecastHourly> getFakeForecastHourly();
    List<BaileyWeather.FakeSuggestion> getFakeSuggestion();

}
