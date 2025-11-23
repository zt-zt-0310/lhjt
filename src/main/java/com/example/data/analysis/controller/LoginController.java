package com.example.data.analysis.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.data.analysis.dto.LoginDate;
import com.example.data.analysis.entity.User;
import com.example.data.analysis.service.UserService;
import com.example.data.analysis.utils.JwtUtil;
import com.example.data.analysis.utils.response.IResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zt
 * @Description TODO
 * @Time 2024/11/25 15:30
 */
@Api(tags = "用户登录")
@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {

    private final JwtUtil jwtUtil;
    private final UserService userService;


    //    生成token
    @PostMapping("/login")
    @ApiOperation(value = "登录接口",httpMethod = "POST",response = User.class)
    public IResponse login(@RequestBody User request) {
        // 在实际应用中，你可以验证用户名和密码，然后生成令牌
        // 这里只是一个简单的示例，假设用户名有效

        User one = userService.getOne(Wrappers.<User>query().lambda()
                .eq(request.getLoginName()!=null,User::getLoginName, request.getLoginName())
                .eq(request.getPassword()!=null,User::getPassword, request.getPassword())
                .eq(request.getPhone()!=null,User::getPhone, request.getPhone()));
        if (null==one){
            return IResponse.fail("账号密码不正确！");
        }
        if("0".equals(one.getEquipmentNo())){
            one.setEquipmentNo(request.getEquipmentNo());
        }else if(!"1".equals(one.getEquipmentNo()) && request.getEquipmentNo().equals(one.getEquipmentNo())){
            return IResponse.fail("当前设备和账户不一致，请联系管理员！");
        }
        userService.updateById(one);
        String token = jwtUtil.generateToken(one.getName(),one.getPassword());
        LoginDate loginDate = new LoginDate();
        loginDate.setToken(token);
        return IResponse.success(loginDate);
    }

    @PostMapping("/insertUser")
    @ApiOperation(value = "新增用户",httpMethod = "POST",response = User.class)
    public IResponse insertUser(@RequestBody User request) {
        return IResponse.fail("注册失败，请联系管理员！");
//        return IResponse.success( userService.saveOrUpdate(request));
    }

    @GetMapping("/user")
    public String getUserInfo(@RequestHeader("Authorization") String token) {
        if (jwtUtil.validateToken(token)) {
            String username = jwtUtil.getUsernameFromToken(token);
            return "Hello, " + username + "!";
        } else {
            return "Invalid token";
        }
    }

}
