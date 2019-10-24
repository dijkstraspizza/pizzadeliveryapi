package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.PizzaStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

/**
 * Special class.
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-10-11T13:23:56.479615-07:00[America/Los_Angeles]")
public class Special   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("desc")
  private String desc = null;

  @JsonProperty("offeredAt")
  @Valid
  private List<PizzaStore> offeredAt = new ArrayList<PizzaStore>();

  public Special id(Integer id) {
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

  public Special desc(String desc) {
    this.desc = desc;
    return this;
  }

  /**
   * Get desc
   * @return desc
  **/
  @ApiModelProperty(example = "50% off two large pizzas", required = true, value = "")
  @NotNull

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public Special offeredAt(List<PizzaStore> offeredAt) {
    this.offeredAt = offeredAt;
    return this;
  }

  public Special addOfferedAtItem(PizzaStore offeredAtItem) {
    this.offeredAt.add(offeredAtItem);
    return this;
  }

  /**
   * Get offeredAt
   * @return offeredAt
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Valid
  public List<PizzaStore> getOfferedAt() {
    return offeredAt;
  }

  public void setOfferedAt(List<PizzaStore> offeredAt) {
    this.offeredAt = offeredAt;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Special special = (Special) o;
    return Objects.equals(this.id, special.id) &&
        Objects.equals(this.desc, special.desc) &&
        Objects.equals(this.offeredAt, special.offeredAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, desc, offeredAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Special {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    desc: ").append(toIndentedString(desc)).append("\n");
    sb.append("    offeredAt: ").append(toIndentedString(offeredAt)).append("\n");
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
