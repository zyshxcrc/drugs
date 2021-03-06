package com.drugs.manage.controller;

import com.drugs.manage.entity.Drug;
import com.drugs.manage.entity.Inventory;
import com.drugs.manage.entity.ResultData;
import com.drugs.manage.service.DrugService;
import com.drugs.manage.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("drug")
public class DrugController {
    @Autowired
    DrugService drugService;

    @Autowired
    InventoryService inventoryService;

    @RequestMapping("/list")
    public ResultData getDrugList(@RequestParam("currentPage") int currPage,
                                  @RequestParam("pageSize") int pageSize,
                                  @RequestParam("drugName") String drugName,
                                  @RequestParam("startDate") String startDate,
                                  @RequestParam("endDate") String endDate){
        try {
            ArrayList<Drug> list = drugService.getDrugList(currPage,pageSize,drugName,startDate,endDate);
            int total = drugService.getDrugCount(drugName,startDate,endDate);

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

    @RequestMapping("allList")
    public ResultData getAllDrugList(){
        try {
            ArrayList<Drug> list = drugService.getAllDrugList();

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

    @RequestMapping("getDrugById")
    public ResultData getDrugById(int id){
        try {
            Drug drug = drugService.getDrugById(id);
            ResultData resultData = new ResultData();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("drug",drug);
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
    @Transactional
    public ResultData insert(@RequestBody Drug drug){
        try {
            int id = drugService.insert(drug);

            Inventory inventory = new Inventory();
            inventory.setDrugId(String.valueOf(drug.getId()));
            inventory.setInventoryNum("0");
            inventory.setWarehouseNum("0");
            inventory.setOutgoingNum("0");
            inventory.setRemarks("");
            inventory.setDrug(null);
            inventoryService.insert(inventory);

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
            drugService.deleteById(id);

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
    public ResultData updateById(@RequestBody Drug drug){
        try {
            drugService.updateById(drug);

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
