package com.gmail.shnapi007.bdd;


import com.gmail.shnapi007.models.SearchResponse;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BDDProductTest {

  private Response response;

  @Given("uri (.*)")
  public void givenLink(String uri) {
    RestAssured.baseURI = uri;
  }

  @When("go to (.*)")
  public void whenGoToLink(String url) {
    response = RestAssured.when().get(url);
  }
  
  @Then("get products")
  public void getProducts() {
    SearchResponse as = response.getBody().as(SearchResponse.class);

    response.then().statusCode(200);
  }

}
