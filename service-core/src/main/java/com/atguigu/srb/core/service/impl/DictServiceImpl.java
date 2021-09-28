package com.atguigu.srb.core.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.srb.core.listener.ExcelDictDtoListener;
import com.atguigu.srb.core.pojo.dto.ExcelDictDTO;
import com.atguigu.srb.core.pojo.entity.Dict;
import com.atguigu.srb.core.mapper.DictMapper;
import com.atguigu.srb.core.service.DictService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2021-09-22
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    @Autowired
    DictMapper dictMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void importData(MultipartFile multipartFile) {
        try {
            EasyExcel.read(multipartFile.getInputStream(), ExcelDictDTO.class, new ExcelDictDtoListener(dictMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ExcelDictDTO> listDictData() {
        List<Dict> dicts = dictMapper.selectList(null);
        ArrayList<ExcelDictDTO> list = new ArrayList<>();
        for (Dict dict : dicts) {
            ExcelDictDTO excelDictDTO = new ExcelDictDTO();
            BeanUtils.copyProperties(dict, excelDictDTO);
            list.add(excelDictDTO);
        }
        return list;
    }

    @Override
    public List<Dict> ListByParentId(Long id) {
        List<Dict> dicts = new ArrayList<>();

        dicts = (List<Dict>) redisTemplate.opsForValue().get("srb:core:dictList:" + id);
        if (null == dicts || dicts.size() <= 0){
            dicts = baseMapper.selectList(new QueryWrapper<Dict>().eq("parent_id", id));

            dicts.forEach(dict -> {
                boolean hasChildren = this.hasChildren(dict.getId());
                dict.setHasChildren(hasChildren);
            });

            if (null !=dicts && dicts.size()>0){
                redisTemplate.opsForValue().set("srb:core:dictList:" + id,dicts);
            }
        }

        return dicts;
    }

    private boolean hasChildren(Long id) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<Dict>().eq("parent_id", id);
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count.intValue() > 0) {
            return true;
        }
        return false;
    }
}
