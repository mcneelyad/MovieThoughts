/**
 * @author mcneelyad
 */
package JavaBeans;

import java.util.*;

public class UserProfile {

    private User user;
    private List<UserItem> items;

    public UserProfile() {
        items = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setItems(List<UserItem> profileItems) {
        items = profileItems;
    }

    public List<UserItem> getItems() {
        return items;
    }

    public int getCount() {
        return items.size();
    }

    public void addItem(UserItem item) {

        items.add(item);
    }

    public void updateItem(UserItem item, int rating) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItem().getItemCode() == item.getItem().getItemCode()) {
                items.get(i).setRating(rating);
                items.set(i, item);
            }
        }
    }

    public void removeItem(UserItem item) {
        String code = item.getItem().getItemCode();
        UserItem profileItem;
        for (int i = 0; i < items.size(); i++) {
            profileItem = items.get(i);
            if (profileItem.getItem().getItemCode() == code) {
                items.remove(i);
                return;
            }
        }
    }

    public void removeItem(int itemIndex) {
        items.remove(itemIndex);
        return;

    }

    public int itemRating(String code) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItem().getItemCode() == code) {
                //return index 
                return i;
            }
        }
        //item not in cart
        return -1;
    }

    public void updateItem(UserItem item, boolean b) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItem().getItemCode() == item.getItem().getItemCode()) {
                //
                items.get(i).setWatchedIt(b);
                items.set(i, item);
            }
        }//To change body of generated methods, choose Tools | Templates.
    }

}
