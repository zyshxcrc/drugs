package com.drugs.manage.service;

import com.drugs.manage.entity.Inventory;
import com.drugs.manage.mapper.InventoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by zyliu on 2019/9/22.
 */
@Service
public class InventoryService {
    @Autowired
    InventoryMapper inventoryMapper;

    public ArrayList<Inventory> getInventoryList(){
        return inventoryMapper.getInventoryList();
    }
}
