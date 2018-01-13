package com.isoftstone.edu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isoftstone.edu.entity.TravellerEntity;
import com.isoftstone.edu.mapper.TravellerMapper;
import com.isoftstone.edu.service.TravellerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.lang.Long;


public class TravellerServiceImpl implements TravellerService {

    @Autowired private TravellerMapper travellerMapper;

    @Override
    public PageInfo<TravellerEntity> getTravellers(Integer pageNum, Integer pageSize, TravellerEntity travellerEntity) {
        // 引入pagerHelper分页插件
        // 在查询之前只需要调用，传入页码及每页大小
        PageHelper.startPage(pageNum, pageSize);
        // startPage后面紧跟的这个查询就是一个分页查询
        List<TravellerEntity> travellers = travellerMapper.getTravellers(travellerEntity);
        // 使用PageInfo包装查询厚的结果，只需要将pageInfo交给页面就行了
        // 封装了详细的分页信息，包括有我们查询出来的数据,传入连续显示的页数
        return new PageInfo<TravellerEntity>(travellers);
    }

    @Override
    public boolean insertTraveller(TravellerEntity travellerEntity) {
        boolean flag = false;
        try
        {

            SimpleDateFormat df2 = new SimpleDateFormat("yyMMddHHmmss");
            String sdate = df2.format(new Date());
            Long convert = null;
            String  next = sdate.trim();
            convert = Long.valueOf(next);
            travellerEntity.setTravellerId(convert);
            travellerMapper.insertSelective(travellerEntity);
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
    public TravellerEntity gotoEditTraveller(Long travellerId) {
        return travellerMapper.selectByPrimaryKey(travellerId);
    }

    @Override
    public boolean saveTraveller(TravellerEntity traveller) {
        boolean flag = false;
        try
        {
            travellerMapper.updateByPrimaryKeySelective(traveller);
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
    public boolean deleteTraveller(Long travellerId) {
        boolean flag = false;
        try
        {
//            Long convert= null;
//            String next = travellerId.trim();
//            convert = Long.valueOf(next);
            travellerMapper.deleteByPrimaryKey(travellerId);
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
