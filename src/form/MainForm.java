package form;

import model.Employee;
import util.DatabaseCache;
import util.DatabaseCacheEmployee;
import util.EmployeeDAO;

import java.util.ArrayList;
import java.util.Scanner;
import util.EmployeeDAOFactory;

public class MainForm {

    private DatabaseCache cache;
    private EmployeeDAOFactory employeeDAO;
    private EditForm editForm;

    private MainForm() {
        employeeDAO = EmployeeDAO.getInstance();
        cache = DatabaseCacheEmployee.getInstance();
        editForm = new EditForm();

    }

    private void retrieveEmployee() {
        int id = 0;
        Employee employee = null;
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Please enter the ID of employee");
            id = in.nextInt();
        } catch (Exception e) {
            System.out.println("ID must be integer");
        }

        // try to get the employee from the cache , if it's not exist in the cache then get it from database
        employee = (Employee) cache.get(id);

        if (employee == null) {
            employee = employeeDAO.getEmployee(id);
            if (employee != null)
                cache.put(employee);
        }

        if (employee != null) {
            String leftAlignFormat = "%-10s|%-10s|%-4d|%-13s|%-6d |%-7f |%-10s |%n";
            System.out.format(
                "+---------+----------+----+-------------+-------+-----------+------------%n");
            System.out.println(
                "FirstName |LastName |Age |Department    |ID     | salary  | Phone Number ");
            System.out.format(
                "+---------+----------+----+-------------+-------+-----------+------------%n");

            System.out.format(leftAlignFormat, employee.getFirstName(), employee.getLastName(),
                employee.getAge(),
                employee.getDepartment(), employee.getId(), employee.getSalary(),
                employee.getPhoneNumber());
            System.out.format(
                "+---------+----------+----+-------------+-------+-----------+------------%n");

        }

    }

    private void retrieveAll() {

        ArrayList<Employee> employeeList = employeeDAO.getAllEmployees();

        String leftAlignFormat = "%-10s|%-10s|%-4d|%-13s|%-6d |%-7f |%-10s |%n";
        System.out
            .format("+---------+----------+----+-------------+-------+-----------+------------%n");
        System.out
            .println("FirstName |LastName |Age |Department    |ID     | salary  | Phone Number ");
        System.out
            .format("+---------+----------+----+-------------+-------+-----------+------------%n");

        for (Employee employee : employeeList) {
            System.out.format(leftAlignFormat, employee.getFirstName(),
                employee.getLastName(),
                employee.getAge(), employee.getDepartment(),
                employee.getId(), employee.getSalary(),
                employee.getPhoneNumber());
            System.out.format(
                "+---------+----------+----+-------------+-------+-----------+------------%n");
        }


    }

    public static void main(String[] args) {
        MainForm mainForm = new MainForm();

        System.out.println("****************Welcome****************");
        Scanner in = new Scanner(System.in);
        int select = 999;

        while (select != 7) {
            System.out.println("***************************************");
            System.out.println("Please select the Operation you want to do from this menu");
            System.out.println(
                "1.Retrieve all employee.\n2.Delete all employee.\n3.Delete employee.\n4.Update employee."
                    +
                    "\n5.Insert new employee.\n6.Retrieve employee.\n7.Exit.");
            System.out.println("***************************************");

            select = in.nextInt();

            switch (select) {
                case 1:
                    mainForm.retrieveAll();
                    break;
                case 2:
                    mainForm.editForm.deleteAll();
                    break;
                case 3:
                    mainForm.editForm.deleteEmployee();
                    break;
                case 4:
                    mainForm.editForm.update();
                    break;
                case 5:
                    mainForm.editForm.insert();
                    break;
                case 6:
                    mainForm.retrieveEmployee();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("incorrect selection , you must select number 1-7");

            }

        }


    }

}

