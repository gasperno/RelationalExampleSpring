package com.company.enterprise.dao;

/**
 * Created by ssubraveti on 6/11/16.
 */
import com.company.enterprise.Employee;

import java.util.List;

public interface EmployeeDAO {
    void insert(Employee employee);
    Employee findById(int id);
    List<Employee> findAll();
    String findNameById(int id);
}
