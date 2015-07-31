/**
 * 
 */
package ph.fortunato.backend.user.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ph.fortunato.backend.generic.bo.impl.GenericBoImpl;
import ph.fortunato.backend.generic.dao.GenericDao;
import ph.fortunato.backend.user.bo.RoleBo;
import ph.fortunato.backend.user.dao.RoleDao;
import ph.fortunato.backend.user.domain.Role;

/**
 * @author Sean Ross
 *
 */
@Service("roleBo")
public class RoleBoImpl extends GenericBoImpl<Role, Long>implements RoleBo {
	
	RoleDao roleDao;
	
	@Autowired
	public RoleBoImpl(@Qualifier("roleDao") GenericDao<Role, Long> genericDao){
		super(genericDao);
        this.roleDao = (RoleDao) genericDao;
	}
}
