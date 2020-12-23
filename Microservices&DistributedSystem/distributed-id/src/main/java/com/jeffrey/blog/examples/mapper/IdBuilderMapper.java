package com.jeffrey.blog.examples.mapper;

import com.jeffrey.blog.examples.model.IdBuilderPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author idea
 * @Date created in 10:17 上午 2020/12/17
 */
@Mapper
public interface IdBuilderMapper {

    @Select("select * from t_id_builder_config")
    List<IdBuilderPO> selectAll();

    @Select("select * from t_id_builder_config where id=#{id} limit 1 for update")
    IdBuilderPO selectOneForUpdate(@Param("id") int id);

    @Update("UPDATE t_id_builder_config set current_threshold=#{currentThreshold},version=version+1 where id=#{id} and version=#{version}")
    Integer updateCurrentThreshold(@Param("currentThreshold") long currentThreshold, @Param("id") int id, @Param("version") int version);
}