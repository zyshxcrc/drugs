package com.drugs.manage.service;

import com.drugs.manage.entity.OutOfStack;
import com.drugs.manage.entity.OutOfStackReceiver;
import com.drugs.manage.mapper.OutOfStackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyliu on 2019/10/1.
 */
@Service
public class OutOfStackService {
    @Autowired
    private OutOfStackMapper outOfStackMapper;

    public ArrayList<OutOfStackReceiver> getOutOfStackList(int currPage, int pageSize, String drugName, String receiverName, String startDate, String endDate){
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currIndex", (currPage-1)*pageSize);
        data.put("pageSize", pageSize);

        if(drugName!=null&&!drugName.equals("")){
            data.put("drugName", drugName);
        }
        if(receiverName!=null&&!receiverName.equals("")){
            data.put("receiverName", receiverName);
        }
        if(startDate!=null&&!startDate.equals("")){
            data.put("startDate", startDate);
        }
        if(endDate!=null&&!endDate.equals("")){
            data.put("endDate", endDate);
        }

        return outOfStackMapper.getOutOfStackList(data);
    }

    public int getOutOfStackCount(String drugName, String receiverName, String startDate, String endDate){
        Map<String, Object> data = new HashMap<String, Object>();

        if(drugName!=null&&!drugName.equals("")){
            data.put("drugName", drugName);
        }
        if(receiverName!=null&&!receiverName.equals("")){
            data.put("receiverName", receiverName);
        }
        if(startDate!=null&&!startDate.equals("")){
            data.put("startDate", startDate);
        }
        if(endDate!=null&&!endDate.equals("")){
            data.put("endDate", endDate);
        }

        return outOfStackMapper.getOutOfStackCount(data);
    }

    public OutOfStackReceiver getOutOfStackById(int id){
        return outOfStackMapper.getOutOfStackById(id);
    }

    @Transactional
    public int batchInsert(List<OutOfStack> list){
        return outOfStackMapper.batchInsert(list);
    }

    @Transactional
    public int updateById(OutOfStack outOfStack){
        return outOfStackMapper.updateById(outOfStack);
    }

    @Transactional
    public int deleteById(int id){
        return outOfStackMapper.deleteById(id);
    }
}
