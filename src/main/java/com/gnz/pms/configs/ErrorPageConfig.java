package com.gnz.pms.configs;


import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ErrorPageConfig implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry errorPageRegistry) {
        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND,"/login_return.html");
        ErrorPage errorPage405 = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED,"/login_return.html");
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/login_return.html");
        errorPageRegistry.addErrorPages(errorPage404,errorPage405,errorPage500);
    }
}
