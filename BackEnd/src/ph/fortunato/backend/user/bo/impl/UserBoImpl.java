/**
 * 
 */
package ph.fortunato.backend.user.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ph.fortunato.backend.generic.bo.impl.GenericBoImpl;
import ph.fortunato.backend.generic.dao.GenericDao;
import ph.fortunato.backend.user.bo.UserBo;
import ph.fortunato.backend.user.dao.UserDao;
import ph.fortunato.backend.user.domain.User;

/**
 * @author Sean Ross
 *
 */
@Service("userBo")
public class UserBoImpl extends GenericBoImpl<User, Long> implements UserBo {

	UserDao userDao;
	
	@Autowired
	public UserBoImpl(@Qualifier("userDao") GenericDao<User, Long> genericDao){
		super(genericDao);
        this.userDao = (UserDao) genericDao;
	}
	
	public UserBoImpl(){
		
	}
	
//	@Override
//    @Transactional(propagation = Propagation.REQUIRED)
//    public boolean removeAdmin(Integer id) {
//        return adminDao.removeAdmin(id);
//    }
// 
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
//    public boolean isAdminRegistered(String userName, String password) {
//        return adminDao.isAdminRegistered(userName, password);
//    }
// 
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
//    public Admin getAdmin(String userName) {
//        return adminDao.getAdmin(userName);
//    }

}
