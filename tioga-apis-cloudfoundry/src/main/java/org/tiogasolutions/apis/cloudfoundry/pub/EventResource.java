package org.tiogasolutions.apis.cloudfoundry.pub;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class EventResource implements Comparable<EventResource> {

  private final Event event;
  private final Metadata metadata;

  public EventResource(@JsonProperty("entity") Event event,
                       @JsonProperty("metadata") Metadata metadata) {

    this.event = event;
    this.metadata = metadata;
  }

  public Metadata getMetadata() {
    return metadata;
  }

  public Event getEvent() {
    return event;
  }

  @Override
  public int compareTo(EventResource that) {
    return this.getEvent().compareTo(that.getEvent());
  }

  public String toString() {
    return event.toString();
  }
}
