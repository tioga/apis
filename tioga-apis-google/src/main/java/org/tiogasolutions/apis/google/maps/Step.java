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

@JsonIgnoreProperties(ignoreUnknown = true)
public class Step {

  private String html_instructions;
  // private Distance distance;
  // private Duration duration;
  // private String start_location;
  // private String end_location;
  // sub_steps
  // transit_details contains transit specific information. This field is only returned with travel_mode is set to "transit". See Transit Details below.

  @JsonCreator
  public Step() {
  }

  public String getHtml_instructions() {
    return html_instructions;
  }

/*
  public Distance getDistance() {
    return distance;
  }

  public Duration getDuration() {
    return duration;
  }

  public String getStart_location() {
    return start_location;
  }

  public String getEnd_location() {
    return end_location;
  }
*/
}
