/**
 * 
 */
package ph.fortunato.backend.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import ph.fortunato.backend.datasource.dto.DataSourceAttrDto;
import ph.fortunato.backend.datasource.dto.DataSourceDto;
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
	 * GETS DATASOURCE PROPERTIES
	 * @return
	 * @throws IOException
	 */
	public static Properties getDataSourceProperties(Environment env) throws IOException{
		return PropertiesUtil.getDataSourceProperties(Arrays.asList(env.getActiveProfiles()).contains("dev")?"dev":"prod");
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
	 * GETS ROOT PROPERTIES
	 * @return
	 * @throws IOException
	 */
	public static Properties getRootProperties(Environment env) throws IOException{
		return PropertiesUtil.getRootProperties(Arrays.asList(env.getActiveProfiles()).contains("dev")?"dev":"prod");
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
		if(NullChecker.isEmpty(rootProps.get("default.datasource.key"))) {
			log.warn("No default datasource key written in root.properties. Selecting the first datasource key found in datasources.properties.");
			for(String key : dbProps.stringPropertyNames()){
				candidate = key;
				break; //only 1 is needed
			}
		}else {
			candidate = rootProps.get("default.datasource.key").toString();
		}
		return candidate;
	}
	
	/**
	 * A tokenizer for converting value paired property into manageable datasource DTO
	 * @param props
	 * @return
	 */
	public static List<DataSourceDto> dataSourcePropertiesTokenizer(Properties props){
		List<DataSourceDto> dataSources = new ArrayList<>();
		
		List<String> keys = new ArrayList<>();
		for(String prop : props.stringPropertyNames()){
			String targetKey = prop.split("\\.", 3)[0];
			if(!keys.contains(targetKey.split("\\.", 3)[0])) keys.add(targetKey.split("\\.", 3)[0]);
		}
		
		for(String key : keys){
			DataSourceDto datasource = new DataSourceDto();
			
			datasource.setDataSourceKey(key);
			List<DataSourceAttrDto> attrList = new ArrayList<>();
			
			for(String prop : props.stringPropertyNames()){
				String targetKey = prop.split("\\.", 3)[0];
				if(key.equals(targetKey)){
					DataSourceAttrDto attr = new DataSourceAttrDto();
					attr.setKey(prop.split("\\.", 3)[2]);
					attr.setProp(props.getProperty(prop));
					attrList.add(attr);
				}
			}
			datasource.setDataSourceAttr(attrList);
			dataSources.add(datasource);
		}

		return dataSources;
	}
}
