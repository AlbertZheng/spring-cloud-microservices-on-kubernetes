package com.kyletiger.cloud.department.mapper;

import java.util.List;
import com.kyletiger.cloud.department.entity.Department;
import org.apache.ibatis.annotations.*;


// I prefer to use @MapperScan in Application class.
// Here just a example of @Mapper
//@Mapper
public interface DepartmentCrudMapper {
    @Select("SELECT * FROM t_department")
    List<Department> findAll();

    @Select("SELECT * FROM t_department WHERE id = #{id}")
    Department findOneById(@Param("id") Long id);

    @Select("SELECT * FROM t_department WHERE name = #{name}")
    Department findOneByName(@Param("name") String name);

    @Insert("INSERT INTO t_department(name, location) VALUES(#{name}, #{location})")
    void insert(Department Department);

    @Update("UPDATE t_department SET name=#{name}, location=#{location} WHERE id=#{id}")
    void update(Department Department);

    @Delete("DELETE FROM t_department WHERE id =#{id}")
    void delete(Long id);
}
