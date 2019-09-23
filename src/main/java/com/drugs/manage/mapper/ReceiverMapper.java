package com.drugs.manage.mapper;

import com.drugs.manage.entity.Receiver;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;

@Repository
public interface ReceiverMapper {
    ArrayList<Receiver> getReceiverList(Map<String, Object> data);

    Receiver getReceiverById(int id);

    int insert(Receiver receiver);

    int updateById(Receiver receiver);

    int deleteById(int id);
}
