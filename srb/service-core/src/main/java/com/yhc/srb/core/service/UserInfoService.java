package com.yhc.srb.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhc.srb.core.pojo.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yhc.srb.core.pojo.query.UserInfoQuery;
import com.yhc.srb.core.pojo.vo.LoginVO;
import com.yhc.srb.core.pojo.vo.RegisterVO;
import com.yhc.srb.core.pojo.vo.UserInfoVO;

/**
 * <p>
 * 用户基本信息 服务类
 * </p>
 *
 * @author yhc
 * @since 2022-07-06
 */
public interface UserInfoService extends IService<UserInfo> {

     UserInfoVO login(LoginVO loginVO, String ip);

    void register(RegisterVO registerVO);

    IPage<UserInfo> listPage(Page<UserInfo> pageParam, UserInfoQuery userInfoQuery);

    void lock(Long id,Integer status);

    boolean checkMobile(String mobile);
}
