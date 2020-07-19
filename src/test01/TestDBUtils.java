package test01;

import beans.Phone;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Before;
import org.junit.Test;
import util.JDBCUtils;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @author azzhu
 * @create 2020-07-18 10:26:36
 */
public class TestDBUtils {
    private QueryRunner runner = new QueryRunner();

    @Before
    public void init() {

    }

    /**
     * BeanHandler:返回单个对象
     * @throws Exception
     */
    @Test
    public void getBean() throws Exception{
        String sql = "select id,phone_name phonename,price from phone where id=?";
        final Connection conn = JDBCUtils.getConnection2();
        final Phone phone = runner.query(conn, sql,
                new BeanHandler<>(Phone.class), 1);
        System.out.println(phone);
        DbUtils.closeQuietly(conn);
    }

    /**
     * BeanListHandler:返回多个对象
     * @throws Exception
     */
    @Test
    public void getBeans() throws Exception{
        String sql = "select id,phone_name phonename,price from phone where id between ? and ?";
        final Connection conn = JDBCUtils.getConnection2();
        final List<Phone> list = runner.query(conn, sql, new BeanListHandler<>(Phone.class), 1, 4);
        list.forEach(System.out::println);
        DbUtils.closeQuietly(conn);
    }

    /**
     * 返回单值结果：max、count、min、sum
     * @throws Exception
     */
    @Test
    public void getSingleValue() throws Exception{
        String sql = "select count(id) from phone";
        final Connection conn = JDBCUtils.getConnection2();
        final Object obj = runner.query(conn, sql, new ScalarHandler<>());
        Long count = (Long) obj;
        System.out.println(count.intValue());
        DbUtils.closeQuietly(conn);
    }

    @Test
    public void testGetMap() throws Exception{
        String sql = "SELECT MAX(price) max_val,MIN(price) min_val,AVG(price) avg_val FROM phone";
        final Connection conn = JDBCUtils.getConnection2();
        final Map<String, Object> map = runner.query(conn, sql, new MapHandler());
        System.out.println(map);
        DbUtils.closeQuietly(conn);
    }

    @Test
    public void testGetMapList() throws Exception{
        String sql = "SELECT dept_id, MAX(salary) max_val, MIN(salary) min_val FROM employee GROUP BY dept_id;";
        final Connection conn = JDBCUtils.getConnection2();
        final List<Map<String, Object>> mapList = runner.query(conn, sql, new MapListHandler());
        mapList.forEach(System.out::println);
        DbUtils.closeQuietly(conn);
    }
}
