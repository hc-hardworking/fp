package com.yhc.srb.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhc.common.exception.Assert;
import com.yhc.common.result.ResponseEnum;
import com.yhc.common.util.MD5;
import com.yhc.srb.base.util.JwtUtils;
import com.yhc.srb.core.mapper.UserAccountMapper;
import com.yhc.srb.core.mapper.UserInfoMapper;
import com.yhc.srb.core.mapper.UserLoginRecordMapper;
import com.yhc.srb.core.pojo.entity.UserAccount;
import com.yhc.srb.core.pojo.entity.UserInfo;
import com.yhc.srb.core.pojo.entity.UserLoginRecord;
import com.yhc.srb.core.pojo.query.UserInfoQuery;
import com.yhc.srb.core.pojo.vo.LoginVO;
import com.yhc.srb.core.pojo.vo.RegisterVO;
import com.yhc.srb.core.pojo.vo.UserInfoVO;
import com.yhc.srb.core.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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

    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private UserLoginRecordMapper userLoginRecordMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public UserInfoVO login(LoginVO loginVO, String ip) {

        String mobile = loginVO.getMobile();
        String password = loginVO.getPassword();
        Integer userType = loginVO.getUserType();

        //获取会员
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        queryWrapper.eq("user_type", userType);
        UserInfo userInfo = baseMapper.selectOne(queryWrapper);

        //用户不存在
        //LOGIN_MOBILE_ERROR(-208, "用户不存在"),
        Assert.notNull(userInfo, ResponseEnum.LOGIN_MOBILE_ERROR);

        //校验密码
        //LOGIN_PASSWORD_ERROR(-209, "密码不正确"),
        Assert.equals(MD5.encrypt(password), userInfo.getPassword(), ResponseEnum.LOGIN_PASSWORD_ERROR);

        //用户是否被禁用
        //LOGIN_DISABLED_ERROR(-210, "用户已被禁用"),
        Assert.equals(userInfo.getStatus(), UserInfo.STATUS_NORMAL, ResponseEnum.LOGIN_LOKED_ERROR);

        //记录登录日志
        UserLoginRecord userLoginRecord = new UserLoginRecord();
        userLoginRecord.setUserId(userInfo.getId());
        userLoginRecord.setIp(ip);
        userLoginRecordMapper.insert(userLoginRecord);

        //生成token
        String token = JwtUtils.createToken(userInfo.getId(), userInfo.getName());
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setToken(token);
        userInfoVO.setName(userInfo.getName());
        userInfoVO.setNickName(userInfo.getNickName());
        userInfoVO.setHeadImg(userInfo.getHeadImg());
        userInfoVO.setMobile(userInfo.getMobile());
        userInfoVO.setUserType(userType);

        return userInfoVO;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void register(RegisterVO registerVO) {

        // 判断用户是否被注册
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",registerVO.getMobile());
        Integer count = baseMapper.selectCount(queryWrapper);
        //MOBILE_EXIST_ERROR(-207, "手机号已被注册"),
        Assert.isTrue(count==0, ResponseEnum.MOBILE_EXIST_ERROR);

        //插入用户基本信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserType(registerVO.getUserType());
        userInfo.setNickName(registerVO.getMobile());
        userInfo.setName(registerVO.getMobile());
        userInfo.setMobile(registerVO.getMobile());
        userInfo.setPassword(MD5.encrypt(registerVO.getPassword()));
        userInfo.setStatus(userInfo.STATUS_NORMAL);

        // 设置一张静态资源服务器上的头像图片
        userInfo.setHeadImg("C:\\Users\\admin\\Desktop\\1.jpg");
        baseMapper.insert(userInfo);

        //创建会员账号
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(userInfo.getId());
        userAccountMapper.insert(userAccount);


    }

    @Override
    public IPage<UserInfo> listPage(Page<UserInfo> pageParam, UserInfoQuery userInfoQuery) {

        String mobile = userInfoQuery.getMobile();
        Integer status = userInfoQuery.getStatus();
        Integer userType = userInfoQuery.getUserType();

        QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();
        if(userInfoQuery == null){
            return baseMapper.selectPage(pageParam,null);
        }

        userInfoQueryWrapper
                .eq(StringUtils.isNotBlank(mobile), "mobile", mobile)
                .eq(status != null, "status", userInfoQuery.getStatus())
                .eq(userType != null, "user_type", userType);

        return baseMapper.selectPage(pageParam,userInfoQueryWrapper);
    }

    @Override
    public void lock(Long id, Integer status) {

        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setStatus(status);
        baseMapper.updateById(userInfo);
    }

    @Override
    public boolean checkMobile(String mobile) {
        QueryWrapper<UserInfo> queryWrapper  = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(queryWrapper);
        return count>0;
    }
}
