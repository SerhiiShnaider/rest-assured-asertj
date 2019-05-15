package com.gmail.shnapi007;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.shnapi007.core.AssureClient;
import com.gmail.shnapi007.models.Product;
import com.gmail.shnapi007.models.SearchResponse;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductsTest {

  AssureClient client;

  @BeforeMethod
  public void before() {
    client = new AssureClient();
  }

  @Test
  public void isProductsPresentTest() throws IOException {

    SearchResponse searchResponse = client.get("/rs/json/products", SearchResponse.class);
    Assertions.assertThat(searchResponse).isNotNull();
  }

  @Test
  public void isResponseSuccessTest() {

    Response response = client.get("/rs/json/products");
    Assertions.assertThat(response.statusCode()).isNotNull().isEqualTo(200);
  }

  @Test
  public void searchTest() throws IOException {

    SearchResponse searchResponse = client.get("/rs/json/products?q=MARMOT", SearchResponse.class);
    Assertions.assertThat(searchResponse.getQ()).isEqualTo("MARMOT");

    List<String> brands = searchResponse.getProducts().stream().map(Product::getBrand)
        .collect(Collectors.toList());
    Assertions.assertThat(brands.stream().allMatch(b -> b.equalsIgnoreCase("MARMOT")));
  }

  @Test
  public void createProductTest() throws JsonProcessingException {

    Product product = new Product();

    product.setId(444);
    product.setBrand("testBrand");
    product.setDescription("testDesc");
    product.setPrice(666256);
    product.setColours(Arrays.asList("black", "red", "blue"));

    String json = new ObjectMapper().writeValueAsString(product);
    Response response = client.post("/rs/json/products/444", json);

    Assertions.assertThat(response.getBody().asString())
        .isNotNull()
        .contains(String.valueOf("Product saved : " + product.getId()));
  }

  @Test
  public void deleteProductTest() {

    Response response = client.delete("/rs/json/products/444");
    Assertions.assertThat(response.getBody().asString())
        .isNotNull()
        .isNotEmpty()
        .contains("Product deleted");
  }

}
