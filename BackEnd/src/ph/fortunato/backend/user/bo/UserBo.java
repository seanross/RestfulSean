/**
 * 
 */
package ph.fortunato.backend.user.bo;

import ph.fortunato.backend.generic.bo.GenericBo;
import ph.fortunato.backend.user.domain.User;
import ph.fortunato.backend.user.dto.UserRolesDto;

/**
 * @author Sean Ross
 *
 */
public interface UserBo extends GenericBo<User, Long>{
	UserRolesDto getCustomUser();
//	public boolean removeAdmin(Integer id);
//    public boolean isAdminRegistered(String userName, String password);
//    public Admin getAdmin(String userName);
}
