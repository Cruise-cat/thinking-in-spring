package com.cruise.thinking.in.spring.bean.scope.controller;

import com.cruise.thinking.in.spring.bean.scope.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.AbstractRequestAttributesScope;
import org.springframework.web.context.support.ServletContextScope;

/**
 * 首页
 *
 * @author Cruise
 * @version 1.0
 * @since 2020/6/22
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    @Qualifier("requestPerson")
    private Person requestPerson;

    @Autowired
    @Qualifier("sessionPerson")
    private Person sessionPerson;

    @Autowired
    @Qualifier("applicationPerson")
    private Person applicationPerson;

    /**
     * 这里注入了一个 {@link RequestScope} 作用范围的 {@link Person}
     * 在这个方法里 requestPerson 是被 cglib 代理的对象而且每次都是一样的，
     * 但是返回给前端的对象都是新生成的，可以debug 调试{@link AbstractRequestAttributesScope}查看。
     * 每调用此接口都会初始化、销毁 一个 requestPerson 类似于 prototype 的感觉
     * requestPerson 在 Spring 上下文中的 beanName是 requestPerson
     * 但是返回给页面时beanName是 scopedTarget.requestPerson
     * <p>
     * {@link RequestScope} 线程间是隔离的 所以是线程安全的
     *
     * @param model
     * @return
     * @see AbstractRequestAttributesScope
     */
    @GetMapping("/request.html")
    public String request(Model model) {
        model.addAttribute("requestPerson", requestPerson);
        return "request";
    }

    /**
     * 这里注入了一个 {@link SessionScope} 作用范围的 {@link Person}
     * 其实此 sessionPerson 已经被 cglib 代理了
     * 同一个浏览器因为cookie相同所以同一个会话期间 sessionPerson 都是同一个，所以只会被初始化一次，
     * 可以debug 调试{@link AbstractRequestAttributesScope}查看。
     * <p>
     * {@link SessionScope} 使用了 线程锁 所以也是线程安全的 但是性能有损耗
     *
     * @param model
     * @return
     * @see SessionScope
     */
    @GetMapping("/session.html")
    public String session(Model model) {
        model.addAttribute("sessionPerson", sessionPerson);
        return "session";
    }

    /**
     * 这里注入了一个 {@link ApplicationScope} 作用范围的 {@link Person}
     * 其实此 applicationPerson 已经被 cglib 代理了
     *
     * @param model
     * @return
     * @see ServletContextScope
     */
    @GetMapping("/application.html")
    public String application(Model model) {
        model.addAttribute("applicationPerson", applicationPerson);
        return "application";
    }
}
