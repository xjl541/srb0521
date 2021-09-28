package com.atguigu.srb.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.srb.core.mapper.DictMapper;
import com.atguigu.srb.core.pojo.dto.ExcelDictDTO;

import java.util.ArrayList;
import java.util.List;

public class ExcelDictDtoListener extends AnalysisEventListener<ExcelDictDTO> {
    private DictMapper dictMapper;
    private static final int BATCH_COUNT = 15;
    List<ExcelDictDTO> list = new ArrayList<>();



    public ExcelDictDtoListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(ExcelDictDTO excelDictDto, AnalysisContext analysisContext) {
        System.out.println("读取到了：" +excelDictDto.getName());
        list.add(excelDictDto);
        if (list.size() >= BATCH_COUNT){
            save();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        save();
        System.out.println("全部读取完成");
    }

    public void save(){
        dictMapper.insertBatch(list);
    }
}
