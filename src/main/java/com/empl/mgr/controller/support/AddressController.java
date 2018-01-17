package com.empl.mgr.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empl.mgr.service.AddressService;
import com.empl.mgr.support.JSONReturn;

@Scope
@Controller
public class AddressController {

	@Autowired
	private AddressService addressService;

	@ResponseBody
	@RequestMapping(value = "findProvinceAll")
	public JSONReturn findProvinceAll() {
		return addressService.findProvinceAll();
	}

	@ResponseBody
	@RequestMapping(value = "findCityByProvinceId")
	public JSONReturn findCityByProvinceId(long provinceId) {
		return addressService.findCityByProvinceId(provinceId);
	}

	@ResponseBody
	@RequestMapping(value = "findCountyByCityId")
	public JSONReturn findCountyByCityId(long cityId) {
		return addressService.findCountyByCityId(cityId);
	}

	@ResponseBody
	@RequestMapping(value = "findTownshipByCountyId")
	public JSONReturn findTownshipByCountyId(long countyId) {
		return addressService.findTownshipByCountyId(countyId);
	}

	@ResponseBody
	@RequestMapping(value = "findVillageByTownshipId")
	public JSONReturn findVillageByTownshipId(long vallageId) {
		return addressService.findVillageByTownshipId(vallageId);
	}

}
