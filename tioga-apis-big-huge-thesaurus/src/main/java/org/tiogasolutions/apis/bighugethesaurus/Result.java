package org.tiogasolutions.apis.bighugethesaurus;

import com.fasterxml.jackson.annotation.*;
import java.util.*;

public class Result {

  private final WordType unknown;
  private final WordType noun;
  private final WordType verb;
  private final WordType adverb;
  private final WordType adjective;

  @JsonCreator
  public Result(@JsonProperty("unknown") WordType unknown,
                @JsonProperty("noun") WordType noun,
                @JsonProperty("verb") WordType verb,
                @JsonProperty("adverb") WordType adverb,
                @JsonProperty("adjective") WordType adjective) {

    this.unknown = unknown;
    this.noun = noun;
    this.verb = verb;
    this.adverb = adverb;
    this.adjective = adjective;
  }

  public WordType getUnknown() {
    return unknown;
  }

  public WordType getAdverb() {
    return adverb;
  }

  public WordType getVerb() {
    return verb;
  }

  public WordType getNoun() {
    return noun;
  }

  public WordType getAdjective() {
    return adjective;
  }

  public Set<String> getAllWords() {
    Set<String> set = new TreeSet<>();

    for (WordType wordType : Arrays.asList(noun, verb, adverb, adjective, unknown)) {
      if (wordType != null) {
        set.addAll(wordType.getAntonyms());
        set.addAll(wordType.getSynonyms());
        set.addAll(wordType.getSimilar());
        set.addAll(wordType.getRelated());
      }
    }
    
    return set;
  }
}
