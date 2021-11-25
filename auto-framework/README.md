# 工程简介
基于 junit 5 完成的接口自动化框架

## 扩展机制
利用 `@ExtendWith` 进行扩展,作为注解,需要实现 `BeforeTestExecutionCallback` 类里面的 `beforeTestExecution` 方法。在这个方法内构造发现请求的实体,在这里民进行 case 筛选,筛选完之后可以对执行的结果进行收集

# 延伸阅读

