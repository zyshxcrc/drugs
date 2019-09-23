package com.drugs.manage.controller;

import com.drugs.manage.entity.Inventory;
import com.drugs.manage.entity.ResultData;
import com.drugs.manage.service.InventoryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyliu on 2019/9/22.
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    /**
     * 分页查询库存列表
     * @param currPage 当前页
     * @param pageSize 每页显示数
     * @return 库存列表
     */
    @RequestMapping("/list")
    public ResultData getInventoryList(@RequestParam("currentPage") int currPage, @RequestParam("pageSize") int pageSize){
        try {
            ArrayList<Inventory> list = inventoryService.getInventoryList(currPage,pageSize);
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

    /**
     * 入库数据批量新增
     * @param list 新增列表
     * @return 1成功
     */
    @RequestMapping("/batchInsert")
    @ResponseBody
    public ResultData batchInsert(@RequestBody List<Inventory> list){
        System.out.println(list);
        try {
            this.inventoryService.batchInsert(list);
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
