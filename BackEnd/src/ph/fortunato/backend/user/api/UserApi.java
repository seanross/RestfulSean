/**
 * 
 */
package ph.fortunato.backend.user.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.fortunato.backend.user.bo.UserBo;
import ph.fortunato.backend.user.domain.User;


/**
 * @author Sean Ross
 *
 */
@Path("user")
@Component
public class UserApi {

	@Autowired
	UserBo userBo;

	@GET
	@Path("list")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getUserList() {
		GenericEntity<List<User>> entity = new GenericEntity<List<User>>(userBo.getAll()) {};
		return Response.status(200).entity(entity).build();
	}
}
