package com.drugs.manage.service;

import com.drugs.manage.entity.Drug;
import com.drugs.manage.mapper.DrugMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class DrugService {
    @Autowired
    DrugMapper drugMapper;

    public ArrayList<Drug> getDrugList(int currPage, int pageSize){
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currIndex", (currPage-1)*pageSize);
        data.put("pageSize", pageSize);
        return drugMapper.getDrugList(data);
    }

    public Drug getDrugById(int id){
        return drugMapper.getDrugById(id);
    }

    public int getDrugCount(){
        return drugMapper.getDrugCount();
    }

    @Transactional
    public int insert(Drug drug){
        return drugMapper.insert(drug);
    }

    @Transactional
    public int updateById(Drug drug){
        return drugMapper.updateById(drug);
    }

    @Transactional
    public int deleteById(int id){
        return drugMapper.deleteById(id);
    }
}
