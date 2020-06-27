package com.cruise.thinking.in.spring.dependency.lookup;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性依赖查找示例
 * <p>
 *     双亲委派
 * </p>
 *
 * <p>
 *     {@link HierarchicalBeanFactory}继承了{@link BeanFactory}，所以此类支持单一类型依赖查找和层次类型查找
 *     {@link ConfigurableBeanFactory}继承了{@link HierarchicalBeanFactory}，所以此类支持单一类型、层次类型依赖查找
 *     {@link ConfigurableListableBeanFactory}继承了 {@link ConfigurableBeanFactory}和{@link ListableBeanFactory}，
 *     所以它支持单一类型、层次类型、集合类型依赖查找
 *     {@link DefaultListableBeanFactory}继承了{@link ConfigurableListableBeanFactory}，所以它
 *     既是单一类型依赖查找，也是集合类型依赖查找，也是层次类型依赖查找
 * </p>
 *
 * <p>{@link AbstractBeanFactory#containsBean(String)}会递归的遍历parentBeanFactory</p>
 * <p>{@link AbstractBeanFactory#containsLocalBean(String)}只会检查当前BeanFactory</p>
 *
 * @author Cruise
 * @see HierarchicalBeanFactory#getParentBeanFactory
 * @see HierarchicalBeanFactory#containsLocalBean(String)
 * @see ConfigurableBeanFactory#setParentBeanFactory
 * @see ConfigurableListableBeanFactory
 * @see DefaultListableBeanFactory
 * @version 1.0
 * @since 2020/6/27
 */
public class HierarchicalDependencyLookupDemo {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(HierarchicalDependencyLookupDemo.class);

        ConfigurableListableBeanFactory currentBeanFactory = context.getBeanFactory();

        System.out.println("当前BeanFactory的父BeanFactory：" + currentBeanFactory.getParentBeanFactory());

        HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
        // 设置parentBeanFactory
        currentBeanFactory.setParentBeanFactory(parentBeanFactory);

        System.out.println("当前BeanFactory的父BeanFactory：" + currentBeanFactory.getParentBeanFactory());

        displayContainsLocalBean(currentBeanFactory, "user");

        displayContainsBean(currentBeanFactory,"user");

        context.close();
    }


    public static HierarchicalBeanFactory createParentBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");
        return beanFactory;
    }

    /**
     * 判断当前BeanFactory及其ParentBeanFactory是否包含Bean
     *
     * @param beanFactory
     * @param beanName
     */
    public static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("BeanFactory：[%s],是否包含Bean：[%s],%s",
                beanFactory, beanName, containsBean(beanFactory, beanName));
    }

    /**
     * 通过递归遍历所有ParentBeanFactory判断是否包含该Bean
     *
     * @param beanFactory
     * @param beanName
     * @return
     */
    public static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory hierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            return containsBean(hierarchicalBeanFactory, beanName);
        }
        return beanFactory.containsLocalBean(beanName);
    }

    /**
     * 判断当前BeanFactory是否包含Bean
     *
     * @param beanFactory
     * @param beanName
     */
    public static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("BeanFactory：[%s],是否包含Bean：[%s],%s，%n",
                beanFactory, beanName, beanFactory.containsLocalBean(beanName));
    }
}
