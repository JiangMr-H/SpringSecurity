package com.mmall.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true) //启动接口上的权限验证
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @RequestMapping("/")
    public String Hello(){
        return "Hello world";
    }

    @RequestMapping("/hello")
    public String HelloWorld(){
        return "Hi";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')") //设置只有ADMIN权限才可以调用
    @RequestMapping("/user")
    public String roleUser(){
        return "我是一个用户";
    }

    @PreAuthorize("#id<10 and principal.username.equals(#username) and #user.username.equals('abc')")  //约束参数id的范围   用户必须是当前用户  user对象中的用户名为指定用户
    @PostAuthorize("returnObject%2==0") //例如：返回结果为偶数    方法执行完后  对返回结果的判断决定结果是否返回  无法决定方法是否被调用
    @RequestMapping("/test")
    public String test(Integer id, String username, User user){
        return "我是一个用户";
    }

    //主要用于集合类型  功能与上方一样
    @PreFilter("filterObject%2==0")
    @PostFilter("filterObject%4==0")
    @RequestMapping("/test2")
    public List<Integer> test2(List<Integer> idList){

        return idList;
    }

}
