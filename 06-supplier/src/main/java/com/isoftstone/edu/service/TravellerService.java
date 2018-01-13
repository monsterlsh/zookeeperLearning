package com.isoftstone.edu.service;

import com.github.pagehelper.PageInfo;
import com.isoftstone.edu.entity.TravellerEntity;

public interface TravellerService {
    public PageInfo<TravellerEntity> getTravellers(Integer pageNum,Integer pageSize,TravellerEntity travellerEntity);
    public boolean insertTraveller(TravellerEntity travellerEntity);

    public TravellerEntity gotoEditTraveller(Long travellerId);

    public boolean saveTraveller(TravellerEntity traveller);

    public boolean deleteTraveller(Long travellerId);
}
