package com.yhc.srb.core.service.impl;

import com.yhc.srb.core.pojo.entity.UserInfo;
import com.yhc.srb.core.mapper.UserInfoMapper;
import com.yhc.srb.core.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author yhc
 * @since 2022-07-06
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
