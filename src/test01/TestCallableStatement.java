package test01;

import org.junit.Test;
import util.JDBCUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * @author azzhu
 * @create 2020-07-18 08:47:40
 */
public class TestCallableStatement {

    @Test
    public void testGetDeptByNo() throws Exception{
        //1.获取conn
        final Connection conn = JDBCUtils.getConn();
        //2.获取CallableStatement
        final CallableStatement cs = conn.prepareCall("CALL pro_findByDeptno(?)");
        cs.setInt(1,212);

        final ResultSet rs = cs.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getInt(1)+","+rs.getString(2)
                    +","+rs.getString(3));
        }

        JDBCUtils.release(rs,cs,conn);
    }
}
