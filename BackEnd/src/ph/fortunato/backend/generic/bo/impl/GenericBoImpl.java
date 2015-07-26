/**
 * 
 */
package ph.fortunato.backend.generic.bo.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ph.fortunato.backend.generic.bo.GenericBo;
import ph.fortunato.backend.generic.dao.GenericDao;

/**
 * @author Sean Ross
 *
 */
@Service("genericBo")
public abstract class GenericBoImpl<E, K> implements GenericBo<E, K> {

	GenericDao<E, K> genericDao;
	 
    public GenericBoImpl(GenericDao<E,K> genericDao) {
        this.genericDao=genericDao;
    }
 
    public GenericBoImpl() {
    }
 
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(E entity) {
        genericDao.saveOrUpdate(entity);
    }
 
 
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public E get(K key) {
        return genericDao.find(key);
    }
 
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(E entity) {
        genericDao.add(entity);
    }
 
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(E entity) {
        genericDao.update(entity);
    }
 
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void remove(E entity) {
        genericDao.remove(entity);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void disable(K key) {
        genericDao.disable(key);
    }
    
    
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<E> get(int page, int size, String column, boolean isAscending){
    	return genericDao.get(page, size, column, isAscending);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Long count(){
    	return genericDao.count();
    }
}
