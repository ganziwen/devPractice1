package cases;


import com.auto.annotations.Api;
import com.auto.annotations.Assert;
import com.auto.annotations.AssertUtil;
import com.auto.utils.BaseCase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/31-14:09
 */
public class TestCase1 extends BaseCase {

    @Test
    @Parameters(value = "test1")
    public void test1(@Optional("test2") String string) {
        System.out.println(string);
    }

    @Test(dataProvider = "data1")
    // @Test()
    public void test2(String name, Integer age) {
        System.out.printf("name is %s,age is %s%n", name, age);
    }

    @Test(testName = "测试名称")
    @Api()
    public void test3() {
        System.out.println("TestCase1.test3");
    }

    @Assert(value = {
            @AssertUtil(sql = ""),
            @AssertUtil(status = "200")
    })
    @DataProvider(name = "data1")
    public Object[][] createData1() {

        return new Object[][]{
                {"Cedric", new Integer(36)},
                {"Anne", new Integer(37)},
        };
    }

}
