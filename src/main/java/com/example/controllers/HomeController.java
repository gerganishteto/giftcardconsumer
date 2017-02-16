package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * HomeController for swagger. This is to access the main swagger page by using "/" without having
 * to know the HTML page name (swagger-ui.html).
 */
@Controller
public class HomeController {
  @RequestMapping("/")
  public ModelAndView home() {
    return new ModelAndView("redirect:/swagger-ui.html");
  }
}