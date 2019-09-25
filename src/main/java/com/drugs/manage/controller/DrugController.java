package com.drugs.manage.controller;

import com.drugs.manage.entity.Drug;
import com.drugs.manage.entity.ResultData;
import com.drugs.manage.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("drug")
public class DrugController {
    @Autowired
    DrugService drugService;

    @RequestMapping("/list")
    public ResultData getDrugList(@RequestParam("currentPage") int currPage, @RequestParam("pageSize") int pageSize){
        try {
            ArrayList<Drug> list = drugService.getDrugList(currPage,pageSize);
            int total = drugService.getDrugCount();

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
    public ResultData insert(@RequestBody Drug drug){
        try {
            drugService.insert(drug);

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
