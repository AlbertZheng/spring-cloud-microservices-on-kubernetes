package com.kyletiger.cloud.employee.mapper;

import java.util.List;
import com.kyletiger.cloud.employee.entity.Employee;
import org.apache.ibatis.annotations.*;


// I prefer to use @MapperScan in Application class.
// Here just a example of @Mapper
//@Mapper
public interface EmployeeCrudMapper {
    @Select("SELECT * FROM t_employee")
    @Results({
          @Result(property = "loginPwd", column = "login_pwd"),
          @Result(property = "pwdSalt", column = "pwd_salt"),
          @Result(property = "departmentId", column = "department_id")
    })
    List<Employee> findAll();

    @Select("SELECT * FROM t_employee WHERE id = #{id}")
    @Results({
          @Result(property = "loginPwd", column = "login_pwd"),
          @Result(property = "pwdSalt", column = "pwd_salt"),
          @Result(property = "departmentId", column = "department_id")
    })
    Employee findOneById(@Param("id") Long id);

    @Select("SELECT * FROM t_employee WHERE mobile = #{mobile}")
    @Results({
            @Result(property = "loginPwd", column = "login_pwd"),
            @Result(property = "pwdSalt", column = "pwd_salt"),
            @Result(property = "departmentId", column = "department_id")
    })
    Employee findOneByMobile(@Param("mobile") String mobile);

    @Insert("INSERT INTO t_employee(name, mobile, login_pwd, pwd_salt, department_id) VALUES(#{name}, #{mobile}, #{loginPwd}, #{pwdSalt}, #{departmentId})")
    void insert(Employee employee);

    @Update("UPDATE t_employee SET name=#{name}, mobile=#{mobile}, login_pwd=#{loginPwd}, pwd_salt=#{pwdSalt}, department_id=#{departmentId} WHERE id=#{id}")
    void update(Employee employee);

    @Delete("DELETE FROM t_employee WHERE id =#{id}")
    void delete(Long id);
}
