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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author zhuzhe
 * @date 2018/6/22 14:11
 * @email zhe.zhu1@outlook.com
 */
@Configuration
@MapperScan(basePackages = "com.space.datasource.mapper.test1", sqlSessionTemplateRef = "SqlSessionTemplate1")
public class DataSource1Config {

    @Value("${spring.datasource.hikari.test1.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.hikari.test1.url}")
    private String url;

    @Value("${spring.datasource.hikari.test1.username}")
    private String username;

    @Value("${spring.datasource.hikari.test1.password}")
    private String password;

    @Bean(name = "DataSource1")
    @Primary  //  @Primary 指定在同一个接口有多个实现类可以注入的时候，默认选择哪一个，
              // 而不是让@Autowire注解报错（一般用于多数据源的情况下）
    public DataSource testDataSource() {
        return DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

//  @Qualifier  指定名称的注入，当一个接口有多个实现类的时候使用
// （在本例中，有两个DataSource类型的实例，需要指定名称注入）

    @Bean(name = "SqlSessionFactory1")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("DataSource1") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/test1/**/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "TransactionManager1")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("DataSource1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "SqlSessionTemplate1")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("SqlSessionFactory1") SqlSessionFactory sqlSessionFactory) {
        // 管理sqlSession 是线程安全的（默认的DefaultSqlSession不是线程安全的）
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
