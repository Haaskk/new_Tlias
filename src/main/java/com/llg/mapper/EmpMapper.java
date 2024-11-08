package com.llg.mapper;

import com.llg.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 李龙
 * @version 1.0
 */
@Mapper
public interface EmpMapper {

    /**
     * 查询总记录数
     * @return
     */
//    @Select("select count(*) from tlias.emp")
//    public Integer total();

    /**
     * 查询emp列表并返回
     *
     * @param start
     * @param pageSize
     * @return
     */
//    @Select("select * from tlias.emp limit #{start},#{pageSize}")
//    public List<Emp> list(@Param("start") Integer start,@Param("pageSize") Integer pageSize);


//    @Select("select * from tlias.emp")
    //使用分页查询，所以使用xml的方式去查询
    public List<Emp> list(@Param(value = "name") String name, @Param(value = "gender") Short gender, @Param(value = "begin") LocalDate begin, @Param("end") LocalDate end);


    void delete(@Param(value = "ids") List<Integer> ids);

    //根据部门id，删除部门下的员工id
    @Delete("delete from emp where dept_id=#{deptId}")
    void deleteByDeptId(Integer deptId);

    void save(Emp emp);


    @Select("select * from tlias.emp where id=#{id}")
    Emp findById(Integer id);

    void update(Emp emp);

    /**
     * 检查用户登录
     * @return
     */
    @Select("select * from tlias.emp where username=#{username} and password=#{password}")
    Emp getByUsernameAndPassword(Emp emp);
}
