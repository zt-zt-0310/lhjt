package com.example.data.analysis.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.data.analysis.entity.User;
import com.example.data.analysis.mapper.UserMapper;
import com.example.data.analysis.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author zt
 * @Description TODO
 * @Time 2024/11/20 17:55
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User findUserById(String userId,String password) {
        log.info("拦截器获取token结果============"+userId);
        return this.getOne(Wrappers.<User>query().lambda()
                .eq(User::getName,userId)
                .eq(User::getPassword, password));
    }
}
