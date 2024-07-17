package com.api.locah.controller.dto;

import com.api.locah.model.CharacterInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterRes {

  private List<CharacterInfo> characterInfo;
}
