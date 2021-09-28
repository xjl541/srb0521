package com.atguigu.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.easyexcel.dto.ExcelStudentDTO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ExcelStudentDTOListener extends AnalysisEventListener<ExcelStudentDTO> {
    @Override
    public void invoke(ExcelStudentDTO data, AnalysisContext analysisContext) {
//        log.info("解析到了一条数据：{}",data);
        System.out.println("解析到了一条数据："+data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成");
    }
}
