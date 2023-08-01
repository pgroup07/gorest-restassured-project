package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .get("/posts")
                .then().statusCode(200);
    }


    // 1.Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("size", equalTo(10));
    }

    // 2.Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demittocentum.”
    @Test
    public void test002() {
        response.body("find{it.id == 56979}.title", equalTo("Summa abduco quae blanditiis deorsum."));
    }



    // 3.Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test004() {
        response.body("id", hasItems(56978, 56973, 56969));
    }

    // 4.Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar
    //spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
    //Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
    //Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
    //antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
    //cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
    //adflicto. Assentator umquam pel."”
    @Test
    public void test005() {
        response.body("find{it.id == 56973}.body", equalTo(" Aperio uredo vesper. Supplanto suasoria adiuvo. Vulgus stella demulceo. Consequatur chirographum vomica. Eos consequatur cilicium. Ipsa cedo vallum. Tenax repellat aestivus. Sint accipio viscus. Vado aggero stillicidium. Coepi claustrum conforto. Vetus curtus accusantium. Capto ipsam pax. Aperio consuasor vero. Aut tredecim terror."));
    }

}
