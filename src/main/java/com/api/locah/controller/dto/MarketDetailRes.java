package com.api.locah.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MarketDetailRes {
  @JsonProperty("Name")
  private String name;

  @JsonProperty("TradeRemainCount")
  private Integer tradeRemainCount;

  @JsonProperty("BundleCount")
  private int bundleCount;

  @JsonProperty("ToolTip")
  private String toolTip;

  @JsonProperty("Stats")
  private List<StatsVO> stats;

  @Getter
  @Setter
  public static class StatsVO {
    @JsonProperty("Date")
    private String date;

    @JsonProperty("AvgPrice")
    private double avgPrice;

    @JsonProperty("TradeCount")
    private int tradeCount;
  }

}
