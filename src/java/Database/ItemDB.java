package Database;

import JavaBeans.Item;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mcneelyad
 */
public class ItemDB implements Serializable {
    
    
    
    public static ArrayList<Item> getItems() {
        
        ArrayList<Item> items = new ArrayList<Item>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        
        String query = "SELECT * FROM Item";
                
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                Item item = new Item();
                item.setItemCode(rs.getString("itemCode"));
                item.setItemName(rs.getString("itemName"));
                item.setCatalogCategory(rs.getString("CatalogCategory"));
                item.setItemDescription(rs.getString("descript"));
                item.setItemRating(rs.getString("itemRating"));
                item.setImageUrl(rs.getString("imageURL"));
                items.add(item);
            }
            return items;
            
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<String> getCategories() {
        ArrayList<String> categories = new ArrayList<>();
        for (Item i : getItems())
            if (!categories.contains(i.getCatalogCategory()))
                categories.add(i.getCatalogCategory());
        return categories;
    }
    
    public static Item getItemById(String itemCode) {
        for (Item i : getItems())
            if (i.getItemCode().equals(itemCode))
                return i;
        return null;
    }
    
    public static Item getItem(String iCode) {
        for (Item item : getItems()) {
            if (item.getItemCode().equals(iCode)) {
                System.out.println("item found  ");
                return item;
            }
        }
        return null;
    }

    public static boolean exists(String itemCode) {
        Item p = getItemById(itemCode);
        return (p != null);
         }
} 
