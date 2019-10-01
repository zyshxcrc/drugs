package com.drugs.manage.service;

import com.drugs.manage.entity.Inventory;
import com.drugs.manage.mapper.InventoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyliu on 2019/9/22.
 */
@Service
public class InventoryService {
    @Autowired
    InventoryMapper inventoryMapper;

    public ArrayList<Inventory> getInventoryList(int currPage, int pageSize, String drugName){
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currIndex", (currPage-1)*pageSize);
        data.put("pageSize", pageSize);

        if(drugName!=null&&!drugName.equals("")){
            data.put("drugName", drugName);
        }
        return inventoryMapper.getInventoryList(data);
    }

    public int getInventoryCount(String drugName){
        Map<String, Object> data = new HashMap<String, Object>();

        if(drugName!=null&&!drugName.equals("")){
            data.put("drugName", drugName);
        }
        return inventoryMapper.getInventoryCount(data);
    }

    public Inventory getInventoryByCode(String drugCode){
        return inventoryMapper.getInventoryByCode(drugCode);
    }

    @Transactional
    public int batchInsert(List<Inventory> list){
        return inventoryMapper.batchInsert(list);
    }
    @Transactional
    public int updateInventory(String warehouseNum,String inventoryNum,int id){
        return inventoryMapper.updateInventory(warehouseNum, inventoryNum, id);
    }
    @Transactional
    public int batchUpdate(List<Map> list){
        return  inventoryMapper.batchUpdate(list);
    }
    @Transactional
    public int batchUpdateDelete(List<Map> list){
        return  inventoryMapper.batchUpdateDelete(list);
    }
}
