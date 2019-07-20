package ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import model.Item;
import model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import user.ItemManager;
import user.UserManager;

import javax.servlet.annotation.WebServlet;

@Theme("mytheme")
public class MyUI extends UI {


    //Fields
    private TextField userNameField;
    private TextField emailField;
    private PasswordField passwordField;
    private TextField taskNameField;
    private TextField taskDescField;
    private DateField taskDateField;

    private CheckBox taskStateField;


    private Label labelOr;

    //Buttons
    private Button userRegisterBtn;
    private Button userLoginBtn;
    private Button addListBtn;
    private Button deleteListBtn;
    private Button userSaveBtn;
    private Button taskSaveBtn;
    private Button showCompleteBtn;

    private User user;
    private Item item;


    @Override
    protected void init(VaadinRequest vaadinRequest) {


        VerticalLayout verticalLayout1 = new VerticalLayout();
        HorizontalLayout horizontalLayout1 = new HorizontalLayout();

        userNameField = new TextField();
        userNameField.setCaption("Type your name and surname here:  ");
        userNameField.setIcon(FontAwesome.USER);
        userNameField.setWidth("150px");
        userNameField.setHeight("30px");
        verticalLayout1.addComponent(userNameField);

        emailField = new TextField();
        emailField.setCaption("Type your email here: ");
        emailField.setIcon(FontAwesome.USER);
        emailField.setWidth("150px");
        emailField.setHeight("30px");
        verticalLayout1.addComponent(emailField);
        emailField.setVisible(false);

        passwordField = new PasswordField();
        passwordField.setCaption("Type your password here:  ");
        passwordField.setWidth("150px");
        passwordField.setHeight("30px");
        verticalLayout1.addComponent(passwordField);

        taskNameField = new TextField();
        taskNameField.setValue("List Name");
        taskNameField.focus();


        taskDescField = new TextField();
        taskDescField.setValue("Task Description");

        taskStateField = new CheckBox();


        taskDateField = new DateField();
        taskDateField.setDateFormat("yyyy-MM-dd");


        userRegisterBtn = new Button();
        userRegisterBtn.setCaption("Register");
        userRegisterBtn.setHeight("25px");
        userRegisterBtn.addStyleName("friendly");
        horizontalLayout1.addComponent(userRegisterBtn);

        labelOr = new Label();
        labelOr.setValue("Or");
        labelOr.setHeight("25px");
        horizontalLayout1.addComponent(labelOr);

        userLoginBtn = new Button();
        userLoginBtn.setCaption("Login");
        userLoginBtn.setHeight("25px");
        userLoginBtn.addStyleName("friendly");
        horizontalLayout1.addComponent(userLoginBtn);

        addListBtn = new Button();
        addListBtn.setCaption("Add New List");
        addListBtn.setHeight("20px");
        addListBtn.addStyleName("tiny");
        addListBtn.setIcon(FontAwesome.PLUS);


        deleteListBtn = new Button();
        deleteListBtn.setCaption("Delete To Do List");
        deleteListBtn.setHeight("20px");
        deleteListBtn.addStyleName("tiny");
        deleteListBtn.setIcon(FontAwesome.CLOSE);

        userSaveBtn = new Button();
        userSaveBtn.setCaption("Save");
        userSaveBtn.setHeight("25px");
        userSaveBtn.addStyleName("friendly");
        userSaveBtn.setIcon(FontAwesome.SAVE);

        taskSaveBtn = new Button();
        taskSaveBtn.setCaption("Save");
        taskSaveBtn.setHeight("25px");
        taskSaveBtn.addStyleName("friendly");
        taskSaveBtn.setIcon(FontAwesome.SAVE);


        showCompleteBtn = new Button();
        showCompleteBtn.setCaption("Show completed tasks");
        showCompleteBtn.setHeight("25px");
        showCompleteBtn.addStyleName("tiny");
        showCompleteBtn.setIcon(FontAwesome.SEARCH);


        setContent(verticalLayout1);
        verticalLayout1.addComponent(horizontalLayout1);


        userRegisterBtn.addClickListener(new Button.ClickListener() {

            /**
             * Kullanıcı önceden kayıtllı değilse login olamamaktadır ve
             * register butonuna tıklayarak bilgileri alınmaktadır.
             */

            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                Window registerWindow = new Window();
                verticalLayout1.addComponent(userNameField);
                emailField.setVisible(true);
                verticalLayout1.addComponent(emailField);
                verticalLayout1.addComponent(passwordField);

                verticalLayout1.addComponent(horizontalLayout1);
                horizontalLayout1.addComponent(userSaveBtn);
                horizontalLayout1.addComponents(userLoginBtn);

                registerWindow.setContent(verticalLayout1);
                userRegisterBtn.setVisible(false);
                labelOr.setVisible(false);

                registerWindow.center();
                addWindow(registerWindow);


            }
        });


