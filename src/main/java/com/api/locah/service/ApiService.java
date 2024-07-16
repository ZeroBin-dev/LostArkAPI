package com.api.locah.service;

import com.api.locah.controller.dto.MarketDetailReq;
import com.api.locah.controller.dto.MarketDetailRes;
import com.api.locah.controller.dto.MarketSearchReq;
import com.api.locah.controller.dto.MarketSearchRes;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class ApiService {

  @Value("${loa.apiKey}")
  private String apiKey;

  @Value("${loa.apiUrl}")
  private String apiUrl;

  HttpHeaders headers;
  RestTemplate restTemplate;

  @PostConstruct
  public void init() {
    headers = new HttpHeaders();
    headers.set("Accept", "application/json");
    headers.set("Authorization", "bearer " + apiKey);
    restTemplate = new RestTemplate();
  }

  /**
   * 거래소검색
   */
  public ResponseEntity<MarketSearchRes> marketSearch(final MarketSearchReq params) {
    HttpEntity<Object> entity = new HttpEntity<>(params, headers);
    return restTemplate.exchange(
      apiUrl + "/markets/items",
      HttpMethod.POST,
      entity,
      MarketSearchRes.class);
  }

  /**
   * 거래소 상세 검색
   */
  public ResponseEntity<MarketDetailRes> marketDetail(final MarketDetailReq params) {
    // HTTP 요청을 위한 HttpEntity 설정
    HttpEntity<Object> entity = new HttpEntity<>(headers);

    // API 호출 및 응답 받기
    ResponseEntity<String> jsonResult = restTemplate.exchange(
      apiUrl + "/markets/items/" + params.getItemId(),
      HttpMethod.GET,
      entity,
      String.class);

    // API 응답을 JSON 문자열로 받아옴
    String jsonArray = jsonResult.getBody();

    // ObjectMapper를 사용하여 JSON 문자열을 MarketDetailRes 객체로 변환
    ObjectMapper objectMapper = new ObjectMapper();
    MarketDetailRes marketDetailRes = null;
    try {
      List<MarketDetailRes> marketDetails = objectMapper.readValue(jsonArray, new TypeReference<List<MarketDetailRes>>() {
      });
      if (!marketDetails.isEmpty()) {
        marketDetailRes = marketDetails.get(0); // 첫 번째 객체를 선택
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    // 변환된 객체를 ResponseEntity에 담아 반환
    if (marketDetailRes != null) {
      return new ResponseEntity<>(marketDetailRes, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
