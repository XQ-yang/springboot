package com.yang.springbootupanddown.mapper;

import com.yang.springbootupanddown.entity.FileModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 小强
* @description 针对表【tb_file】的数据库操作Mapper
* @createDate 2022-11-15 20:07:46
* @Entity com.yang.springbootupanddown.entity.FileModel
*/
@Mapper
public interface FileModelMapper extends BaseMapper<FileModel> {

}




