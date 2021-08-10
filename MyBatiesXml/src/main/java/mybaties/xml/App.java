package mybaties.xml;

import mybaties.xml.bean.TbUser1;
import mybaties.xml.mapper.TbUserMapper1;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName App
 * @Description
 * @date 2021/8/8 14:00
 */
public class App {

    private SqlSession sqlSession;

    @Before
    public void init() {
        // 创建一个配置文件流
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("mybaties-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //利用配置文件流利用工厂生成一个 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in, "development");

        // SqlSession 相当于连接池,并且开启自动提交
        this.sqlSession = sqlSessionFactory.openSession(true);
    }

    @After
    public void close() {
        this.sqlSession.close();
    }

    @Test
    public void testSelectByUserId() {
        TbUserMapper1 tbUser1Mapper = sqlSession.getMapper(TbUserMapper1.class);
        List<TbUser1> tu1s = tbUser1Mapper.selectByUserId("1000001");
        System.out.println("tu1s = " + tu1s);
    }

    @Test
    public void testSelectByUser1() {
        TbUserMapper1 tbUser1Mapper = sqlSession.getMapper(TbUserMapper1.class);
        List<TbUser1> tu1s = tbUser1Mapper.selectByUser1("1000002", "zhangsan2");
        System.out.println("tu1s = " + tu1s);
    }

    @Test
    public void testSelectByUser2() {
        TbUserMapper1 tbUser1Mapper = sqlSession.getMapper(TbUserMapper1.class);
        List<TbUser1> tu1s = tbUser1Mapper.selectByUser2(new TbUser1("1000003", "zhangsan3"));
        System.out.println("tu1s = " + tu1s);
    }

    @Test
    public void testSelectByUser3() {
        TbUserMapper1 tbUser1Mapper = sqlSession.getMapper(TbUserMapper1.class);
        List<TbUser1> tu1s = tbUser1Mapper.selectByUser3("1000003", "zhangsan3");
        System.out.println("tu1s = " + tu1s);
    }

    @Test
    public void testSelectByUser4() {
        TbUserMapper1 tbUser1Mapper = sqlSession.getMapper(TbUserMapper1.class);
        List<TbUser1> tu1s = tbUser1Mapper.selectByUser4("1000003", "zhangsan3");
        System.out.println("tu1s = " + tu1s);
    }
}