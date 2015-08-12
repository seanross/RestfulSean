/**
 * 
 */
package ph.fortunato.backend;

import java.util.EnumSet;
import java.util.Set;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;



/**
 * @author Sean Ross
 *
 */
public class BackEndInitializer implements WebApplicationInitializer {


	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		 final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		    context.setConfigLocation("ph.fortunato.backend.configuration");

		    final FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter());
		    characterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
		    characterEncodingFilter.setInitParameter("encoding", "UTF-8");
		    characterEncodingFilter.setInitParameter("forceEncoding", "true");

		    servletContext.addListener(new ContextLoaderListener(context));
		    servletContext.setInitParameter("spring.profiles.default", "prod");
		    servletContext.setInitParameter("spring.profiles.active", "prod");
		    
		    final SpringServlet servlet = new SpringServlet();

		    final ServletRegistration.Dynamic appServlet = servletContext.addServlet("appServlet", servlet);
		    appServlet.setInitParameter("com.sun.jersey.config.property.packages", "ph.fortunato.backend");
		    appServlet.setLoadOnStartup(1);

		    final Set<String> mappingConflicts = appServlet.addMapping("/*");

		    if (!mappingConflicts.isEmpty()) {
		        throw new IllegalStateException("'appServlet' cannot be mapped to '/' under Tomcat versions <= 7.0.14");
		    }
	}

}
