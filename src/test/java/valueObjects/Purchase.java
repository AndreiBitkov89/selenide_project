package valueObjects;

public class Purchase {

    private final String NAME;
    private final String COUNTRY;
    private final String CITY;
    private final String CARD;
    private final String MONTH;
    private final String YEAR;

    public Purchase(String name, String country, String city, String card, String month, String year) {
        this.NAME = name;
        this.COUNTRY = country;
        this.CITY = city;
        this.CARD = card;
        this.MONTH = month;
        this.YEAR = year;
    }

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
}
