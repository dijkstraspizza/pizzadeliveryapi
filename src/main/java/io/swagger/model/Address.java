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
 * Address
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-11T13:23:56.479615-07:00[America/Los_Angeles]")
public class Address   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("streetAddr")
  private String streetAddr = null;

  @JsonProperty("city")
  private String city = null;

  @JsonProperty("state")
  private String state = null;

  @JsonProperty("zip")
  private Integer zip = null;

  public Address id(Integer id) {
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

  public Address streetAddr(String streetAddr) {
    this.streetAddr = streetAddr;
    return this;
  }

  /**
   * Get streetAddr
   * @return streetAddr
  **/
  @ApiModelProperty(example = "1234 56th St. SW", required = true, value = "")
  @NotNull

  public String getStreetAddr() {
    return streetAddr;
  }

  public void setStreetAddr(String streetAddr) {
    this.streetAddr = streetAddr;
  }

  public Address city(String city) {
    this.city = city;
    return this;
  }

  /**
   * Get city
   * @return city
  **/
  @ApiModelProperty(example = "Seattle", required = true, value = "")
  @NotNull

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Address state(String state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
  **/
  @ApiModelProperty(example = "WA", required = true, value = "")
  @NotNull

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Address zip(Integer zip) {
    this.zip = zip;
    return this;
  }

  /**
   * Get zip
   * @return zip
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public Integer getZip() {
    return zip;
  }

  public void setZip(Integer zip) {
    this.zip = zip;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address = (Address) o;
    return Objects.equals(this.id, address.id) &&
        Objects.equals(this.streetAddr, address.streetAddr) &&
        Objects.equals(this.city, address.city) &&
        Objects.equals(this.state, address.state) &&
        Objects.equals(this.zip, address.zip);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, streetAddr, city, state, zip);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Address {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    streetAddr: ").append(toIndentedString(streetAddr)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    zip: ").append(toIndentedString(zip)).append("\n");
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
