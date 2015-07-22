/**
 * 
 */
package ph.fortunato.backend.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import ph.fortunato.backend.exceptions.NoDataSourcePropertyFoundException;

/**
 * @author Sean Ross
 *
 */
@Component
public class PropertiesUtil {
	
	static Logger log = Logger.getLogger(Properties.class);
	private static final String PROD_DATASOURCE_PROPERTIES_LOCATION = "/prod-datasource.properties";
	private static final String PROD_PROPERTIES_LOCATION = "/prod.properties";
	private static final String DEV_DATASOURCE_PROPERTIES_LOCATION = "/dev-datasource.properties";
	private static final String DEV_PROPERTIES_LOCATION = "/dev.properties";
	
	/**
	 * GETS DATASOURCE PROPERTIES
	 * @return
	 * @throws IOException
	 */
	public static Properties getDataSourceProperties(String env) throws IOException{
		Resource resource = new ClassPathResource(env.equals("dev")?DEV_DATASOURCE_PROPERTIES_LOCATION:PROD_DATASOURCE_PROPERTIES_LOCATION);
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		showLog("Displaying DB properties: ", props);
		return props;
	}
	
	/**
	 * GETS ROOT PROPERTIES
	 * @return
	 * @throws IOException
	 */
	public static Properties getRootProperties(String env) throws IOException{
		Resource resource = new ClassPathResource(env.equals("dev")?DEV_PROPERTIES_LOCATION:PROD_PROPERTIES_LOCATION);
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		showLog("Displaying ROOT properties: ", props);
		return props;
	}
	
	/**
	 * Log the contents of the property
	 * @param message
	 * @param props
	 */
	private static void showLog(String message, Properties props){
		log.debug(message);
		for(String key : props.stringPropertyNames()){
			log.debug(key + "="+ props.getProperty(key));
		}
	}
	
	/**
	 * Gets the default data source key from db.properties
	 * @return
	 * @throws IOException
	 */
	public static String getDefaultDataSourceKey(String env) throws IOException{
		Properties dbProps = PropertiesUtil.getDataSourceProperties(env);
		if(dbProps.isEmpty()) throw new NoDataSourcePropertyFoundException();
		Properties rootProps = PropertiesUtil.getRootProperties(env);
		String candidate = "";
		if(NullChecker.isEmpty(rootProps.get("common.datasource.default"))) {
			log.warn("No default datasource key written in root.properties. Selecting the first datasource key found in datasources.properties.");
			for(String key : dbProps.stringPropertyNames()){
				candidate = key;
				break; //only 1 is needed
			}
		}else {
			candidate = rootProps.get("common.datasource.default").toString();
		}
		return candidate;
	}
}
