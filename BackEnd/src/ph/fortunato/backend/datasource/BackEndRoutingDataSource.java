/**
 * 
 */
package ph.fortunato.backend.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

/**
 * @author Sean Ross
 *
 */
@Component("backEndRoutingDataSource")
public class BackEndRoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return "DEFAULT";
	}

}
