/**
 * 
 */
package ph.fortunato.backend.datasource;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.jolbox.bonecp.BoneCPDataSource;

import ph.fortunato.backend.utils.PropertiesUtil;

/**
 * @author Sean Ross
 *
 */
@Component
public class DataSourceFactoryProcessor implements BeanFactoryPostProcessor, EnvironmentAware {

	Environment env;
	

	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		try {
			registerCompanyDataSourceByProperties(beanFactory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * REGISTER BEANS USING FILE PROPERTIES
	 * @param beanFactory
	 * @throws IOException
	 * @throws NoDataSourcePropertyFoundException 
	 */
	private void registerCompanyDataSourceByProperties(ConfigurableListableBeanFactory beanFactory) throws IOException{
		BeanDefinitionRegistry factory = (BeanDefinitionRegistry) beanFactory;
		BeanDefinitionBuilder datasourceDefinitionBuilder;
		Properties dbProps = PropertiesUtil.getDataSourceProperties(Arrays.asList(env.getActiveProfiles()).contains("dev")?"dev":"prod");
		Properties rootProps = PropertiesUtil.getRootProperties(Arrays.asList(env.getActiveProfiles()).contains("dev")?"dev":"prod");
		
		/**
		 * REGISTER BEANS USING KEYS
		 */
		for(String key : dbProps.stringPropertyNames()){
			
//			String arr[] = key.split("\\.", 0);
//			for(String x : arr){
//				Logger.getLogger(this.getClass()).info( key.split("\\.", 3)[2]);
//			}
//			 String[] result = key.split("\\.");
//		     for (int x=0; x<result.length; x++)
//		         System.out.println(result[x]);
			datasourceDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(BoneCPDataSource.class);
			datasourceDefinitionBuilder.addPropertyValue("jdbcUrl", dbProps.getProperty(key));
			datasourceDefinitionBuilder.addPropertyValue("user", rootProps.getProperty("common.datasource.user"));
			datasourceDefinitionBuilder.addPropertyValue("password", rootProps.getProperty("common.datasource.password"));
			datasourceDefinitionBuilder.addPropertyValue("driverClass", rootProps.getProperty("common.datasource.driverClass"));
			datasourceDefinitionBuilder.addPropertyValue("idleMaxAgeInMinutes", rootProps.getProperty("common.datasource.idleMaxAgeInMinutes"));
			datasourceDefinitionBuilder.addPropertyValue("idleConnectionTestPeriodInMinutes", rootProps.getProperty("common.datasource.idleConnectionTestPeriodInMinutes"));
			datasourceDefinitionBuilder.addPropertyValue("maxConnectionsPerPartition", rootProps.getProperty("common.datasource.maxConnectionsPerPartition"));
			datasourceDefinitionBuilder.addPropertyValue("minConnectionsPerPartition", rootProps.getProperty("common.datasource.minConnectionsPerPartition"));
			datasourceDefinitionBuilder.addPropertyValue("partitionCount", rootProps.getProperty("common.datasource.partitionCount"));
			datasourceDefinitionBuilder.addPropertyValue("acquireIncrement", rootProps.getProperty("common.datasource.acquireIncrement"));
			datasourceDefinitionBuilder.addPropertyValue("statementsCacheSize", rootProps.getProperty("common.datasource.statementsCacheSize"));

			factory.registerBeanDefinition(key + "DataSource", datasourceDefinitionBuilder.getBeanDefinition());
		}
		
		MutablePropertyValues mutablePropertyValues = factory.getBeanDefinition("backEndRoutingDataSource").getPropertyValues();
		mutablePropertyValues.removePropertyValue("defaultTargetDataSource");
		
		//GET the default key for datasource AND MAKE IT AS THE DEFAULT DATASOURCE
		mutablePropertyValues.addPropertyValue("defaultTargetDataSource", new RuntimeBeanReference(PropertiesUtil.getDefaultDataSourceKey(Arrays.asList(env.getActiveProfiles()).contains("dev")?"dev":"prod") + "DataSource"));
		
		ManagedMap<String, RuntimeBeanReference> mm = new ManagedMap<String, RuntimeBeanReference>();

        for(String key : dbProps.stringPropertyNames()){
            mm.put(key, new RuntimeBeanReference(key + "DataSource"));
        }

		mutablePropertyValues.addPropertyValue("targetDataSources", mm);

	}

	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}
}
