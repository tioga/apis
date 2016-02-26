package org.tiogasolutions.apis.easypost;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.tiogasolutions.apis.easypost.carrier.UspsPredefinedPackages;
import org.tiogasolutions.apis.easypost.pub.*;
import org.tiogasolutions.apis.easypost.requests.BuyRateRequest;
import org.tiogasolutions.apis.easypost.requests.BuyRateResponse;
import org.tiogasolutions.apis.easypost.requests.CreateAddressRequest;
import org.tiogasolutions.apis.easypost.requests.CreateParcelRequest;
import org.tiogasolutions.dev.common.EnvUtils;

import java.util.List;

public class EasyPostClientTest {

  private EasyPostClient client;

  @BeforeClass
  public void beforeClass() {
    String apiKey = EnvUtils.requireProperty("tioga.test.easypost.api.key", SkipException.class);
    client = new EasyPostClient(apiKey);
  }

  @Test
  public void testCreateBusinessAddress() {
    Address address = client.createBusinessAddress(
        "Jacob Parr", "Sierra Telephone",
        "40033 Sierra Way", null,
        "Oakhurst", "CA", "93644",
        "111-222-3333", "jacobp.work@example.com");

    Assert.assertNotNull(address);
    Assert.assertNotNull(address.getId());
  }

  @Test
  public void testCreateResidentialAddress() {
    CreateAddressRequest request = RequestFactory.createResidentialAddress();
    Address address = client.createResidentialAddress(
        "Jacob Parr",
        "40565 Goldside Dr", null,
        "Oakhurst", "CA", "93644",
        "444-555-6666", "jacobp.home@example.com");

    Assert.assertNotNull(address);
    Assert.assertNotNull(address.getId());
  }

  @Test
  public void testCreatePredefinedParcel() {
    Parcel parcel = client.createParcel(10, UspsPredefinedPackages.flatRateLegalEnvelope);

    Assert.assertNotNull(parcel);
    Assert.assertNotNull(parcel.getId());

    Assert.assertEquals(parcel.getWeight(), 10, 0.01);
    Assert.assertEquals(parcel.getLength(), 0, 0.01);
    Assert.assertEquals(parcel.getWidth(), 0, 0.01);
    Assert.assertEquals(parcel.getHeight(), 0, 0.01);
  }

  @Test
  public void testCreateDimensionParcel() {
    Parcel parcel = client.createParcel(10, 3, 4, 5);

    Assert.assertNotNull(parcel);
    Assert.assertNotNull(parcel.getId());

    Assert.assertEquals(parcel.getWeight(), 10, 0.01);
    Assert.assertEquals(parcel.getLength(), 3, 0.01);
    Assert.assertEquals(parcel.getWidth(), 4, 0.01);
    Assert.assertEquals(parcel.getHeight(), 5, 0.01);
  }

  @Test
  public void testCreateShipmentWithDimensions_OneShot() {

    CreateParcelRequest parcel = new CreateParcelRequest(10, 3, 4, 5);
    CreateAddressRequest toAddress = RequestFactory.createBusinessAddress();
    CreateAddressRequest frAddress = RequestFactory.createResidentialAddress();

    Shipment shipment = client.createShipment(parcel, toAddress, frAddress);
    Assert.assertNotNull(shipment);

    Assert.assertNotNull(shipment.getParcel());
    Assert.assertNotNull(shipment.getToAddress());
    Assert.assertNotNull(shipment.getFromAddress());

    List<Rate> rates = shipment.getRates();
    Assert.assertNotNull(rates);
    Assert.assertTrue(rates.size() >= 4, "Expected at least 4 " + rates);
  }

  @Test
  public void testCreateShipmentWithPredefinedParcel_OneShot() {

    CreateParcelRequest parcel = new CreateParcelRequest(10, UspsPredefinedPackages.flatRateEnvelope);
    CreateAddressRequest toAddress = RequestFactory.createBusinessAddress();
    CreateAddressRequest frAddress = RequestFactory.createResidentialAddress();

    Shipment shipment = client.createShipment(parcel, toAddress, frAddress);
    Assert.assertNotNull(shipment);

    Assert.assertNotNull(shipment.getRates());
    Assert.assertEquals(shipment.getRates().size(), 2);
  }

  @Test
  public void testCreateShipmentWithPredefinedParcel_FourShot() {

    Parcel parcel = client.createParcel(10, UspsPredefinedPackages.flatRateEnvelope);
    Address toAddress = client.createAddress(RequestFactory.createBusinessAddress());
    Address frAddress = client.createAddress(RequestFactory.createResidentialAddress());

    Shipment shipment = client.createShipmentFrom(parcel, toAddress, frAddress);
    Assert.assertNotNull(shipment);

    Assert.assertNotNull(shipment.getParcel());
    Assert.assertNotNull(shipment.getToAddress());
    Assert.assertNotNull(shipment.getFromAddress());

    Assert.assertNotNull(shipment.getRates());
    Assert.assertEquals(shipment.getRates().size(), 2);
  }

  @Test
  public void testCreateShipmentWithDimensions_FourShot() {

    Parcel parcel = client.createParcel(10, 4, 3, 2);
    Address toAddress = client.createAddress(RequestFactory.createBusinessAddress());
    Address frAddress = client.createAddress(RequestFactory.createResidentialAddress());

    Shipment shipment = client.createShipmentFrom(parcel, toAddress, frAddress);
    Assert.assertNotNull(shipment);

    Assert.assertNotNull(shipment.getParcel());
    Assert.assertNotNull(shipment.getToAddress());
    Assert.assertNotNull(shipment.getFromAddress());

    List<Rate> rates = shipment.getRates();
    Assert.assertNotNull(rates);
    Assert.assertTrue(rates.size() >= 4, "Expected at least 4 " + rates);
  }

  @Test
  public void testBuyShipment() {

    CreateParcelRequest parcel = new CreateParcelRequest(10, 2, 3, 4);
    CreateAddressRequest toAddress = RequestFactory.createBusinessAddress();
    CreateAddressRequest frAddress = RequestFactory.createResidentialAddress();

    Shipment shipment = client.createShipment(parcel, toAddress, frAddress);
    List<Rate> rates = shipment.getRates();
    Assert.assertNotNull(rates);
    Assert.assertTrue(rates.size() >= 4, "Expected at least 4 " + rates);

    Rate rate = shipment.getRates().get(0);
    BuyRateRequest buyRateRequest = new BuyRateRequest(rate);

    BuyRateResponse response = client.buyPostage(buyRateRequest);
    Assert.assertNotNull(response);

    PostageLabel postageLabel = response.getPostageLabel();
    Assert.assertNotNull(postageLabel);
  }
}