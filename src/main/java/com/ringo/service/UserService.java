package com.ringo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ringo.dao.UserDao;
import com.ringo.utils.AssetUtil;
import com.ringo.vo.User;
import com.ringo.vo.UserQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserDao userDao;

    //与xml中的name对应,key与参数对应
    @Cacheable(value = "users",key = "#name")
    public User queryUserByUsername(String name) {
        return userDao.queryUserByName(name);
    }
    @Cacheable(value = "users",key = "#id")
    public User queryUserById(Integer id) {
        AssetUtil.isTrue(true,"异常的测试");
        return userDao.queryUserById(id);
    }

    public void save(User user){
        AssetUtil.isTrue(StringUtils.isBlank(user.getUsername()),"用户名不能为空！");
        AssetUtil.isTrue(StringUtils.isBlank(user.getPasswd()),"用户密码不能为空！");
        AssetUtil.isTrue(null!=userDao.queryUserByName(user.getUsername()),"该用户已经存在");
        AssetUtil.isTrue(userDao.save(user)<1,"用户添加失败");

    };
    //修改自己的信息
    @CacheEvict(value = "users",key = "#user.id")
    public void update(User user){
        AssetUtil.isTrue(StringUtils.isBlank(user.getUsername()),"用户名不能为空！");
        AssetUtil.isTrue(StringUtils.isBlank(user.getPasswd()),"用户名密码不能为空！");
        AssetUtil.isTrue(null==userDao.queryUserById(user.getId()),"该用户不存在");
        //用户名唯一值校验
        User temp=userDao.queryUserByName(user.getUsername());
        AssetUtil.isTrue(null!=temp&&(temp.getId()==user.getId()),"用户已存在");
        AssetUtil.isTrue(userDao.update(user)<1,"用户修改失败");
    };

    //多条件查询有分页
    @Cacheable(value = "users",key = "#userQuery.username+'-'+#userQuery.pagenum+'-'+#userQuery.pagesize")
    public PageInfo<User> selectByParameters(UserQuery userQuery){
        PageHelper.startPage(userQuery.getPagenum(),userQuery.getPagesize());
        List<User> list=userDao.selectByParameters(userQuery);
        return new PageInfo<User>(list);
    };

    @Transactional(propagation = Propagation.REQUIRED)
    @CacheEvict(value = "users",allEntries = true)
    public  void delete(Integer id) {
        AssetUtil.isTrue(null==id||null==userDao.queryUserById(id),"用户不存在");
        AssetUtil.isTrue(userDao.delete(id)<1,"用户删除失败");
        int a=1/0;
    };
}
