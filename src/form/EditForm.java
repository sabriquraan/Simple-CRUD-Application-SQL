package form;

import java.util.Scanner;
import model.Employee;
import util.DatabaseCache;
import util.DatabaseCacheEmployee;
import util.EmployeeDAO;
import util.EmployeeDAOFactory;

class EditForm {

  private DatabaseCache cache;
  private EmployeeDAOFactory employeeDAO;

  EditForm() {
    employeeDAO = EmployeeDAO.getInstance();
    cache = DatabaseCacheEmployee.getInstance();
  }

  void deleteAll() {
    employeeDAO.deleteAllEmployees();
    cache.clear();
  }

   void update() {
    int id = 0;
    Scanner in = new Scanner(System.in);
    try {
      System.out.println("Please enter the ID of employee");
      id = in.nextInt();
    } catch (Exception e) {
      System.out.println("ID must be integer (6 digit)");
      return;
    }

    try {
      System.out.println("You have to fill all fields before Update !");

      Employee employee = new Employee();
      System.out.println("Please enter the First Name of Employee");
      String firstName = in.next();
      employee.setFirstName(firstName);
      System.out.println("Please enter the Last Name of Employee");
      String lastName = in.next();
      employee.setLastName(lastName);
      System.out.println("Please enter the Age of Employee");
      int age = in.nextInt();
      employee.setAge(age);
      System.out.println("Please enter the Department of Employee");
      String department = in.next();
      employee.setDepartment(department);
      employee.setId(id);
      System.out.println("Please enter the salary of Employee");
      int salary = in.nextInt();
      employee.setSalary(salary);

      System.out.println("Please enter the Phone Number of Employee(must be 10 digit)");
      String phoneNumber = in.next();
      employee.setPhoneNumber(phoneNumber);
      if (employeeDAO.updateEmployee(employee) == 1) {
        cache.put(employee);
      }

    } catch (Exception e) {
      System.out.println("Please make sure that you insert the data correctly");
    }
  }

   void insert() {
    Scanner in = new Scanner(System.in);

    System.out.println("You have to fill all fields before insert !");

    try {

      Employee employee = new Employee();
      System.out.println("Please enter the First Name of Employee");
      String firstName = in.next();
      employee.setFirstName(firstName);
      System.out.println("Please enter the Last Name of Employee");
      String lastName = in.next();
      employee.setLastName(lastName);
      System.out.println("Please enter the Age of Employee");
      int age = in.nextInt();
      employee.setAge(age);
      System.out.println("Please enter the Department of Employee");
      String department = in.next();
      employee.setDepartment(department);
      System.out.println("Please enter the ID of Employee (must be 6 digit)");
      int id = in.nextInt();
      employee.setId(id);
      System.out.println("Please enter the salary of Employee");
      int salary = in.nextInt();
      employee.setSalary(salary);
      System.out.println("Please enter the Phone Number of Employee (must be 10 digit)");
      String phoneNumber = in.next();
      employee.setPhoneNumber(phoneNumber);

      employeeDAO.insertEmployee(employee);


    } catch (Exception e) {
      System.out.println("Please make sure that you insert the data correctly");
    }
  }

   void deleteEmployee() {
    int id = 0;
    Scanner in = new Scanner(System.in);
    try {
      System.out.println("Please enter the ID of employee");
      id = in.nextInt();
    } catch (Exception e) {
      System.out.println("ID must be integer");
      return;
    }

    if (employeeDAO.deleteEmployee(id) == 1) {
      cache.remove(id);
    }
  }

}
