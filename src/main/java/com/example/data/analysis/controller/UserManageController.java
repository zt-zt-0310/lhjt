package com.example.data.analysis.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.data.analysis.entity.User;
import com.example.data.analysis.service.UserService;
import com.example.data.analysis.utils.response.IResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理控制器
 */
@Api(tags = "用户管理")
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserManageController {

    private final UserService userService;

    @PostMapping("/add")
    @ApiOperation(value = "新增用户", httpMethod = "POST")
    public IResponse addUser(@RequestBody User user) {
        return IResponse.success(userService.save(user));
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新用户", httpMethod = "PUT")
    public IResponse updateUser(@RequestBody User user) {
        return IResponse.success(userService.updateById(user));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除用户", httpMethod = "DELETE")
    public IResponse deleteUser(@PathVariable Long id) {
        return IResponse.success(userService.removeById(id));
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "获取用户详情", httpMethod = "GET")
    public IResponse<User> getUserById(@PathVariable Long id) {
        return IResponse.success(userService.getById(id));
    }

    @GetMapping("/list")
    @ApiOperation(value = "分页查询用户列表", httpMethod = "GET")
    public IResponse<IPage<User>> getUserList(
            @RequestParam(defaultValue = "1") Integer pageIndex,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        IPage<User> page = new Page<>(pageIndex, pageSize);
        return IResponse.success(userService.page(page));
    }
}
