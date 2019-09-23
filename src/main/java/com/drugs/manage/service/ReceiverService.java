package com.drugs.manage.service;

import com.drugs.manage.entity.Receiver;
import com.drugs.manage.mapper.ReceiverMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReceiverService {
    @Autowired
    ReceiverMapper receiverMapper;

    public ArrayList<Receiver> getReceiverList(int currPage, int pageSize){
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currIndex", (currPage-1)*pageSize);
        data.put("pageSize", pageSize);
        return receiverMapper.getReceiverList(data);
    }

    public Receiver getReceiverById(int id){
        return receiverMapper.getReceiverById(id);
    }

    @Transactional
    public int insert(Receiver receiver){
        return receiverMapper.insert(receiver);
    }

    @Transactional
    public int updateById(Receiver receiver){
        return receiverMapper.updateById(receiver);
    }

    @Transactional
    public int deleteById(int id){
        return receiverMapper.deleteById(id);
    }
}
