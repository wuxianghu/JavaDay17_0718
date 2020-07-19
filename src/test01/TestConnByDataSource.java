package test01;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;
import util.JDBCUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author azzhu
 * @create 2020-07-18 09:11:17
 */
public class TestConnByDataSource {

    @Test
    public void testGetConn() throws Exception{
        final Connection conn = JDBCUtils.getConnection2();
        System.out.println(conn);

        final PreparedStatement ps = conn.prepareStatement("select * from account");
        final ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
        JDBCUtils.release(rs,ps,conn);
    }

    @Test
    public void testGetConn2() throws Exception{
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            final Connection conn = JDBCUtils.getConnection2();
            JDBCUtils.release(null,null,conn);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }

    @Test
    public void testGetConn3() throws Exception{
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            final Connection conn = JDBCUtils.getConn();
            JDBCUtils.release(null,null,conn);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }

    @Test
    public void testGetConn4() throws Exception{
        Properties pro = new Properties();
        pro.load(TestConnByDataSource.class.getClassLoader().getResourceAsStream("druid.properties"));
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);
        Connection conn = ds.getConnection();
        System.out.println(conn);
    }
}
