package ir.fanap.e4;

import java.util.HashMap;
import java.util.Map;

public class Q6 {

    public static void main(String[] args) {
        Map<String, Country> countryMap = new HashMap<>();

        Country country1 = new Country("Tehran", "THR", 6_000_000L);
        Country country2 = new Country("Tabriz", "TBR", 1_000_000L);
        Country country3 = new Country("Yazd", "YZD", 1_000_000L);
        Country country4 = new Country("Isfahan", "ISF", 1_000_000L);

        countryMap.put(country1.code(), country1);
        countryMap.put(country2.code(), country2);
        countryMap.put(country3.code(), country3);
        countryMap.put(country4.code(), country4);

        System.out.println(countryMap.get(country1.code()));
        // result: Country[name=Tehran, code=THR, population=6000000]
    }

}