package cases;


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
}
