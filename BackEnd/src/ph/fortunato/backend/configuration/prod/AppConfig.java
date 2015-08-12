package ph.fortunato.backend.configuration.prod;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.ImprovedNamingStrategy;
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
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ph.fortunato.backend.datasource.BackEndRoutingDataSource;

/**
 * @author Sean Ross
 *
 */
@Profile("prod")
@Configuration
@ComponentScan(basePackages = "ph.fortunato.backend")
@PropertySource("classpath:${APP_ENV:prod}.properties")
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableTransactionManagement
@Import({AppSecurityConfig.class})
public class AppConfig extends WebMvcConfigurerAdapter {

	static{
		Logger.getLogger(AppConfig.class).info("Setting up application context configuration under PRODUCTION.");
	}
	
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
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
		sessionBuilder.scanPackages(hibernatePackageToScan);
		sessionBuilder.setProperty("hibernate.show_sql", hibernateShowSql);
		sessionBuilder.setProperty("hibernate.hbm2ddl.auto", hibernateHbm2DdlAuto);
		sessionBuilder.setProperty("hibernate.dialect", hibernateDialect);
		sessionBuilder.setNamingStrategy(getNamingStrategy());
		return sessionBuilder.buildSessionFactory();
	}
	
	@Bean
	public ImprovedNamingStrategy getNamingStrategy(){
		return new ImprovedNamingStrategy();
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
	    HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
	    return transactionManager;
	}
	
	@Value("${hibernate.package.to.scan}") private String hibernatePackageToScan;
	@Value("${hibernate.show.sql}") private String hibernateShowSql;
	@Value("${hibernate.hbm2ddl.auto}") private String hibernateHbm2DdlAuto;
	@Value("${hibernate.sql.dialect}") private String hibernateDialect;
	
}
