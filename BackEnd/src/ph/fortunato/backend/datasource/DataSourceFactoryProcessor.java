/**
 * 
 */
package ph.fortunato.backend.datasource;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
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

import ph.fortunato.backend.datasource.dto.DataSourceAttrDto;
import ph.fortunato.backend.datasource.dto.DataSourceDto;
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

		/**
		 * REGISTER BEANS USING KEYS
		 */
		for(DataSourceDto ds : PropertiesUtil.dataSourcePropertiesTokenizer(dbProps)){
			datasourceDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(BoneCPDataSource.class);
			for(DataSourceAttrDto attr : ds.getDataSourceAttr()){
				datasourceDefinitionBuilder.addPropertyValue(attr.getKey(), attr.getProp());
			}
			factory.registerBeanDefinition(ds.getDataSourceKey() + "DataSource", datasourceDefinitionBuilder.getBeanDefinition());
		}
		
		MutablePropertyValues mutablePropertyValues = factory.getBeanDefinition("backEndRoutingDataSource").getPropertyValues();
		mutablePropertyValues.removePropertyValue("defaultTargetDataSource");
		
		//GET the default key for datasource AND MAKE IT AS THE DEFAULT DATASOURCE
		mutablePropertyValues.addPropertyValue("defaultTargetDataSource", new RuntimeBeanReference(PropertiesUtil.getDefaultDataSourceKey(Arrays.asList(env.getActiveProfiles()).contains("dev")?"dev":"prod") + "DataSource"));
		
		ManagedMap<String, RuntimeBeanReference> mm = new ManagedMap<String, RuntimeBeanReference>();

		for(DataSourceDto ds : PropertiesUtil.dataSourcePropertiesTokenizer(dbProps)){
			mm.put(ds.getDataSourceKey(), new RuntimeBeanReference(ds.getDataSourceKey() + "DataSource"));
		}
		mutablePropertyValues.addPropertyValue("targetDataSources", mm);

	}

	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}
	
}
