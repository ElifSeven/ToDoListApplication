package Controller;

import dao.impl.ItemDAOImpl;
import model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ItemService;

@Controller

public class ItemController implements ItemService {

@Autowired
ItemDAOImpl itemDAO;

    @Override
    @RequestMapping(value = "/item/insertItem", method = RequestMethod.POST)

    public void insertItem(Item item) {
        if (item.getItemId() == 0) {
            this.itemDAO.insertItem(item);
        } else {
            this.itemDAO.insertItem(item);
        }

    }

    @Override
    public Item findItemList(int userId) {
        return itemDAO.findItemList(userId);
    }


}
