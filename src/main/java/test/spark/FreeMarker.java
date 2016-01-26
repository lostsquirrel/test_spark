package test.spark;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;
import test.spark.utils.JsonUtils;



public class FreeMarker {

	public static void main(String[] args) {
		FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
		Configuration freeMarkerConfiguration = new Configuration();
		freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(FreeMarker.class, "/"));
		freeMarkerEngine.setConfiguration(freeMarkerConfiguration);
		
		// get all post (using HTTP get method)
		get("/:name", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();
			
	        attributes.put("name", request.params("name"));
		    if (shouldReturnHtml(request)) {
		        response.status(200);
		        response.type("text/html");
		        
		        return freeMarkerEngine.render(new ModelAndView(attributes, "freemarker.ftl"));
		    } else {
		        response.status(200);
		        response.type("application/json");
		        return JsonUtils.toJsonString(attributes);
		    }
		});
	}
	
	
	private static boolean shouldReturnHtml(Request request) {
	    String accept = request.headers("Accept");
	    return accept != null && accept.contains("text/html");
	}
}
