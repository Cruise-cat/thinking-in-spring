package com.cruise.thinking.in.spring.i18n;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * {@link MessageFormat} 示例
 *
 * @author Cruise
 * @version 1.0
 * @see MessageFormat
 * @since 2020/7/4
 */
public class MessageFormatDemo {

    public static void main(String[] args) {
        //simpleDemo();
        advancedFeatures();
    }

    /**
     * {@link MessageFormat} 简单示例
     *
     */
    private static void simpleDemo(){
        int planet = 7;
        String event = "a disturbance in the Force";

        String result = MessageFormat.format(
                "At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.",
                planet, new Date(), event);
        System.out.println(result);
    }

    /**
     * {@link MessageFormat} 高级特性
     *
     */
    private static void advancedFeatures(){
        int planet = 7;
        String event = "a disturbance in the Force";
        String pattern = "At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.";
        MessageFormat format = new MessageFormat(pattern);
        String result = format.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        // 重置 Locale
        format.setLocale(Locale.ENGLISH);
        // 需要重置 pattern ？
        format.applyPattern(pattern);
        result = format.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        // 重置 format
        format.setFormat(1,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        result = format.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        // 重置 pattern
        format.applyPattern("This is text:{0}");
        result = format.format(new Object[]{"hello world"});
        System.out.println(result);
    }
}
