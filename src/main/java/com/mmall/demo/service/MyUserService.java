/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: Uservice
 * Author:   891649
 * Date:     2020/5/7 11:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.mmall.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br> 
 * 〈密码的自定义验证〉
 *
 * @author 891649
 * @create 2020/5/7
 * @since 1.0.0
 */
@Component  //表示这个类就是Spring管理的类
public class MyUserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}