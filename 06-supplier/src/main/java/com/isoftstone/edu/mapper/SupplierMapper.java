package com.isoftstone.edu.mapper;

import java.util.List;

import com.isoftstone.edu.entity.SupplierEntity;

public interface SupplierMapper {

	List<SupplierEntity> getSuppliers(SupplierEntity supplier);

    SupplierEntity selectByPrimaryKey(String supplierId);

    int deleteByPrimaryKey(String supplierId);
    
    int insertSelective(SupplierEntity record);

    int updateByPrimaryKeySelective(SupplierEntity record);
}