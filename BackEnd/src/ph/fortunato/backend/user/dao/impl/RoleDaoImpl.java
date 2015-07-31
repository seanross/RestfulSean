/**
 * 
 */
package ph.fortunato.backend.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import ph.fortunato.backend.generic.dao.impl.GenericDaoImpl;
import ph.fortunato.backend.user.dao.RoleDao;
import ph.fortunato.backend.user.domain.Role;

/**
 * @author Sean Ross
 *
 */
@Repository("roleDao")
public class RoleDaoImpl extends GenericDaoImpl<Role, Long>implements RoleDao {

}
