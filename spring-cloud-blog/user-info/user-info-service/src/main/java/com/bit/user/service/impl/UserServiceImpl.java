package com.bit.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bit.blog.api.BlogServiceApi;
import com.bit.blog.api.pojo.BlogInfoResponse;
import com.bit.common.exception.BlogException;
import com.bit.common.pojo.Result;
import com.bit.common.utils.JWTUtils;
import com.bit.common.utils.SecurityUtil;
import com.bit.user.api.pojo.UserInfoRequest;
import com.bit.user.api.pojo.UserInfoResponse;
import com.bit.user.api.pojo.UserLoginResponse;
import com.bit.user.dataobject.UserInfo;
import com.bit.user.mapper.UserInfoMapper;
import com.bit.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private BlogServiceApi blogServiceApi;
    @Override
    public UserLoginResponse login(UserInfoRequest user) {
        //验证账号密码是否正确
        UserInfo userInfo = selectUserInfoByName(user.getUserName());
        if (userInfo==null || userInfo.getId()==null){
            throw new BlogException("用户不存在");
        }

        if (!SecurityUtil.verify(user.getPassword(),userInfo.getPassword())){
            throw new BlogException("用户密码不正确");
        }
        //账号密码正确的逻辑
        Map<String,Object> claims = new HashMap<>();
        claims.put("id", userInfo.getId());
        claims.put("name", userInfo.getUserName());

        String jwt = JWTUtils.genJwt(claims);
        return new UserLoginResponse(userInfo.getId(), jwt);
    }

    @Override
    public UserInfoResponse getUserInfo(Integer userId) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        UserInfo userInfo = selectUserInfoById(userId);
        BeanUtils.copyProperties(userInfo, userInfoResponse);
        return userInfoResponse;
    }

    @Override
    public UserInfoResponse selectAuthorInfoByBlogId(Integer blogId) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        //远程调用
        //1. 根据博客ID, 获取作者ID
        Result<BlogInfoResponse> blogDeatail = blogServiceApi.getBlogDeatail(blogId);

        //2. 根据作者ID, 获取作者信息
        if (blogDeatail == null || blogDeatail.getData() == null){
            throw new BlogException("博客不存在");
        }
        UserInfo userInfo = selectUserInfoById(blogDeatail.getData().getUserId());
        BeanUtils.copyProperties(userInfo, userInfoResponse);
        return userInfoResponse;
    }

    public UserInfo selectUserInfoByName(String userName) {
        return userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getUserName, userName).eq(UserInfo::getDeleteFlag, 0));
    }
    private UserInfo selectUserInfoById(Integer userId) {
        return userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getId, userId).eq(UserInfo::getDeleteFlag, 0));
    }

}
