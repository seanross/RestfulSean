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

	void add(E entity) ;
    void saveOrUpdate(E entity) ;
    void update(E entity) ;
    void remove(E entity);
    E find(K key);
    List<E> get(int page, int size, String column, boolean isAscending);
    Long count();
}
