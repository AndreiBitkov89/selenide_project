package valueObjects;

public class Purchase {

    private final String name;
    private final String country;
    private final String city;
    private final String card;
    private final String month;
    private final String year;

    private Purchase(Builder builder) {
        this.name = builder.name;
        this.country = builder.country;
        this.city = builder.city;
        this.card = builder.creditCard;
        this.month = builder.month;
        this.year = builder.year;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getCard() {
        return card;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
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



