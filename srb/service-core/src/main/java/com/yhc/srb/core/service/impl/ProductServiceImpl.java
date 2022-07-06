package com.yhc.srb.core.service.impl;

import com.yhc.srb.core.pojo.entity.Product;
import com.yhc.srb.core.mapper.ProductMapper;
import com.yhc.srb.core.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yhc
 * @since 2022-07-06
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
