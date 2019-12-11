package util;

import java.util.HashMap;
import model.Employee;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.stream.Collectors;


public class DatabaseCacheEmployee extends DatabaseCache {
    private Map <Employee,Integer> employees;
    private final int MAX_SIZE = 75;
    private final int RELEASE_LIMIT = 65;

    private static  DatabaseCacheEmployee instance = null;

    public static DatabaseCacheEmployee getInstance() {
        if ( instance == null) {
            instance = new DatabaseCacheEmployee();
        }
        return instance;
    }

    // make the constructor private so that we can't create objects of this class from outside
    private DatabaseCacheEmployee(){
        employees = new WeakHashMap<>();
    }

    public void put(Object o){
        Employee employee=(Employee)o;
        if (employees.get(employee) != null) {
            employees.replace(employee, employees.get(employee) + 1);
        }else if(employees.size()<MAX_SIZE) {
                employees.put(employee, 1);
        }else
            // if the cache is full , remove employees who have the least number of use
            removeLastTenEmployees();
    }

    public Employee get(Object o){
        int id= (int) o;
        for(Map.Entry<Employee,Integer> entry : employees.entrySet()) {
            if(entry.getKey().getId()==id) {
                employees.replace(entry.getKey(),entry.getValue()+1);
                return entry.getKey();
            }
        }
        return null;
    }

    private void removeLastTenEmployees(){

        if(employees.size()<MAX_SIZE)
            return;

        Map<Employee, Integer> sortedMap =
                employees.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));

        // remove employees who have the least number of use from the cache (Least 10 employees)
        for(Map.Entry<Employee,Integer> entry : sortedMap.entrySet()) {
            if(employees.size()>=RELEASE_LIMIT)
                employees.remove(entry.getKey());
            else
                break;
        }
    }

    public void clear(){
        employees.clear();
    }

    public void remove(Object o){
        int id= (int) o;
        for(Map.Entry<Employee,Integer> entry : employees.entrySet()) {
            if(entry.getKey().getId()==id) {
                employees.remove(entry.getKey());
                break;
            }
        }
    }



}
