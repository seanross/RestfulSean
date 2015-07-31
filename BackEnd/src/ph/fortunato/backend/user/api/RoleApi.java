/**
 * 
 */
package ph.fortunato.backend.user.api;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ph.fortunato.backend.generic.api.GenericApi;
import ph.fortunato.backend.generic.bo.GenericBo;
import ph.fortunato.backend.user.bo.RoleBo;
import ph.fortunato.backend.user.domain.Role;

/**
 * @author Sean Ross
 *
 */
@Path("role")
@Component
public class RoleApi extends GenericApi<Role, Long> {

	RoleBo roleBo;
	
	@Autowired
	public RoleApi(@Qualifier("roleBo") GenericBo<Role, Long> genericBo){
		super(genericBo);
        this.roleBo = (RoleBo) genericBo;
	}

}
