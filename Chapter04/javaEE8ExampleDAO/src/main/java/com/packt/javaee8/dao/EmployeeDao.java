package com.packt.javaee8.dao;

import com.packt.javaee8.entity.Employee;

import javax.ejb.Stateless;
import java.util.Collections;
import java.util.List;

@Stateless
public class EmployeeDao extends AbstractDao <Employee> {

    public List<Employee> findByName(String name ){

        return this.listWithNamedQuery("Employee.findByName",
                                       Collections.singletonMap( "name", name ) );

    }

    public List<Employee> findAll(){

        return this.listWithNamedQuery("Employee.findAll",
                                        Collections.emptyMap());

    }

}
