package org.tiogasolutions.apis.easypost.pub;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EpEvent<T> {
	private final String id;
	private final String description;
	private final String mode;
	private final T result;

	@JsonCreator
	public EpEvent(@JsonProperty("id") String id,
				   @JsonProperty("description") String description,
				   @JsonProperty("mode") String mode,
				   @JsonProperty("result") T result) {
		this.id = id;
		this.description = description;
		this.mode = mode;
		this.result = result;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getMode() {
		return mode;
	}

	public T getResult() {
		return result;
	}
}
