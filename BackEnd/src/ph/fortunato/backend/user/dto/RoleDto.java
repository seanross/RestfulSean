package ph.fortunato.backend.user.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Sean Ross
 *
 */
@XmlRootElement
public class RoleDto {

	private String description;
	private String code;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
