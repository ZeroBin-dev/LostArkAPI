package com.api.locah.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MarketSearchRes {
  @JsonProperty("PageNo")
  private int pageNo;

  @JsonProperty("PageSize")
  private int pageSize;

  @JsonProperty("TotalCount")
  private int totalCount;

  @JsonProperty("Items")
  private List<MarketResultVO> items;

  @Getter
  @Setter
  public static class MarketResultVO {
    @JsonProperty("Id")
    private int id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Grade")
    private String grade;

    @JsonProperty("Icon")
    private String icon;

    @JsonProperty("BundleCount")
    private int bundleCount;

    @JsonProperty("TradeRemainCount")
    private Integer tradeRemainCount;

    @JsonProperty("YDayAvgPrice")
    private double yDayAvgPrice;

    @JsonProperty("RecentPrice")
    private int recentPrice;

    @JsonProperty("CurrentMinPrice")
    private int currentMinPrice;
  }

}
