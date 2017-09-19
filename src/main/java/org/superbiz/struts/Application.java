package org.superbiz.struts;

import com.opensymphony.module.sitemesh.filter.PageFilter;
import org.apache.struts2.dispatcher.ActionContextCleanUp;
import org.apache.struts2.dispatcher.FilterDispatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;
import java.util.Collections;

@SpringBootApplication
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public FilterRegistrationBean getFilterDispatcher(){
        int priority = 0;
        FilterDispatcher filterDispatcher = new FilterDispatcher();
        return makeFilterRegistrationBean(priority,filterDispatcher);
    }

    @Bean
    public FilterRegistrationBean getActionCleanUpContext(){
        int priority = 1;
        ActionContextCleanUp cleanUpContext = new ActionContextCleanUp();
        return makeFilterRegistrationBean(priority, cleanUpContext);
    }

    @Bean
    public FilterRegistrationBean getPageFilter(){
        int priority = 2;
        PageFilter pageFilter = new PageFilter();
        return makeFilterRegistrationBean(priority, pageFilter);
    }

    private FilterRegistrationBean makeFilterRegistrationBean(int priority, Filter filter){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        registrationBean.setUrlPatterns(Collections.singletonList("/*"));
        registrationBean.setOrder(priority);
        return registrationBean;
    }
}
