package api_test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api_endpoints.UserEndPoints;
import api_endpoints.UserEndPoints2;
import api_payload.User;
import io.restassured.response.Response;

public class UserTests2 {

	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername("qauser_" + System.currentTimeMillis());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//log
		logger = LogManager.getLogger(this.getClass());
		logger.debug("Debuging..................");
	}
	
	@Test(priority=1)
	public void testPostUser() {
		
		logger.info("**************** Creating User **********************");
		
		Response response = UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**************** User is created **********************");
	}
	
	@Test(priority = 2, dependsOnMethods = "testPostUser")
	public void testGetUserByName() throws InterruptedException {
		
		logger.info("**************** Reading User Info **********************");
		
		Thread.sleep(3000);
		Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**************** User Info is Displayed **********************");
	}
	
	@Test(priority = 3, dependsOnMethods = "testPostUser")
	public void testUpdateUserByName() {
		
		logger.info("**************** Updating User **********************");
		
		//update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//checking data after update
		Response responseAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
		logger.info("**************** User is Updated **********************");
	}
	
	@Test(priority = 4, dependsOnMethods = "testPostUser")
	public void deleteUserByName() {
		
		logger.info("**************** Deleting User **********************");
		
		Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**************** User is Deleted **********************");
	}
}
