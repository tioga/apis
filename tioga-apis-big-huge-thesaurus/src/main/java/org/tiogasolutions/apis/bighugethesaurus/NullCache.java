package org.tiogasolutions.apis.bighugethesaurus;

public class NullCache implements Cache {

  @Override
  public String get(String word) {
    return null;
  }

  @Override
  public String put(String word, String json) {
    return null;
  }
}
