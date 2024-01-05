package ir.fanap.e4;

import ir.fanap.e5.exception.CountryNullNameException;
import ir.fanap.e5.exception.CountryNullPopulationException;

public record Country(String name, String code, Long population) {
    public String print() throws CountryNullNameException, CountryNullPopulationException {
        String toString = String.format("%s[name=%s, code=%s, population=%s]", this.name(), this.name, this.code, this.population);
        if (this.name == null || this.name.isBlank())
            throw new CountryNullNameException();
        if (this.population == null)
            throw new CountryNullPopulationException();

        System.out.println(toString);
        return toString;
    }
}
