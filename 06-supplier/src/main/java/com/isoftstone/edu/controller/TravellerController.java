package com.isoftstone.edu.controller;

import com.github.pagehelper.PageInfo;
import com.isoftstone.edu.common.model.ResultDTO;
import com.isoftstone.edu.entity.SupplierEntity;
import com.isoftstone.edu.entity.TravellerEntity;
import com.isoftstone.edu.service.TravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TravellerController {

    @Autowired
    private TravellerService travellerService;

    @RequestMapping(value = "datatablesTraveller" )
    public String datatables() {
        return "datatablesTraveller";
    }

    @RequestMapping(value = "indexTraveller")
    public String index() {
        return "indexTraveller";
    }

    /**
     *  分页查询
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/travellers")
    @ResponseBody
    public ResultDTO getSuppliers(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            TravellerEntity travellerEntity) {
        PageInfo<TravellerEntity> travellers = travellerService.getTravellers(page, rows, travellerEntity);
        return new ResultDTO(travellers, true, "查询成功");
    }

    @RequestMapping(value = "/gotoTravellerRegister")
    public String gotoRegister() {
        return "registerTraveller";
    }

    @RequestMapping(value = "/saveTravellerRegister", method= RequestMethod.POST)
    public String register(TravellerEntity travellerEntity) {
        boolean flag =travellerService.insertTraveller(travellerEntity); //.insertTraveller()//.insertSupplier(supplier);
        if(flag) {
            System.out.println("success");
            return "indexTraveller";
        } else {
            return "registerTraveller";
        }
    }

    @RequestMapping(value = "/gotoTravellerEdit")
    public String gotoEdit(Model model, Long travellerId) {
         TravellerEntity travellerEntity = travellerService.gotoEditTraveller(travellerId);
        model.addAttribute("traveller",travellerEntity);
        return "editTraveller";
    }

    @RequestMapping(value = "/saveTraveller", method=RequestMethod.POST)
    public String saveSupplier(TravellerEntity travellerEntity) {
        travellerService.saveTraveller(travellerEntity);
        return "indexTraveller";
    }

    @RequestMapping(value = "/deleteTraveller")
    @ResponseBody
    public ResultDTO deleteSupplier(Long travellerId) {
        boolean flag = travellerService.deleteTraveller(travellerId);
        if(flag) {
            return new ResultDTO(null, true, "删除成功！");
        } else {
            return new ResultDTO(null, true, "删除失败！");
        }
    }

}
