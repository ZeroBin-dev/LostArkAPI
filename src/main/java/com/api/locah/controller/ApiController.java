package com.api.locah.controller;

import com.api.locah.controller.dto.MarketDetailReq;
import com.api.locah.controller.dto.MarketDetailRes;
import com.api.locah.controller.dto.MarketSearchReq;
import com.api.locah.controller.dto.MarketSearchRes;
import com.api.locah.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

  private final ApiService _apiService;

  /**
   * 거래소 검색
   */
  @PostMapping("/market/search")
  public ResponseEntity<MarketSearchRes> marketSearch(@RequestBody MarketSearchReq params) {
    return _apiService.marketSearch(params);
  }

  /**
   * 거래소 상세 검색
   */
  @PostMapping("/market/detail")
  public ResponseEntity<MarketDetailRes> marketDetail(@RequestBody MarketDetailReq params) {
    return _apiService.marketDetail(params);
  }

  /**
   * 캐릭터 기본정보 조회
   */


}
