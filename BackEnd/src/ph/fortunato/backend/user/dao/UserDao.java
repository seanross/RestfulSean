/**
 * 
 */
package ph.fortunato.backend.user.dao;

import ph.fortunato.backend.generic.dao.GenericDao;
import ph.fortunato.backend.user.domain.User;
import ph.fortunato.backend.user.dto.UserRolesDto;

/**
 * @author Sean Ross
 *
 */
public interface UserDao extends GenericDao<User, Long> {
	
	UserRolesDto getCustomUser();
//	public boolean removeAdmin(Integer id);
//	public boolean isAdminRegistered(String userName, String password);
//	public Admin getAdmin(String username);
	    
}
