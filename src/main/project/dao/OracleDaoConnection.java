package project.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Locale;

@Repository
public class OracleDaoConnection implements DaoConnection{
    private static final Logger LOGGER = Logger.getLogger(OracleDaoConnection.class);
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private DataSourse dataSourse;
    private ComboPooledDataSource spds;
    @Autowired
    public void setSpds(ComboPooledDataSource spds) {
        this.spds = spds;
    }

    @Autowired()
    public OracleDaoConnection(DataSourse dataSourse) {
        this.dataSourse = dataSourse;

    }

    @Override
    public Connection connect() {
        Locale.setDefault(Locale.ENGLISH);
      /*  try {
           Class.forName(dataSourse.getDriverClass()).getDeclaredConstructor().newInstance();
           connection = DriverManager.getConnection(dataSourse.getConnectionUrl(),
                        dataSourse.getUserName(), dataSourse.getPassword());


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        try {
            connection = spds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
/*
       @Override
        public Connection connect() {
            Locale.setDefault(Locale.ENGLISH);
            try {
                ComboPooledDataSource cpds = new ComboPooledDataSource();
                cpds.setJdbcUrl(dataSourse.getConnectionUrl());
                cpds.setUser(dataSourse.getUserName());
                cpds.setPassword(dataSourse.getPassword());
                cpds.setDriverClass(dataSourse.getDriverClass());
                //Settings
                cpds.setInitialPoolSize(dataSourse.getInitialPoolSize());
                cpds.setMinPoolSize(dataSourse.getMinPoolSize());
                cpds.setAcquireIncrement(dataSourse.getAcquireIncrement());
                cpds.setMaxPoolSize(dataSourse.getMaxPoolSize());
                connection =  cpds.getConnection();
                //Class.forName(dataSourse.getDriverClass()).getDeclaredConstructor().newInstance();
                // connection = DriverManager.getConnection(dataSourse.getConnectionUrl(),
                //        dataSourse.getUserName(), dataSourse.getPassword());
                if (!connection.isClosed()) {
                    System.out.println("Connected successful!");
                }
            }
            catch (SQLException e) {
                LOGGER.error("Ошибка в настройках SourseName- ",e);
            }
            catch (Exception ex){
                LOGGER.error(ex);
            }
            return connection;
        }
*/

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
