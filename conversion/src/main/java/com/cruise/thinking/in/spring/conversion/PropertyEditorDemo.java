package com.cruise.thinking.in.spring.conversion;

import java.beans.PropertyEditor;

/**
 * {@link PropertyEditor} 示例
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/7/12
 */
public class PropertyEditorDemo {

    public static void main(String[] args) {
        // 模拟 Spring 操作
        String text = "name = Cruise";
        StringToPropertiesPropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();
        // 传递 text 内容
        propertyEditor.setAsText(text);
        Object value = propertyEditor.getValue();
        System.out.println(value);
        System.out.println(propertyEditor.getAsText());
    }
}
