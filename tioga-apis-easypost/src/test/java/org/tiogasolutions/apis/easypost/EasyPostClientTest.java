package org.tiogasolutions.apis.easypost;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.tiogasolutions.apis.easypost.carrier.EpUspsPredefinedPackages;
import org.tiogasolutions.apis.easypost.pub.*;
import org.tiogasolutions.apis.easypost.requests.EpPurchaseShipmentRequest;
import org.tiogasolutions.apis.easypost.requests.EpPurchaseShippingResponse;
import org.tiogasolutions.apis.easypost.requests.EpCreateAddressRequest;
import org.tiogasolutions.apis.easypost.requests.EpCreateParcelRequest;
import org.tiogasolutions.dev.common.EnvUtils;

import java.util.List;

@Test
public class EasyPostClientTest {

  private EasyPostClient client;

  private EpParcel parcel;
  private EpAddress fromAddress;
  private EpAddress toAddress;
  private EpShipment shipment;
  private EpPostageLabel postageLabel;

  @BeforeClass
  public void beforeClass() {
    String apiKey = EnvUtils.requireProperty("tioga.test.easypost.api.key", SkipException.class);
    client = new EasyPostClient(apiKey);
  }

  public void testCreateBusinessAddress() {
    fromAddress = client.createBusinessAddress(
        "Jacob Parr", "Sierra Telephone",
        "40033 Sierra Way", null,
        "Oakhurst", "CA", "93644",
        "111-222-3333", "jacobp.work@example.com");

    Assert.assertNotNull(fromAddress);
    Assert.assertNotNull(fromAddress.getId());
  }

  public void testCreateResidentialAddress() {
    toAddress = client.createResidentialAddress(
        "Jacob Parr",
        "40565 Goldside Dr", null,
        "Oakhurst", "CA", "93644",
        "444-555-6666", "jacobp.home@example.com");

    Assert.assertNotNull(toAddress);
    Assert.assertNotNull(toAddress.getId());
  }

  public void testCreateDimensionParcel() {
    parcel = client.createParcel(10, 3, 4, 5);

    Assert.assertNotNull(parcel);
    Assert.assertNotNull(parcel.getId());

    Assert.assertEquals(parcel.getWeight(), 10, 0.01);
    Assert.assertEquals(parcel.getLength(), 3, 0.01);
    Assert.assertEquals(parcel.getWidth(), 4, 0.01);
    Assert.assertEquals(parcel.getHeight(), 5, 0.01);
  }

  public void testCreatePredefinedParcel() {
    EpParcel parcel = client.createParcel(10, EpUspsPredefinedPackages.flatRateLegalEnvelope);

    Assert.assertNotNull(parcel);
    Assert.assertNotNull(parcel.getId());

    Assert.assertEquals(parcel.getWeight(), 10, 0.01);
    Assert.assertEquals(parcel.getLength(), 0, 0.01);
    Assert.assertEquals(parcel.getWidth(), 0, 0.01);
    Assert.assertEquals(parcel.getHeight(), 0, 0.01);
  }

  public void testCreateShipmentWithDimensions_OneShot() {

    EpCreateParcelRequest parcel = new EpCreateParcelRequest(10, 3, 4, 5);
    EpCreateAddressRequest toAddress = EpRequestFactory.createBusinessAddress();
    EpCreateAddressRequest frAddress = EpRequestFactory.createResidentialAddress();

    EpShipment shipment = client.createShipment(parcel, frAddress, toAddress, EpLabelFormat.PDF);
    Assert.assertNotNull(shipment);

    Assert.assertNotNull(shipment.getParcel());
    Assert.assertNotNull(shipment.getToAddress());
    Assert.assertNotNull(shipment.getFromAddress());

    List<EpRate> rates = shipment.getRates();
    Assert.assertNotNull(rates);
    Assert.assertTrue(rates.size() >= 4, "Expected at least 4 " + rates);
  }

  public void testCreateShipmentWithPredefinedParcel_OneShot() {

    EpCreateParcelRequest parcel = new EpCreateParcelRequest(10, EpUspsPredefinedPackages.flatRateEnvelope);
    EpCreateAddressRequest toAddress = EpRequestFactory.createBusinessAddress();
    EpCreateAddressRequest frAddress = EpRequestFactory.createResidentialAddress();

    EpShipment shipment = client.createShipment(parcel, frAddress, toAddress, EpLabelFormat.PDF);
    Assert.assertNotNull(shipment);

    Assert.assertNotNull(shipment.getRates());
    Assert.assertEquals(shipment.getRates().size(), 2);
  }

