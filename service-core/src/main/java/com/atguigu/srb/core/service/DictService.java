package com.atguigu.srb.core.service;

import com.atguigu.srb.core.pojo.dto.ExcelDictDTO;
import com.atguigu.srb.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author Helen
 * @since 2021-09-22
 */
public interface DictService extends IService<Dict> {

    void importData(MultipartFile multipartFile);

    List<ExcelDictDTO> listDictData();

    List<Dict> ListByParentId(Long id);
}
