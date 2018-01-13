package com.isoftstone.edu.service;

import com.github.pagehelper.PageInfo;
import com.isoftstone.edu.entity.SupplierEntity;

public interface SupplierService {

	public PageInfo<SupplierEntity> getSuppliers(Integer pageNum, Integer pageSize, SupplierEntity suplier);
	
	public boolean insertSupplier(SupplierEntity suplier);
	
	public SupplierEntity gotoEditSupplier(String supplierId);

	public boolean saveSupplier(SupplierEntity supplier);
	
	public boolean deleteSupplier(String supplierId);
}
