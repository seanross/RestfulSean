/**
 * 
 */
package ph.fortunato.backend.generic.dao;

import java.util.List;

/**
 * @author Sean Ross
 *
 */
public interface GenericDao<E, K> {

	public void add(E entity) ;
    public void saveOrUpdate(E entity) ;
    public void update(E entity) ;
    public void remove(E entity);
    public E find(K key);
    public List<E> getAll() ;
    
}
