/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: MyPassworldEncoder
 * Author:   891649
 * Date:     2020/5/7 11:26
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.mmall.demo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 891649
 * @create 2020/5/7
 * @since 1.0.0
 */
public class MyPassworldEncoder implements PasswordEncoder {

    /**
     * 加密方法
     * @param rawPassword
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        //Security 中有多个封装的加密类
        BCryptPasswordEncoder bCryptPas = new BCryptPasswordEncoder();
        return bCryptPas.encode(rawPassword.toString());
    }

    /**
     * 匹配方法
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        BCryptPasswordEncoder bCryptPas = new BCryptPasswordEncoder();
        return bCryptPas.matches(encodedPassword,rawPassword.toString());
    }
}