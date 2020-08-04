package main.java.dao;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.Hashtable;
import java.util.Locale;

@Repository
public class OracleDaoConnection implements DaoConnection{
    private static final Logger LOGGER = Logger.getLogger(OracleDaoConnection.class);
    private Connection connection;
    private Context ctx;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private Hashtable<String, String> ht = new Hashtable<>();
    private ResultSet resultSet;
    private DataSourse dataSourse;

    @Autowired()
    public OracleDaoConnection(DataSourse dataSourse) {
        this.dataSourse = dataSourse;

    }

    @Override
    public Connection connect() {
        Locale.setDefault(Locale.ENGLISH);
        ht.put(Context.INITIAL_CONTEXT_FACTORY,dataSourse.getFactory());
        ht.put(Context.PROVIDER_URL, dataSourse.getUrl());
        try {
            ctx = new InitialContext(ht);
            DataSource ds = (DataSource) ctx.lookup(dataSourse.getDataSourse());
            connection = ds.getConnection();
        } catch (NamingException | SQLException e) {
            LOGGER.error("Error in connection to db", e);
        }
        return connection;
    }

    @PostConstruct
    @Override
    public void creatTable() {
        connection = connect();
        int i = 0;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select count(1) CNT from USER_TABLES where table_name like  'BOOK_%'");
            while (resultSet.next()) {
                i = resultSet.getInt("CNT");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        if(i == 0){
            System.out.println("Создание таблиц");
            try {
                File creatTables = ResourceUtils.getFile("classpath:createTables.sql");
                ScriptRunner scriptRunner = new ScriptRunner(connection);
                scriptRunner.setDelimiter("/");
                scriptRunner.setStopOnError(false);
                scriptRunner.setAutoCommit(true);
                scriptRunner.setSendFullScript(false);
                scriptRunner.runScript(new BufferedReader(new FileReader(creatTables)));
            }
            catch (FileNotFoundException e){
                LOGGER.error(e);
            }
        }
    }

    @Override
    public void disconnect() {
        try {
            if(connection != null){
                connection.close();
                System.out.println("Connection was closed");
            }
            else {
                System.out.println("Connection was closed");
            }
            if (resultSet != null) {
                resultSet.close();
                System.out.println("Resultset was closed");
            }
            if(statement != null) {
                statement.close();
                System.out.println("Statement was closed");
            }
            if(preparedStatement != null){
                preparedStatement.close();
                System.out.println("PrepareStatment was closed");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }



    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public DataSourse getDataSourse() {
        return dataSourse;
    }

    public void setDataSourse(DataSourse dataSourse) {
        this.dataSourse = dataSourse;
    }


}
