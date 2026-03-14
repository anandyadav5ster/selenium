package com.selenium;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class apitest extends BaseTest {
     GenericFunctions gf;
     String url;

 @BeforeMethod
    public void initializeGenericFunctions() {
        gf = new GenericFunctions(driver);
        url = gf.readPropertyFile("apiurl");
    }

   @Test
    public void testGetUserDetails() {
        test.info("Base url is " + url);
        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given();
        Response response = request.get("/users/2");
        test.info("Validate response : ==================");
         System.out.println(response);
        // verifyResponseData(response);
    }

    // public static void verifyResponseData(Response response) {
    //       ApiResponse apiResponse = response.as(ApiResponse.class, ObjectMapperType.JACKSON_2);
    //     // ApiResponse apiResponse = response.as(ApiResponse.class);

    //     assertEquals(apiResponse.getData().getId(), 2);
    //     assertEquals(apiResponse.getData().getEmail(), "janet.weaver@reqres.in");
    //     assertEquals(apiResponse.getData().getFirst_name(), "Janet");
    //     assertEquals(apiResponse.getData().getLast_name(), "Weaver");
    //     assertTrue(apiResponse.getData().getAvatar().contains("image.jpg"));
    // }
       
}