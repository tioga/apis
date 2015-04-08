package org.tiogasolutions.apis.bighugethesaurus;

import java.io.IOException;
import java.net.URI;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import org.tiogasolutions.dev.common.StringUtils;
import org.tiogasolutions.dev.common.exceptions.ApiException;
import org.tiogasolutions.dev.common.json.JsonTranslator;
import org.tiogasolutions.dev.common.net.HttpStatusCode;

public class BigHugeThesaurusApis {

  private final String apiKey;
  private final String version;
  private final JsonTranslator translator;
  private final Cache cache;
  private final String urlTemplate;
  Client client = ClientBuilder.newBuilder().build();

  public BigHugeThesaurusApis(JsonTranslator translator, Cache cache, String apiKey) {
    this("words.bighugelabs.com", translator, cache, "2", apiKey);
  }

  public BigHugeThesaurusApis(String apiEndPoint, JsonTranslator translator, Cache cache, String version, String apiKey) {
    this.cache = cache;
    this.apiKey = apiKey;
    this.version = version;
    this.translator = translator;
    this.urlTemplate = "http://"+apiEndPoint+"/api/%s/%s/%s/json";
  }

  public Result lookup(final String word) throws IOException {

    String path = String.format(urlTemplate, version, apiKey, StringUtils.encodeUrl(word));
    URI uri = URI.create(path);

    try {
      String json = cache.get(word);

      if (json == null) {

        Response response = client.target(uri).request(MediaType.APPLICATION_JSON_TYPE).get();
        int status = response.getStatus();

        if (status == 200) {
          json = response.readEntity(String.class);
          cache.put(word, json);

        } else if (status == 404) {
          System.out.printf("The word \"%s\" was not found.%n", word);
          // cache.put(word, "");
          return null;

        } else {
          String msg = String.format("%s: Unable to process request for \"%s\" at %s", status, word, uri);
          throw new ApiException(HttpStatusCode.findByCode(status), msg);
        }
      } else if (StringUtils.isBlank(json)) {
        // The file exists but it is empty because we
        // know the word does not exist in the thesaurus
        return null;
      }

      return translator.fromJson(Result.class, json);

    } catch (ApiException ex) {
      throw ex;

    } catch (Exception ex) {
      String msg = String.format("Unexpected exception processing \"%s\" at %s", word, uri);
      throw new IOException(msg, ex);
    }
  }

  public String getApiKey() {
    return apiKey;
  }

  public String getVersion() {
    return version;
  }

  public JsonTranslator getTranslator() {
    return translator;
  }

  public Cache getCache() {
    return cache;
  }

}
