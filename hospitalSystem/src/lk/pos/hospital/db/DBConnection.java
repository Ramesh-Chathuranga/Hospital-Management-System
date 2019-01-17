package lk.pos.hospital.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;
    public static DBConnection getInstance() throws Exception {

        if(dbConnection==null){
            dbConnection=new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection(){
        return connection;
    }

    private DBConnection() throws IOException, SQLException {
        File file=new File("hospitalSystem/resource/application.properties");
        FileReader fileReader = new FileReader(file);
        Properties properties=new Properties();
        properties.load(fileReader);

        String ip = properties.getProperty("ip");
        String port = properties.getProperty("port");
        String database = properties.getProperty("database");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        String url="jdbc:mysql://"+ip+":"+port+"/"+database+"?useSSL=false";

        connection = DriverManager.getConnection(url, username, password);
    }

}
