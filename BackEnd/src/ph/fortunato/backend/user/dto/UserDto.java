/**
 * 
 */
package ph.fortunato.backend.user.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sean Ross
 *
 */
@XmlRootElement
public class UserDto {

	private String username;
	private List<RoleDto> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}
	
	
	
}
