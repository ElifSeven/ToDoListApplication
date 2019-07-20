package service;


import dao.ItemDAO;
import model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class ItemServiceImpl implements ItemService {


   @Autowired
    ItemDAO itemDAO;

    @Override
    @Transactional

    public void insertItem(Item item) {

        this.itemDAO.insertItem(item);
    }

    @Override
    public Item findItemList(int userId) {

        return this.itemDAO.findItemList(userId);
    }
}
