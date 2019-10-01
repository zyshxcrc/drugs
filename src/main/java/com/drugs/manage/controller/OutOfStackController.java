package com.drugs.manage.controller;

import com.drugs.manage.entity.OutOfStack;
import com.drugs.manage.entity.ResultData;
import com.drugs.manage.entity.Warehouse;
import com.drugs.manage.service.InventoryService;
import com.drugs.manage.service.OutOfStackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            ArrayList<OutOfStack> list = outOfStackService.getOutOfStackList(currPage, pageSize,drugName,startDate,endDate);
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

    @RequestMapping("/batchInsert")
    @ResponseBody
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
}
