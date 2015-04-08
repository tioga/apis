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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Leg {

  private final Step[] steps;
  private final Distance distance;
  private final Duration duration;

  @JsonCreator
  public Leg(@JsonProperty("steps") Step[] steps,
             @JsonProperty("distance") Distance distance,
             @JsonProperty("duration") Duration duration) {

    this.steps = steps;
    this.distance = distance;
    this.duration = duration;
  }

  public Step[] getSteps() {
    return steps;
  }

  public Distance getDistance() {
    return distance;
  }

  public Duration getDuration() {
    return duration;
  }

  public List<String> getInstructions() {
    List<String> instructions = new ArrayList<String>();
    for (Step step : steps) {
      instructions.add(step.getHtml_instructions());
    }
    return instructions;
  }
}
