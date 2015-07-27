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
public class Wrapper {

	private String content;

	public Wrapper(String content){
		this.content = content;
	}
	
	public Wrapper(){
		
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public static Wrapper message(String content){
		return new Wrapper(content); 
	}
}
