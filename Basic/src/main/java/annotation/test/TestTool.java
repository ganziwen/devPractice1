package annotation.test;

import annotation.NoBug;
import annotation.annoer.JianCha;

import java.lang.reflect.Method;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/11/12-16:10
 */
public class TestTool {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        NoBug testobj = new NoBug();
        Class clazz = testobj.getClass();
        Method[] method = clazz.getDeclaredMethods();
        //用来记录测试产生的 log 信息
        StringBuilder log = new StringBuilder();
        // 记录异常的次数
        int errorNum = 0;
        // 记录 pass 的次数
        int successNum = 0;
        for (Method m : method) {
            // 只有被 @Jiecha 标注过的方法才进行测试
            if (m.isAnnotationPresent(JianCha.class)) {
                try {
                    // 设置属性为可访问的
                    m.setAccessible(true);
                    m.invoke(testobj, null);
                    successNum++;
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    errorNum++;
                    log.append(String.format("%s has error:\n\rcaused by:%s\n\r%s\n", m.getName(), e.getCause().getClass().getSimpleName(), e.getCause().getMessage()));
                }
            }
        }
        log.append(String.format("Report Info:\n %s\n pass %s cases.\nfailed %s cases", clazz.getSimpleName(), successNum, errorNum));

        // 生成测试报告
        System.out.println(log.toString());
    }
}
