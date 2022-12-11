package com.skopjegeoguessr.springbatchdemo.config;

import com.skopjegeoguessr.springbatchdemo.model.AllData;

import org.springframework.batch.item.ItemProcessor;

public class DataProcessor implements ItemProcessor<AllData, AllData> {

    @Override
    public AllData process(AllData allData) throws Exception {
        return allData;
    }
}
