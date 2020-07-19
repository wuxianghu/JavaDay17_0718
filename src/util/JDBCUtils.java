package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author azzhu
 * @create 2020-07-15 09:42:54
 */
public class JDBCUtils {
    private static String driverName;
    private static String  url;
    private static String  user;
    private static String  pwd;

    static {
        //在src路径下的资源，会跟编译之后的文件一样，放在同一个路径下【这个路径我们称为类路径】
        //加载类路径下的资源，需要使用类加载器去加载
        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties prop = new Properties();
        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
            throw  new RuntimeException("文件加载失败......");
        }

        //从prop中根据key获取value，并为我们的属性赋值
        driverName = prop.getProperty("driverName");
        url = prop.getProperty("url");
        user = prop.getProperty("user");
        pwd = prop.getProperty("pwd");

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw  new RuntimeException("加载驱动出问题了......");
        }
    }

    /**
     * 获取连接
     * @return
     */
    public static Connection getConn() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,user,pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 释放资源
     * @param conn
     */
    public static void release(ResultSet rs, Statement st,Connection conn) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //使用C3P0数据库连接池的配置文件方式，获取数据库的连接：推荐
    private static DataSource cpds = new ComboPooledDataSource("helloc3p0");
    public static Connection getConnection2() throws SQLException{
        Connection conn = cpds.getConnection();

        return conn;
    }


}
