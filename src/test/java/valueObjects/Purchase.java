package valueObjects;

public class Purchase {

    //todo верхний регистр
    private final String NAME;
    private final String COUNTRY;
    private final String CITY;
    private final String CARD;
    private final String MONTH;
    private final String YEAR;

    private Purchase(Builder builder) {
        this.NAME = builder.name;
        this.COUNTRY = builder.country;
        this.CITY = builder.city;
        this.CARD = builder.creditCard;
        this.MONTH = builder.month;
        this.YEAR = builder.year;
    }

    //todo getName(), лучше lowerCamelCase использовать для методов
    public String getNAME() {
        return NAME;
    }

    public String getCOUNTRY() {
        return COUNTRY;
    }

    public String getCITY() {
        return CITY;
    }

    public String getCARD() {
        return CARD;
    }

    public String getMONTH() {
        return MONTH;
    }

    public String getYEAR() {
        return YEAR;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String country;
        private String city;
        private String creditCard;
        private String month;
        private String year;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withCreditCard(String creditCard) {
            this.creditCard = creditCard;
            return this;
        }

        public Builder withMonth(String month) {
            this.month = month;
            return this;
        }

        public Builder withYear(String year) {
            this.year = year;
            return this;
        }

        public Purchase build() {
            return new Purchase(this);
        }
    }

}



