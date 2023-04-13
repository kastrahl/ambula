package com.example.ambula.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
@RequestMapping("/api")
public class DatabaseController {

    private final DataSource dataSource;

    public DatabaseController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostMapping("/create-data")        //@PostMapping annotation maps the endpoint to the /api/create-data URL
    public void createTable() {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = ((Connection) connection).createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user_location (id INT IDENTITY PRIMARY KEY, name VARCHAR(255), latitude DOUBLE, longitude DOUBLE)");
        } catch (SQLException e) {
            throw new RuntimeException("Error creating table", e);
        }
    }
}
