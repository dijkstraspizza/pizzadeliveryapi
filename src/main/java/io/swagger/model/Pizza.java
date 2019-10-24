package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Ingredient;
import io.swagger.model.Menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

/**
 * Pizza class.
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-10-11T13:23:56.479615-07:00[America/Los_Angeles]")
public class Pizza {

  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("sizeInches")
  private Integer sizeInches = null;

  @JsonProperty("sizeDesc")
  private String sizeDesc = null;

  @JsonProperty("ingredients")
  @Valid
  private List<Ingredient> ingredients = new ArrayList<Ingredient>();

  @JsonProperty("price")
  private Double price = null;

  @JsonProperty("menusOn")
  @Valid
  private List<Menu> menusOn = new ArrayList<Menu>();

  public Pizza id(Integer id) {
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

  public Pizza sizeInches(Integer sizeInches) {
    this.sizeInches = sizeInches;
    return this;
  }

  /**
   * Get sizeInches.
   *
   * @return sizeInches
   **/
  @ApiModelProperty(example = "11", required = true, value = "")
  @NotNull

  public Integer getSizeInches() {
    return sizeInches;
  }

  public void setSizeInches(Integer sizeInches) {
    this.sizeInches = sizeInches;
  }

  public Pizza sizeDesc(String sizeDesc) {
    this.sizeDesc = sizeDesc;
    return this;
  }

  /**
   * Get sizeDesc.
   *
   * @return sizeDesc
   **/
  @ApiModelProperty(example = "Small", required = true, value = "")
  @NotNull

  public String getSizeDesc() {
    return sizeDesc;
  }

  public void setSizeDesc(String sizeDesc) {
    this.sizeDesc = sizeDesc;
  }

  public Pizza ingredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
    return this;
  }

  public Pizza addIngredientsItem(Ingredient ingredientsItem) {
    this.ingredients.add(ingredientsItem);
    return this;
  }

  /**
   * Get ingredients.
   *
   * @return ingredients
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Valid
  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

  public Pizza price(Double price) {
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

  public Pizza menusOn(List<Menu> menusOn) {
    this.menusOn = menusOn;
    return this;
  }

  public Pizza addMenusOnItem(Menu menusOnItem) {
    this.menusOn.add(menusOnItem);
    return this;
  }

  /**
   * Get menusOn.
   *
   * @return menusOn
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Valid
  public List<Menu> getMenusOn() {
    return menusOn;
  }

  public void setMenusOn(List<Menu> menusOn) {
    this.menusOn = menusOn;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pizza pizza = (Pizza) o;
    return Objects.equals(this.id, pizza.id)
        && Objects.equals(this.sizeInches, pizza.sizeInches)
        && Objects.equals(this.sizeDesc, pizza.sizeDesc)
        && Objects.equals(this.ingredients, pizza.ingredients)
        && Objects.equals(this.price, pizza.price)
        && Objects.equals(this.menusOn, pizza.menusOn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, sizeInches, sizeDesc, ingredients, price, menusOn);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Pizza {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    sizeInches: ").append(toIndentedString(sizeInches)).append("\n");
    sb.append("    sizeDesc: ").append(toIndentedString(sizeDesc)).append("\n");
    sb.append("    ingredients: ").append(toIndentedString(ingredients)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    menusOn: ").append(toIndentedString(menusOn)).append("\n");
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
