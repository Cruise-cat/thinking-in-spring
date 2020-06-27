package com.cruise.thinking.in.spring.dependency.injection.holder;

import com.cruise.thinking.in.spring.dependency.injection.enumeration.City;
import com.cruise.thinking.in.spring.dependency.injection.type.BaseTypeDependencyInjectionDemo;
import org.springframework.core.io.Resource;

/**
 * 描述：{@link City} 的Holder 类
 *
 * @author Cruise
 * @version 1.0
 * @see BaseTypeDependencyInjectionDemo
 * @since 2020/06/27
 */
public class CityHolder {

    private City city;

    private Resource cityResource;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Resource getCityResource() {
        return cityResource;
    }

    public void setCityResource(Resource cityResource) {
        this.cityResource = cityResource;
    }

    @Override
    public String toString() {
        return "CityHolder{" +
                "city=" + city +
                ", cityResource=" + cityResource +
                '}';
    }
}
