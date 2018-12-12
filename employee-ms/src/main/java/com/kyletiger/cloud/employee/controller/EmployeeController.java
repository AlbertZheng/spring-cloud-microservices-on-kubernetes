package com.kyletiger.cloud.employee.controller;

import com.google.common.base.Optional;
import com.kyletiger.cloud.employee.entity.Department;
import com.kyletiger.cloud.employee.entity.Employee;

import com.kyletiger.cloud.employee.feign.DepartmentFeignClient;
import com.kyletiger.cloud.employee.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(value = "员工管理REST API", description = "提供员工账号管理功能")
@RequestMapping(value="/employees")   // 配置使下面的映射都在/employees端点下
public class EmployeeController {
    // 注：因为Spring Cloud已经为Feign整合了Ribbon和Hystrix，因此这里,
    // 1. 不需要像使用RestTemplate那样加入``@Autowired LoadBalancerClient``来显式引入Ribbon;
    // 2. 也不需要像使用RestTemplate那样在每个@XxxMapping之前加
    //    ``@HystrixCommand(fallbackMethod = "xxxFallback")``来显式引入Hystrix和配置它
    //    的降级实现；因为Spring Cloud已经为Feign整合了Hystrix，如果要想为Feign打开Hystrix支持，
    //    只需要设置``feign.hystrix.enabled=true``。
    @Autowired
    private DepartmentFeignClient departmentFeignClient;

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "获取员工列表", notes = "")
    // Notice:
    // Swagger 2 generated "*/*", while I was expecting "application/json" for Response Content
    // Type. It is a tip that adding 'produces = "application/json"' will resolve this issue.
    //
    // I prefer to use @GetMapping rather than @RequestMapping
    @GetMapping(value = "", produces = "application/json")
    //@RequestMapping(value={""}, method=RequestMethod.GET, produces = "application/json")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @ApiOperation(value = "获取员工详细信息（注：会顺便内部调用Department微服务查询该员工的部门名称，然后一起返回。）", notes = "根据URL的id来获取员工详细信息")
    @ApiImplicitParam(name = "id", value = "员工Id", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Employee findOne(@PathVariable Long id) {
        Optional<Employee> optionalEmployee = Optional.fromNullable(this.employeeService.findOne(id));
        if (optionalEmployee.isPresent()) {
            Employee employee =  optionalEmployee.get();
            // Querying the remote Department Microservice for filling Department Name field.
            Optional<Department> optionalDepartment = Optional.fromNullable(departmentFeignClient.findOne(employee.getDepartmentId()));
            employee.setDepartmentName(optionalDepartment.or(new Department(-1L, "<Not Found>", "")).getName());
            optionalEmployee = Optional.of(employee);
        }

        return optionalEmployee.orNull();
    }

    @ApiOperation(value = "创建员工", notes = "根据Employee对象创建员工")
    @ApiImplicitParam(name = "employee", value = "员工的详细实体", required = true, dataType = "Employee")
    @PostMapping(value = "", produces = "application/json")
    public void create(@RequestBody Employee employee) throws Exception {
        employeeService.insert(employee);
    }

    @ApiOperation(value = "更新员工详细信息", notes = "根据URL的Id来指定更新对象，并根据传过来的Employee信息来更新员工详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "员工ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "employee", value = "员工的详细实体", required = true, dataType = "Employee")
    })
    @PutMapping(value = "/{id}", produces = "application/json")
    public void update(@PathVariable Long id, @RequestBody Employee employee) throws Exception {
        employeeService.update(id, employee);
    }

    @ApiOperation(value="删除员工", notes="根据URL的Id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "员工ID", required = true, dataType = "Long")
    @DeleteMapping(value="/{id}", produces = "application/json")
    public void delete(@PathVariable Long id) throws Exception {
        employeeService.delete(id);
    }
}
