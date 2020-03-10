package JavaBeans;

import java.io.Serializable;

/**
 * @author mcneelyad
 */
public class Item implements Serializable {

    private String itemCode;
    private String itemName;
    private String catalogCategory;
    private String itemDescription;
    private String itemRating;
    private String imageUrl;

    public Item() {
        itemCode = "";
        itemName = "";
        catalogCategory = "";
        itemDescription = "";
        itemRating = "";
        imageUrl = "";
    }

    public Item(String itemCode, String catalogCategory, String itemName,
            String itemDescription, String itemRating, String imageUrl) 
    {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.catalogCategory = catalogCategory;
        this.itemDescription = itemDescription;
        this.itemRating = itemRating;
        this.imageUrl = imageUrl;
    }

    //Item Code
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    //Item Name
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    //Catalog Category
    public String getCatalogCategory() {
        return catalogCategory;
    }

    public void setCatalogCategory(String catalogCategory) {
        this.catalogCategory = catalogCategory;
    }
    
    //Item Description
    public String getItemDescription() 
    {
        return itemDescription;
    }
    
    public void setItemDescription(String itemDescription) 
    {
        this.itemDescription = itemDescription;
    }
    
    //Item Rating
    public String getItemRating() 
    {
        return itemRating;
    }
    
    public void setItemRating(String itemRating) 
    {
        this.itemRating = itemRating;
    }
    
    //Image Url
    public String getImageUrl() 
    {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) 
    {
        this.imageUrl = imageUrl;
    }
} //end of Item class

