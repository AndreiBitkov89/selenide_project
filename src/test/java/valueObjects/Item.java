package valueObjects;

public class Item {

    private final String ITEM_TITLE;
    private final int itemPrice;

    public Item(String itemTitle, int itemPrice) {
        this.ITEM_TITLE = itemTitle;
        this.itemPrice = itemPrice;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public String getItemTitle() {
        return ITEM_TITLE;
    }

}
