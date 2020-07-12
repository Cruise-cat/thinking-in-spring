package com.cruise.thinking.in.spring.conversion;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;

/**
 * String -> Properties 的 {@link PropertyEditor}
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/12
 */
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport implements PropertyEditor {

    /**
     * 必须实现 {@link #setAsText}
     *
     * @param text
     * @throws IllegalArgumentException
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        // 类型转换
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(text));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        // 临时存储 Properties
        setValue(properties);
        // next 通过 #getValue 获取临时 Properties
    }

    /**
     * 必须实现 {@link #getAsText()}
     * @return
     */
    @Override
    public String getAsText() {
        Properties properties = (Properties) getValue();
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            stringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append(System.getProperty("line.separator"));
        }
        return stringBuilder.toString();
    }
}
