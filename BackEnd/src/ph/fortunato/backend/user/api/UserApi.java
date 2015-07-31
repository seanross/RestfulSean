/**
 * 
 */
package ph.fortunato.backend.user.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.JResponse;

import ph.fortunato.backend.generic.api.GenericApi;
import ph.fortunato.backend.generic.bo.GenericBo;
import ph.fortunato.backend.generic.dto.Wrapper;
import ph.fortunato.backend.user.bo.RoleBo;
import ph.fortunato.backend.user.bo.UserBo;
import ph.fortunato.backend.user.domain.Role;
import ph.fortunato.backend.user.domain.User;
import ph.fortunato.backend.user.dto.UserDto;


/**
 * @author Sean Ross
 *
 */
@Path("user")
@Component
public class UserApi extends GenericApi<User, Long>{

	UserBo userBo;
	
	@Autowired
	public UserApi(@Qualifier("userBo") GenericBo<User, Long> genericBo){
		super(genericBo);
        this.userBo = (UserBo) genericBo;
	}

}
