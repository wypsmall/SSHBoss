【2016-03-04】
1.初步了解xml、注解的加载机制
2.分析将注解方式入驻切换到xml配置bean的解决方式
3.还需要深入了解

http://www.cnblogs.com/elleniou/archive/2013/07/03/3168836.html
http://www.cnblogs.com/iuranus/archive/2012/07/19/2599084.html
http://blog.csdn.net/chunqiuwei/article/details/16115135
【场景-1】
如果所有的配置都采用注解的方式进行，就需要加入
<context:component-scan base-package="com.neo.account,com.neo.user"/>
那么@Resource、@Autowired，都会被自动加载
但是处于依赖引用的泛滥，想对bean进行整治，就需要使用
    <context:component-scan base-package="com.neo.account,com.neo.user" use-default-filters="false">
        <context:include-filter type="assignable" expression="com.neo.account.service.AccountServiceImpl"/>
        <context:include-filter type="assignable" expression="com.neo.user.dao.UserDaoImpl"/> 【或者A方式】
    </context:component-scan>
这样的方式制定要注入的bean，如果@Autowired对应的变量没有set方法，就无法使用
    <bean name="userService" class="com.neo.user.service.UserServiceImpl">
        <!--<property name="userDao" ref="userDao"></property>-->  【无效的】
    </bean>

    <bean name="userDao" class="com.neo.user.dao.UserDaoImpl" />   【或者B方式】

信息: Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@7adbbfc0: defining beans [dataSource,sessionFactory,transactionManager,org.springframework.aop.config.internalAutoProxyCreator,org.springframework.transaction.annotation.AnnotationTransactionAttributeSource#0,org.springframework.transaction.interceptor.TransactionInterceptor#0,org.springframework.transaction.config.internalTransactionAdvisor,org.springframework.beans.factory.config.PropertyPlaceholderConfigurer#0,accountService,org.springframework.context.annotation.internalConfigurationAnnotationProcessor,org.springframework.context.annotation.internalAutowiredAnnotationProcessor,org.springframework.context.annotation.internalRequiredAnnotationProcessor,org.springframework.context.annotation.internalCommonAnnotationProcessor,org.springframework.context.annotation.internalPersistenceAnnotationProcessor,userService,org.springframework.context.annotation.ConfigurationClassPostProcessor$ImportAwareBeanPostProcessor#0]; root of factory hierarchy


【2016-02-29】
1.弹出莫斯对话框口
http://www.jeasyui.com/demo/main/index.php?plugin=Form&theme=default&dir=ltr&pitem=

【2016-02-26】
1.集成列表空间功能，解决分页传参
2.datagrid-toolbar 未实现

jQuery EasyUI教程之datagrid应用（三）
http://hegz.iteye.com/blog/2032650

jquery easyui datagrid 分页 详解
http://www.cnblogs.com/huozhicheng/archive/2011/09/27/2193605.html

【2016-02-25】
1.集成easyui的菜单和Tab+iframe
2.加入datagrid空间

【2016-02-24】
1.github [*]@163.com/[*].~12  【解决git提交】
2.hibernate集成
3.注解是事务生效

【2016-02-23】
1.完成工程大家，集成struts2，返回json，解决乱码问题
2.明天计划
    a.集成hibernate
    b.添加struts过滤器
    c.考虑集成ext
    d.上传github
3.集成easyui，静态页面
http://localhost:8080/SSHBoss/test/Layout.html

【测试地址】
http://localhost:8080/SSHBoss/strust2Test!testFunc.action 【进入action】
http://localhost:8080/SSHBoss/strust2Test!test.action   【无此action】
http://localhost:8080/SSHBoss/json/getUserInfo.action   【返回json，开始的时候出现了中文乱码，原因不是配置，是java源码不是utf8的格式】
http://localhost:8080/SSHBoss/watson/json.action   【返回json】
http://localhost:8080/SSHBoss/watson/userList.action

Struts2返回JSON对象的方法总结
http://kingxss.iteye.com/blog/1622455

使用IntelliJ IDEA 14和Maven创建java web项目
http://www.cnblogs.com/jifeng/p/4658765.html

使用Maven搭建Struts2+Spring3+Hibernate4的整合开发环境
http://www.linuxidc.com/Linux/2015-02/114265p3.htm