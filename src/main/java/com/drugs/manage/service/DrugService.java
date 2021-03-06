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

    public ArrayList<Drug> getDrugList(int currPage, int pageSize, String drugName, String startDate, String endDate){
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
        return drugMapper.getDrugList(data);
    }

    public ArrayList<Drug> getAllDrugList(){
        return drugMapper.getAllDrugList();
    }

    public Drug getDrugById(int id){
        return drugMapper.getDrugById(id);
    }

    public int getDrugCount(String drugName, String startDate, String endDate){
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
        return drugMapper.getDrugCount(data);
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
