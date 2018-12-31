/**
 * @(#)UserService.java, 2018-12-31.
 *
 * Copyright 2018 Stalary.
 */
package com.stalary.pf.outside.service;

import com.stalary.pf.outside.client.UserClient;
import com.stalary.pf.outside.data.Constant;
import com.stalary.pf.outside.data.ResponseMessage;
import com.stalary.pf.outside.data.UploadAvatar;
import com.stalary.pf.outside.holder.UserHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * UserService
 *
 * @author lirongqian
 * @since 2018/12/31
 */
@Service
public class UserService {

    @Resource
    private UserClient userClient;

    @Resource
    private QiNiuService qiNiuService;

    public boolean uploadAvatar(MultipartFile avatar) {
        String name = Constant.getKey(Constant.USER_AVATAR, String.valueOf(UserHolder.get()));
        String url = qiNiuService.uploadPicture(avatar, name);
        return userClient.uploadAvatar(new UploadAvatar(UserHolder.get(), url)).isSuccess();
    }

}