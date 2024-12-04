package org.example.collectivepurchases.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipDto {
   private Long id;
   private Long userId;
   @JsonProperty("friendIds")
   private List<Long> friendIds;
}
