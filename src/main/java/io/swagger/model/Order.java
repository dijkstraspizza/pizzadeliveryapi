package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Address;
import io.swagger.model.Pizza;
import io.swagger.model.Special;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

/**
 * Order class.
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-10-11T13:23:56.479615-07:00[America/Los_Angeles]")
public class Order {

  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("storeAddr")
  private Address storeAddr = null;

  @JsonProperty("custAddr")
  private Address custAddr = null;

  @JsonProperty("order")
  @Valid
  private List<Pizza> order = new ArrayList<Pizza>();

  @JsonProperty("special")
  private Special special = null;

  @JsonProperty("price")
  private Double price = null;

  public Order id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id.
   *
   * @return id
   **/
  @ApiModelProperty(example = "1", value = "")

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Order storeAddr(Address storeAddr) {
    this.storeAddr = storeAddr;
    return this;
  }

  /**
   * Get storeAddr.
   *
   * @return storeAddr
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid
  public Address getStoreAddr() {
    return storeAddr;
  }

  public void setStoreAddr(Address storeAddr) {
    this.storeAddr = storeAddr;
  }

  public Order custAddr(Address custAddr) {
    this.custAddr = custAddr;
    return this;
  }

  /**
   * Get custAddr.
   *
   * @return custAddr
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid
  public Address getCustAddr() {
    return custAddr;
  }

  public void setCustAddr(Address custAddr) {
    this.custAddr = custAddr;
  }

  public Order order(List<Pizza> order) {
    this.order = order;
    return this;
  }

  public Order addOrderItem(Pizza orderItem) {
    this.order.add(orderItem);
    return this;
  }

  /**
   * Get order.
   *
   * @return order
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Valid
  public List<Pizza> getOrder() {
    return order;
  }

  public void setOrder(List<Pizza> order) {
    this.order = order;
  }

  public Order special(Special special) {
    this.special = special;
    return this;
  }

  /**
   * Get special.
   *
   * @return special
   **/
  @ApiModelProperty(value = "")

  @Valid
  public Special getSpecial() {
    return special;
  }

  public void setSpecial(Special special) {
    this.special = special;
  }

  public Order price(Double price) {
    this.price = price;
    return this;
  }

  /**
   * Get price.
   *
   * @return price
   **/
  @ApiModelProperty(example = "19.99", required = true, value = "")
  @NotNull

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(this.id, order.id)
        && Objects.equals(this.storeAddr, order.storeAddr)
        && Objects.equals(this.custAddr, order.custAddr)
        && Objects.equals(this.order, order.order)
        && Objects.equals(this.special, order.special)
        && Objects.equals(this.price, order.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, storeAddr, custAddr, order, special, price);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Order {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    storeAddr: ").append(toIndentedString(storeAddr)).append("\n");
    sb.append("    custAddr: ").append(toIndentedString(custAddr)).append("\n");
    sb.append("    order: ").append(toIndentedString(order)).append("\n");
    sb.append("    special: ").append(toIndentedString(special)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first
   * line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
