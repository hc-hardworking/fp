package com.yhc.srb.core.mapper;

import com.yhc.srb.core.pojo.dto.ExcelDictDTO;
import com.yhc.srb.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author yhc
 * @since 2022-07-06
 */
public interface DictMapper extends BaseMapper<Dict> {




    void insertBatch(List<ExcelDictDTO> list);
}
