package com.drugs.manage.mapper;

import com.drugs.manage.entity.Inventory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zyliu on 2019/9/22.
 */
@Repository
public interface InventoryMapper {
    ArrayList<Inventory> getInventoryList(Map<String,Object> data);

    int getInventoryCount(Map<String,Object> data);

    int batchInsert(List<Inventory> list);

    int insert(Inventory inventory);

    Inventory getInventoryByCode(String drugCode);

    Inventory getInventoryByDrugId(int drugId);

    int updateInventory(@Param("warehouseNum") String warehouseNum,@Param("inventoryNum") String inventoryNum,@Param("id") int id);

    int updateInventoryOutgoing(@Param("outgoingNum") String outgoingNum, @Param("inventoryNum") String inventoryNum, @Param("id") int id);

    int batchUpdate(List<Map> list);

    int batchUpdateDelete(List<Map> list);
}
