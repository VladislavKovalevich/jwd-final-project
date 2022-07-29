package by.vlad.library.model.pool;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPoolTest {
    private ConnectionPool pool;

    @BeforeMethod
    public void init(){
        pool = ConnectionPool.getInstance();
    }

    @Test
    public void testGetConnection(){
        Connection connection = pool.getConnection();
        Assert.assertNotNull(connection);
    }

    @Test
    public void testReleaseConnection(){
        Connection connection = pool.getConnection();
        pool.releaseConnection(connection);
    }

    @Test
    public void testReleaseConnectionException() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                "root", "root");
        pool.releaseConnection(connection);
    }

    @AfterMethod
    public void clean() {
        pool = null;
    }
}
