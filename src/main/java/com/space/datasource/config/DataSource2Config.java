package com.space.datasource.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author zhuzhe
 * @date 2018/6/22 15:37
 * @email zhe.zhu1@outlook.com
 */
@Configuration
@MapperScan(basePackages = "com.space.datasource.mapper.test2", sqlSessionTemplateRef = "SqlSessionTemplate2")
public class DataSource2Config {

    @Value("${spring.datasource.hikari.test2.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.hikari.test2.url}")
    private String url;

    @Value("${spring.datasource.hikari.test2.username}")
    private String username;

    @Value("${spring.datasource.hikari.test2.password}")
    private String password;

    @Bean(name = "DataSource2")
    public DataSource testDataSource() {
        return DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

    @Bean(name = "SqlSessionFactory2")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("DataSource2") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/test2/**/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "TransactionManager2")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("DataSource2") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "SqlSessionTemplate2")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("SqlSessionFactory2") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
