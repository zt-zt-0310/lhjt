package com.example.data.analysis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.data.analysis.entity.User;

/**
 * @Author zt
 * @Description TODO
 * @Time 2024/11/20 17:55
 */
public interface UserService extends IService<User> {
    User findUserById(String userId,String password);
}
