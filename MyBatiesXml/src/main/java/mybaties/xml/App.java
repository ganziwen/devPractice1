package mybaties.xml;

import com.google.common.collect.Lists;
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
import java.util.ArrayList;
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

    /**
     * 动态sql
     */
    @Test
    public void testSelectByUser5() {
        TbUserMapper1 tbUser1Mapper = sqlSession.getMapper(TbUserMapper1.class);
        List<TbUser1> tu0s = tbUser1Mapper.selectUserForDynamic(new TbUser1(null, null));
        List<TbUser1> tu1s = tbUser1Mapper.selectUserForDynamic(new TbUser1("1000001", null));
        List<TbUser1> tu2s = tbUser1Mapper.selectUserForDynamic(new TbUser1(null, "zhangsan1"));
        List<TbUser1> tu3s = tbUser1Mapper.selectUserForDynamic(new TbUser1("1000001", "zhangsan1"));
        System.out.println("tu0s = " + tu0s);
        System.out.println("tu1s = " + tu1s);
        System.out.println("tu2s = " + tu2s);
        System.out.println("tu3s = " + tu3s);
    }

    @Test
    public void testSelectByUser6() {
        TbUserMapper1 tbUser1Mapper = sqlSession.getMapper(TbUserMapper1.class);
        TbUser1 user1 = new TbUser1("1000001", "zhangsan1");
        TbUser1 user2 = new TbUser1("1000002", "zhangsan2");

        List<TbUser1> tbUser1List = new ArrayList<>();
        tbUser1List.add(user1);
        tbUser1List.add(user2);

        List<TbUser1> tu0s = tbUser1Mapper.selectUserForDynamicWithEach1(tbUser1List);
        System.out.println("tu0s = " + tu0s);

    }

    @Test
    public void testSelectByUser7() {
        TbUserMapper1 tbUser1Mapper = sqlSession.getMapper(TbUserMapper1.class);
        List<String> userIdList = new ArrayList<>();
        userIdList.add("1000001");
        userIdList.add("1000002");

        List<TbUser1> tu0s = tbUser1Mapper.selectUserForDynamicWithEach2(userIdList);
        System.out.println("tu0s = " + tu0s);

    }
}