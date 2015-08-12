/**
 * 
 */
package ph.fortunato.backend.configuration.dev;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Sean Ross
 *
 */
@Profile("dev")
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	static{
		Logger.getLogger(AppSecurityConfig.class).info("Setting up spring security configuration under DEVELOPMENT.");
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
 
		http.authorizeRequests().antMatchers("/**").permitAll();
//		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//		.and().formLogin()
//		.loginPage("/login")
//		.failureUrl("/login?error")
//		.usernameParameter("username")
//		.passwordParameter("password")
//		.and().logout().logoutSuccessUrl("/login?logout")
//		.and().csrf()
//		.and().exceptionHandling().accessDeniedPage("/403");
		http.csrf().disable();
	}
}
