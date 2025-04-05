package selenide.helpers;


public class Item {
    private String itemTitle;
    private int itemPrice;

    public Item(String itemTitle, int itemPrice) {
        this.itemTitle = itemTitle;
        this.itemPrice = itemPrice;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }
}
