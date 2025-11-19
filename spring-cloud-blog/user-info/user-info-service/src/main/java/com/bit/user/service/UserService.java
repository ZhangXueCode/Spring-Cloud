package com.bit.user.service;


import com.bit.user.api.pojo.UserInfoRequest;
import com.bit.user.api.pojo.UserInfoResponse;
import com.bit.user.api.pojo.UserLoginResponse;

public interface UserService {
    UserLoginResponse login(UserInfoRequest user);

    UserInfoResponse getUserInfo(Integer userId);

    UserInfoResponse selectAuthorInfoByBlogId(Integer blogId);
}
