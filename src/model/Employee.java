package model;

import java.util.Objects;


public class Employee implements Comparable{
    private String firstName;
    private String lastName;
    private String department;
    private String phoneNumber;
    private int id;
    private int age;
    private int salary;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }




    public Employee() {
        this.firstName = "";
        this.lastName = "";
        this.department = "";
        this.phoneNumber = "";
        this.id = 0;
        this.age = 0;
        this.salary = 0;
    }

    public Employee(String firstName, String lastName, String department, String phoneNumber, int id, int age, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.age = age;
        this.salary = salary;
    }
    public Employee(String firstName, String lastName, String department, String phoneNumber, int age, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" + "firstName=" + firstName + ", lastName=" + lastName + ", department=" + department +
            ", phoneNumber=" + phoneNumber + ", id=" + id + ", age=" + age + ", salary=" + salary + '}';
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        if (Double.doubleToLongBits(this.salary) != Double.doubleToLongBits(other.salary)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.department, other.department)) {
            return false;
        }
        return Objects.equals(this.phoneNumber, other.phoneNumber);
    }


    @Override
    public int compareTo(Object o) {
        Employee employee = (Employee)o;
        return Integer.compare(this.getId(), employee.getId());


    }
}
