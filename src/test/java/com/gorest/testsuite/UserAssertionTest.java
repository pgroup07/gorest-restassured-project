package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        response = given()
                .when()
                .get("/public/v2/users")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void VerifyTheIfTheTotalRecordIs21() {
        response.body("size", equalTo(10));
    }

    // 2. Verify the if the name of id = 2272630 is equal to ”Hamsini Trivedi”
    @Test
    public void test002() {
        response.body("[18].name", equalTo("Sarala Menon"));
    }

    // 3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void test003() {
        response.body("[5].name", equalTo("Jagmeet Jha"));
    }

    // 4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan Guha, Karthik Dubashi IV)
    @Test
    public void test004() {
        response.body("name", hasItems("Tanushri Nayar", "Jagmeet Jha", "Amarnath Devar"));
    }

    // 5. Verify the email of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void test005() {
        response.body("[0].id", equalTo(4040732));
        response.body("[0].email", equalTo("lakshmi_chaturvedi_pres@howell-leannon.test"));

    }

    // 6. Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void test006() {
        response.body("[3].status", equalTo("active"));
        response.body("[3].name", equalTo("Tanushri Nayar"));

    }

    // 7. Verify the Gender = male of user name is “Niro Prajapat”
    @Test
    public void test007() {
        response.body("[5].gender", equalTo("male"));
        response.body("[5].name", equalTo("Jagmeet Jha"));
    }
}
