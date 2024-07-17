package com.api.locah.service;

import com.api.locah.controller.dto.*;
import com.api.locah.model.CharacterInfo;
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
   * 거래소검색(POST)
   */
  public ResponseEntity<MarketSearchRes> marketSearch(final MarketSearchReq params) {
    return restTemplate.exchange(
      apiUrl + "/markets/items",
      HttpMethod.POST,
      _getEntity(params),
      MarketSearchRes.class);
  }

  /**
   * 거래소 상세 검색(GET)
   */
  public ResponseEntity<MarketDetailRes> marketDetail(final MarketDetailReq params) {
    ResponseEntity<String> jsonResult = restTemplate.exchange(
      apiUrl + "/markets/items/" + params.getItemId(),
      HttpMethod.GET,
      _getEntity(null),
      String.class);

    MarketDetailRes marketDetailRes = _getArrayData(jsonResult, MarketDetailRes.class).get(0);

    if (marketDetailRes != null) {
      return new ResponseEntity<>(marketDetailRes, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * 캐릭터 기본정보 조회(GET)
   */
  public ResponseEntity<CharacterRes> characterSearch(CharacterReq params) {
    ResponseEntity<String> jsonResult = restTemplate.exchange(
      apiUrl + "/characters/" + params.getCharacterName() + "/siblings",
      HttpMethod.GET,
      _getEntity(null),
      String.class);

    List<CharacterInfo> characterInfo = _getArrayData(jsonResult, CharacterInfo.class);
    if(characterInfo == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    for(CharacterInfo info : characterInfo){
      String name = info.getCharacterName();
      CharacterInfo.ArmoryProfile armoryProfile = restTemplate.exchange(
        apiUrl + "/armories/characters/" + name + "/profiles",
        HttpMethod.GET,
        _getEntity(null),
        CharacterInfo.ArmoryProfile.class).getBody();

      info.setArmoryProfile(armoryProfile);
    }

    CharacterRes res = new CharacterRes();
    res.setCharacterInfo(characterInfo);

    return new ResponseEntity<>(res, HttpStatus.OK);
  }

  /**
   * HttpEntity 획득
   */
  private HttpEntity _getEntity(Object obj) {
    return obj == null ? new HttpEntity<>(headers) : new HttpEntity<>(obj, headers);
  }

  /**
   * JsonArray 데이터 파싱
   */
  private <T> List<T> _getArrayData(ResponseEntity<String> str, Class<T> clazz) {
    String jsonArray = str.getBody();
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      // JSON 배열을 리스트로 변환
      return objectMapper.readValue(jsonArray, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    } catch (IOException e) {
      e.printStackTrace(); // 예외 발생 시 스택 트레이스를 출력
      return null;
    }
  }

}
