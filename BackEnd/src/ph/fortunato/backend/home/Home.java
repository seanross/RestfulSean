/**
 * 
 */
package ph.fortunato.backend.home;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.fortunato.backend.utils.TokenUtil;

/**
 * @author Sean Ross
 *
 */
@Path("/")
@Component
public class Home {
	
	@Autowired
	TokenUtil util;

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
		  Logger.getLogger(this.getClass()).info(util.getUserNameFromToken("sean"));
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
