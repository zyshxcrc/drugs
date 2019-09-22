package com.drugs.manage.controller;

import com.drugs.manage.entity.Inventory;
import com.drugs.manage.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by zyliu on 2019/9/22.
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @RequestMapping("/list")
    public ArrayList<Inventory> getInventoryList(){
        return inventoryService.getInventoryList();
    }
}
