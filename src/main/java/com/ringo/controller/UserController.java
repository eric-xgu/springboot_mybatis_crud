package com.ringo.controller;

import com.github.pagehelper.PageInfo;
import com.ringo.exceptions.ParamsException;
import com.ringo.model.ResultInfo;
import com.ringo.service.UserService;
import com.ringo.vo.User;
import com.ringo.vo.UserQuery;
import io.swagger.annotations.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


@Api(tags="用户管理模块")
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation(value = "用户管理模块-按用户名查询" ,notes="校验用户输入用户名")
    @ApiImplicitParam(name="username",value="查询参数",required = true,paramType = "path")
    @GetMapping("user/get1/{username}")
    public User queryUserByName(@PathVariable String username){
        return userService.queryUserByUsername(username);
    }

    @GetMapping("user/get/{id}")
    public User queryUserById(@PathVariable Integer id){
        return userService.queryUserById(id);
    }

    @PutMapping("user/add")
    public ResultInfo saveUser1(@RequestBody User user){
        ResultInfo resultInfo=new ResultInfo();
        userService.save(user);
        return resultInfo;
    }
    //控制层将异常返回给前端,put方法
    @PostMapping("user02")
    @ApiOperation(value = "用户添加")
    @ApiImplicitParam(name = "user02",value = "用户实体类",dataType = "User")
    //@PutMapping("user/add")
    public ResultInfo saveUser(@RequestBody User user){
        ResultInfo resultInfo=new ResultInfo();
        userService.save(user);
        return resultInfo;
    }
    @PostMapping("user03")
    //@PutMapping("user/add")
    public ResultInfo saveUser2(@Valid User user){
        ResultInfo resultInfo=new ResultInfo();
        userService.save(user);
        return resultInfo;
    }

    @DeleteMapping("user/delete/{id}")
    public ResultInfo deleteUserById(@PathVariable Integer id){
        ResultInfo resultInfo=new ResultInfo();
        try {
            userService.delete(id);
        }catch (ParamsException e){
            resultInfo.setCode(e.getCode());
            resultInfo.setMsg("用户删除异常");
            e.printStackTrace();
        }
        return resultInfo;
    }
    @PutMapping("user/update")
    public ResultInfo updateUser(@RequestBody User user){
        ResultInfo resultInfo=new ResultInfo();
        try {
            userService.update(user);
        }catch (ParamsException e){
            resultInfo.setCode(e.getCode());
            resultInfo.setMsg("用户更新异常");
            e.printStackTrace();
        }
        return resultInfo;
    }
    @GetMapping("user/list")
    public PageInfo<User> queryUserByParams1(UserQuery userQuery){
        return userService.selectByParameters(userQuery);
    }
    @GetMapping("user/list/{username}")
    public PageInfo<User> queryUserByParams2(@PathVariable String username){
        UserQuery userQuery=new UserQuery();
        userQuery.setUsername(username);
        return userService.selectByParameters(userQuery);
    }
    @PutMapping("user/list1")
    public PageInfo<User> queryUserByParams(@RequestBody UserQuery userQuery){
        return userService.selectByParameters(userQuery);
    }

}
