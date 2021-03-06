/*
 * Copyright 2012 Jacob D Parr
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tiogasolutions.apis.google.maps;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {

  private final String summary;
  private final Leg[] legs;

  @JsonCreator
  public Route(@JsonProperty("summary") String summary,
               @JsonProperty("legs") Leg[] legs) {

    this.summary = summary;
    this.legs = legs;
  }

  public String getSummary() {
    return summary;
  }

  public Leg[] getLegs() {
    return legs;
  }

  public Leg getFirstLeg() {
    return (legs == null || legs.length == 0) ? null : legs[0];
  }
}