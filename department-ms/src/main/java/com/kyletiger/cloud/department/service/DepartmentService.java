package com.kyletiger.cloud.department.service;

import com.google.common.base.Preconditions;
import com.kyletiger.cloud.department.entity.Department;
import com.kyletiger.cloud.department.mapper.DepartmentCrudMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DepartmentService {
    @Autowired
    private DepartmentCrudMapper departmentCrudMapper;

    public List<Department> findAll() {
        return departmentCrudMapper.findAll();
    }

    public Department findOne(Long id) {
        Preconditions.checkNotNull(id, "'id' should not be NULL");

        return departmentCrudMapper.findOneById(id);
    }

    public Department findOne(String name) {
        Preconditions.checkNotNull(name, "'name' should not be NULL");

        return departmentCrudMapper.findOneByName(name);
    }

    public void insert(Department Department) throws Exception {
        Preconditions.checkNotNull(Department, "'Department' should not be NULL");

        if (findOne(Department.getId()) != null || findOne(Department.getName()) != null) {
            throw new Exception("Existing Department !");
        }

        departmentCrudMapper.insert(Department);
    }

    public void update(Long id, Department Department) throws Exception {
        Preconditions.checkNotNull(id, "'id' should not be NULL");
        Preconditions.checkNotNull(Department, "'Department' should not be NULL");

        Department existingDepartment;
        if ((existingDepartment = findOne(id)) == null) {
            throw new Exception("Un-existing Department !");
        }

        existingDepartment.setName(Department.getName());
        existingDepartment.setName(Department.getLocation());

        departmentCrudMapper.update(existingDepartment);
    }

    public void delete(Long id) throws Exception {
        Preconditions.checkNotNull(id, "'id' should not be NULL");

        if (findOne(id) == null) {
            throw new Exception("Un-existing Department !");
        }

        departmentCrudMapper.delete(id);
    }
}
