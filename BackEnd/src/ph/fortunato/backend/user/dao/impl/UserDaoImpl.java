/**
 * 
 */
package ph.fortunato.backend.user.dao.impl;

import org.springframework.stereotype.Repository;


import ph.fortunato.backend.generic.dao.impl.GenericDaoImpl;
import ph.fortunato.backend.user.dao.UserDao;
import ph.fortunato.backend.user.domain.User;

/**
 * @author Sean Ross
 *
 */
@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

//	@Override
//    public boolean removeAdmin(Integer id) {
//        Query employeeTaskQuery = currentSession().createQuery(
//                "from Admin u where :id");
//        employeeTaskQuery.setParameter("id", id);
//        return employeeTaskQuery.executeUpdate() > 0;
//    }
// 
//    @Override
//    public boolean isAdminRegistered(String userName, String password) {
//        /*You can use any character instead of 'A'. If a record is found, 
//        only single character, in this example 'A', will return from database
//        */
//        Query employeeTaskQuery = currentSession().createQuery(
//                "select 'A' from Admin u where username=:username and password=:password");
//        employeeTaskQuery.setParameter("username", userName);
//        employeeTaskQuery.setParameter("password", password);
//        return employeeTaskQuery.list().size() > 0;
//    }
// 
//    @Override
//    public Admin getAdmin(String username) {
//        Query query = currentSession().createQuery(
//                "from Admin " +
//                        "where username=:username");
//        query.setParameter("username", username);
//        return (Admin) query.uniqueResult();
// 
//    }
    
}
