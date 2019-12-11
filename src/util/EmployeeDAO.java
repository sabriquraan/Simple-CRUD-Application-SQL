package util;

import java.util.Scanner;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class EmployeeDAO implements EmployeeDAOFactory {
    private ConnectionPool connectionPool;
    private Connection connection;

    private static EmployeeDAO instance = null;

    public static EmployeeDAO getInstance() {
        if ( instance == null) {
            instance = new EmployeeDAO();
        }
        return instance;
    }

    private EmployeeDAO() {
        connectionPool = new ConnectionPool();
    }

    @Override
    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        Employee employee;
        try {
            connection = connectionPool.getConnectionFromPool();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");
            while(resultSet.next()){
                employee = new Employee();
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setLastName(resultSet.getString("lastName"));
                employee.setAge(resultSet.getInt("age"));
                employee.setDepartment(resultSet.getString("department"));
                employee.setId(resultSet.getInt("id"));
                employee.setSalary(resultSet.getInt("salary"));
                employee.setPhoneNumber(resultSet.getString("phoneNumber"));
                employeeList.add(employee);
            }
        } catch (Exception e) {
            System.out.println("Please run MySql server and make sure that the username and the password is correct and try again");
        } finally {
            connectionPool.returnConnectionToPool(connection);
        }
        return employeeList;
    }

    @Override
    public Employee getEmployee(int id) {
        Employee employee = null;
        try {
            connection = connectionPool.getConnectionFromPool();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee WHERE id = " + id);
            while(resultSet.next()){
                employee = new Employee();
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setLastName(resultSet.getString("lastName"));
                employee.setAge(resultSet.getInt("age"));
                employee.setDepartment(resultSet.getString("department"));
                employee.setId(resultSet.getInt("id"));
                employee.setSalary(resultSet.getInt("salary"));
                employee.setPhoneNumber(resultSet.getString("phoneNumber"));
            }
            if (employee==null)
                System.out.println("Wrong employee ID !");
        } catch (Exception e) {
            System.out.println("Please run MySql server and make sure that the username and the password is correct and try again");
        } finally {
            connectionPool.returnConnectionToPool(connection);
        }
        return employee;
    }

    @Override
    public int updateEmployee(Employee employee) {
        int result = -1;
        try {
            connection = connectionPool.getConnectionFromPool();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET firstName = ? ,lastName = ? ,salary = ? , age = ?, department = ?, phoneNumber = ? WHERE id = ?");
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setDouble(3,employee.getSalary());
            preparedStatement.setInt(4,employee.getAge());
            preparedStatement.setString(5,employee.getDepartment());
            preparedStatement.setString(6,employee.getPhoneNumber());
            preparedStatement.setInt(7,employee.getId());
            result = preparedStatement.executeUpdate();
            if (result==1)
               System.out.println("Updated successfully !");
            else
                System.out.println("Wrong employee ID !");
        } catch (Exception e) {
            System.out.println("Please run MySql server and make sure that the username and the password is correct and try again");
        } finally {
            connectionPool.returnConnectionToPool(connection);
        }
        return result;
    }

    private int validateId(int id){
        int validID=id;
        if (validID <0)
        {
            System.out.println("Incorrect ID!! Please enter the id again ");
            Scanner in = new Scanner(System.in);
            validID=in.nextInt();
            return validateId(validID);

        }
        return id;

    }

    @Override
    public int deleteEmployee(int id) {
        id=validateId(id);
        int result=-1;
        try {
            connection = connectionPool.getConnectionFromPool();
            Statement statement = connection.createStatement();
            result = statement.executeUpdate("DELETE FROM employee where id = " + id);
            if (result==1)
               System.out.println("Deleted successfully !");
            else
                System.out.println("Wrong employee ID !");

        } catch (Exception e) {
           System.out.println("Please run MySql server and make sure that the username and the password is correct and try again");
        } finally {
            connectionPool.returnConnectionToPool(connection);
        }
        return result;
    }

    @Override
    public void deleteAllEmployees() {
        try {
            connection = connectionPool.getConnectionFromPool();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM employee");
        } catch (Exception e) {
           System.out.println("Please run MySql server and make sure that the username and the password is correct and try again");
        } finally {
            connectionPool.returnConnectionToPool(connection);
        }
    }

    @Override
    public void insertEmployee(Employee employee) {
        try {
            connection = connectionPool.getConnectionFromPool();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee "
                + "(firstName,lastName ,salary , age , department, phoneNumber,id) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setDouble(3,employee.getSalary());
            preparedStatement.setInt(4,employee.getAge());
            preparedStatement.setString(5,employee.getDepartment());
            preparedStatement.setString(6,employee.getPhoneNumber());
            preparedStatement.setInt(7, employee.getId());
            preparedStatement.executeUpdate();
            System.out.println("Inserted successfully !");
        } catch (Exception e) {
            System.out.println("Please run MySql server and make sure that the username and the password is correct and try again");
        } finally {
            connectionPool.returnConnectionToPool(connection);
        }
    }
}
