package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

class ConnectionPool {

    private String driverName;
    private String url;
    private String username;
    private String password;
    private final int MAX_POOL_SIZE = 20;
    private ArrayList <Connection> connectionPool = new ArrayList<>();

    ConnectionPool() {
        this.url = Constants.URL.getConfigure();
        this.username = Constants.USERNAME.getConfigure();
        this.password = Constants.PASSWORD.getConfigure();
        this.driverName = Constants.DRIVER.getConfigure();

        while (!checkIfConnectionPoolIsFull()) {
            Connection connection = createNewConnectionForPool();
            if(connection!=null)
                connectionPool.add(createNewConnectionForPool());
            else {
                System.out.println("Please run MySql server and make sure that the username and the password is correct and try again(connection pool)");
                break;
            }
        }
    }

    private synchronized boolean checkIfConnectionPoolIsFull() {
        return connectionPool.size() > MAX_POOL_SIZE;
    }

    private Connection createNewConnectionForPool() {
        Connection connection;
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            return null;
        }

        return connection;
    }

    synchronized Connection getConnectionFromPool() {
        Connection connection = null;

        if (connectionPool.size() > 0) {
            connection = connectionPool.get(0);
            connectionPool.remove(0);
        }
        return connection;
    }

    synchronized void returnConnectionToPool(Connection connection) {
        connectionPool.add(connection);
    }
}