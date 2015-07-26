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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.fortunato.backend.generic.dto.Count;
import ph.fortunato.backend.generic.dto.Message;
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
	@Path("get/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response get(@PathParam("id") Long id){
		return Response.ok(userBo.get(id)).build();
	}
	
	@POST
	@Path("add")	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response add(User user){
		userBo.add(user);
		return Response.ok(new Message<String>("User saved " + user)).build();
	}
	
	@PUT
	@Path("update")	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response update(User user){
		userBo.saveOrUpdate(user);
		return Response.ok(new Message<String>("User updated " + user)).build();
	}
	
	@DELETE
	@Path("disable/{id}")	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response disable(@PathParam("id") Long id){
		userBo.disable(id);
		return Response.ok(new Message<String>("User disabled")).build();
	}
	
	@GET
	@Path("count")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response count() {
		return Response.ok(new Count(userBo.count())).build();
	}
}
