package com.kyletiger.cloud.employee.service;

import com.google.common.base.Preconditions;
import com.kyletiger.cloud.employee.entity.Employee;
import com.kyletiger.cloud.employee.mapper.EmployeeCrudMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeCrudMapper employeeCrudMapper;

    public List<Employee> findAll() {
        return employeeCrudMapper.findAll();
    }

    public Employee findOne(Long id) {
        Preconditions.checkNotNull(id, "'id' should not be NULL");

        LOGGER.info("Try to find out this Employee by id: {}", id);

        Employee employee = employeeCrudMapper.findOneById(id);

        if (employee == null) {
            LOGGER.info("Failed to find out this Employee by id: {}", id);
        }
        else {
            LOGGER.info("Found Employee: {}", employee);
        }

        return employee;
    }

    public Employee findOne(String mobile) {
        Preconditions.checkNotNull(mobile, "'mobile' should not be NULL");

        return employeeCrudMapper.findOneByMobile(mobile);
    }

    public void insert(Employee employee) throws Exception {
        Preconditions.checkNotNull(employee, "'employee' should not be NULL");

        if (findOne(employee.getId()) != null || findOne(employee.getMobile()) != null) {
            throw new Exception("Existing Employee !");
        }

        final String salt = generatePasswordSalt();
        final String loginPwd = digestWithSalt(employee.getLoginPwd(), salt);
        employee.setLoginPwd(loginPwd);
        employee.setPwdSalt(salt);

        employeeCrudMapper.insert(employee);
    }

    public void update(Long id, Employee employee) throws Exception {
        Preconditions.checkNotNull(id, "'id' should not be NULL");
        Preconditions.checkNotNull(employee, "'employee' should not be NULL");

        Employee existingEmployee;
        if ((existingEmployee = findOne(id)) == null) {
            throw new Exception("Un-existing Employee !");
        }

        existingEmployee.setName(employee.getName());
        existingEmployee.setDepartmentId(employee.getDepartmentId());
        final String salt = generatePasswordSalt();
        final String loginPwd = digestWithSalt(employee.getLoginPwd(), salt);
        existingEmployee.setLoginPwd(loginPwd);
        existingEmployee.setPwdSalt(salt);

        employeeCrudMapper.update(existingEmployee);
    }

    public void delete(Long id) throws Exception {
        Preconditions.checkNotNull(id, "'id' should not be NULL");

        if (findOne(id) == null) {
            throw new Exception("Un-existing Employee !");
        }

        employeeCrudMapper.delete(id);
    }

    private String generatePasswordSalt() {
        return DigestUtils.sha256Hex(String.valueOf(System.nanoTime()));
    }

    private String digestWithSalt(String content, String secret) {
        String result = content;
        for (int i = 0; i < 3; i++) {
            result = DigestUtils.sha256Hex(result + secret);
        }
        return result;
    }
}
