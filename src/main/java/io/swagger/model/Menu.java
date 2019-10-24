package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Ingredient;
import io.swagger.model.Pizza;
import io.swagger.model.PizzaStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

/**
 * Menu class.
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-10-11T13:23:56.479615-07:00[America/Los_Angeles]")
public class Menu {

  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("storesOffering")
  @Valid
  private List<PizzaStore> storesOffering = new ArrayList<PizzaStore>();

  @JsonProperty("pizzas")
  @Valid
  private List<Pizza> pizzas = new ArrayList<Pizza>();

  @JsonProperty("ingredients")
  @Valid
  private List<Ingredient> ingredients = new ArrayList<Ingredient>();

  public Menu id(Integer id) {
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

  public Menu storesOffering(List<PizzaStore> storesOffering) {
    this.storesOffering = storesOffering;
    return this;
  }

  public Menu addStoresOfferingItem(PizzaStore storesOfferingItem) {
    this.storesOffering.add(storesOfferingItem);
    return this;
  }

  /**
   * Get storesOffering.
   *
   * @return storesOffering
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Valid
  public List<PizzaStore> getStoresOffering() {
    return storesOffering;
  }

  public void setStoresOffering(List<PizzaStore> storesOffering) {
    this.storesOffering = storesOffering;
  }

  public Menu pizzas(List<Pizza> pizzas) {
    this.pizzas = pizzas;
    return this;
  }

  public Menu addPizzasItem(Pizza pizzasItem) {
    this.pizzas.add(pizzasItem);
    return this;
  }

  /**
   * Get pizzas.
   *
   * @return pizzas
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Valid
  public List<Pizza> getPizzas() {
    return pizzas;
  }

  public void setPizzas(List<Pizza> pizzas) {
    this.pizzas = pizzas;
  }

  public Menu ingredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
    return this;
  }

  public Menu addIngredientsItem(Ingredient ingredientsItem) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Menu menu = (Menu) o;
    return Objects.equals(this.id, menu.id)
        && Objects.equals(this.storesOffering, menu.storesOffering)
        && Objects.equals(this.pizzas, menu.pizzas)
        && Objects.equals(this.ingredients, menu.ingredients);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, storesOffering, pizzas, ingredients);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Menu {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    storesOffering: ").append(toIndentedString(storesOffering)).append("\n");
    sb.append("    pizzas: ").append(toIndentedString(pizzas)).append("\n");
    sb.append("    ingredients: ").append(toIndentedString(ingredients)).append("\n");
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
