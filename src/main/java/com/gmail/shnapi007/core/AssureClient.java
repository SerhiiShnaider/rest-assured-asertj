package com.gmail.shnapi007.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.List;

public class AssureClient {

  private RestAssured restAssured = new RestAssured();

  public Response get(String url) {
    RestAssured.baseURI = "http://localhost:8080";

    return restAssured.get(url)
        .then()
        .statusCode(200)
        .extract()
        .response();
  }

  public <T> T get(String url, Class<T> className) throws IOException {
    RestAssured.baseURI = "http://localhost:8080";

    String jsonBody = restAssured
        .get(url)
        .then()
        .contentType(ContentType.JSON)
        .statusCode(200)
        .extract()
        .response()
        .asString();

    return convertJson(jsonBody, className);
  }

  public Response post(String url, String jsonBody) {
    RestAssured.baseURI = "http://localhost:8080";

    return restAssured.given()
        .contentType(ContentType.JSON)
        .body(jsonBody)
        .post(url)
        .then()
        .statusCode(201)
        .extract()
        .response();
  }

  public Response delete(String url) {
    RestAssured.baseURI = "http://localhost:8080";

    return restAssured.delete(url)
        .then()
        .statusCode(200)
        .extract()
        .response();
  }

  private <T> T convertJson(String jsonBody, Class<T> className) throws IOException {

    if (!List.class.equals(className) && jsonBody.contains("{")
        && jsonBody.indexOf("{") < jsonBody.lastIndexOf("}")) {
      jsonBody = jsonBody.substring(jsonBody.indexOf("{"), jsonBody.lastIndexOf("}") + 1);
    }

    return new ObjectMapper().readValue(jsonBody, className);
  }
}
