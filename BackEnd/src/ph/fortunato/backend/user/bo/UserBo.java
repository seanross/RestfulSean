/**
 * 
 */
package ph.fortunato.backend.user.bo;

import java.util.List;

import ph.fortunato.backend.generic.bo.GenericBo;
import ph.fortunato.backend.user.domain.User;
import ph.fortunato.backend.user.dto.UserDto;

/**
 * @author Sean Ross
 *
 */
public interface UserBo extends GenericBo<User, Long>{
	UserDto getCustomUser();
	List<User> getInitializedUser(int page, int size, String column, boolean isAscending);
//	public boolean removeAdmin(Integer id);
//    public boolean isAdminRegistered(String userName, String password);
//    public Admin getAdmin(String userName);
}
