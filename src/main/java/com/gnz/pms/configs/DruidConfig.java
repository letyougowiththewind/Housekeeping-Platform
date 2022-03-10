package com.gnz.pms.configs;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 该方法可以返回一个初始化了上下文（指定了一些初始化参数）的Servlet，相当于在web容器中注册（添加）了一个性能监控管理的servlet
 */
@Configuration
public class DruidConfig {//Druid实现性能监控的配置类
    @Bean
    public ServletRegistrationBean getServletRegistrationBean(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        bean.addInitParameter("allow","127.0.0.1,192.168.142.1");//白名单（允许访问指定路径的ip地址）
//        bean.addInitParameter("deny","192.168.142.2");//黑名单
        bean.addInitParameter("loginUsername","gnz");
        bean.addInitParameter("loginPassword","200082");
        bean.addInitParameter("resetEnable","false");//表示不能重置数据源
        return bean;
    }

    /**
     * 该方法向容器中注册了一个过滤器，该过滤器专门负责请求的性能监控统计
     * 并且给该过滤器指定要拦截的路径和放行的路径
     * @return
     */
    @Bean
    public FilterRegistrationBean getFilterRegistrationBean(){
        FilterRegistrationBean filter = new FilterRegistrationBean();
        //指定过滤器要拦截的路径
        filter.addUrlPatterns("/*");
        //指定过滤器要放行的路径
        filter.addInitParameter("exclusions","*.jpg,*.png,*.css,*.js,*.ico,*.gif,/druid/*");
        //指定处理该路径的过滤器
        filter.setFilter(new WebStatFilter());
        return filter;
    }

    /**
     * 需要使用到连接池的配置信息
     * @return
     */
    @Bean
    public DataSource getDataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/pms?useUnicode=true&useJDBCCompliantTimezoneShift=true&serverTimezone=CTT&characterEncoding=utf8&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("200082");
        dataSource.setMinIdle(5);
        dataSource.setInitialSize(10);
        dataSource.setMaxWait(2000);
        dataSource.setMaxActive(100);
        dataSource.setFilters("stat,wall");
        return dataSource;
    }

    /**
     * 该方法是向Spring容器中注入一个负责监控统计方法性能的对象，
     * 这个对象就类似于之前做日志处理的切面类的对象
     * @return
     */
    @Bean
    public DruidStatInterceptor getDruidStatInterceptor(){
        return new DruidStatInterceptor();
    }

    /**
     * 该方法是向Spring容器中注入一个表示监控性能的切点的对象，换句话说，
     * 将要监控的切点封装到了JdkRegexpMethodPointcut类对象中了
     * @return
     */
    @Bean
    public JdkRegexpMethodPointcut getMethodPointcut(){
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        //设置切点
        pointcut.setPattern("com.gnz.pms.service.impl.*");
        return pointcut;
    }

    /**
     * 该方法的作用是将要监控的切点交给指定的切面处理，相当于起到了切点和切面的枢纽配置作用
     * @param statInterceptor
     * @param pointcut
     * @return
     */
    @Bean
    public DefaultPointcutAdvisor getDefaultPointcutAdvisor(DruidStatInterceptor statInterceptor, JdkRegexpMethodPointcut pointcut){
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        //注入切点
        advisor.setPointcut(pointcut);
        //注入切面
        advisor.setAdvice(statInterceptor);
        return advisor;
    }
}
