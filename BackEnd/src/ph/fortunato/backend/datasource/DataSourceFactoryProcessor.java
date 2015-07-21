/**
 * 
 */
package ph.fortunato.backend.datasource;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.stereotype.Component;

import com.jolbox.bonecp.BoneCPDataSource;

import ph.fortunato.backend.utils.PropertiesUtil;

/**
 * @author Sean Ross
 *
 */
@Component
public class DataSourceFactoryProcessor implements BeanFactoryPostProcessor {

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
		Properties dbProps = PropertiesUtil.getDataSourceProperties();
		Properties rootProps = PropertiesUtil.getRootProperties();
		
		/**
		 * REGISTER BEANS USING KEYS
		 */
		for(String key : dbProps.stringPropertyNames()){
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
		mutablePropertyValues.addPropertyValue("defaultTargetDataSource", new RuntimeBeanReference(PropertiesUtil.getDefaultDataSourceKey() + "DataSource"));
		
		ManagedMap<String, RuntimeBeanReference> mm = new ManagedMap<String, RuntimeBeanReference>();

        for(String key : dbProps.stringPropertyNames()){
            mm.put(key, new RuntimeBeanReference(key + "DataSource"));
        }

		mutablePropertyValues.addPropertyValue("targetDataSources", mm);

	}
}
