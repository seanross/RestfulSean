/**
 * 
 */
package ph.fortunato.backend.generic.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sean Ross
 *
 */
@XmlRootElement
public class Count {

	private Long value;

	public Count(Long value){
		this.value = value;
	}
	
	public Count(){
		
	}
	
	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
}
