/**
 * 
 */
package ph.fortunato.backend.user.dto;

import java.util.List;

import javax.management.relation.Role;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sean Ross
 *
 */
@XmlRootElement
public class UserRolesDto {

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
