package user.impl;


import dao.impl.ItemDAOImpl;
import model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.ItemManager;

@Service

public class ItemManagerEntityService implements ItemManager{


    @Autowired
    private ItemDAOImpl itemDAO;




    @Override
    public void insert(Item item) throws Exception {

        itemDAO.insertItem(item);

    }





    @Override
    public Item findItemList(int  userId) throws Exception {
        return itemDAO.findItemList(userId);
    }


}
