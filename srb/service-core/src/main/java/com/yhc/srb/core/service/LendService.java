package com.yhc.srb.core.service;

import com.yhc.srb.core.pojo.entity.BorrowInfo;
import com.yhc.srb.core.pojo.entity.Lend;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yhc.srb.core.pojo.vo.BorrowInfoApprovalVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标的准备表 服务类
 * </p>
 *
 * @author yhc
 * @since 2022-07-06
 */
public interface LendService extends IService<Lend> {

    void createLend(BorrowInfoApprovalVO borrowInfoApprovalVO, BorrowInfo borrowInfo);

    List<Lend> selectList();

    Map<String, Object> getLendDetail(Long id);
}
