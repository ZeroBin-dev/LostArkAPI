package com.api.locah.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

  @GetMapping(value = {"/main", "/index", "/"})
  public String userMain(final Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
    return "index";
  }

}
