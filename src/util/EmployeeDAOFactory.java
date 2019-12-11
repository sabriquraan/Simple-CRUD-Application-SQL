package util;


import model.Employee;

import java.util.ArrayList;

public interface EmployeeDAOFactory {

    ArrayList<Employee> getAllEmployees();

    Employee getEmployee(int id);

    int updateEmployee(Employee employee);

    int deleteEmployee(int id);

    void deleteAllEmployees();

    void insertEmployee(Employee employee);
}
