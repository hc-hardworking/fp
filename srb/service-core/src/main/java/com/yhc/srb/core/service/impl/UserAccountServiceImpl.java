package com.yhc.srb.core.service.impl;

import com.yhc.srb.core.pojo.entity.UserAccount;
import com.yhc.srb.core.mapper.UserAccountMapper;
import com.yhc.srb.core.service.UserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author yhc
 * @since 2022-07-06
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

}
