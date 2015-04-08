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

@JsonIgnoreProperties(ignoreUnknown = true)
public class DrivingDirections {

  private final Status status;
  private final String errorMessage;

  private final Route[] routes;

  @JsonCreator
  public DrivingDirections(@JsonProperty("status") Status status,
                           @JsonProperty("error_message") String errorMessage,
                           @JsonProperty("routes") Route[] routes) {
    this.status = status;
    this.errorMessage = errorMessage;
    this.routes = routes;
  }

  public Status getStatus() {
    return status;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public Route[] getRoutes() {
    return routes;
  }

  public Route getFirstRoute() {
    return (routes == null || routes.length == 0) ? null : routes[0];
  }
}
