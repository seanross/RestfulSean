/**
 * 
 */
package ph.fortunato.backend.generic.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sean Ross
 *
 */
@XmlRootElement
public class Message<T> {

	private T content;

	public Message(T content){
		this.content = content;
	}
	
	public Message(){
		
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}
	
	
	
}
