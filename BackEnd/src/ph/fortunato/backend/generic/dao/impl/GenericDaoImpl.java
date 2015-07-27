/**
 * 
 */
package ph.fortunato.backend.generic.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ph.fortunato.backend.generic.dao.GenericDao;
import ph.fortunato.backend.utils.NullChecker;

/**
 * @author Sean Ross
 *
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@Repository("genericDao")
public abstract class GenericDaoImpl<E, K extends Serializable> implements GenericDao<E, K> {

	@Autowired
    SessionFactory sessionFactory;
	
	protected Class<? extends E> daoType;
    
    /**
    * By defining this class as abstract, we prevent Spring from creating 
    * instance of this class If not defined as abstract, 
    * getClass().getGenericSuperClass() would return Object. There would be 
    * exception because Object class does not java constructor with parameters.
    */
	public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
    }
     
    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
     
    @Override
    public void create(E entity) {
        currentSession().save(entity);
    }
     
    @Override
    public void saveOrUpdate(E entity) {
        currentSession().saveOrUpdate(entity);
    }
     
    @Override
    public void update(E entity) {
        currentSession().saveOrUpdate(entity);
    }
     
    @Override
    public void remove(E entity) {
        currentSession().delete(entity);
    }
    
    @Override
    public void disable(K key){
    	Query hql = currentSession().createQuery("update "+ daoType.getName() +" set isDisabled = :isDisabled where id = :id");
    	hql.setParameter("isDisabled", true);
    	hql.setParameter("id", key);
    	hql.executeUpdate();
    }
     
    @Override
    public E find(K key) {
    	Criteria criteria = currentSession().createCriteria(daoType);
    	criteria.add(Property.forName("id").eq(key));
    	criteria.add(Restrictions.isNull("isDisabled"));
        return (E) criteria.uniqueResult();
    }
    
    @Override
    public List<E> get(int page, int size, String column, boolean isAscending){
    	Criteria criteria = currentSession().createCriteria(daoType);
    	criteria.add(Restrictions.isNull("isDisabled"));
    	if(!NullChecker.isEmpty(column)){
	        if (isAscending) {
	            criteria.addOrder(Order.asc(column));
	        } else {
	            criteria.addOrder(Order.desc(column));
	        }
	    } else {
	        if (isAscending) {
	            criteria.addOrder(Order.asc("id"));
	            criteria.addOrder(Order.asc("updatedDate"));
	        } else {
	            criteria.addOrder(Order.desc("id"));
	            criteria.addOrder(Order.desc("updatedDate"));
	        }
	    }
    	criteria.setFirstResult((page - 1) * size);
    	criteria.setMaxResults(size);
	    return criteria.list();
    }

    @Override
    public Long count(){
    	Criteria criteria = currentSession().createCriteria(daoType);
    	criteria.add(Restrictions.isNull("isDisabled"))
	    		.setProjection(Projections.rowCount());
    	return (Long) criteria.uniqueResult();
    }
}
