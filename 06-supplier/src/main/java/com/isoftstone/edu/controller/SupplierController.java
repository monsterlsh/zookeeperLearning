package com.isoftstone.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.isoftstone.edu.common.model.ResultDTO;
import com.isoftstone.edu.entity.SupplierEntity;
import com.isoftstone.edu.service.SupplierService;

@Controller
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
	@RequestMapping(value = "datatables")
	public String datatables() {
		return "datatables";
	}
	
	@RequestMapping(value = "index")
	public String index() {
		return "index";
	}

	/**
	 *  分页查询
	 * @param 导入jackson包
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/suppliers")
	@ResponseBody
	public ResultDTO getSuppliers(
			@RequestParam(value = "page", defaultValue = "1") Integer page, 
			@RequestParam(value = "rows", defaultValue = "10") Integer rows,
			SupplierEntity supplier) {
		PageInfo<SupplierEntity> suppliers = supplierService.getSuppliers(page, rows, supplier);
		return new ResultDTO(suppliers, true, "查询成功");
	}
	
	@RequestMapping(value = "/gotoRegister")
	public String gotoRegister() {
		return "register";
	}
	
	@RequestMapping(value = "/saveRegister", method=RequestMethod.POST)
	public String register(SupplierEntity supplier) {
		boolean flag = supplierService.insertSupplier(supplier);
		if(flag) {
			System.out.println("success");
			return "index";
		} else {
			return "register";
		}
	}
	
	@RequestMapping(value = "/gotoEdit")
	public String gotoEdit(Model model, String supplierId) {
		SupplierEntity supplier = supplierService.gotoEditSupplier(supplierId);
		model.addAttribute("supplier",supplier);
		return "editSupplier";
	}
	
	@RequestMapping(value = "/saveSupplier", method=RequestMethod.POST)
	public String saveSupplier(SupplierEntity supplier) {
		supplierService.saveSupplier(supplier);
		return "index";
	}
	
	@RequestMapping(value = "/deleteSupplier")
	@ResponseBody
	public ResultDTO deleteSupplier(String supplierId) {
		boolean flag = supplierService.deleteSupplier(supplierId);
		if(flag) {
			return new ResultDTO(null, true, "删除成功！");
		} else {
			return new ResultDTO(null, true, "删除失败！");
		}
	}

}
