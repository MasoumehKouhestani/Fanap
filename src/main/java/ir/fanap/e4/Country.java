package ir.fanap.e4;

import ir.fanap.e5.exception.CountryNullFieldException;

public record Country(String name, String code, Long population) {
    public String print() throws CountryNullFieldException {
        String toString = String.format("%s[name=%s, code=%s, population=%s]", this.name(), this.name, this.code, this.population);
        if (this.name == null || this.name.isBlank() ||
                this.code == null || this.code.isBlank() ||
                this.population == null)
            throw new CountryNullFieldException();

        System.out.println(toString);
        return toString;
    }
}
