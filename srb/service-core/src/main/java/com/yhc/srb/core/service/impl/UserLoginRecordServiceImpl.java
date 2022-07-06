package com.yhc.srb.core.service.impl;

import com.yhc.srb.core.pojo.entity.UserLoginRecord;
import com.yhc.srb.core.mapper.UserLoginRecordMapper;
import com.yhc.srb.core.service.UserLoginRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录记录表 服务实现类
 * </p>
 *
 * @author yhc
 * @since 2022-07-06
 */
@Service
public class UserLoginRecordServiceImpl extends ServiceImpl<UserLoginRecordMapper, UserLoginRecord> implements UserLoginRecordService {

}
