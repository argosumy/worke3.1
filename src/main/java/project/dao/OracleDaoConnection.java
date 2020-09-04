package main.java.project.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Repository;
import java.sql.Connection;


import javax.annotation.PostConstruct;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Hashtable;

@Repository
public class OracleDaoConnection implements DaoConnection{
    private static final Logger LOGGER = Logger.getLogger(OracleDaoConnection.class);
    private Connection connection;
    private final ResourceLoader resourceLoader;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final DataSourseMy dataSourseMy;
    private DataSource dataSource;


    @Autowired
    public OracleDaoConnection(ResourceLoader resourceLoader,DataSourseMy dataSourseMy){
        this.resourceLoader = resourceLoader;
        this.dataSourseMy = dataSourseMy;
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

    /**
     * Метод создает все таблицы при первом запуске приложения.
     * префикс "BOOK_*"
     *
     */
    @Override
    @PostConstruct
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
                Resource resourceCreation = resourceLoader.getResource("classpath:createTables.sql");
                EncodedResource encodeRes = new EncodedResource(resourceCreation,StandardCharsets.UTF_8);
                ScriptUtils.executeSqlScript(connection,encodeRes,false,false,"--","/","/*","*/");

                // File creatTables = ResourceUtils.getFile("classpath:createTables.sql");
               // File creatTables = new ClassPathResource("/createTables.sql").getFile();
                //File creatTables = ResourceUtils.getFile("file:e:\\Мои документы\\Lab3\\worke3\\src\\main\\resources\\createTables.sql");
                /*ScriptRunner scriptRunner = new ScriptRunner(connection);
                scriptRunner.setDelimiter("/");
                scriptRunner.setStopOnError(false);
                scriptRunner.setAutoCommit(true);
                scriptRunner.setSendFullScript(false);
                scriptRunner.runScript(new BufferedReader(new FileReader(creatTables)));*/
            }catch (Exception e){
                LOGGER.error(e);
            }
        }
    }

    @Override
    public void disconnect() {
        try {
            if(connection != null){
                connection.close();
            }
            else {
            }
            if (resultSet != null) {
                resultSet.close();
            }
            if(statement != null) {
                statement.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
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
