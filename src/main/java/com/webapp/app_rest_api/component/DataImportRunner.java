package com.webapp.app_rest_api.component;

import com.webapp.app_rest_api.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataImportRunner implements CommandLineRunner {
    @Autowired
    private FoodService foodService;

    @Override
    public void run(String... args) throws Exception {
        String csvFilePath = "D:\\itrytostudy\\spring learning\\HealthyLife\\healthy-life-rest-api\\food_data.csv";
//        foodService.saveDataFromCsv(csvFilePath);
    }
}