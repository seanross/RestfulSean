/**
 * 
 */
package ph.fortunato.backend.generic.bo;

import java.util.List;

/**
 * @author Sean Ross
 *
 */
public interface GenericBo<E, K> {

	void saveOrUpdate(E entity);
    E get(K id);
    void add(E entity);
    void update(E entity);
    void remove(E entity);
    List<E> get(int page, int size, String column, boolean isAscending);
    Long count();
}
