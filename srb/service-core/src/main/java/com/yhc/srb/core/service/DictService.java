package com.yhc.srb.core.service;

import com.yhc.srb.core.pojo.dto.ExcelDictDTO;
import com.yhc.srb.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author yhc
 * @since 2022-07-06
 */
public interface DictService extends IService<Dict> {
    void importData(InputStream inputStream);
    List<ExcelDictDTO> listDictData();
    List<Dict> listByParentId(Long parentId);

    List<Dict> findByDictCode(String dictCode);

    String getNameByParentDictCodeAndValue(String dictCode, Integer value);
}
