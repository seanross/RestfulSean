/**
 * 
 */
package ph.fortunato.backend.generic.api;

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

import com.sun.jersey.api.JResponse;

import ph.fortunato.backend.generic.bo.GenericBo;
import ph.fortunato.backend.generic.dto.Wrapper;

/**
 * @author Sean Ross
 *
 */
public abstract class GenericApi<E, K> {

	GenericBo<E, K> genericBo;
	 
    public GenericApi(GenericBo<E,K> genericBo) {
        this.genericBo=genericBo;
    }
	
    /**
     * Fetch the generic list
     * @param page
     * @param size
     * @param column
     * @param isAscending
     * @return
     */
    @GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public JResponse<List<E>> get(
			@QueryParam("page") int page,
			@QueryParam("size") int size,
			@QueryParam("column") String column,
			@QueryParam("isAscending") boolean isAscending) {
		List<E> list = genericBo.get(page, size, column, isAscending);
		return JResponse.ok(list).build();
	}
	
    /**
     * Get generic object by id
     * @param id
     * @return
     */
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public JResponse<E> get(@PathParam("id") K id){
		return JResponse.ok(genericBo.get(id)).build();
	}
	
	/**
	 * Creates generic entity
	 * @param entity
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public JResponse<Wrapper> create(E entity){
		genericBo.create(entity);
		return JResponse.ok(Wrapper.message(entity.getClass().getName() + " created " + entity)).build();
	}
	
	/**
	 * Updates generic entity
	 * @param entity
	 * @return
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public JResponse<Wrapper> update(E entity){
		genericBo.update(entity);
		return JResponse.ok(Wrapper.message(entity.getClass().getName() + " updated " + entity)).build();
	}
	
	/**
	 * Disables generic entity
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public JResponse<Wrapper> disable(@PathParam("id") K id){
		genericBo.disable(id);
		return JResponse.ok(Wrapper.message("disabled")).build();
	}
	
	/**
	 * Counts the size of generic entities
	 * @return
	 */
	@GET
	@Path("count")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public JResponse<Wrapper> count() {
		return JResponse.ok(Wrapper.message(genericBo.count().toString())).build();
	}
}
