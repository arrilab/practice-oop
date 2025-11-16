package org.example.s4_jdbc.v0;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class ConnectionManager {
    public static DataSource getDataSource() {
        var ds = new HikariDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setJdbcUrl("jdbc:h2:mem:test");
        ds.setUsername("sa");
        ds.setPassword("");
        return ds;
    }
}
