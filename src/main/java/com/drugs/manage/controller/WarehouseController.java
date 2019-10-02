package com.drugs.manage.controller;

import com.drugs.manage.entity.Inventory;
import com.drugs.manage.entity.ResultData;
import com.drugs.manage.entity.Warehouse;
import com.drugs.manage.service.InventoryService;
import com.drugs.manage.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;
    @Autowired
    InventoryService inventoryService;

    @RequestMapping("list")
    public ResultData getInventoryList(@RequestParam("currentPage") int currPage,
                                       @RequestParam("pageSize") int pageSize,
                                       @RequestParam("drugName") String drugName,
                                       @RequestParam("startDate") String startDate,
                                       @RequestParam("endDate") String endDate){
        try {
            ArrayList<Warehouse> list = warehouseService.getWarehouseList(currPage, pageSize,drugName,startDate,endDate);
            int total = warehouseService.getWarehouseCount(drugName,startDate,endDate);

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
    public ResultData getWarehouseById(int id){
        try {
            Warehouse warehouse = warehouseService.getWarehouseById(id);

            ResultData result = new ResultData();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("warehouse",warehouse);

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

    /**
     * 入库数据批量新增
     * @param list 新增列表
     * @return 1成功
     */
    @RequestMapping("/batchInsert")
    @ResponseBody
    @Transactional
    public ResultData batchInsert(@RequestBody List<Warehouse> list){
        try {
            warehouseService.batchInsert(list);

            List<Map> newList = new ArrayList<>();
            for(Warehouse i : list){
                Map<String,Object> map = new HashMap<>();
                map.put("warehouseNum",i.getDrawNum());
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

    /**
     * 编辑
     * @param warehouse 修改条目
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    @Transactional
    public ResultData updateWarehouse(@RequestBody Warehouse warehouse){
        try {
            Warehouse item = warehouseService.getWarehouseById(warehouse.getId());
            int itemCode = item.getDrugId();
            Inventory inventory = inventoryService.getInventoryByDrugId(itemCode);
            BigInteger a = BigInteger.valueOf(Integer.parseInt(item.getDrawNum()));
            BigInteger b = BigInteger.valueOf(Integer.parseInt(inventory.getInventoryNum()));
            BigInteger c = BigInteger.valueOf(Integer.parseInt(inventory.getWarehouseNum()));
            BigInteger d = BigInteger.valueOf(Integer.parseInt(warehouse.getDrawNum()));

            String newInventoryNum = d.subtract(a).add(b).toString();
            String newWarehouseNum = d.subtract(a).add(c).toString();

            warehouseService.updateWarehouse(warehouse);
            inventoryService.updateInventory(newWarehouseNum,newInventoryNum,inventory.getId());

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

    @RequestMapping("/batchDelete")
    public ResultData batchDelete(@RequestBody List<Warehouse> list){
        try {
            List<Integer> idList = list.stream().map(Warehouse::getId).collect(Collectors.toList());
            warehouseService.batchDelete(idList);

            List<Map> inList = new ArrayList<>();
            for(Warehouse i : list){
                Map<String,String> map = new HashMap<>();
                map.put("warehouseNum",i.getDrawNum());
                map.put("inventoryNum",i.getDrawNum());
                map.put("drugCode",i.getDrugCode());
                inList.add(map);
            }
            inventoryService.batchUpdateDelete(inList);

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
    @Transactional
    public ResultData deleteById(int id){
        try {
            Warehouse item = warehouseService.getWarehouseById(id);
            int itemCode = item.getDrugId();
            Inventory inventory = inventoryService.getInventoryByDrugId(itemCode);
            BigInteger a = BigInteger.valueOf(Integer.parseInt(item.getDrawNum()));
            BigInteger b = BigInteger.valueOf(Integer.parseInt(inventory.getInventoryNum()));
            BigInteger c = BigInteger.valueOf(Integer.parseInt(inventory.getWarehouseNum()));
            BigInteger d = BigInteger.valueOf(0);

            String newInventoryNum = d.subtract(a).add(b).toString();
            String newWarehouseNum = d.subtract(a).add(c).toString();

            warehouseService.deleteById(id);
            inventoryService.updateInventory(newWarehouseNum,newInventoryNum,inventory.getId());

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
