package valueObjects;

public class Item {
    //todo меня смущает верхний регистр, все же "A constant variable is a final variable of primitive type or type
    // String that is initialized with a constant expression", тут у тебя нет инициализации значением
    private final String ITEM_TITLE;
    private final int ITEM_PRICE;

    public Item(String itemTitle, int itemPrice) {
        this.ITEM_TITLE = itemTitle;
        this.ITEM_PRICE = itemPrice;
    }

    public int getItemPrice() {
        return ITEM_PRICE;
    }

    //todo getItemTitle()
    public String getTitle() {
        return ITEM_TITLE;
    }

}
