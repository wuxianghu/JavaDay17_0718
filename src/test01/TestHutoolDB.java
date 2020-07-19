package test01;

import beans.Phone;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.sql.Condition;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Phaser;

/**
 * @author azzhu
 * @create 2020-07-18 11:46:06
 */
public class TestHutoolDB {

    private Entity ent;

    @Test
    public void testInsert() throws Exception{
        Db.use().insertForGeneratedKey(
                Entity.create("phone")
                        .set("phone_name", "华为pxxx")
                        .set("price", 66)
        );
    }

    @Test
    public void testDel() throws Exception{
        Db.use().del(
                Entity.create("phone").set("id", "10001")//where条件
        );

    }

    @Test
    public void testUpdate() throws Exception{
        Db.use().update(
                Entity.create().set("phone_name", "sdfsdfsdafsadf"), //修改的数据
                Entity.create("phone").set("id", "1") //where条件
        );
    }

    @Test
    public void testGetAll() throws Exception{
        //user为表名
        //Db.use().findAll("phone").forEach(System.out::println);
        //final List<Entity> list = Db.use().findLike("phone", "phone_name", "df", Condition.LikeType.Contains);
        List<Entity> results = Db.use().findAll(
                Entity.create("phone")
                        .set("id", "> 1"));
        results.forEach(entity -> {
            System.out.println(entity.toBean(Phone.class));
        });
    }
}
