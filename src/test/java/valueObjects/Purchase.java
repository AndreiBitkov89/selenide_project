package valueObjects;

public class Purchase {

    private String name;
    private String country;
    private String city;
    private String card;
    private String month;
    private String year;

    public Purchase(String name, String country, String city, String card, String month, String year) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.card = card;
        this.month = month;
        this.year = year;
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
}
