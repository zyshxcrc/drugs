package com.drugs.manage.mapper;

import com.drugs.manage.entity.Drug;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;

@Repository
public interface DrugMapper {
    ArrayList<Drug> getDrugList(Map<String, Object> data);

    ArrayList<Drug> getAllDrugList();

    Drug getDrugById(int id);

    int getDrugCount(Map<String, Object> data);

    int insert(Drug drug);

    int updateById(Drug drug);

    int deleteById(int id);
}
