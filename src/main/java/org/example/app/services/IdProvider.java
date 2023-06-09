package org.example.app.services;    /*
 *created by WerWolfe on IdProvider
 */

import lombok.Data;
import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
public class IdProvider implements InitializingBean, DisposableBean, BeanPostProcessor {

    Logger logger = Logger.getLogger(IdProvider.class);

    public String provideId(Book book) {
        return this.hashCode() + "_" + book.hashCode();
    }

    private void initIdProvider() {
        logger.info("provider init");
    }

    private void destroyIdProvider() {
        logger.info("provider destroy");
    }

    private void defaultInit() {
        logger.info("default init in provider");
    }

    private void defaultDestroy() {
        logger.info("default destroy in provider");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("provider afterPropertiesSet invoked");
    }

    @Override
    public void destroy() throws Exception {
        logger.info("DisposableBean destroy invoked");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.info("postProcessBeforeInitialization invoked by bean " + beanName);
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("postProcessAfterInitialization invoked by bean " + beanName);
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @PostConstruct
    public void postConstructIdProvider() {
        logger.info("postConstruct annotated method called");
    }

    @PreDestroy
    public void preDestroyIdProvider() {
        logger.info("preDestroy annotated method called");
    }
}
