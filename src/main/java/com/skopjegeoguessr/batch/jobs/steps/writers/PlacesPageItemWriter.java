package com.skopjegeoguessr.batch.jobs.steps.writers;

import com.google.maps.model.PlacesSearchResult;
import lombok.NonNull;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.JsonObjectMarshaller;
import org.springframework.batch.item.support.AbstractFileItemWriter;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//
public class PlacesPageItemWriter extends AbstractFileItemWriter<PlacesSearchResult[]> {
	private static final char JSON_OBJECT_SEPARATOR = ',';
	private static final char JSON_ARRAY_START = '[';
	private static final char JSON_ARRAY_STOP = ']';
	private JsonObjectMarshaller<PlacesSearchResult> jsonObjectMarshaller;

	public PlacesPageItemWriter(Resource resource, JsonObjectMarshaller<PlacesSearchResult> jsonObjectMarshaller) {
		Assert.notNull(resource, "resource must not be null");
		Assert.notNull(jsonObjectMarshaller, "json object marshaller must not be null");
		this.setResource(resource);
		this.setJsonObjectMarshaller(jsonObjectMarshaller);
		this.setHeaderCallback((writer) -> writer.write(91));
		this.setFooterCallback((writer) -> writer.write(this.lineSeparator + ']' + this.lineSeparator));
		this.setExecutionContextName(ClassUtils.getShortName(JsonFileItemWriter.class));
	}

	public void afterPropertiesSet() {
		if (this.append) {
			this.shouldDeleteIfExists = false;
		}

	}

	public void setJsonObjectMarshaller(JsonObjectMarshaller<PlacesSearchResult> jsonObjectMarshaller) {
		this.jsonObjectMarshaller = jsonObjectMarshaller;
	}

	@Override
	public String doWrite(@NonNull List<? extends PlacesSearchResult[]> items) {
		StringBuilder lines = new StringBuilder();
		Iterator<? extends PlacesSearchResult[]> iterator = items.iterator();
		if (!items.isEmpty() && this.state.getLinesWritten() > 0L) {
			lines.append(',').append(this.lineSeparator);
		}

		while (iterator.hasNext()) {
			PlacesSearchResult[] item = iterator.next();
			Iterator<PlacesSearchResult> itemIterator = Arrays.stream(item).iterator();
			while (itemIterator.hasNext()) {
				PlacesSearchResult actualItem = itemIterator.next();
				lines.append(' ').append(this.jsonObjectMarshaller.marshal(actualItem));
				if (itemIterator.hasNext()) {
					lines.append(',').append(this.lineSeparator);
				}
			}
			if (iterator.hasNext()) {
				lines.append(',').append(this.lineSeparator);
			}
		}

		return lines.toString();
	}
}
