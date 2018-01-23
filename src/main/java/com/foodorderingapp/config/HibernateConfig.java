package com.foodorderingapp.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = { "com.foodorderingapp" })
@EnableTransactionManagement
@PropertySource(value={"classpath:application.properties"})
public class HibernateConfig {

    private final Environment environment;

    @Autowired
    public HibernateConfig(Environment environment){
        this.environment=environment;
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(environment.getRequiredProperty("spring.datasource.driver"));
        dataSource.setUrl(environment.getRequiredProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getRequiredProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getRequiredProperty("spring.datasource.password"));
        System.out.println(dataSource);
        return dataSource;
    }

    @Bean
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
        builder.addProperties(getHibernateProperties());
        builder.scanPackages("com.foodorderingapp");
        return builder.buildSessionFactory();

    }

    private Properties getHibernateProperties() {

        Properties properties = new Properties();

        properties.put("hibernate.dialect",environment.getRequiredProperty("spring.datasource.dialect"));
        properties.put("hibernate.enable_lazy_load_no_trans",environment.getRequiredProperty("hibernate.enable_lazy_load_no_trans"));
        return properties;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
    {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }
}