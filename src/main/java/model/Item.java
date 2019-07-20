package model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "item")

public class Item {
    @SequenceGenerator(name = "generator", sequenceName = "ITEM_SEQ_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
    @Column
    private int itemId;

    @Column(name = "userId")
    private int userId;

    @Column(name = "itemName")
    private String itemName;

    @Column(name = "itemDescr")
    private String itemDescr;

    @Column(name = "itemState")
    private  boolean itemState;

    @Column(name = "itemDate")
    private Date itemDate;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescr() {
        return itemDescr;
    }

    public void setItemDescr(String itemDescr) {
        this.itemDescr = itemDescr;
    }

    public boolean isItemState() {
        return itemState;
    }

    public void setItemState(boolean itemState) {
        this.itemState = itemState;
    }

    public Date getItemDate() {
        return itemDate;
    }


    public void setItemDate(Date itemDate) {
        this.itemDate =itemDate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", userId=" + userId +
                ", itemName='" + itemName + '\'' +
                ", itemDescr='" + itemDescr + '\'' +
                ", itemState='" + itemState + '\'' +
                ", itemDate='" + itemDate + '\'' +
                '}';
    }
}
