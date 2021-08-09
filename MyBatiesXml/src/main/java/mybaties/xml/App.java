package mybaties.xml;

import mybaties.xml.bean.TbUser1;
import mybaties.xml.mapper.TbUserMapper1;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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
    public static void main(String[] args) {
        testSelectByUserId();
        testSelectByUser1();
        testSelectByUser2();
    }

    public static void testSelectByUserId() {
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
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        TbUserMapper1 tbUser1Mapper = sqlSession.getMapper(TbUserMapper1.class);
        List<TbUser1> tu1s = tbUser1Mapper.selectByUserId("1000001");
        System.out.println("tu1s = " + tu1s);

        // 一定记得 close
        sqlSession.close();
    }

    public static void testSelectByUser1() {
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
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        TbUserMapper1 tbUser1Mapper = sqlSession.getMapper(TbUserMapper1.class);
        List<TbUser1> tu1s = tbUser1Mapper.selectByUser1("1000002", "zhangsan2");
        System.out.println("tu1s = " + tu1s);

        // 一定记得 close
        sqlSession.close();
    }

    public static void testSelectByUser2() {
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
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        TbUserMapper1 tbUser1Mapper = sqlSession.getMapper(TbUserMapper1.class);
        List<TbUser1> tu1s = tbUser1Mapper.selectByUser2(new TbUser1("1000003", "zhangsan3"));
        System.out.println("tu1s = " + tu1s);

        // 一定记得 close
        sqlSession.close();
    }
}