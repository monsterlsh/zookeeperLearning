package com.isoftstone.edu.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.edu.entity.SupplierEntity;
import com.isoftstone.edu.mapper.SupplierMapper;
import com.isoftstone.edu.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService
{
    
    @Autowired
    public SupplierMapper supplierMapper;
    
    @Override
    public PageInfo<SupplierEntity> getSuppliers(Integer pageNum, Integer pageSize, SupplierEntity supplier)
    {
    	// 引入pagerHelper分页插件
		// 在查询之前只需要调用，传入页码及每页大小
		PageHelper.startPage(pageNum, pageSize);
		// startPage后面紧跟的这个查询就是一个分页查询
        List<SupplierEntity> suppliers = supplierMapper.getSuppliers(supplier);
        // 使用PageInfo包装查询厚的结果，只需要将pageInfo交给页面就行了
     	// 封装了详细的分页信息，包括有我们查询出来的数据,传入连续显示的页数
        return new PageInfo<SupplierEntity>(suppliers);
    }
    
    @Override
    public boolean insertSupplier(SupplierEntity supplier)
    {
        boolean flag = false;
        try
        {
            
            SimpleDateFormat df2 = new SimpleDateFormat("yyMMddHHmmss");
            String sdate = df2.format(new Date());
            supplier.setSupplierId(sdate);
            supplierMapper.insertSelective(supplier);
            flag = true;
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return flag;
    }
    
    @Override
    public SupplierEntity gotoEditSupplier(String supplierId)
    {
        return supplierMapper.selectByPrimaryKey(supplierId);
    }
    
    @Override
    public boolean saveSupplier(SupplierEntity supplier)
    {
        boolean flag = false;
        try
        {
            
            supplierMapper.updateByPrimaryKeySelective(supplier);
            flag = true;
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return flag;
    }
    
    @Override
    public boolean deleteSupplier(String supplierId)
    {
        boolean flag = false;
        try
        {
            
            supplierMapper.deleteByPrimaryKey(supplierId);
            flag = true;
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return flag;
    }
    
}
