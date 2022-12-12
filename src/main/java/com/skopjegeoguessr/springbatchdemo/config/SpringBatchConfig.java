package com.skopjegeoguessr.springbatchdemo.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResult;
import com.skopjegeoguessr.springbatchdemo.config.processors.PlaceProcessor;
import com.skopjegeoguessr.springbatchdemo.mappers.BaseToFilteredPlacesMapper;
import com.skopjegeoguessr.springbatchdemo.model.Place;
import com.skopjegeoguessr.springbatchdemo.repository.PlaceRepository;
import com.skopjegeoguessr.springbatchdemo.utility.Utilities;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.maps.model.PlacesSearchResponse;
import org.springframework.core.io.FileSystemResource;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;
    private PlaceRepository placeRepository;

    //public static final String GCP_API_KEY = "AIzaSyCZMPjDh82Z_NKVyeTHsddOYS_hMAmQg8w";

    @Bean
    public Job jsonCoffeeJob() throws IOException, InterruptedException, ApiException {
        return jobBuilderFactory.get("jsonCoffeeJob").start(CoffeeStep()).build();
    }

    @Bean
    public Job jsonLandmarkJob() throws IOException, InterruptedException, ApiException {
        return jobBuilderFactory.get("jsonLandmarkJob").start(LandmarkStep()).build();
    }


    @Bean
    public Step CoffeeStep() throws IOException, InterruptedException, ApiException {
        return stepBuilderFactory.get("CoffeeStep")
                .<Place, Place>chunk(10)
                .reader(jsonCoffeeItemReader()).processor(processor()).writer(jsonWriter())
                .build();
    }

    @Bean
    public Step LandmarkStep() throws IOException, InterruptedException, ApiException {
        return stepBuilderFactory.get("LandmarkStep")
                .<Place, Place>chunk(10)
                .reader(jsonLandmarkItemReader()).processor(processor()).writer(jsonWriter())
                .build();
    }

    @Bean
    public JsonItemReader<Place> jsonCoffeeItemReader() throws IOException, InterruptedException, ApiException {
        final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        final JacksonJsonObjectReader<Place> jsonObjectReader = new JacksonJsonObjectReader<>(Place.class);
        jsonObjectReader.setMapper(mapper);
        GeoApiContext context1 = new GeoApiContext.Builder().apiKey("AIzaSyCZMPjDh82Z_NKVyeTHsddOYS_hMAmQg8w").build();
        PlacesSearchResponse results =  PlacesApi.textSearchQuery(context1, "coffeeshops+skopje+macedonia").await();
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalTime.class, (JsonDeserializer<LocalTime>) (json, type, jsonDeserializationContext) -> {
            try{
                return LocalTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            } catch (DateTimeParseException e){
                return LocalTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
            }
        }).setPrettyPrinting().create();

        List<PlacesSearchResponse> responses = new java.util.ArrayList<>(List.of(results));
        Thread.sleep(3000);
        while (responses.get(responses.size() - 1).nextPageToken != null &&
                responses.get(responses.size() - 1).nextPageToken.length() != 0) {
            Thread.sleep(3000);
            responses.add(
                    Utilities.wrapWithContext(context ->
                            PlacesApi.textSearchQuery(
                                    context,
                                    "coffeeshops+skopje+macedonia"
                            ).pageToken(responses.get(responses.size() - 1).nextPageToken).await()
                    )
            );
        }
        List<PlacesSearchResult[]> responses1 = new ArrayList<>();
        for(PlacesSearchResponse p: responses){
            responses1.add(p.results);
        }
        List<PlacesSearchResult> responses2 = new ArrayList<>();
        for(PlacesSearchResult[] p:responses1){
            responses2.addAll(Arrays.asList(p));
        }
        List<Place> placeList = responses2.stream().map(BaseToFilteredPlacesMapper.INSTANCE::toDto).collect(Collectors.toList());

        FileWriter writer = new FileWriter("src/main/resources/static/results.json");
        gson.toJson(placeList, writer);
        writer.flush();
        return new JsonItemReaderBuilder<Place>().jsonObjectReader(jsonObjectReader)
                    .resource(new FileSystemResource("src/main/resources/static/results.json"))
                    .name("jsonItemReader")
                    .build();
    }

    @Bean
    public JsonItemReader<Place> jsonLandmarkItemReader() throws IOException, InterruptedException, ApiException {
        final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        final JacksonJsonObjectReader<Place> jsonObjectReader = new JacksonJsonObjectReader<>(Place.class);
        jsonObjectReader.setMapper(mapper);
        GeoApiContext context1 = new GeoApiContext.Builder().apiKey("AIzaSyCZMPjDh82Z_NKVyeTHsddOYS_hMAmQg8w").build();
        PlacesSearchResponse results =  PlacesApi.textSearchQuery(context1, "landmarks+skopje+macedonia").await();
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalTime.class, (JsonDeserializer<LocalTime>) (json, type, jsonDeserializationContext) -> {
            try{
                return LocalTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            } catch (DateTimeParseException e){
                return LocalTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
            }
        }).setPrettyPrinting().create();

        List<PlacesSearchResponse> responses = new java.util.ArrayList<>(List.of(results));
        Thread.sleep(3000);
        while (responses.get(responses.size() - 1).nextPageToken != null &&
                responses.get(responses.size() - 1).nextPageToken.length() != 0) {
            Thread.sleep(3000);
            responses.add(
                    Utilities.wrapWithContext(context ->
                            PlacesApi.textSearchQuery(
                                    context,
                                    "landmarks+skopje+macedonia"
                            ).pageToken(responses.get(responses.size() - 1).nextPageToken).await()
                    )
            );
        }
        List<PlacesSearchResult[]> responses1 = new ArrayList<>();
        for(PlacesSearchResponse p: responses){
            responses1.add(p.results);
        }
        List<PlacesSearchResult> responses2 = new ArrayList<>();
        for(PlacesSearchResult[] p:responses1){
            responses2.addAll(Arrays.asList(p));
        }
        responses2.forEach(i -> System.out.println(i.types));
        List<Place> placeList = responses2.stream().map(BaseToFilteredPlacesMapper.INSTANCE::toDto).collect(Collectors.toList());

        FileWriter writer = new FileWriter("src/main/resources/static/results1.json");
        gson.toJson(placeList, writer);
        writer.flush();
        return new JsonItemReaderBuilder<Place>().jsonObjectReader(jsonObjectReader)
                .resource(new FileSystemResource("src/main/resources/static/results1.json"))
                .name("jsonItemReader")
                .build();
    }

    @Bean
    public RepositoryItemWriter<Place> jsonWriter(){
        RepositoryItemWriter<Place> writer = new RepositoryItemWriter<>();
        writer.setRepository(placeRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public PlaceProcessor processor(){
        return new PlaceProcessor();
    }


}
