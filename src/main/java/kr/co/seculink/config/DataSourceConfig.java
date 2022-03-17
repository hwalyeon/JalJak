package kr.co.seculink.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DataSourceConfig
{
//    @Bean(name="writerDataSource")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.hikari.writer")
//    public DataSource writerDataSource() throws Exception
//    {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }

//    @Bean(name="readerDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.hikari.reader")
//    public DataSource readerDataSource() throws Exception
//    {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }


    @Bean(name="writerHikariConfig")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig writerHikariConfig() {
        return new HikariConfig();
    }

    @Bean(name = "writerDataSource")
    @Primary
    public DataSource writerDataSource() throws Exception {
        return new HikariDataSource(writerHikariConfig());
    }

//    @Bean(name = "readerHikariConfig")
//    @ConfigurationProperties(prefix = "spring.datasource.hikari.reader")
//    public HikariConfig readerHikariConfig() {
//        return new HikariConfig();
//    }
//
//    @Bean(name = "readerDataSource")
//    public DataSource readerDataSource() throws Exception {
//        return new HikariDataSource(readerHikariConfig());
//    }
}
