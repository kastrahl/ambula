package com.example.ambula;

import com.example.ambula.controller.DatabaseController;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseControllerTest {

    @Mock
    private DataSource dataSource;

    @InjectMocks
    private DatabaseController databaseController;

    @Test
    public void testCreateTable() throws SQLException {
        Connection connection = mock(Connection.class);
        Statement statement = mock(Statement.class);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        databaseController.createTable();

        Mockito.verify(connection, times(1)).createStatement();
        verify(statement, times(1)).executeUpdate(
                "CREATE TABLE user_location (id BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY, name VARCHAR(255), latitude DOUBLE, longitude DOUBLE)"
        );
    }
}
