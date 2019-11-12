package edu.neu.khoury.cs5500.dijkstraspizza.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController provides redirection to swagger api documentation from root path "/".
 */
@Controller
public class HomeController {

  @RequestMapping(value = "/")
  public String index() {
    System.out.println("swagger-ui.html");
    return "redirect:swagger-ui.html";
  }
}
