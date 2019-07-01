package com.epam.esm.giftcertificates.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Configuration class for embedded database.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackages = "com.epam.esm.giftcertificates")
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@PropertySource("paths.properties")
@Profile("test")
public class EmbeddedDatabaseConfig {

    @Value("${path.schema}")
    private String schema;
    @Value("${path.init}")
    private String init;

    /**
     * Bean for class {@link DataSource}.
     *
     * @return value of the object {@link DataSource}
     */
    @Bean
    public DataSource getDataSource() {
        final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.HSQL).addScripts(schema, init).build();
    }

    /**
     * Bean for class {@link JdbcTemplate}.
     *
     * @param dataSource value of the object {@link DataSource}
     * @return value of the object {@link JdbcTemplate}
     */
    @Bean
    public JdbcTemplate getJdbcTemplate(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * Bean for class {@link PlatformTransactionManager}.
     *
     * @param dataSource value of the object {@link DataSource}
     * @return value of the object {@link PlatformTransactionManager}
     */
    @Bean
    public PlatformTransactionManager getPlatformTransactionManager(final DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
