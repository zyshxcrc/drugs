package com.drugs.manage.service;

import com.drugs.manage.entity.Warehouse;
import com.drugs.manage.mapper.WarehouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WarehouseService {
    @Autowired
    WarehouseMapper warehouseMapper;

    public ArrayList<Warehouse> getWarehouseList(int currPage, int pageSize, String drugName, String startDate, String endDate){
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currIndex", (currPage-1)*pageSize);
        data.put("pageSize", pageSize);

        if(drugName!=null&&!drugName.equals("")){
            data.put("drugName", drugName);
        }
        if(startDate!=null&&!startDate.equals("")){
            data.put("startDate", startDate);
        }
        if(endDate!=null&&!endDate.equals("")){
            data.put("endDate", endDate);
        }
        return warehouseMapper.getWarehouseList(data);
    }

    public int getWarehouseCount(String drugName, String startDate, String endDate){
        Map<String, Object> data = new HashMap<String, Object>();

        if(drugName!=null&&!drugName.equals("")){
            data.put("drugName", drugName);
        }
        if(startDate!=null&&!startDate.equals("")){
            data.put("startDate", startDate);
        }
        if(endDate!=null&&!endDate.equals("")){
            data.put("endDate", endDate);
        }
        return warehouseMapper.getWarehouseCount(data);
    }

    public Warehouse getWarehouseById(int id){
        return warehouseMapper.getWarehouseById(id);
    }

    @Transactional
    public int batchInsert(List<Warehouse> list){
        return warehouseMapper.batchInsert(list);
    }

    @Transactional
    public int updateWarehouse(Warehouse warehouse){
        return  warehouseMapper.updateWarehouse(warehouse);
    }

    @Transactional
    public int batchDelete(List<Integer> list){
        return warehouseMapper.batchDelete(list);
    }

    @Transactional
    public int deleteById(int id){
        return warehouseMapper.deleteById(id);
    }
}
