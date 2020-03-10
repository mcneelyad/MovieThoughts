package Controllers;

import JavaBeans.Item;
import Database.ItemDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mcneelyad
 */
@WebServlet(name = "CatalogController", urlPatterns = {"/controller"})
public class CatalogController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // TODO: Get the items from the ItemDB instance and store them in this ArrayList
        ArrayList<Item> items = ItemDB.getItems();
        
        // TODO: Get the itemCode parameter from the request
        String itemCode = request.getParameter("itemCode");
        
        // TODO: Create a variable for the url and set its default value to go the item page
        String url = "/item.jsp";
        
        // Create a null item object
        Item item = null;
        
        // TODO: If the itemCode parameter is not null and is not empty
        if (itemCode != null && !itemCode.isEmpty()) 
        {
            // TODO: Loop through the items in the items ArrayList with an enhanced for loop (AKA: for each loop)
            for (Item i: items) 
            {
                // TODO: If itemCode of the current item in list matches the itemCode from the request
                if (i.getItemCode().equals(itemCode)) 
                {
                    // TODO: Set the null item object to the current item in the list
                    item = i;
                    // TODO: Set the item as an attribute of the request
                    request.setAttribute("item",item);
                } 
            }
            
        // If the itemCode parameter does not exist
        } else {
            // TODO: Set the url to go to the categories page
            url = "/categories.jsp";
            // TODO: Set the items list as an attribute of the request
            request.setAttribute("itemList",items);
            request.setAttribute("categoriesList", ItemDB.getCategories());
        }
        
        // Forward the request. 
        // NOTE: this must always be the last line of code executed in your servlet!
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
