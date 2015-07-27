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

	void create(E entity) ;
    void saveOrUpdate(E entity) ;
    void update(E entity) ;
    void remove(E entity);
    void disable(K key);
    E find(K key);
    List<E> get(int page, int size, String column, boolean isAscending);
    Long count();
}
