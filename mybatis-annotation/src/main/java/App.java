import bean.TbUser1;
import mapper.TbUserMapper1;
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
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/9-10:19
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
        List<TbUser1> tu1s = tbUser1Mapper.selectByUserId("1000005");
        System.out.println("tu1s = " + tu1s);

    }

    @Test
    public void testSelectByUser() {
        TbUserMapper1 tbUser1Mapper = sqlSession.getMapper(TbUserMapper1.class);
        List<TbUser1> tu1s = tbUser1Mapper.selectByUser1("1000002", "zhangsan2");
        System.out.println("tu1s = " + tu1s);
    }

    @Test
    public void testInsertUser() {
        TbUserMapper1 tbUser1Mapper = sqlSession.getMapper(TbUserMapper1.class);
        Integer insertNums = tbUser1Mapper.insertUser1(new TbUser1("1000002", "zhangsan2"));
        System.out.println("insertNums = " + insertNums);
    }

    /**
     * 奇怪的是返回的 key = 1?这还是有些许奇怪的
     */
    @Test
    public void testInsertUserRetKey() {
        TbUserMapper1 tbUser1Mapper = sqlSession.getMapper(TbUserMapper1.class);
        TbUser1 tbUser1 = new TbUser1("1000007", "zhangsan7");
        System.out.println("tbUser1 = " + tbUser1);
        int efdfectRows = tbUser1Mapper.insertUser3(tbUser1);
        System.out.println("efdfectRows = " + efdfectRows);
        System.out.println("tbUser1 = " + tbUser1);
    }


    /**
     * update
     */
    @Test
    public void testUpdateUser1() {
        TbUserMapper1 tbUser1Mapper = sqlSession.getMapper(TbUserMapper1.class);
        int effectRows = tbUser1Mapper.updateUser1(new TbUser1(1L, "1000001", "zhangsan1"));
        System.out.println("effectRows = " + effectRows);
    }

    /**
     * delete
     */
    @Test
    public void testdeleteUser1() {
        TbUserMapper1 tbUser1Mapper = sqlSession.getMapper(TbUserMapper1.class);
        int effectRows = tbUser1Mapper.deleteUser1(new TbUser1(5L, "1000005", "zhangsan5"));
        System.out.println("effectRows = " + effectRows);
    }
}
