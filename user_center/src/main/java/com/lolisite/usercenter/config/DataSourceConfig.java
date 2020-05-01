package com.lolisite.usercenter.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author cap_ljf
 * @description 数据源配置
 * @create 2020/04/12 17:53
 */
@Configuration
@ConditionalOnProperty(name = "spring.datasource.dbcp2.url", matchIfMissing = false)
@MapperScan(value = {"com.lolisite.usercenter.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dbcp2")
    public DataSource dataSource() {
        return new BasicDataSource();
    }

    /**
     * 数据库事务处理器
     *
     * @param dataSource 数据源
     * @return transactionManager
     */
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager dbOneTransactionManager(
            @Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * mybatis 会话工厂
     *
     * @param dataSource 数据源
     * @return sqlSessionFactory
     * @throws Exception 异常
     */
    @Bean(name = "sqlSessionFactory")
    @ConditionalOnMissingBean(name = "sqlSessionFactory")
    public SqlSessionFactory dbOneSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*Mapper.xml"));
        return sessionFactory.getObject();
    }

}
