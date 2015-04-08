package org.tiogasolutions.apis.bighugethesaurus;

public interface Cache {
  String get(String word);
  String put(String word, String json);
}
