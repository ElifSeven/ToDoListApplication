package service;

import model.Item;

public interface ItemService {

    public void insertItem(Item item);
    public Item findItemList(int userId);
}
