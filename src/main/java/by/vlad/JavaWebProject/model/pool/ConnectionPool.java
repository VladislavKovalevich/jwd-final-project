package by.vlad.JavaWebProject.model.pool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final String DB_PROP_PATH = "config/database.properties";
    private static final String DB_DRIVER_PROPERTY = "driver";
    private static final String DB_USER_PROPERTY = "user";
    private static final String DB_PASSWORD_PROPERTY = "password";
    private static final String DB_URL_PROPERTY = "url";

    private static final String DB_DRIVER;
    private static final String DB_USER;
    private static final String DB_PASSWORD;
    private static final String DB_URL;

    private static final int POOL_SIZE = 4;

    private static final Properties properties = new Properties();
    private static Lock lock = new ReentrantLock();
    private static AtomicBoolean instanceIsExists = new AtomicBoolean(false);

    private static ConnectionPool instance;

    private BlockingQueue<ProxyConnection> freeConnection = new LinkedBlockingQueue<>(8);
    private BlockingQueue<ProxyConnection> givenAwayConnection = new LinkedBlockingQueue<>(8);

    static {
        try(InputStream in = ConnectionPool.class.getClassLoader().getResourceAsStream(DB_PROP_PATH)) {
            properties.load(in);

            DB_DRIVER = properties.getProperty(DB_DRIVER_PROPERTY);
            DB_USER = properties.getProperty(DB_USER_PROPERTY);
            DB_PASSWORD = properties.getProperty(DB_PASSWORD_PROPERTY);
            DB_URL = properties.getProperty(DB_URL_PROPERTY);

            Class.forName(DB_DRIVER);
        } catch (IOException e) {
            //log
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            //log
            throw new RuntimeException(e);
        }
    }

    private ConnectionPool(){

        for (int i = 0; i < POOL_SIZE; i++) {
            ProxyConnection proxyConnection;
            try {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                proxyConnection = new ProxyConnection(connection);
            } catch (SQLException e) {
                throw new ExceptionInInitializerError(e);
            }
            freeConnection.add(proxyConnection);
        }
    }

    public static ConnectionPool getInstance(){
        if (!instanceIsExists.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection(){
        ProxyConnection proxyConnection = null;

        try {
            proxyConnection = freeConnection.take();
            givenAwayConnection.put(proxyConnection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return proxyConnection;
    }

    public void releaseConnection(Connection connection){
        //проверки на нужный коннекшн
        if (connection instanceof ProxyConnection) {
            try {
                givenAwayConnection.remove((ProxyConnection) connection);
                freeConnection.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }else{
            throw new RuntimeException("unknown connection");
        }
    }

    public void destroyPool(){
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnection.take().reallyClose();
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
              // log e.printStackTrace();
            } catch (SQLException e){
             //logger
            }
        }

        deregisterDriver();
    }

    public void deregisterDriver() {
        DriverManager.getDrivers().asIterator().forEachRemaining(d -> {
            try {
                DriverManager.deregisterDriver(d);
            } catch (SQLException e) {
                //logger;
            }
        });
    }

}