           userSaveBtn.addClickListener(new Button.ClickListener() {

            /**
             * Kullanıcı bilgileri veritabanına kayıt olmaktadır.
             */

            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {


                ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

                UserManager userManager = (UserManager) context.getBean("userManagerEntityService");

                //UserController userController = (UserController) context.getBean("userController");

                User person = new User();

                person.setUserName(userNameField.getValue());

                person.setPassword(passwordField.getValue());

                person.seteMail(emailField.getValue());

                try {
                    userManager.insert(person);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                Notification notification = new Notification("Saved");
                notification.show("Saved");

            }
        });


           userLoginBtn.addClickListener(new Button.ClickListener() {

            /**
             * Kullanıcı bilgileri veritabanında kayıtlı ise login olabilecek, değilse uyarı verecek.
             */

            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

                UserManager userManager = (UserManager) context.getBean("userManagerEntityService");

                try {
                    user = userManager.findUser(userNameField.getValue(), passwordField.getValue());

                    if (user == null) {

                        new Notification(null, "hata", Notification.Type.ERROR_MESSAGE, true).show(Page.getCurrent());

                    }

                } catch (Exception e) {
                    e.printStackTrace();

                }


                Window loginWindow = new Window();
                VerticalLayout verticalLayout2 = new VerticalLayout();
                HorizontalLayout toolbar = new HorizontalLayout();

                loginWindow.setContent(verticalLayout2);

                verticalLayout2.addComponent(toolbar);
                toolbar.addComponent(taskNameField);
                toolbar.addComponent(addListBtn);
                toolbar.addComponent(deleteListBtn);
                toolbar.addComponents(showCompleteBtn);
                taskNameField.setWidth("100px");
                taskNameField.setHeight("30px");
                taskNameField.focus();

                taskNameField.addValueChangeListener(new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                        taskNameField.setEnabled(false);
                    }
                });
                /**
                 * Tasklerin girileceği grid oluşturuluyor.
                 */

                GridLayout itemListGrid = new GridLayout(1, 30);
                verticalLayout2.addComponent(itemListGrid);


                for (int gridColumn = 0; gridColumn < itemListGrid.getColumns(); gridColumn++) {
                    for (int gridRow = 0; gridRow < itemListGrid.getRows(); gridRow++) {

                        HorizontalLayout horizontalLayout2 = new HorizontalLayout();
                        CheckBox taskStateCheckBox = new CheckBox();
                        Button editTaskBtn = new Button();
                        editTaskBtn.setCaption("Edit");
                        editTaskBtn.setIcon(FontAwesome.EDIT);


                        TextField taskField = new TextField("");
                        taskField.setWidth("1000px");

                        horizontalLayout2.addComponent(taskStateCheckBox);
                        horizontalLayout2.addComponent(taskField);
                        horizontalLayout2.addComponent(editTaskBtn);

                          Window completedTasksWindow = new Window();

                          taskStateCheckBox.addValueChangeListener(new Property.ValueChangeListener() {

                            /**
                             *
                             * Task tamamlandıysa işaretleniyor ve içeriği temizleniyor.
                             * Bir başka yere tamamlandı listesi olarak gönderiliyor.
                             */

                            @Override
                            public void valueChange(Property.ValueChangeEvent event) {
                                TextField completedTaskField = new TextField();
                                completedTaskField.setValue(taskField.getValue());
                                taskField.setValue("");

                                showCompleteBtn.addClickListener(new Button.ClickListener() {

                                    /**
                                     * Önceden tamamlanan taskler gösteriliyor.
                                     */


                                    @Override
                                    public void buttonClick(Button.ClickEvent clickEvent) {
                                        VerticalLayout verticalLayout3 = new VerticalLayout();
                                        completedTasksWindow.setContent(verticalLayout3);
                                        verticalLayout3.addComponents(completedTaskField);
                                        addWindow(completedTasksWindow);
                                        completedTasksWindow.center();

                                    }
                                });

                                Notification notification = new Notification("Completed");
                                notification.show("Completed!");

                            }

                        });


                        editTaskBtn.addClickListener(new Button.ClickListener() {
                            /**
                             *
                             * Task'in name, description, date, state bilgileri.
                             */
                            @Override
                            public void buttonClick(Button.ClickEvent clickEvent) {


                                Window taskInfoWindow = new Window();
                                VerticalLayout verticalLayout4 = new VerticalLayout();

                                verticalLayout4.addComponent(taskNameField);
                                verticalLayout4.addComponent(taskDescField);
                                verticalLayout4.addComponent(taskDateField);
                                verticalLayout4.addComponent(taskStateField);
                                verticalLayout4.addComponent(taskSaveBtn);

                                taskInfoWindow.setContent(verticalLayout4);

                                taskInfoWindow.center();

                                addWindow(taskInfoWindow);


                            }
                        });

                        deleteListBtn.addClickListener(new Button.ClickListener() {

                            /**
                             *
                             * Kullanıcı listeleri veritabanından silinir ve ekran kapatılır.
                             */
                            @Override
                            public void buttonClick(Button.ClickEvent clickEvent) {

                                loginWindow.close();

                            }
                        });

                        itemListGrid.addComponent(horizontalLayout2);
                    }

                    itemListGrid.insertRow(1);
                }

                itemListGrid.setSizeFull();
                loginWindow.center();
                addWindow(loginWindow);

            }

        });


        taskSaveBtn.addClickListener(new Button.ClickListener() {
            /**
             *
             * Item bilgileri veritabanına kaydediliyor.
             * Kullanıcının id bilgisi ile kayıt oluyor.
             * Her kullanıcı kendi item bilgilerini görebiliyor.
             */
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
                ItemManager itemManager = (ItemManager) context.getBean("itemManagerEntityService");

                Item item = new Item();

                item.setItemName(taskNameField.getValue());

                item.setUserId(user.getUserId());

                item.setItemDescr(taskDescField.getValue());

                item.setItemDate(taskDateField.getValue());

                item.setItemState(taskStateField.getValue());


                try {
                    itemManager.insert(item);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


        addListBtn.addClickListener(new Button.ClickListener() {
            /**
             *
             * Kullanıcı istediği sayıda yeni listeler oluşturabilir.
             */
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                TextField itemName = new TextField();
                itemName.setWidth("100px");
                itemName.setHeight("30px");

                Window newListWindow = new Window();
                VerticalLayout verticalLayout = new VerticalLayout();
                HorizontalLayout horLayout = new HorizontalLayout();


                newListWindow.setContent(verticalLayout);
                verticalLayout.addComponent(horLayout);
                horLayout.addComponent(itemName);
                horLayout.addComponent(addListBtn);
                horLayout.addComponent(deleteListBtn);
                horLayout.addComponents(showCompleteBtn);
                itemName.focus();

                itemName.addValueChangeListener(new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                        itemName.setEnabled(false);
                    }
                });


                GridLayout itemListGrid = new GridLayout(1, 20);
                verticalLayout.addComponent(itemListGrid);

                for (int gridColumn = 0; gridColumn < itemListGrid.getColumns(); gridColumn++) {
                    for (int gridRow = 0; gridRow < itemListGrid.getRows(); gridRow++) {

                        HorizontalLayout components = new HorizontalLayout();
                        CheckBox taskStateCheckBox = new CheckBox();
                        Button editTaskBtn = new Button();
                        editTaskBtn.setCaption("Edit");
                        editTaskBtn.setIcon(FontAwesome.EDIT);

                        TextField taskField = new TextField("");
                        taskField.setWidth("1000px");

                        components.addComponent(taskStateCheckBox);
                        components.addComponent(taskField);
                        components.addComponents(editTaskBtn);


                        taskStateCheckBox.addValueChangeListener(new Property.ValueChangeListener() {

                            @Override
                            public void valueChange(Property.ValueChangeEvent event) {

                                TextField completedTaskField = new TextField();
                                completedTaskField.setValue(taskField.getValue());
                                taskField.setValue("");

                                showCompleteBtn.addClickListener(new Button.ClickListener() {

                                    @Override
                                    public void buttonClick(Button.ClickEvent clickEvent) {


                                   /*     ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

                                        ItemManager itemManager = (ItemManager) context.getBean("itemManagerEntityService");

                                        try {
                                            item = itemManager.findItemList(4);



                                        } catch (Exception e) {
                                            e.printStackTrace();



                                        }*/


                                        Window completedTasksWindow = new Window();
                                        VerticalLayout verticalLayout1 = new VerticalLayout();
                                        verticalLayout1.addComponents(completedTaskField);
                                        completedTasksWindow.setContent(verticalLayout1);

                                        addWindow(completedTasksWindow);
                                        completedTasksWindow.center();

                                    }
                                });


                                Notification notification = new Notification("Completed");
                                notification.show("Completed");

                            }


                        });


                        editTaskBtn.addClickListener(new Button.ClickListener() {
                            @Override
                            public void buttonClick(Button.ClickEvent clickEvent) {


                                Window taskInfoWindow = new Window();

                                VerticalLayout verticalLayout1 = new VerticalLayout();
                                verticalLayout1.addComponent(itemName);
                                verticalLayout1.addComponent(taskDescField);
                                verticalLayout1.addComponent(taskDateField);
                                verticalLayout1.addComponent(taskStateField);
                                verticalLayout1.addComponent(taskSaveBtn);

                                taskInfoWindow.setContent(verticalLayout1);
                                taskInfoWindow.center();
                                addWindow(taskInfoWindow);


                            }
                        });


                        deleteListBtn.addClickListener(new Button.ClickListener() {

                            @Override
                            public void buttonClick(Button.ClickEvent clickEvent) {

                                newListWindow.close();

                            }
                        });

                        itemListGrid.addComponent(components);

                    }

                    itemListGrid.insertRow(1);
                }


                itemListGrid.setSizeFull();
                newListWindow.center();
                addWindow(newListWindow);

            }
        });

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}

