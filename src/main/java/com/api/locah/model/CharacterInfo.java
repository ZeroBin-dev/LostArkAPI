package com.api.locah.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterInfo {
  @JsonProperty("ServerName")
  private String serverName;

  @JsonProperty("CharacterName")
  private String characterName;

  @JsonProperty("CharacterLevel")
  private Integer characterLevel;

  @JsonProperty("CharacterClassName")
  private String characterClassName;

  @JsonProperty("ItemAvgLevel")
  private String itemAvgLevel;

  @JsonProperty("ItemMaxLevel")
  private String itemMaxLevel;

  private ArmoryProfile armoryProfile;

  @Getter
  @Setter
  public static class ArmoryProfile {
    @JsonProperty("CharacterImage")
    private String characterImage;

    @JsonProperty("ExpeditionLevel")
    private Integer expeditionLevel;

    @JsonProperty("TownName")
    private String townName;

    @JsonProperty("TotalSkillPoint")
    private Integer totalSkillPoint;
  }
}
