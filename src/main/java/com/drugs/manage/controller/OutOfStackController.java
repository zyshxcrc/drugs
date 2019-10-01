package com.drugs.manage.controller;

import com.drugs.manage.entity.*;
import com.drugs.manage.service.InventoryService;
import com.drugs.manage.service.OutOfStackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyliu on 2019/10/1.
 */
@RestController
@RequestMapping("outOfStack")
public class OutOfStackController {
    @Autowired
    private OutOfStackService outOfStackService;

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping("list")
    public ResultData getOutOfStackList(@RequestParam("currentPage") int currPage,
                                       @RequestParam("pageSize") int pageSize,
                                       @RequestParam("drugName") String drugName,
                                       @RequestParam("startDate") String startDate,
                                       @RequestParam("endDate") String endDate){
        try {
            ArrayList<OutOfStackReceiver> list = outOfStackService.getOutOfStackList(currPage, pageSize,drugName,startDate,endDate);
            int total = outOfStackService.getOutOfStackCount(drugName,startDate,endDate);

            ResultData result = new ResultData();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("list",list);

            Map<String,Integer> pagination = new HashMap<>();
            pagination.put("total",total);
            pagination.put("pageSize",pageSize);
            pagination.put("currentPage",currPage);
            map.put("pagination",pagination);

            result.setResult(true);
            result.setValue(map);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            ResultData resultData = new ResultData();
            resultData.setResult(false);
            resultData.setValue(null);
            return resultData;
        }
    }

    @RequestMapping("getItemById")
    public ResultData getOutOfStackById(int id){
        try {
            OutOfStackReceiver outOfStackReceiver = outOfStackService.getOutOfStackById(id);
            ResultData result = new ResultData();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("outOfStackReceiver",outOfStackReceiver);

            result.setResult(true);
            result.setValue(map);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            ResultData resultData = new ResultData();
            resultData.setResult(false);
            resultData.setValue(null);
            return resultData;
        }
    }

    @RequestMapping("/batchInsert")
    @ResponseBody
    @Transactional
    public ResultData batchInsert(@RequestBody List<OutOfStack> list){
        try {
            outOfStackService.batchInsert(list);

            List<Map> newList = new ArrayList<>();
            for(OutOfStack i : list){
                Map<String,Object> map = new HashMap<>();
                map.put("outgoingNum",i.getDrawNum());
                map.put("inventoryNum",i.getDrawNum());
                map.put("drugId",i.getDrugId());
                newList.add(map);
            }
            inventoryService.batchUpdate(newList);

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

    @RequestMapping("update")
    @ResponseBody
    @Transactional
    public ResultData updateById(@RequestBody OutOfStack outOfStack){
        try {
            OutOfStackReceiver item = outOfStackService.getOutOfStackById(outOfStack.getId());
            int itemCode = item.getDrugId();
            Inventory inventory = inventoryService.getInventoryByDrugId(itemCode);
            BigInteger a = BigInteger.valueOf(Integer.parseInt(item.getDrawNum()));
            BigInteger b = BigInteger.valueOf(Integer.parseInt(inventory.getInventoryNum()));
            BigInteger c = BigInteger.valueOf(Integer.parseInt(inventory.getOutgoingNum()));
            BigInteger d = BigInteger.valueOf(Integer.parseInt(outOfStack.getDrawNum()));

            String newInventoryNum = a.subtract(d).add(b).toString();
            String newOutgoingNum = d.subtract(a).add(c).toString();

            outOfStackService.updateById(outOfStack);
            inventoryService.updateInventoryOutgoing(newOutgoingNum,newInventoryNum,inventory.getId());

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
