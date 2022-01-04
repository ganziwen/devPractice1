package com.development.mock.cases;

import com.development.mock.model.MockContext;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/1/4-12:19
 */
public class Case1 {
    @Test()
    public void testGetFileName(@Optional("/create_account/account_1") String uri) {
        System.out.println("uri = " + uri);
        MockContext mockContext = MockContext.builder().requestUri(uri).build();
        System.out.println("mockContext.getMockFileName() = " + mockContext.getMockFileName());
    }
}
