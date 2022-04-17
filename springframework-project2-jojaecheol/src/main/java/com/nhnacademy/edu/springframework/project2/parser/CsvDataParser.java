package com.nhnacademy.edu.springframework.project2.parser;

import com.nhnacademy.edu.springframework.project2.repository.Tariff;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CsvDataParser implements DataParser {
    @Override
    public List<Tariff> parse(String fileLocation) {
        List<Tariff> tariffList = new ArrayList<>();

        try (
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileLocation);
        ) {
            assert inputStream != null;
            try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream,
                StandardCharsets.UTF_8);
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
                String line = null;
                line = bufferedReader.readLine();
                line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] token = line.split(",");
                    tariffList.add(new Tariff(Integer.parseInt(token[0]), token[1], token[2],
                        Integer.parseInt(token[3]), Integer.parseInt(token[4]),
                        Integer.parseInt(token[5]), Long.parseLong(token[6])));
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException();
        }

        return tariffList;
    }
}
