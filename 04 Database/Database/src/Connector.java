import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by HAWK-VAIO on 2/7/2017.
 */
public class Connector {
    java.sql.Connection conn;
    java.sql.Statement stat;
    static String url, database, username, password, hostname, port, driver;

    public Connector(Properties props, String pass){
        database = props.getProperty("Database");
        username = props.getProperty("User_Name");
        password = pass;
        hostname = props.getProperty("Host_Name");
        port = props.getProperty("Port");
        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://"+hostname+":"+port+"/"+database;
        //This is for debugging to make sure url is jdbc:mysql://127.0.0.1:3306/sakila where sakila is one of the database (could be sys)
        System.out.println(url);
    }
    public boolean open(){
        try {
            DriverManager.registerDriver((java.sql.Driver) Class.forName(driver).newInstance());
            conn = DriverManager.getConnection(url, username, password);
            stat = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            if(conn == null){
                return false;
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
            if(conn == null){
                return false;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            if(conn == null){
                return false;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            if(conn == null){
                return false;
            }
        }

        System.out.println("Connection Successful");

        return true;
    }

    public ResultSet executeQuery(String s) throws SQLException {
        return stat.executeQuery(s);
    }

    public void executeUpdate(String s) throws SQLException {
        stat.executeUpdate(s);
    }
}
