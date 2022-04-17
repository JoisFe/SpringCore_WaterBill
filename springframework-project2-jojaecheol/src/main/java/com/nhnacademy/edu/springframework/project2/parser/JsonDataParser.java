package com.nhnacademy.edu.springframework.project2.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springframework.project2.repository.Tariff;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class JsonDataParser implements DataParser {
    public List<Tariff> parse(String fileLocation) {
        List<Tariff> tariffList = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();

        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileLocation);
        ) {
            assert inputStream != null;
            try(InputStreamReader inputStreamReader = new InputStreamReader(inputStream,
                StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
                Tariff[] tariffs = objectMapper.readValue(bufferedReader, Tariff[].class);

                tariffList = List.of(tariffs);

            }

            } catch (IOException e) {
                e.printStackTrace();
            }


            return tariffList;
    }
}
