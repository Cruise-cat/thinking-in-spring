package com.cruise.thinking.in.spring.dependency.injection.holder;

import com.cruise.thinking.in.spring.dependency.injection.enumeration.City;
import com.cruise.thinking.in.spring.dependency.injection.type.CollectionTypeDependencyInjectionDemo;

import java.util.Arrays;
import java.util.List;

/**
 * 描述：多个 {@link City} Holder 类
 *
 * @author Cruise
 * @version 1.0
 * @see CollectionTypeDependencyInjectionDemo
 * @since 2020/06/27
 */
public class MultiCityHolder {

    private City[] cities;

    private List<City> cityList;

    public City[] getCities() {
        return cities;
    }

    public void setCities(City[] cities) {
        this.cities = cities;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    @Override
    public String toString() {
        return "MultiCityHolder{" +
                "cities=" + Arrays.toString(cities) +
                ", cityList=" + cityList +
                '}';
    }
}
