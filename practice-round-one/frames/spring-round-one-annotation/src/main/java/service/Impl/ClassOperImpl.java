package service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import otherBean.SayHello;
import service.ClassOper;

import java.util.List;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ClassOperImpl
 * @Description
 * @date 2022/3/13 10:25
 */
@Service
public class ClassOperImpl implements ClassOper {

    // 依赖注入,将依赖的 sayHello 对象给注入进来
    @Autowired
    private SayHello sayHelloBean;


    @Value("value-value")
    private String myValue;

    @Override
    public String getClassinfo(String className) {

        // 这里通过注入引用的 bean 玩一下
        System.out.println("sayHelloBean.sayHelloToSomeOne(\"sayHelloBean\") = " + sayHelloBean.sayHelloToSomeOne("sayHelloBean"));
        System.out.println("myValue = " + myValue);
        return "ClassOperImpl" + className;
    }
}
