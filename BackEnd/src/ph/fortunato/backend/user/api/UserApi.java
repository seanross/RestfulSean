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
import org.springframework.stereotype.Component;

import com.sun.jersey.api.JResponse;

import ph.fortunato.backend.generic.dto.Wrapper;
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
	public JResponse<List<User>> get(
			@QueryParam("page") int page,
			@QueryParam("size") int size,
			@QueryParam("column") String column,
			@QueryParam("isAscending") boolean isAscending) {
		List<User> users = userBo.get(page, size, column, isAscending);
		return JResponse.ok(users).build();
	}

	@GET
	@Path("get/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public JResponse<User> get(@PathParam("id") Long id){
		return JResponse.ok(userBo.get(id)).build();
	}
	
	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public JResponse<Wrapper> create(User user){
		userBo.create(user);
		return JResponse.ok(Wrapper.message("User created " + user)).build();
	}
	
	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public JResponse<Wrapper> update(User user){
		userBo.update(user);
		return JResponse.ok(Wrapper.message("User updated " + user)).build();
	}
	
	@DELETE
	@Path("disable/{id}")	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public JResponse<Wrapper> disable(@PathParam("id") Long id){
		userBo.disable(id);
		return JResponse.ok(Wrapper.message("User disabled")).build();
	}
	
	@GET
	@Path("count")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public JResponse<Wrapper> count() {
		return JResponse.ok(Wrapper.message(userBo.count().toString())).build();
	}
}
