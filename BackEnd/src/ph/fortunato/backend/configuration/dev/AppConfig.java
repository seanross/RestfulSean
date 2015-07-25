package ph.fortunato.backend.configuration.dev;


import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesView;

import ph.fortunato.backend.datasource.BackEndRoutingDataSource;

/**
 * @author Sean Ross
 *
 */
@Profile("dev")
@Configuration
@ComponentScan(basePackages = "ph.fortunato.backend")
@PropertySource("classpath:${APP_ENV:dev}.properties")
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableTransactionManagement
@Import({AppSecurityConfig.class})
public class AppConfig extends WebMvcConfigurerAdapter {

	static{
		Logger.getLogger(AppConfig.class).info("Setting up application context configuration under DEVELOPMENT.");
	}
	
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
//	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//	}
	
	@Bean(name="propertySourcePlaceholderConfigurer")
	public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean(name="backEndRoutingDataSource")
	public BackEndRoutingDataSource getBackEndRoutingDataSource(){
		return new BackEndRoutingDataSource();
	}
	
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(BackEndRoutingDataSource dataSource){
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.scanPackages(dbPackageToScan);
		sessionBuilder.setProperty("hibernate.show_sql", dbShowSql);
		sessionBuilder.setProperty("hibernate.hbm2ddl.auto", dbHbm2DdlAuto);
		sessionBuilder.setProperty("hibernate.dialect", dbDialect);
		return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
	    HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
	    return transactionManager;
	}
	
	@Value("${db.package.to.scan}") private String dbPackageToScan;
	@Value("${db.show.sql}") private String dbShowSql;
	@Value("${db.hbm2ddl.auto}") private String dbHbm2DdlAuto;
	@Value("${db.sql.dialect}") private String dbDialect;
//	@Value("${file.max.upload.size}") private Integer maxUploadSize;
//	@Value("${message.source.base.name}") private String messageSourceBaseName;
//	@Value("${mail.host}") private String mailHost;
//	@Value("${mail.port}") private Integer mailPort;
//	@Value("${mail.username}") private String mailUsername;
//	@Value("${mail.password}") private String mailPassword;
//	@Value("${mail.smtp.auth}") private Boolean mailSmtpAuth;
//	@Value("${mail.smtp.starttls.enable}") private Boolean mailSmtpStarttlsEnable;
}
