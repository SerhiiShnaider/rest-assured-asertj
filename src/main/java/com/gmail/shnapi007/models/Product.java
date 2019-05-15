package com.gmail.shnapi007.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class Product {

  @JsonProperty("id")
  private long id;

  @JsonProperty("price")
  private long price;

  @JsonProperty("brand")
  private String brand;

  @JsonProperty("description")
  private String description;


  @JsonProperty("colours")
  private List<String> colours;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<String> getColours() {
    return colours;
  }

  public void setColours(List<String> colours) {
    this.colours = colours;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return id == product.id &&
        price == product.price &&
        Objects.equals(brand, product.brand) &&
        Objects.equals(description, product.description) &&
        Objects.equals(colours, product.colours);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, price, brand, description, colours);
  }

  @Override
  public String toString() {
    return com.google.common.base.MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("price", price)
        .add("brand", brand)
        .add("description", description)
        .add("colours", colours)
        .toString();
  }
}
