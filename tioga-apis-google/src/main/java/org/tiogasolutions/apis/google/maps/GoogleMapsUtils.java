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

import com.fasterxml.jackson.databind.ObjectMapper;
import org.tiogasolutions.dev.common.IoUtils;
import org.tiogasolutions.dev.common.exceptions.ApiException;
import org.tiogasolutions.dev.common.exceptions.ExceptionUtils;
import org.tiogasolutions.dev.domain.locality.*;
import org.tiogasolutions.dev.jackson.TiogaJacksonObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLConnection;

import static org.tiogasolutions.dev.common.StringUtils.encodeUrl;
import static org.tiogasolutions.dev.common.StringUtils.getTagContents;

public class GoogleMapsUtils {

  private static final BigDecimal kmToMilesRatio = new BigDecimal("0.621371");
  private static final BigDecimal metersToMilesRatio = new BigDecimal("0.000621371");
  private static final ObjectMapper objectMapper = new TiogaJacksonObjectMapper();

  public static BigDecimal getMilesByGoogle(LatLng locationA, LatLng locationB) throws IOException {
    DrivingDirections directions = getDrivingDirections(locationA, locationB);

    Leg leg = directions.getRoutes()[0].getLegs()[0];
    long meters = leg.getDistance().getMeters();

    BigDecimal miles = metersToMilesRatio.multiply(new BigDecimal(meters));
    return miles.setScale(1, RoundingMode.HALF_EVEN);
  }

  public static BigDecimal getMilesByLatLng(LatLng locationA, LatLng locationB) {
    LatLng locationC = new LatLng(locationA.getLatitude(), locationB.getLongitude());

    BigDecimal firstLeg = calculateHaversineDistance(
      locationA.getLatitudeDouble(), locationA.getLongitudeDouble(),
      locationC.getLatitudeDouble(), locationC.getLongitudeDouble());

    BigDecimal secondLeg = calculateHaversineDistance(
      locationC.getLatitudeDouble(), locationC.getLongitudeDouble(),
      locationB.getLatitudeDouble(), locationB.getLongitudeDouble());

    BigDecimal miles = firstLeg.add(secondLeg);
    return miles.setScale(1, RoundingMode.HALF_EVEN);
  }

  public static DrivingDirections   getDrivingDirections(LatLng locationA, LatLng locationB) throws IOException {
    // https://developers.google.com/maps/documentation/directions/

    ExceptionUtils.assertNotNull(locationA, "locationA");
    ExceptionUtils.assertNotNull(locationB, "locationB");

    String url = "http://maps.googleapis.com/maps/api/directions/json";
    url += String.format("?origin=%s,%s", locationA.getLatitude(), locationA.getLongitude());
    url += String.format("&destination=%s,%s", locationB.getLatitude(), locationB.getLongitude());
    url += "&sensor=false";

    URLConnection connection = new URL(url).openConnection();
    String json = IoUtils.toString(connection.getInputStream());

    DrivingDirections directions = objectMapper.readValue(json, DrivingDirections.class);

    if (directions.getStatus().isNotOK()) {
      String msg = String.format("Unable to compute driving directions: %s (%s)", directions.getStatus(), directions.getStatus().getDescription());
      throw ApiException.internalServerError(msg);
    }

    return directions;
  }

  public static BigDecimal calculateHaversineDistance(double userLat, double userLng, double venueLat, double venueLng) {

      double latDistance = Math.toRadians(userLat - venueLat);
      double lngDistance = Math.toRadians(userLng - venueLng);

      double a = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) +
                      (Math.cos(Math.toRadians(userLat))) *
                      (Math.cos(Math.toRadians(venueLat))) *
                      (Math.sin(lngDistance / 2)) *
                      (Math.sin(lngDistance / 2));

      double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    BigDecimal kiloMeters = new BigDecimal(6372.797 * c);
    BigDecimal miles = kiloMeters.multiply(kmToMilesRatio);
    return miles.setScale(1, RoundingMode.HALF_EVEN);
  }

  public static LatLng getLatLng(String address, String city, State state, String zip) throws IOException {
    String st = (state == null) ? null : state.getAbbreviation();
    return getLatLng(address, city, st, zip);
  }

  public static LatLng getLatLng(String address, String city, String state, String zip) throws IOException {
    String url = "https://maps.googleapis.com/maps/api/geocode/xml?sensor=false&address=";
    url += encodeUrl(String.format("%s, %s, %s %s", address, city, state, zip));
    URLConnection connection = new URL(url).openConnection();
    String data = IoUtils.toString(connection.getInputStream());
    data = getTagContents(data, "location", 0);

    return new LatLng(
        getTagContents(data, "lat", 0),
        getTagContents(data, "lng", 0)
    );
  }
}
