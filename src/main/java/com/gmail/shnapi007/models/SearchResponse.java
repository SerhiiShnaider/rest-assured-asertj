package com.gmail.shnapi007.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import java.util.List;
import java.util.Objects;

public class SearchResponse {

  @JsonProperty("q")
  private String q;

  @JsonProperty("products")
  private List<Product> products;

  public String getQ() {
    return q;
  }

  public void setQ(String q) {
    this.q = q;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchResponse that = (SearchResponse) o;
    return Objects.equals(q, that.q) &&
        Objects.equals(products, that.products);
  }

  @Override
  public int hashCode() {
    return Objects.hash(q, products);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("q", q)
        .add("products", products)
        .toString();
  }
}