  @Test(dependsOnMethods = {"testCreateBusinessAddress", "testCreateResidentialAddress", "testCreateDimensionParcel"})
  public void testCreateShipment() {

    shipment = client.createShipmentFrom(parcel, fromAddress, toAddress, EpLabelFormat.PDF);
    Assert.assertNotNull(shipment);

    Assert.assertNotNull(shipment.getParcel());
    Assert.assertNotNull(shipment.getToAddress());
    Assert.assertNotNull(shipment.getFromAddress());

    List<EpRate> rates = shipment.getRates();
    Assert.assertNotNull(rates);
    Assert.assertTrue(rates.size() >= 4, "Expected at least 4 " + rates);
  }

  @Test(dependsOnMethods = "testCreateShipment")
  public void testBuyPostage() {

    // Arbitrarily take the first one
    EpRate rate = shipment.getRates().get(0);
    EpPurchaseShipmentRequest buyRateRequest = new EpPurchaseShipmentRequest(rate);

    EpPurchaseShippingResponse response = client.purchaseShipment(buyRateRequest);
    Assert.assertNotNull(response);

    postageLabel = response.getPostageLabel();
    Assert.assertNotNull(postageLabel);
    Assert.assertTrue(postageLabel.getLabelUrl().endsWith(".pdf"), "Found " + postageLabel.getLabelUrl());
  }

  @Test(dependsOnMethods = "testCreateDimensionParcel")
  public void testGetParcel() {
    EpParcel newParcel = client.getParcel(parcel);
    Assert.assertNotNull(newParcel);
    Assert.assertEquals(newParcel.getId(), parcel.getId());

    newParcel = client.getParcel(parcel.getId());
    Assert.assertNotNull(newParcel);
    Assert.assertEquals(newParcel.getId(), parcel.getId());
  }

  @Test(dependsOnMethods = {"testCreateBusinessAddress", "testCreateResidentialAddress"})
  public void testGetAddress() {
    EpAddress newAddress = client.getAddress(fromAddress);
    Assert.assertNotNull(newAddress);
    Assert.assertEquals(newAddress.getId(), fromAddress.getId());
    Assert.assertNotEquals(newAddress.getId(), toAddress.getId());

    newAddress = client.getAddress(fromAddress.getId());
    Assert.assertNotNull(newAddress);
    Assert.assertEquals(newAddress.getId(), fromAddress.getId());
    Assert.assertNotEquals(newAddress.getId(), toAddress.getId());

    newAddress = client.getAddress(toAddress);
    Assert.assertNotNull(newAddress);
    Assert.assertEquals(newAddress.getId(), toAddress.getId());
    Assert.assertNotEquals(newAddress.getId(), fromAddress.getId());

    newAddress = client.getAddress(toAddress.getId());
    Assert.assertNotNull(newAddress);
    Assert.assertEquals(newAddress.getId(), toAddress.getId());
    Assert.assertNotEquals(newAddress.getId(), fromAddress.getId());
  }

  @Test(dependsOnMethods = "testCreateShipment")
  public void testGetShipment() {

    EpShipment newShipment = client.getShipment(shipment);
    Assert.assertNotNull(newShipment);
    Assert.assertEquals(newShipment.getId(), shipment.getId());

    newShipment = client.getShipment(shipment.getId());
    Assert.assertNotNull(newShipment);
    Assert.assertEquals(newShipment.getId(), shipment.getId());
  }

//  @Test(dependsOnMethods = "testBuyPostage")
//  public void testGetPostage() {
//
//    PostageLabel newPostageLabel = client.getPostageLabel(shipment, postageLabel);
//    Assert.assertNotNull(newPostageLabel);
//    Assert.assertEquals(newPostageLabel.getId(), postageLabel.getId());
//
//    newPostageLabel = client.getPostageLabel(shipment.getId(), postageLabel.getId());
//    Assert.assertNotNull(newPostageLabel);
//    Assert.assertEquals(newPostageLabel.getId(), postageLabel.getId());
//  }
}











