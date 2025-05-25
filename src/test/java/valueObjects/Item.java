package valueObjects;

public class Item {
    private final String ITEM_TITLE;
    private final int ITEM_PRICE;

    public Item(String itemTitle, int itemPrice) {
        this.ITEM_TITLE = itemTitle;
        this.ITEM_PRICE = itemPrice;
    }

    public int getItemPrice() {
        return ITEM_PRICE;
    }

    public String getTitle() {
        return ITEM_TITLE;
    }

}
