package ir.fanap.e5;

import ir.fanap.e4.Country;
import ir.fanap.e5.exception.CountryNullFieldException;

import java.util.HashMap;
import java.util.Map;

public class Q2 {

    public static void main(String[] args) {
        Map<String, Country> countryMap = new HashMap<>();

        Country country1 = new Country("Tehran", "THR", 6_000_000L);
        Country country2 = new Country("Tabriz", "TBR", 1_000_000L);
        Country country3 = new Country("Yazd", "YZD", null);
        Country country4 = new Country(null, "ISF", 1_000_000L);

        countryMap.put(country1.code(), country1);
        countryMap.put(country2.code(), country2);
        countryMap.put(country3.code(), country3);
        countryMap.put(country4.code(), country4);

        for (String code : countryMap.keySet()) {
            try {
                countryMap.get(code).print();
            } catch (CountryNullFieldException e) {
                System.out.println(e.getClass().getSimpleName() + " has been thrown!");
            }
        }
    }
}
