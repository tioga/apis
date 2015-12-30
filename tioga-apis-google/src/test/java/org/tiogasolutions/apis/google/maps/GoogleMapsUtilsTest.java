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

import java.io.IOException;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import org.testng.SkipException;
import org.tiogasolutions.dev.domain.locality.*;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@Test
public class GoogleMapsUtilsTest {

  private LatLng homeLatLng;
  private LatLng workLatLng;
  private LatLng jonLatLng;
  private LatLng sunnyLatLng;

  @BeforeClass
  public void beforeClass() throws Exception {
    try {
      homeLatLng = GoogleMapsUtils.getLatLng("40565 Goldside Dr", "Oakhurst", State.CA, "93644");
      workLatLng = GoogleMapsUtils.getLatLng("40033 Sierra Way", "Oakhurst", State.CA, "93644");
      jonLatLng = GoogleMapsUtils.getLatLng("2203 E Berkeley", "Fresno", "CA", "93703");
      sunnyLatLng = GoogleMapsUtils.getLatLng("5235 E Kings Canyon Suite #105", "Fresno", "CA", "93727");
    } catch (UnknownHostException e) {
      throw new SkipException("Offline", e);
    }
  }

  public void testGetDirections_JonToSunnySide() throws Exception {
    DrivingDirections directions = GoogleMapsUtils.getDrivingDirections(jonLatLng, sunnyLatLng);
    assertNotNull(directions);
    assertNotNull(directions.getRoutes());
    assertEquals(directions.getRoutes().length, 1);

    Route route = directions.getFirstRoute();
    assertNotNull(route);
    assertNotNull(route.getLegs());
    assertEquals(route.getLegs().length, 1);

    Leg leg = route.getFirstLeg();
    assertNotNull(leg);

    assertNotNull(leg.getDistance());

    // Google re-routs which causes differences in distance
    long meters = leg.getDistance().getMeters();
    assertTrue(meters > 10000, "Found " + meters);
    assertTrue(meters < 13000, "Found " + meters);

    String label = leg.getDistance().getLabel();
    boolean contains = Arrays.asList("6.8 mi", "6.9 mi", "7.0 mi", "8.0 mi").contains(label);
    assertTrue(contains, "Found " + label);

    assertNotNull(leg.getDuration());

    // Google will change routes which messes with tests.
    long seconds = leg.getDuration().getSeconds();
    assertTrue(seconds > 500 && seconds < 800, "Found " + seconds);

    List<String> valid = Arrays.asList("11 mins", "12 mins", "13 mins");
    label = leg.getDuration().getLabel();
    assertTrue(valid.contains(label), "Was "+label+" but exepected one of " + valid);
  }

  public void testGetMilesByGoogle_JonToSunnySide() throws Exception {
    BigDecimal miles = GoogleMapsUtils.getMilesByGoogle(jonLatLng, sunnyLatLng);

    assertTrue(miles.doubleValue() > 6, "Found " + miles);
    assertTrue(miles.doubleValue() < 9, "Found " + miles);
  }

  public void testGetMilesByGoogle_HomeToJon() throws Exception {
    BigDecimal miles = GoogleMapsUtils.getMilesByGoogle(homeLatLng, jonLatLng);

    assertTrue(miles.doubleValue() > 45, "Found " + miles);
    assertTrue(miles.doubleValue() < 50, "Found " + miles);
  }

  public void testGetMilesByGoogle_HomeToWork() throws Exception {
    BigDecimal miles = GoogleMapsUtils.getMilesByGoogle(homeLatLng, workLatLng);
    assertEquals(miles, new BigDecimal("4.7"));
  }

  public void testGetMilesByLatLng() throws Exception {
    BigDecimal miles = GoogleMapsUtils.getMilesByLatLng(jonLatLng, sunnyLatLng);
    assertEquals(miles, new BigDecimal("6.1"));

    miles = GoogleMapsUtils.getMilesByLatLng(homeLatLng, jonLatLng);
    assertEquals(miles, new BigDecimal("43.4"));

    miles = GoogleMapsUtils.getMilesByLatLng(homeLatLng, workLatLng);
    assertEquals(miles, new BigDecimal("3.9"));
  }

  public void testCalculateHaversineDistance() throws Exception {
    BigDecimal miles = GoogleMapsUtils.calculateHaversineDistance(
        jonLatLng.getLatitudeDouble(), jonLatLng.getLongitudeDouble(),
        sunnyLatLng.getLatitudeDouble(), sunnyLatLng.getLongitudeDouble());
    assertEquals(miles, new BigDecimal("4.4"));

    miles = GoogleMapsUtils.calculateHaversineDistance(
        homeLatLng.getLatitudeDouble(), homeLatLng.getLongitudeDouble(),
        jonLatLng.getLatitudeDouble(), jonLatLng.getLongitudeDouble());
    assertEquals(miles, new BigDecimal("39.5"));

    miles = GoogleMapsUtils.calculateHaversineDistance(
        homeLatLng.getLatitudeDouble(), homeLatLng.getLongitudeDouble(),
        workLatLng.getLatitudeDouble(), workLatLng.getLongitudeDouble());
    assertEquals(miles, new BigDecimal("3.5"));
  }

  public void testGetLatLong() throws Exception {
    assertNotNull(homeLatLng);
    assertEquals(homeLatLng.getLatitude(), "37.3382030");
    assertEquals(homeLatLng.getLongitude(), "-119.7085060");

    assertNotNull(workLatLng);
    assertEquals(workLatLng.getLatitude(), "37.3304880");
    assertEquals(workLatLng.getLongitude(), "-119.6464390");

    assertNotNull(jonLatLng);
    assertEquals(jonLatLng.getLatitude(), "36.7704690");
    assertEquals(jonLatLng.getLongitude(), "-119.7849170");

    assertNotNull(sunnyLatLng);
    assertEquals(sunnyLatLng.getLatitude(), "36.7364561");
    assertEquals(sunnyLatLng.getLongitude(), "-119.7171841");
  }
}
