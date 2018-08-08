package com.packt.javaee8.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(
                name = "Employee.findByName",
                query = "select e from Employee e where e.name = :name"),
        @NamedQuery(
                name = "Employee.findAll",
                query = "select e from Employee e ")
})

@javax.persistence.Entity(name = "Employee")
public class Employee implements Entity<Long> {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name="name")
    private String name;


    @Column(name="address")
    private String address;

    @NotNull
    @Column(name="salary")
    private Double salary;

    public Employee(){}

    public Employee( String name, String address, Double salary){

        this.name = name;
        this.address = address;
        this.salary = salary;

    }

    public Employee(Long id, String name, String address, Double salary){

        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
