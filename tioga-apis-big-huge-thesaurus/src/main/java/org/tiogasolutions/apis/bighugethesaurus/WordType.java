package org.tiogasolutions.apis.bighugethesaurus;

import com.fasterxml.jackson.annotation.*;
import java.util.*;

public class WordType {

  private final List<String> synonyms;
  private final List<String> antonyms;
  private final List<String> related;
  private final List<String> similar;
  private final List<String> suggested;

  @JsonCreator
  public WordType(@JsonProperty("syn") List<String> synonyms,
                  @JsonProperty("ant") List<String> antonyms,
                  @JsonProperty("rel") List<String> related,
                  @JsonProperty("sim") List<String> similar,
                  @JsonProperty("usr") List<String> suggested) {

    this.synonyms =  (synonyms != null) ? synonyms : Collections.emptyList();
    this.antonyms =  (antonyms != null) ? antonyms : Collections.emptyList();
    this.related  =  (related != null) ?  related :  Collections.emptyList();
    this.similar  =  (similar != null) ?  similar :  Collections.emptyList();
    this.suggested = (suggested != null) ? suggested : Collections.emptyList();
  }

  public List<String> getSuggested() {
    return suggested;
  }

  public List<String> getSynonyms() {
    return synonyms;
  }

  public List<String> getAntonyms() {
    return antonyms;
  }

  public List<String> getRelated() {
    return related;
  }

  public List<String> getSimilar() {
    return similar;
  }

  public String toString() {
    return String.format("synonyms: %s, antonyms: %s, related: %s, similar: %s, suggested: %s", synonyms, antonyms, related, similar, suggested);
  }
}
