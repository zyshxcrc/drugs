package com.drugs.manage.service;

import com.drugs.manage.entity.Company;
import com.drugs.manage.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class CompanyService {
    @Autowired
    CompanyMapper companyMapper;

    public ArrayList<Company> getCompanyList(int currPage, int pageSize){
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currIndex", (currPage-1)*pageSize);
        data.put("pageSize", pageSize);
        return companyMapper.getCompanyList(data);
    }

    public ArrayList<Company> getAllCompanyList(){
        return companyMapper.getAllCompanyList();
    }

    public Company getCompanyById(int id){
        return companyMapper.getCompanyById(id);
    }

    public int getCompanyCount(){
        return companyMapper.getCompanyCount();
    }

    @Transactional
    public int insert(Company company){
        return companyMapper.insert(company);
    }

    @Transactional
    public int updateById(Company company){
        return companyMapper.updateById(company);
    }

    @Transactional
    public int deleteById(int id){
        return companyMapper.deleteById(id);
    }
}
