package ph.fortunato.backend.configuration.prod;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	
}
