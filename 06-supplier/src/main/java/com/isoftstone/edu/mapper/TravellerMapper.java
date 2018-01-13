package com.isoftstone.edu.mapper;

import com.isoftstone.edu.entity.TravellerEntity;

import java.util.List;

public interface TravellerMapper {
    List<TravellerEntity> getTravellers(TravellerEntity travellerEntity);

    TravellerEntity selectByPrimaryKey(Long travellerId);

    int deleteByPrimaryKey(Long travellerId);

    int insertSelective(TravellerEntity record);

    int updateByPrimaryKeySelective(TravellerEntity record);
}
