/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SpringSecurityConfig
 * Author:   891649
 * Date:     2020/5/7 9:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.mmall.demo.config;

import com.mmall.demo.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 891649
 * @create 2020/5/7
 * @since 1.0.0
 */
@Configuration  //表名他是一个配置
@EnableWebSecurity   //打开web支持
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserService myUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     /*   //inMemoryAuthentication 从内存中获取
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("ADMIN");
        //表示内存中有一个admin对象   这里的版本是Security 5.0以上
        //可以指定多人
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("李四")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("USER");*/

        //指将用户放在数据库中管理
        //将用户的方法交给auth管理
        auth.userDetailsService(myUserService).passwordEncoder(new BCryptPasswordEncoder());
        auth.jdbcAuthentication().usersByUsernameQuery("").authoritiesByUsernameQuery("").passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        //配置安全策略
        http.authorizeRequests()
                .antMatchers("/").permitAll() //项目的主路径可以放行
                .anyRequest().authenticated()                //其他的请求经过验证
                .and()                                       //允许
                .logout().permitAll()                        //注销允许访问
                .and()                                       //允许
                .formLogin();                                //表单登录
        http.csrf().disable();  //关闭默认的scrf验证
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
          //忽略静态资源  以下都不会进行资源拦截
        web.ignoring().antMatchers("/js/**","/css/**","/images/**");
    }

}