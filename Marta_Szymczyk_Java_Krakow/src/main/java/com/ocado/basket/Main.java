package com.ocado.basket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String absolutePathToConfigFile = "src/main/resources/config.json";
        BasketSplitter basketSplitter = new BasketSplitter(absolutePathToConfigFile);

        ObjectMapper mapper = new ObjectMapper();
        InputStream basketOne = Main.class.getClassLoader().getResourceAsStream("basket-1.json");
        InputStream basketTwo = Main.class.getClassLoader().getResourceAsStream("basket-2.json");
        List<String> dataListOne = mapper.readValue(basketOne, new TypeReference<>() {});
        List<String> dataListTwo = mapper.readValue(basketTwo, new TypeReference<>() {});

        System.out.println(basketSplitter.split(dataListOne));
        System.out.println(basketSplitter.split(dataListTwo));
    }
}
