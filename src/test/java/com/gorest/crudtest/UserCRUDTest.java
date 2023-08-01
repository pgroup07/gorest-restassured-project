package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest {
    static String name = "Prime" + TestUtils.getRandomValue();
    static String email = TestUtils.getRandomValue() + "Prime@gmail.com";
    static String updatedEmail = "Updated" + TestUtils.getRandomValue() + "@gmail.com";
    static String gender = "male";
    static String status = "active";
    static int userId;
    static String token = "d88d02069e48aedd8e5032e17f21b929e78721f871e7289b0dc925c3571ee4f7";

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
    }

    @Test()
    public void averifyUserCreatedSuccessfully() {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .headers("Content-Type", "application/json", "Authorization", "Bearer " + token)
                .when()
                .body(userPojo)
                .post("/public/v2/users");
        response.then().log().all().statusCode(201);

        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        userId = jsonPath.getInt("id");
    }

    @Test
    public void bverifyUserUpdateSuccessfully() {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(updatedEmail);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .headers("Content-Type", "application/json", "Authorization", "Bearer " + token)
                .when()
                .body(userPojo)
                .patch("/public/v2/users/" + userId);
        response.then().log().all().statusCode(200);

    }

    @Test
    public void zzVerifyUserDeleteSuccessfully() {

        given()
                .headers("Content-Type", "application/json", "Authorization", "Bearer " + token)
                .pathParam("id", userId)
                .when()
                .delete("/public/v2/users/{id}")
                .then()
                .statusCode(204);
    }
}