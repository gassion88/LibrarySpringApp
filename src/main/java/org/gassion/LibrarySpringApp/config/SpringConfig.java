package org.gassion.LibrarySpringApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import javax.sql.DataSource;

@Configuration
@ComponentScan("org.gassion.LibrarySpringApp")
@PropertySource("classpath:config.properties")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {

    @Value("${db.url}")
    private static String DB_URL;
    @Value("${db.driver_name}")
    private static String DB_Driver_Name;
    @Value("${db.name}")
    private static String DB_USER_NAME;
    @Value("${db.password}")
    private static String DB_PASSWORD;

    private final ApplicationContext applicationContext;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SpringConfig(ApplicationContext applicationContext, JdbcTemplate jdbcTemplate) {
        this.applicationContext = applicationContext;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(DB_Driver_Name);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USER_NAME);
        dataSource.setPassword(DB_PASSWORD);

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}