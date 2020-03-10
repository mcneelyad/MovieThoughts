package JavaBeans;

import java.io.Serializable;

/**
 *
 * @author mcneelyad
 */
public class UserItem implements Serializable {

    private Item item;
    private int rating;
    private boolean watchedIt;

    public UserItem() {
        this.item = null;
        this.rating = 0;
        this.watchedIt = false;
    }

    public UserItem(Item item, int rating, boolean watchedIt) {
        this.item = item;
        this.rating = rating;
        this.watchedIt = watchedIt;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean HaveWatchedIt() {
        return watchedIt;
    }

    public void setWatchedIt(boolean watchedIt) {
        this.watchedIt = watchedIt;
    }

}
