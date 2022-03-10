package com.gnz.pms.configs;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
     public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        resolver.setViewNames("jsp/*");//当控制器返回的viewName符合规则时才使用这个视图解析器
        resolver.setOrder(2);//设置优先级,数值越小优先级越高
        return resolver;
     }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 配置消息转换器,覆盖spring自带的json转换方式，这里使用fastjson来实现
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建一个转换器对象
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //创建换器配置对象
        FastJsonConfig config = new FastJsonConfig();
        //配置转换规则
        config.setSerializerFeatures(
                SerializerFeature.QuoteFieldNames,//输出key的时候是否使用双引号,默认是不false(不加)
                SerializerFeature.WriteDateUseDateFormat,//实现日期格式化
                SerializerFeature.PrettyFormat,
                SerializerFeature.DisableCircularReferenceDetect//消除对同一个对象循环引用的问题
        );
        //指定转换器的配置
        fastJsonHttpMessageConverter.setFastJsonConfig(config);
        //将转换器添加到集合中
        converters.add(fastJsonHttpMessageConverter);
    }
    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources");
    }

    /**
     * 该配置是让templates目录下的login.html页面可以直接在浏览器通过url访问
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login.html");
        registry.addViewController("/home.html");
        registry.addViewController("/imgCode");
        registry.addViewController("/msg_login");
        registry.addViewController("/map.html");
        registry.addViewController("/server.html");
        registry.addViewController("/user_list.html");
        registry.addViewController("/userpages/add_user.html");
        registry.addViewController("/index_v3.html");
        registry.addViewController("basic_gallery.html");
        registry.addViewController("upload.html");
        registry.addViewController("/tenants/*.html");
        registry.addViewController("/addcommunityinfo/*.html");
        registry.addViewController("/res/*.html");
        registry.addViewController("/property/*.html");
        registry.addViewController("/druid");
        registry.addViewController("/calendar.html");
    }
//    @Bean //表示该方法产生的对象交给Spring容器管理
//    public MyIntercepter getMyIntercepter(){
//        return new MyIntercepter();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(getMyIntercepter()).addPathPatterns("/home.html");
//    }
}
