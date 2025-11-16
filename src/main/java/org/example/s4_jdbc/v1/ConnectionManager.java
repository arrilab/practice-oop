package org.example.s4_jdbc.v1;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    public static final String DB_DRIVER = "org.h2.Driver";
    public static final String DB_URL = "jdbc:h2:mem:test";
    public static final int MAX_POOL_SIZE = 10;
    public static final int MIN_IDLE_POOL_SIZE = 1;

    private static final DataSource ds;

    static {
        var dataSource = new HikariDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setJdbcUrl(DB_URL);
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        // 커넥션 풀
        dataSource.setMaximumPoolSize(MAX_POOL_SIZE);
        dataSource.setMinimumIdle(MIN_IDLE_POOL_SIZE);

        ds = dataSource;
    }

    public static Connection getConnection() {
        try {
            Class.forName("org.h2.Driver");
            return ds.getConnection();
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static DataSource getDataSource() {
        return ds;
    }
}
