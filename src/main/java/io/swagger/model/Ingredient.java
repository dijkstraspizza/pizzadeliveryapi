package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Ingredient
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-11T13:23:56.479615-07:00[America/Los_Angeles]")
public class Ingredient   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("category")
  private String category = null;

  @JsonProperty("isGlutenFree")
  private Boolean isGlutenFree = null;

  public Ingredient id(Integer id) {
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

  public Ingredient name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "Sausage", required = true, value = "")
  @NotNull

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Ingredient category(String category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
  **/
  @ApiModelProperty(example = "Meat", required = true, value = "")
  @NotNull

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Ingredient isGlutenFree(Boolean isGlutenFree) {
    this.isGlutenFree = isGlutenFree;
    return this;
  }

  /**
   * Get isGlutenFree
   * @return isGlutenFree
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public Boolean isIsGlutenFree() {
    return isGlutenFree;
  }

  public void setIsGlutenFree(Boolean isGlutenFree) {
    this.isGlutenFree = isGlutenFree;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ingredient ingredient = (Ingredient) o;
    return Objects.equals(this.id, ingredient.id) &&
        Objects.equals(this.name, ingredient.name) &&
        Objects.equals(this.category, ingredient.category) &&
        Objects.equals(this.isGlutenFree, ingredient.isGlutenFree);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, category, isGlutenFree);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ingredient {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    isGlutenFree: ").append(toIndentedString(isGlutenFree)).append("\n");
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
