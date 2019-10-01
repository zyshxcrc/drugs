package com.drugs.manage.mapper;

import com.drugs.manage.entity.OutOfStack;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zyliu on 2019/10/1.
 */
@Repository
public interface OutOfStackMapper {
    ArrayList<OutOfStack> getOutOfStackList(Map<String,Object> data);

    int getOutOfStackCount(Map<String,Object> data);

    int batchInsert(List<OutOfStack> list);
}
