package com.panteon.stock.tools;

import com.panteon.stock.dao.ItemDao;
import com.panteon.stock.dao.UserDao;
import com.panteon.stock.services.ItemService;
import com.panteon.stock.services.LoginService;
import com.panteon.stock.services.PersonalEquipmentService;
import com.panteon.stock.services.UserProfileService;


// Container with all Services and DAO objects
// Must be 1 exemplar only --> Singletone
public class MyContext {

    public static final String LOGIN_ATTRIBUTE = "login";
    public static final String USERITEMSDTO_ATTRIBUTE = "userItemsDto";

    private UserDao userDao;
    private ItemDao itemDao;

    private UserProfileService userProfileService;
    private ItemService itemService;
    private PersonalEquipmentService personalEquipmentService;
    private LoginService loginService;

    private static volatile MyContext instance = null;

    private MyContext (){
        initComponents();

    }

    public static MyContext getInstance(){
        if (instance == null){
            synchronized (MyContext.class){
                if (instance == null){
                    instance = new MyContext();
                }
            }
        }
        return instance;
    }


    private void initComponents(){
        userDao = new UserDao();
        itemDao = new ItemDao();
        userProfileService = new UserProfileService(userDao);
        itemService = new ItemService(itemDao);
        personalEquipmentService = new PersonalEquipmentService(userDao, itemDao);
        loginService = new LoginService(userDao);
    }


    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public UserProfileService getUserProfileService() {
        return userProfileService;
    }

    public void setUserProfileService(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public PersonalEquipmentService getPersonalEquipmentService() {
        return personalEquipmentService;
    }

    public void setPersonalEquipmentService(PersonalEquipmentService personalEquipmentService) {
        this.personalEquipmentService = personalEquipmentService;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
