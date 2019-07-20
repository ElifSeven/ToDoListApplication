package dao;

import model.Item;

public interface ItemDAO {

    void insertItem(Item item);

    Item findItemList(int userId);
}
