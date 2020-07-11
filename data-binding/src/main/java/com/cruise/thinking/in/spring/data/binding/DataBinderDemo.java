package com.cruise.thinking.in.spring.data.binding;

import com.cruise.thinking.in.spring.data.binding.domain.Employee;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link DataBinder} 示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/11
 */
public class DataBinderDemo {

    public static void main(String[] args) {
        test1();
        //test2();
        test3();
        test4();
        test5();
    }

    public static void test1() {
        // 1创建空白对象
        Employee employee = new Employee();
        // 2创建 DataBinder
        DataBinder dataBinder = new DataBinder(employee, "employee");
        // 3创建PropertyValues
        Map<Object, Object> source = new HashMap<>();
        source.put("id", "1");
        source.put("name", "Cruise");
        PropertyValues propertyValues = new MutablePropertyValues(source);
        dataBinder.bind(propertyValues);
        System.out.println(employee);
        // 4获取绑定结果
        BindingResult bindingResult = dataBinder.getBindingResult();
        System.out.println(bindingResult);
    }

    public static void test2() {
        // 1创建空白对象
        Employee employee = new Employee();
        // 2创建 DataBinder
        DataBinder dataBinder = new DataBinder(employee, "employee");
        // 3创建PropertyValues
        Map<Object, Object> source = new HashMap<>();
        source.put("id", "1");
        source.put("name", "Cruise");
        // 设置不存在的属性，DataBinder 默认忽略未知的属性
        source.put("age", 18);
        PropertyValues propertyValues = new MutablePropertyValues(source);
        // 设置 ignoreUnknownFields true -> false 抛出异常
        dataBinder.setIgnoreUnknownFields(false);

        dataBinder.bind(propertyValues);

        System.out.println(employee);
        // 4获取绑定结果
        BindingResult bindingResult = dataBinder.getBindingResult();
        System.out.println(bindingResult);
    }

    public static void test3() {
        // 1创建空白对象
        Employee employee = new Employee();
        // 2创建 DataBinder
        DataBinder dataBinder = new DataBinder(employee, "employee");
        // 3创建PropertyValues
        Map<Object, Object> source = new HashMap<>();
        source.put("id", "1");
        source.put("name", "Cruise");
        // 设置嵌套属性,默认支持
        source.put("company.name", "Alibaba");
        PropertyValues propertyValues = new MutablePropertyValues(source);
        dataBinder.bind(propertyValues);

        System.out.println(employee);
        // 4获取绑定结果
        BindingResult bindingResult = dataBinder.getBindingResult();
        System.out.println(bindingResult);
    }

    public static void test4() {
        Employee employee = new Employee();
        // 2创建 DataBinder
        DataBinder dataBinder = new DataBinder(employee, "employee");
        // 3创建PropertyValues
        Map<Object, Object> source = new HashMap<>();
        source.put("id", "1");
        source.put("name", "Cruise");
        // 设置嵌套属性,默认支持
        //source.put("company", new Company());
        source.put("company.name", "Alibaba");
        PropertyValues propertyValues = new MutablePropertyValues(source);
        // 设置 ignoreInvalidFields false -> true 需要配合 autoGrowNestedPaths = false
        dataBinder.setIgnoreInvalidFields(true);
        // 设置不支持嵌套属性 autoGrowNestedPaths true -> false 抛出异常
        dataBinder.setAutoGrowNestedPaths(false);

        dataBinder.bind(propertyValues);

        System.out.println(employee);
        // 4获取绑定结果
        BindingResult bindingResult = dataBinder.getBindingResult();
        System.out.println(bindingResult);
    }

    public static void test5() {
        Employee employee = new Employee();
        // 2创建 DataBinder
        DataBinder dataBinder = new DataBinder(employee, "employee");
        // 3创建PropertyValues
        Map<Object, Object> source = new HashMap<>();
        source.put("id", "1");
        source.put("name", "Cruise");
        PropertyValues propertyValues = new MutablePropertyValues(source);
        dataBinder.setRequiredFields("id","name","city");
        dataBinder.bind(propertyValues);

        System.out.println(employee);
        // 4获取绑定结果,存在错误信息
        BindingResult bindingResult = dataBinder.getBindingResult();
        System.out.println(bindingResult);
    }
}
