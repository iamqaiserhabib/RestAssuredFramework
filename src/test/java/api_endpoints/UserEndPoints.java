package api_endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api_payload.User;

public class UserEndPoints {

	public static Response createUser(User payload){
		Response response= given()
		       .contentType(ContentType.JSON)
		       .accept(ContentType.JSON)
		       .body(payload)
		.when()
		       .post(Routes.post_url);
		return response;
	}
	
	public static Response readUser(String username){
		Response response= given()
				.pathParam("username", username)
		.when()
		       .get(Routes.get_url);
		return response;
	}
	
	public static Response updateUser(String username, User payload){
		Response response= given()
		       .contentType(ContentType.JSON)
		       .accept(ContentType.JSON)
		       .pathParam("username", username)
		       .body(payload)
		.when()
		       .put(Routes.update_url);
		return response;
	}
	
	public static Response deleteUser(String username){
		Response response= given()
				.pathParam("username", username)
		.when()
		       .delete(Routes.delete_url);
		return response;
	}
}
