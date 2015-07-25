/**
 * 
 */
package ph.fortunato.backend.home;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

/**
 * @author Sean Ross
 *
 */
@Path("/")
@Component
public class Home {

	  @GET
	  @Produces(MediaType.TEXT_PLAIN)
	  public String sayPlainTextHome() {
	    return "BackEnd is now Active";
	  }

	  @GET
	  @Produces(MediaType.TEXT_XML)
	  public String sayXMLHome() {
	    return "<?xml version=\"1.0\"?>" + "<home> BackEnd is now Active" + "</home>";
	  }

	  @GET
	  @Produces(MediaType.TEXT_HTML)
	  public String sayHtmlHome() {
	    return "<html> " 
	    		+  "<title>" 
	    			+ "BackEnd REST API" 
	    		+ "</title>"
	    		+ "<body>"
	    			+ "<h1>" 
	    				+ "BackEnd is now Active" 
	    			+ "</h1>"
    			+ "</body>" 
			+ "</html> ";
	  }
	
}
