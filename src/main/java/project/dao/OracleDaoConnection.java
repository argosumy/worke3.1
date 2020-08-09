package project.dao;

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
import java.io.*;
import java.sql.*;
import java.util.Hashtable;

@Repository
public class OracleDaoConnection implements DaoConnection{
    private static final Logger LOGGER = Logger.getLogger(OracleDaoConnection.class);
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    @Autowired
    private DataSourseMy dataSourseMy;
    public DataSource dataSource;
    public OracleDaoConnection() {
    }

    @Override
    public DataSource getDataSource() {
        Hashtable<String, String> ht = new Hashtable<>();
        DataSource ds = null;
        ht.put(Context.INITIAL_CONTEXT_FACTORY,dataSourseMy.getFactory());
        ht.put(Context.PROVIDER_URL, dataSourseMy.getUrl());
        try {
            Context ctx = new InitialContext(ht);
            ds = (DataSource) ctx.lookup(dataSourseMy.getNameJNDI());
        }catch ( NamingException e){
            LOGGER.error(e);
        }
        return ds;
    }

    @Override
    public Connection connect() {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException  e) {
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
               // File creatTables = new ClassPathResource("/createTables.sql").getFile();
               // File creatTables = ResourceUtils.getFile("file:e:\\Мои документы\\Lab3\\worke3\\src\\main\\resources\\createTables.sql");
                ScriptRunner scriptRunner = new ScriptRunner(connection);
                scriptRunner.setDelimiter("/");
                scriptRunner.setStopOnError(false);
                scriptRunner.setAutoCommit(true);
                scriptRunner.setSendFullScript(false);
                scriptRunner.runScript(new BufferedReader(new FileReader(creatTables)));
            }
            catch (FileNotFoundException e){
                LOGGER.error(e);
                System.out.println(e);
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

    @PostConstruct
    public void setDataSource() {
        this.dataSource = getDataSource();
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




}
