package com.javacodegeeks.snippets.enterprise.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.company.enterprise.EmployeeRowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import com.company.enterprise.Employee;
import com.company.enterprise.dao.EmployeeDAO;

public class EmployeeDAOImpl implements EmployeeDAO
{
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Employee employee){

        String sql = "INSERT INTO employee (ID, NAME, AGE) VALUES (?, ?, ?)";

        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, new Object[]{employee.getId(), employee.getName(), employee.getAge()});

    }

    @SuppressWarnings({ "unchecked" })
    public Employee findById(int id){

        String sql = "SELECT * FROM EMPLOYEE WHERE ID = ?";

        jdbcTemplate = new JdbcTemplate(dataSource);
//        Employee employee = (Employee) jdbcTemplate.queryForObject(
//                sql, new Object[] { id }, new EmployeeRowMapper());
        Employee employee = (Employee) jdbcTemplate.queryForObject(
                sql, new Object[] { id }, new BeanPropertyRowMapper(Employee.class));
        System.out.println("found"+employee);


        return employee;
    }

    @Override
    public List<Employee> findAll() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM EMPLOYEE";

        List<Employee> employees = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Employee employee = new Employee();
            employee.setId(Integer.parseInt(String.valueOf(row.get("ID"))));
            employee.setName((String)row.get("NAME"));
            employee.setAge(Integer.parseInt(String.valueOf(row.get("AGE"))));
            employees.add(employee);
        }

        return employees;
    }

    @Override
    public String findNameById(int id) {
        String sql = "SELECT NAME FROM EMPLOYEE WHERE ID = ?";

        String name = (String)jdbcTemplate.queryForObject(
                sql, new Object[] { id }, String.class);

        return name;
    }

}