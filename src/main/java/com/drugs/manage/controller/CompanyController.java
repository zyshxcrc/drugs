package com.drugs.manage.controller;

import com.drugs.manage.entity.Company;
import com.drugs.manage.entity.Inventory;
import com.drugs.manage.entity.ResultData;
import com.drugs.manage.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @RequestMapping("/list")
    public ResultData getInventoryList(@RequestParam("currentPage") int currPage,
                                       @RequestParam("pageSize") int pageSize,
                                       @RequestParam("companyName") String companyName){
        try {
            ArrayList<Company> list = companyService.getCompanyList(currPage,pageSize,companyName);
            int total = companyService.getCompanyCount(companyName);

            ResultData resultData = new ResultData();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("list",list);

            Map<String,Integer> pagination = new HashMap<>();
            pagination.put("total",total);
            pagination.put("pageSize",pageSize);
            pagination.put("currentPage",currPage);
            map.put("pagination",pagination);

            resultData.setResult(true);
            resultData.setValue(map);
            return resultData;
        } catch (Exception e) {
            e.printStackTrace();
            ResultData resultData = new ResultData();
            resultData.setResult(false);
            resultData.setValue(null);
            return resultData;
        }
    }

    @RequestMapping("/allList")
    public ResultData getAllCompanyList(){
        try {
            ArrayList<Company> list = companyService.getAllCompanyList();

            ResultData resultData = new ResultData();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("list",list);

            resultData.setResult(true);
            resultData.setValue(map);
            return resultData;
        } catch (Exception e) {
            e.printStackTrace();
            ResultData resultData = new ResultData();
            resultData.setResult(false);
            resultData.setValue(null);
            return resultData;
        }
    }

    @RequestMapping("getCompanyById")
    public ResultData getCompanyById(int id){
        try {
            Company company = companyService.getCompanyById(id);
            ResultData resultData = new ResultData();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("company",company);
            resultData.setResult(true);
            resultData.setValue(map);
            return resultData;
        } catch (Exception e) {
            e.printStackTrace();
            ResultData resultData = new ResultData();
            resultData.setResult(false);
            resultData.setValue(null);
            return resultData;
        }
    }

    @RequestMapping("insert")
    @ResponseBody
    public ResultData insert(@RequestBody Company company){
        try {
            companyService.insert(company);

            ResultData resultData = new ResultData();
            resultData.setResult(true);
            resultData.setValue(null);
            return resultData;
        } catch (Exception e) {
            e.printStackTrace();
            ResultData resultData = new ResultData();
            resultData.setResult(false);
            resultData.setValue(null);
            return resultData;
        }
    }

    @RequestMapping("deleteById")
    public ResultData deleteById(int id){
        try {
            companyService.deleteById(id);

            ResultData resultData = new ResultData();
            resultData.setResult(true);
            resultData.setValue(null);
            return resultData;
        } catch (Exception e) {
            e.printStackTrace();
            ResultData resultData = new ResultData();
            resultData.setResult(false);
            resultData.setValue(null);
            return resultData;
        }
    }

    @RequestMapping("updateById")
    @ResponseBody
    public ResultData updateById(@RequestBody Company company){
        try {
            companyService.updateById(company);

            ResultData resultData = new ResultData();
            resultData.setResult(true);
            resultData.setValue(null);
            return resultData;
        } catch (Exception e) {
            e.printStackTrace();
            ResultData resultData = new ResultData();
            resultData.setResult(false);
            resultData.setValue(null);
            return resultData;
        }
    }
}
