package com.drugs.manage.mapper;

import com.drugs.manage.entity.Warehouse;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public interface WarehouseMapper {
    ArrayList<Warehouse> getWarehouseList(Map<String,Object> data);

    int batchInsert(List<Warehouse> list);

    int updateWarehouse(Warehouse warehouse);

    Warehouse getWarehouseById(int id);
}
