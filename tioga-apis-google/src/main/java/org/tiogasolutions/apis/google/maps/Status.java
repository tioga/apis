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

public enum Status {

  OK("The response contains a valid result"),
  NOT_FOUND("At least one of the locations specified (origin, destination, or waypoints) could not be geocoded."),
  ZERO_RESULTS("No route could be found between the origin and destination."),
  MAX_WAYPOINTS_EXCEEDED("Too many waypoints were provided in the request."),
  INVALID_REQUEST("The provided request was invalid."),
  OVER_QUERY_LIMIT("The service has received too many requests from your application within the allowed time period."),
  REQUEST_DENIED("The service denied use of the directions service by your application."),
  UNKNOWN_ERROR("A directions request could not be processed due to a server error. The request may succeed if you try again.");

  private final String description;

  private Status(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public boolean isOK() {
    return this == OK;
  }

  public boolean isNotOK() {
    return this != OK;
  }
}
