package com.example.demo.service;

import com.example.demo.dao.DepartmentReposity;
import com.example.demo.fact.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentReposity departmentReposity;
    public void sava(Department department){
        departmentReposity.save(department);
    }
}
