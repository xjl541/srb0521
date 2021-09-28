package com.atguigu.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.atguigu.easyexcel.dto.ExcelStudentDTO;
import com.atguigu.easyexcel.listener.ExcelStudentDTOListener;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelWriteTest {
    @Test
    public void simpleWriteXlsx(){
        String fileName = "e:/excel/simpleWriteXlsx.xlsx";
        List<ExcelStudentDTO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExcelStudentDTO studentDTO = new ExcelStudentDTO();
            studentDTO.setName("xie" + i);
            studentDTO.setBirthday(new Date());
            studentDTO.setSalary(1000d*i);
            list.add(studentDTO);
        }
        EasyExcel.write(fileName, ExcelStudentDTO.class).sheet("模板").doWrite(list);
    }

    @Test
    public void simpleReadXlsx(){
        String fileName = "e:/excel/simpleWriteXlsx.xlsx";
        EasyExcel.read(fileName,ExcelStudentDTO.class,new ExcelStudentDTOListener()).sheet().doRead();
    }
}
