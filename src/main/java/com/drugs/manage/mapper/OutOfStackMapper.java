package com.drugs.manage.mapper;

import com.drugs.manage.entity.OutOfStack;
import com.drugs.manage.entity.OutOfStackReceiver;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zyliu on 2019/10/1.
 */
@Repository
public interface OutOfStackMapper {
    ArrayList<OutOfStackReceiver> getOutOfStackList(Map<String,Object> data);

    int getOutOfStackCount(Map<String,Object> data);

    int batchInsert(List<OutOfStack> list);

    OutOfStackReceiver getOutOfStackById(int id);

    int updateById(OutOfStack outOfStack);

    int deleteById(int id);
}
