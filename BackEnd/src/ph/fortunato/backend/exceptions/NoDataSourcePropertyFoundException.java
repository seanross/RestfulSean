/**
 * 
 */
package ph.fortunato.backend.exceptions;

import org.springframework.beans.BeansException;

/**
 * @author Sean Ross
 *
 */
public class NoDataSourcePropertyFoundException extends BeansException {

	final static String MESSAGE = "No datasource keys and values found in datasources.properties. "
			+ "Make sure you write atleast one datasource property. ";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoDataSourcePropertyFoundException(){
		super(MESSAGE);
	}
	
	public NoDataSourcePropertyFoundException(String message){
		super(MESSAGE + message);
	}
	
}
