package com.kyletiger.cloud.department.controller;

import com.kyletiger.cloud.department.entity.Department;

import com.kyletiger.cloud.department.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(value = "部门管理REST API", description = "提供部门管理功能")
@RequestMapping(value="/departments")   // 配置使下面的映射都在这个端点下
public class DepartmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService DepartmentService;

    @ApiOperation(value = "获取部门列表", notes = "")
    // Notice:
    // Swagger 2 generated "*/*", while I was expecting "application/json" for Response Content
    // Type. It is a tip that adding 'produces = "application/json"' will resolve this issue.
    //
    // I prefer to use @GetMapping rather than @RequestMapping
    @GetMapping(value = "", produces = "application/json")
    //@RequestMapping(value={""}, method=RequestMethod.GET, produces = "application/json")
    public List<Department> findAll() {
        return DepartmentService.findAll();
    }

    @ApiOperation(value = "获取部门详细信息", notes = "根据URL的id来获取部门详细信息")
    @ApiImplicitParam(name = "id", value = "部门Id", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Department findOne(@PathVariable Long id) {
        LOGGER.info("Received REST Request: Try to find out Department by id: {}", id);

        return this.DepartmentService.findOne(id);
    }

    @ApiOperation(value = "创建部门", notes = "根据Department对象创建部门")
    @ApiImplicitParam(name = "Department", value = "部门的详细实体", required = true, dataType = "Department")
    @PostMapping(value = "", produces = "application/json")
    public void create(@RequestBody Department Department) throws Exception {
        DepartmentService.insert(Department);
    }

    @ApiOperation(value = "更新部门详细信息", notes = "根据URL的Id来指定更新对象，并根据传过来的Department信息来更新部门详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "部门ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "Department", value = "部门的详细实体", required = true, dataType = "Department")
    })
    @PutMapping(value = "/{id}", produces = "application/json")
    public void update(@PathVariable Long id, @RequestBody Department Department) throws Exception {
        DepartmentService.update(id, Department);
    }

    @ApiOperation(value="删除部门", notes="根据URL的Id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "部门ID", required = true, dataType = "Long")
    @DeleteMapping(value="/{id}", produces = "application/json")
    public void delete(@PathVariable Long id) throws Exception {
        DepartmentService.delete(id);
    }
}
