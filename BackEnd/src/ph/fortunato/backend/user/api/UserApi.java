/**
 * 
 */
package ph.fortunato.backend.user.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.fortunato.backend.generic.domain.Count;
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
	@Path("get")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response get(
			@QueryParam("page") int page,
			@QueryParam("size") int size,
			@QueryParam("column") String column,
			@QueryParam("isAscending") boolean isAscending) {
		List<User> users = userBo.get(page, size, column, isAscending);
		GenericEntity<List<User>> wrapper = new GenericEntity<List<User>>(users){
			//GenericEntity is used to wrap collection of objects
		};
		return Response.ok(wrapper).build();
	}
	
	@GET
	@Path("count")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response count() {
		return Response.ok(new Count(userBo.count())).build();
	}
}
