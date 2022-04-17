package com.nhnacademy.edu.springframework.project2.repository;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tariff implements Comparable<Tariff> {
    int id;
    String city;
    String sector;
    int stage;
    int intervalStart;
    int intervalEnd;
    long unitPrice;


    public Tariff(@JsonProperty("순번") int id, @JsonProperty("지자체명") String city, @JsonProperty("업종") String sector, @JsonProperty("단계") int stage, @JsonProperty("구간시작(세제곱미터)") int intervalStart, @JsonProperty("구간끝(세제곱미터)") int intervalEnd,
                  @JsonProperty("구간금액(원)") long unitPrice) {
        this.id = id;
        this.city = city;
        this.sector = sector;
        this.stage = stage;
        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;
        this.unitPrice = unitPrice;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getSector() {
        return sector;
    }

    public int getIntervalStart() {
        return intervalStart;
    }

    public int getIntervalEnd() {
        return intervalEnd;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    @Override
    public String toString() {
        return "Tariff{" +
            "id=" + id +
            ", city='" + city + '\'' +
            ", sector='" + sector + '\'' +
            ", stage=" + stage +
            ", intervalStart=" + intervalStart +
            ", intervalEnd=" + intervalEnd +
            ", unitPrice=" + unitPrice +
            '}';
    }

    @Override
    public int compareTo(Tariff o) {
        return (int) (this.unitPrice - o.unitPrice);
    }
}
