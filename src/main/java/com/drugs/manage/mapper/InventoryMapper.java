package com.drugs.manage.mapper;

import com.drugs.manage.entity.Inventory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by zyliu on 2019/9/22.
 */
@Repository
public interface InventoryMapper {
    ArrayList<Inventory> getInventoryList();
}
