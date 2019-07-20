package user;

import model.Item;

public interface ItemManager {
    void insert(Item item) throws Exception;
    Item findItemList(int userId) throws Exception;


}
