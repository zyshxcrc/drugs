package com.drugs.manage.mapper;

import com.drugs.manage.entity.Inventory;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zyliu on 2019/9/22.
 */
@Repository
public interface InventoryMapper {
    ArrayList<Inventory> getInventoryList(Map<String,Object> data);

    int batchInsert(List<Inventory> list);

    Inventory getInventoryByCode(String drugCode);

    int updateInventory(String warehouseNum,String inventoryNum,int id);

    int batchUpdate(List<Map> list);

    int batchUpdateDelete(List<Map> list);
}
