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

	public void saveOrUpdate(E entity);
    public List<E> getAll();
    public E get(K id);
    public void add(E entity);
    public void update(E entity);
    public void remove(E entity);
    
}
