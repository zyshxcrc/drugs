package com.drugs.manage.mapper;

import com.drugs.manage.entity.Company;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;

@Repository
public interface CompanyMapper {
    ArrayList<Company> getCompanyList(Map<String,Object> data);

    ArrayList<Company> getAllCompanyList();

    int getCompanyCount(@Param("companyName") String companyName);

    Company getCompanyById(int id);

    int insert(Company company);

    int updateById(Company company);

    int deleteById(int id);
}
