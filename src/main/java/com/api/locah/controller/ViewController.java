package com.api.locah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

  /**
   * 거래소
   */
  @GetMapping(value = {"/main", "/index", "/", "/market"})
  public String market() {
    return "views/market";
  }

  /**
   * 캐릭터
   */
  @GetMapping("/character")
  public String character() {
    return "views/character";
  }

}
