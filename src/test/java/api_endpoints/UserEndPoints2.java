package api_endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api_payload.User;

public class UserEndPoints2 {
	
	static ResourceBundle getURL(){
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}

	public static Response createUser(User payload){
		
		String post_url = getURL().getString("post_url");
		
		Response response= given()
		       .contentType(ContentType.JSON)
		       .accept(ContentType.JSON)
		       .body(payload)
		.when()
		       .post(post_url);
		return response;
	}
	
	public static Response readUser(String username){
		
		String get_url = getURL().getString("get_url");
		
		Response response= given()
				.pathParam("username", username)
		.when()
		       .get(get_url);
		return response;
	}
	
	public static Response updateUser(String username, User payload){
		
		String update_url = getURL().getString("update_url");
		
		Response response= given()
		       .contentType(ContentType.JSON)
		       .accept(ContentType.JSON)
		       .pathParam("username", username)
		       .body(payload)
		.when()
		       .put(update_url);
		return response;
	}
	
	public static Response deleteUser(String username){
		
		String delete_url = getURL().getString("delete_url");
		
		Response response= given()
				.pathParam("username", username)
		.when()
		       .delete(delete_url);
		return response;
	}
}
