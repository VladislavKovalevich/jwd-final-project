package by.vlad.library.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

/**
 * {@code Connection} class represent thread-safe pool of connection to database
 */
public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();

    private static final String DB_PROP_PATH = "config/database.properties";
    private static final String DB_DRIVER_PROPERTY = "driver";
    private static final String DB_USER_PROPERTY = "user";
    private static final String DB_PASSWORD_PROPERTY = "password";
    private static final String DB_URL_PROPERTY = "url";
    private static final String DB_POOL_SIZE = "pool_size";

    private static final String DB_DRIVER;
    private static final String DB_USER;
    private static final String DB_PASSWORD;
    private static final String DB_URL;
    private static final int POOL_SIZE;

    private static final Properties properties = new Properties();
    private static Lock lock = new ReentrantLock();
    private static AtomicBoolean instanceIsExists = new AtomicBoolean(false);

    private static ConnectionPool instance;

    private BlockingQueue<ProxyConnection> freeConnection;
    private BlockingQueue<ProxyConnection> givenAwayConnection;

    static {
        try(InputStream in = ConnectionPool.class.getClassLoader().getResourceAsStream(DB_PROP_PATH)) {
            properties.load(in);

            DB_DRIVER = properties.getProperty(DB_DRIVER_PROPERTY);
            DB_USER = properties.getProperty(DB_USER_PROPERTY);
            DB_PASSWORD = properties.getProperty(DB_PASSWORD_PROPERTY);
            DB_URL = properties.getProperty(DB_URL_PROPERTY);
            POOL_SIZE = Integer.parseInt(properties.getProperty(DB_POOL_SIZE));

            Class.forName(DB_DRIVER);
        } catch (IOException e) {
            logger.fatal("Property file not found or has incorrect data " + DB_PROP_PATH);
            throw new RuntimeException("Property file not found or has incorrect data ", e);
        } catch (ClassNotFoundException e) {
            logger.fatal("Driver not found");
            throw new RuntimeException("Driver not found", e);
        }
    }

    /**
     * Constructor creates queue of free {@link ProxyConnection}
     */
    private ConnectionPool(){

        freeConnection = new LinkedBlockingQueue<>(POOL_SIZE);
        givenAwayConnection = new LinkedBlockingQueue<>(POOL_SIZE);

        for (int i = 0; i < POOL_SIZE; i++) {
            ProxyConnection proxyConnection;
            try {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                proxyConnection = new ProxyConnection(connection);
            } catch (SQLException e) {
                logger.error("Connection was not created");
                throw new ExceptionInInitializerError(e);
            }
            freeConnection.add(proxyConnection);
        }
    }

    /**
     * {@code getInstance} method represent thread-safe singleton
     * @return instance of {@link ConnectionPool}
     */
    public static ConnectionPool getInstance(){
        if (!instanceIsExists.get()) {
            lock.lock();
            try {
                if (instanceIsExists.compareAndSet(false, true)) {
                    instance = new ConnectionPool();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * {@code getConnection} method get free {@link Connection} from {@link ConnectionPool}
     * @return free {@link Connection} from {@link ConnectionPool}
     */
    public Connection getConnection(){
        ProxyConnection proxyConnection = null;

        try {
            proxyConnection = freeConnection.take();
            givenAwayConnection.put(proxyConnection);
        } catch (InterruptedException e) {
            logger.error("Try to get connection from pool was failed " + e);
            Thread.currentThread().interrupt();
        }

        return proxyConnection;
    }

    /**
     * {@code releaseConnection} method release {@link Connection} into {@link ConnectionPool}
     */
    public void releaseConnection(Connection connection){
        if (connection instanceof ProxyConnection) {
            try {
                givenAwayConnection.remove((ProxyConnection) connection);
                freeConnection.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                logger.error("Try to release connection from pool was failed " + e);
                Thread.currentThread().interrupt();
            }
        }else{
            logger.fatal("Unknown connection");
        }
    }

    /**
     * {@code destroyPool} method destroy {@link ConnectionPool}
     */
    public void destroyPool(){
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnection.take().reallyClose();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Try to destroy connection was failed " + e);
            } catch (SQLException e){
                logger.error("Try to destroy connection was failed " + e);
            }
        }

        deregisterDriver();
    }

    /**
     * {@code deregisterDriver} method deregister all drivers from {@link DriverManager}
     */
    public void deregisterDriver() {
        DriverManager.getDrivers().asIterator().forEachRemaining(d -> {
            try {
                DriverManager.deregisterDriver(d);
            } catch (SQLException e) {
                logger.error("Try to deregister driver " + d + " was failed");
            }
        });
    }

}
