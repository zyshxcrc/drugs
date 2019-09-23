package com.drugs.manage.controller;

import com.drugs.manage.entity.Company;
import com.drugs.manage.entity.Receiver;
import com.drugs.manage.entity.ResultData;
import com.drugs.manage.service.CompanyService;
import com.drugs.manage.service.ReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("receiver")
public class ReceiverController {
    @Autowired
    ReceiverService receiverService;

    @RequestMapping("/list")
    public ResultData getReceiverList(@RequestParam("currentPage") int currPage, @RequestParam("pageSize") int pageSize){
        try {
            ArrayList<Receiver> list = receiverService.getReceiverList(currPage,pageSize);
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

    @RequestMapping("getReceiverById")
    public ResultData getReceiverById(int id){
        try {
            Receiver receiver = receiverService.getReceiverById(id);
            ResultData resultData = new ResultData();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("receiver",receiver);
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
    public ResultData insert(@RequestBody Receiver receiver){
        try {
            receiverService.insert(receiver);

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
            receiverService.deleteById(id);

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
    public ResultData updateById(@RequestBody Receiver receiver){
        try {
            receiverService.updateById(receiver);

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
