package com.cruise.thinking.in.spring.bean.scope;

import com.cruise.thinking.in.spring.ioc.container.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Bean 作用域示例
 * <p>结论：</p>
 * <ul>
 *     <li>singleton 无论是依赖查找还是依赖注入，每次都是相同的Bean</li>
 *     <li>prototype 无论是依赖查找还是依赖注入，每次都是新生成的Bean</li>
 *     <li>如果是依赖查找或依赖注入集合包装类型的对象，那么singleton和prototype都只有一个</li>
 * </ul>
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/22
 */
public class BeanScopeDemo {

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;

    @Autowired
    private Map<String,User> userMap;

    @Autowired
    private List<User> userList;

    @Bean
    public User singletonUser() {
        User user = new User();
        user.setId(Instant.now().toEpochMilli());
        return user;
    }

    @Bean
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User prototypeUser() {
        User user = new User();
        user.setId(Instant.now().toEpochMilli());
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanScopeDemo.class);

        lookup(context);
        injection(context);

        context.close();
    }

    /**
     * 依赖查找中的 Bean Scope
     *
     * @param context
     */
    public static void lookup(AnnotationConfigApplicationContext context) {

        for (int i = 0; i < 3; i++) {

            System.out.println("singletonUser:"+ context.getBean("singletonUser",User.class));
            System.out.println("prototypeUser:"+ context.getBean("prototypeUser",User.class));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("=======================");
        }
    }

    /**
     * 依赖注入中的 Bean Scope
     *
     * @param context
     */
    public static void injection(AnnotationConfigApplicationContext context) {
        BeanScopeDemo bean = context.getBean(BeanScopeDemo.class);

        System.out.println("bean.singletonUser:" + bean.singletonUser);
        System.out.println("bean.singletonUser1:" + bean.singletonUser1);
        System.out.println("bean.prototypeUser:" + bean.prototypeUser);
        System.out.println("bean.prototypeUser1:" + bean.prototypeUser1);
        // 注意 ： 对于集合类型的注入，就算是 prototype bean，也只会存在一个,因为已经被注入的prototype bean不在受Spring管理了
        System.out.println("bean.userMap:" + bean.userMap);
        System.out.println("bean.userList:" + bean.userList);
    }

}
