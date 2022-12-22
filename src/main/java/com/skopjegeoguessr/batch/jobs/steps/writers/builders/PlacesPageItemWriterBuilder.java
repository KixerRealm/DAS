package com.skopjegeoguessr.batch.jobs.steps.writers.builders;

import com.google.maps.model.PlacesSearchResult;
import com.skopjegeoguessr.batch.jobs.steps.writers.PlacesPageItemWriter;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.json.JsonObjectMarshaller;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

public class PlacesPageItemWriterBuilder {
	private Resource resource;
	private JsonObjectMarshaller<PlacesSearchResult> jsonObjectMarshaller;
	private FlatFileHeaderCallback headerCallback;
	private FlatFileFooterCallback footerCallback;
	private String name;
	private String encoding = "UTF-8";
	private String lineSeparator;
	private boolean append;
	private boolean forceSync;
	private boolean saveState;
	private boolean shouldDeleteIfExists;
	private boolean shouldDeleteIfEmpty;
	private boolean transactional;

	public PlacesPageItemWriterBuilder() {
		this.lineSeparator = PlacesPageItemWriter.DEFAULT_LINE_SEPARATOR;
		this.append = false;
		this.forceSync = false;
		this.saveState = true;
		this.shouldDeleteIfExists = true;
		this.shouldDeleteIfEmpty = false;
		this.transactional = true;
	}

	public PlacesPageItemWriterBuilder saveState(boolean saveState) {
		this.saveState = saveState;
		return this;
	}

	public PlacesPageItemWriterBuilder name(String name) {
		this.name = name;
		return this;
	}

	public PlacesPageItemWriterBuilder forceSync(boolean forceSync) {
		this.forceSync = forceSync;
		return this;
	}

	public PlacesPageItemWriterBuilder lineSeparator(String lineSeparator) {
		this.lineSeparator = lineSeparator;
		return this;
	}

	public PlacesPageItemWriterBuilder jsonObjectMarshaller(JsonObjectMarshaller<PlacesSearchResult> jsonObjectMarshaller) {
		this.jsonObjectMarshaller = jsonObjectMarshaller;
		return this;
	}

	public PlacesPageItemWriterBuilder resource(Resource resource) {
		this.resource = resource;
		return this;
	}

	public PlacesPageItemWriterBuilder encoding(String encoding) {
		this.encoding = encoding;
		return this;
	}

	public PlacesPageItemWriterBuilder shouldDeleteIfEmpty(boolean shouldDelete) {
		this.shouldDeleteIfEmpty = shouldDelete;
		return this;
	}

	public PlacesPageItemWriterBuilder shouldDeleteIfExists(boolean shouldDelete) {
		this.shouldDeleteIfExists = shouldDelete;
		return this;
	}

	public PlacesPageItemWriterBuilder append(boolean append) {
		this.append = append;
		return this;
	}

	public PlacesPageItemWriterBuilder headerCallback(FlatFileHeaderCallback callback) {
		this.headerCallback = callback;
		return this;
	}

	public PlacesPageItemWriterBuilder footerCallback(FlatFileFooterCallback callback) {
		this.footerCallback = callback;
		return this;
	}

	public PlacesPageItemWriterBuilder transactional(boolean transactional) {
		this.transactional = transactional;
		return this;
	}

	public PlacesPageItemWriter build() {
		Assert.notNull(this.resource, "A resource is required.");
		Assert.notNull(this.jsonObjectMarshaller, "A json object marshaller is required.");
		if (this.saveState) {
			Assert.hasText(this.name, "A name is required when saveState is true");
		}

		PlacesPageItemWriter placesPageItemWriter = new PlacesPageItemWriter(this.resource, this.jsonObjectMarshaller);
		placesPageItemWriter.setName(this.name);
		placesPageItemWriter.setAppendAllowed(this.append);
		placesPageItemWriter.setEncoding(this.encoding);
		if (this.headerCallback != null) {
			placesPageItemWriter.setHeaderCallback(this.headerCallback);
		}

		if (this.footerCallback != null) {
			placesPageItemWriter.setFooterCallback(this.footerCallback);
		}

		placesPageItemWriter.setForceSync(this.forceSync);
		placesPageItemWriter.setLineSeparator(this.lineSeparator);
		placesPageItemWriter.setSaveState(this.saveState);
		placesPageItemWriter.setShouldDeleteIfEmpty(this.shouldDeleteIfEmpty);
		placesPageItemWriter.setShouldDeleteIfExists(this.shouldDeleteIfExists);
		placesPageItemWriter.setTransactional(this.transactional);
		return placesPageItemWriter;
	}
}

