/**
 * 
 */
package ph.fortunato.backend.configuration.prod;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Sean Ross
 *
 */
@Profile("prod")
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	static{
		Logger.getLogger(AppConfig.class).info("Setting up spring security configuration under PRODUCTION.");
	}
	
}
