package com.llg.mapper;

import com.llg.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 李龙
 * @version 1.0
 */
@Mapper
public interface DeptMapper {

    @Select("select * from tlias.dept")
    List<Dept> list();

    @Delete("delete from tlias.dept where id=#{id}")
    void delete(Integer id);

    @Insert("insert into tlias.dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Update("update tlias.dept set dept.name=#{name} where id=#{id}")
    void update(Dept dept);

    @Select("select * from tlias.dept where id=#{id}")
    Dept findById(Integer id);
}
