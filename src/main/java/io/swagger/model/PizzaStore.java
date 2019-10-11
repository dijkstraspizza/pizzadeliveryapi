package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Address;
import io.swagger.model.Menu;
import io.swagger.model.Special;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PizzaStore
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-11T13:23:56.479615-07:00[America/Los_Angeles]")
public class PizzaStore   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("address")
  private Address address = null;

  @JsonProperty("menu")
  private Menu menu = null;

  @JsonProperty("specials")
  @Valid
  private List<Special> specials = new ArrayList<Special>();

  public PizzaStore id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "1", value = "")

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public PizzaStore address(Address address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid
  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public PizzaStore menu(Menu menu) {
    this.menu = menu;
    return this;
  }

  /**
   * Get menu
   * @return menu
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid
  public Menu getMenu() {
    return menu;
  }

  public void setMenu(Menu menu) {
    this.menu = menu;
  }

  public PizzaStore specials(List<Special> specials) {
    this.specials = specials;
    return this;
  }

  public PizzaStore addSpecialsItem(Special specialsItem) {
    this.specials.add(specialsItem);
    return this;
  }

  /**
   * Get specials
   * @return specials
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Valid
  public List<Special> getSpecials() {
    return specials;
  }

  public void setSpecials(List<Special> specials) {
    this.specials = specials;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PizzaStore pizzaStore = (PizzaStore) o;
    return Objects.equals(this.id, pizzaStore.id) &&
        Objects.equals(this.address, pizzaStore.address) &&
        Objects.equals(this.menu, pizzaStore.menu) &&
        Objects.equals(this.specials, pizzaStore.specials);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, address, menu, specials);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PizzaStore {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    menu: ").append(toIndentedString(menu)).append("\n");
    sb.append("    specials: ").append(toIndentedString(specials)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
