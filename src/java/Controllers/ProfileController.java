/*
 * @author nanajjar
 * @author mcneelyad
 */
package Controllers;

import Database.ItemDB;
import Database.UserDB;
import JavaBeans.Item;
import JavaBeans.User;
import JavaBeans.UserItem;
import JavaBeans.UserProfile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ProfileController", urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {

    private static final String DEFAULTURL = "/myItems.jsp";

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String url = DEFAULTURL;

        HttpSession session = request.getSession();
        //check for a user in session
        User theUser = (User) session.getAttribute("theUser");
        UserProfile profile = (UserProfile) session.getAttribute("currentProfile");
        String action = request.getParameter("action");

        if (theUser == null || profile == null) {
            System.out.println("user or profile are null");
            //initialize hardcoded user db and user items
            //UserDB.initializeData();            
            //get a user as if they logged in
            theUser = UserDB.getUser("john_smith@example.com");
            session.setAttribute("theUser", theUser);
            action = "showProfile";
            UserProfile userProfile = new UserProfile();
        }

        if (action == null || action.trim().equals("") || action.trim().equalsIgnoreCase("showProfile")) 
        {
            url = showProfile(request, response);
        } 
        else if (action.trim().equalsIgnoreCase("update")) 
        {
            url = updateProfile(request, response);
        } 
        else if (action.trim().equalsIgnoreCase("save")) 
        {
            url = updateProfileSave(request, response);
        } 
        else if (action.trim().equalsIgnoreCase("rate")) 
        {
            url = updateProfileRate(request, response);
        } 
        else if (action.trim().equalsIgnoreCase("watchedIt")) 
        {
            url = updateProfileWatchedIt(request, response);
        } 
        else if (action.trim().equalsIgnoreCase("delete")) 
        {
            url = updateProfileDelete(request, response);
        } 
//        else if (action.trim().equalsIgnoreCase("registerUser")) 
//        {
//            url = processUser(request, response);
//        } 
        else if (action.trim().equalsIgnoreCase("displayUser")) 
        {
            url = "/myItems.jsp";
        } 
        else if (action.trim().equalsIgnoreCase("signout")) 
        {
            //save user profile to database
            System.out.println("signout");
            session.invalidate();
            session = request.getSession(true);
            url = "/index";
        }
        //before forwarding to view, check if cart is empty after updates 
        UserProfile userProfile = (UserProfile) session.getAttribute("currentProfile");
        if (userProfile == null || userProfile.getCount() == 0) {
            request.setAttribute("emptyProfile", "Your profile is empty");
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    private String showProfile(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserProfile userProfile = (UserProfile) session.getAttribute("currentProfile");
        if (userProfile == null || userProfile.getCount() == 0) {
            request.setAttribute("emptyProfile", "Your profile is empty");
        } else {
            request.getSession().setAttribute("currentProfile", userProfile);
        }
        return DEFAULTURL;
    }

    private String updateProfile(HttpServletRequest request, HttpServletResponse response) {
        String viewURL = DEFAULTURL;
        //get all item codes on view
        String[] viewItems = request.getParameterValues("itemList");
        String itemCodeParam = request.getParameter("itemCode");
        //validate itemCodeParam
        if (itemCodeParam != null || !itemCodeParam.equalsIgnoreCase("")) {
            //get profile from session
            HttpSession session = request.getSession();
            UserProfile userProfile = (UserProfile) session.getAttribute("currentProfile");
            UserItem itemRating;
            //validate item requested for update is in itemList
            //convert to List for searching
            List itemList = Arrays.asList(viewItems);
            if (isItemInView(itemList, itemCodeParam)) {
                //get item from profile. If not in profile redisplay profile view
                itemRating = isItemInProfile(userProfile, itemCodeParam);
                if (itemRating != null) {
                    request.setAttribute("thisItemRating", itemRating);
                    return "/feedback.jsp";
                }
            }

        }

        return viewURL;
    }

    private boolean isItemInProfile(HttpServletRequest request,
            HttpServletResponse response) {
        String[] viewItems = request.getParameterValues("itemList");
        //convert to List for searching
        List itemList = Arrays.asList(viewItems);
        //System.out.println("itemlist size  " + itemList.size());
        //get profile from session
        HttpSession session = request.getSession();
        UserProfile userProfile = (UserProfile) session.getAttribute("currentProfile");
        UserItem tempItemRating;

        String itemCode = "";
        int rating = 0;
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println("itemlist item  " + (String) itemList.get(i));
            //validate item code
            itemCode = (String) itemList.get(i);
            if (!itemCode.equalsIgnoreCase("") && itemCode.equalsIgnoreCase("0")) {
                //check if item in profile
                int itemIndex = userProfile.itemRating(itemCode);
                if (itemIndex != -1) {
                    //item in profile
                    return true;
                }
            }
        }
        return false;
    }

    private UserItem isItemInProfile(UserProfile userProfile, String itemCode) {

        UserItem tempItemRating;
        ArrayList userProfileItems = (ArrayList) userProfile.getItems();

        for (int i = 0; i < userProfileItems.size(); i++) {
            if (((UserItem) userProfileItems.get(i)).getItem().getItemCode() == itemCode) {
                return (UserItem) userProfileItems.get(i);
            }
        }
        return null;
    }

    private String addItem(HttpServletRequest request) {
        //if request makes it here that means it was validated: 
        //user verified.
        //requested item is in view
        //requested item is not already in user profile
        HttpSession session = request.getSession();
        UserProfile profile = (UserProfile) session.getAttribute("currentProfile");
        String userId = ((User) session.getAttribute("theUser")).getUserID();

        String itemCode = request.getParameter("itemCode");
        if (ItemDB.exists(itemCode)) {
            Item item = ItemDB.getItemById(request.getParameter("itemCode"));

            UserItem profileItem = new UserItem();
            profileItem.setItem(item);
            profile.addItem(profileItem);

        }
        session.setAttribute("currentProfile", profile);
        return DEFAULTURL;
    }

    private String updateProfileSave(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String viewURL = DEFAULTURL;
        UserProfile userProfile = (UserProfile) session.getAttribute("currentProfile");
        UserItem tempItemRating;
        //
        String[] viewItems = request.getParameterValues("itemList");
        String itemCodeParam = request.getParameter("itemCode");
        List itemList = Arrays.asList(viewItems);

        if ((isItemInView(itemList, itemCodeParam)) && (userProfile.getCount() == 0 || !isItemInProfile(request, response))) {
            viewURL = addItem(request);
        } else {
            request.setAttribute("errorMessage", "problem with item save request. Try again.");
        }
        return viewURL;
    }

    private String updateProfileRate(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String viewURL = DEFAULTURL;
        UserProfile userProfile = (UserProfile) session.getAttribute("currentProfile");
        UserItem tempItemRating;
        //
        String[] viewItems = request.getParameterValues("itemList");
        String itemCodeParam = request.getParameter("itemCode");
        List itemList = Arrays.asList(viewItems);

        if ((isItemInView(itemList, itemCodeParam)) && (isItemInProfile(request, response))) {
            String ratingParam = request.getParameter("rating");
            int rating = Integer.parseInt(ratingParam);
            try {
                if (rating <= 5 && rating >= 1) {
                    UserItem itemRating = isItemInProfile(userProfile, itemCodeParam);
                    userProfile.updateItem(itemRating, rating);
                    session.setAttribute("currentProfile", userProfile);
                    String userId = ((User) session.getAttribute("theUser")).getUserID();
                } else {
                    request.setAttribute("errorMessage", "problem with item rating feedback request. Try again.");
                }
            } catch (NumberFormatException nfe) {
                request.setAttribute("errorMessage", "problem with item rating feedback request. Try again.");
            }
        } else {
            request.setAttribute("errorMessage", "problem with item rating feedback request. Try again.");
        }
        return viewURL;
    }

    private String updateProfileWatchedIt(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String viewURL = DEFAULTURL;
        UserProfile userProfile = (UserProfile) session.getAttribute("currentProfile");
        UserItem tempItemRating;
        //
        String[] viewItems = request.getParameterValues("itemList");
        String itemCodeParam = request.getParameter("itemCode");
        List itemList = Arrays.asList(viewItems);

        if ((isItemInView(itemList, itemCodeParam)) && (isItemInProfile(request, response))) {
            String watchedIt = request.getParameter("watchedIt");
            //if parameter is the available update
            if (watchedIt.equalsIgnoreCase("yes")) {
                UserItem itemRating = isItemInProfile(userProfile, itemCodeParam);
                userProfile.updateItem(itemRating, true);
                session.setAttribute("currentProfile", userProfile);
                String userId = ((User) session.getAttribute("theUser")).getUserID();
            } else if (watchedIt.equalsIgnoreCase("no")) {
                UserItem itemRating = isItemInProfile(userProfile, itemCodeParam);
                userProfile.updateItem(itemRating, false);
                session.setAttribute("currentProfile", userProfile);
                String userId = ((User) session.getAttribute("theUser")).getUserID();
            } else {
                request.setAttribute("errorMessage", "problem with item rating feedback request. Try again.");
            }
        } else {
            request.setAttribute("errorMessage", "problem with item rating feedback request. Try again.");
        }
        return viewURL;
    }

    private boolean isItemInView(List viewItems, String itemCodeParam) {
        //check if item in profile
        for (int i = 0; i < viewItems.size(); i++) {
            if (((String) viewItems.get(i)).equals(itemCodeParam)) {
                //item is in profile
                return true;
            }
        }
        //item is not in profile
        return false;
    }

    private String updateProfileDelete(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String viewURL = DEFAULTURL;
        UserProfile userProfile = (UserProfile) session.getAttribute("currentProfile");
        UserItem tempItemRating;
        //
        String[] viewItems = request.getParameterValues("itemList");
        String itemCodeParam = request.getParameter("itemCode");
        List itemList = Arrays.asList(viewItems);

        if ((isItemInView(itemList, itemCodeParam)) && (isItemInProfile(request, response))) {
            UserItem itemRating = isItemInProfile(userProfile, itemCodeParam);
            userProfile.removeItem(itemRating);
            session.setAttribute("currentProfile", userProfile);
            String userId = ((User) session.getAttribute("theUser")).getUserID();

        } else {
            request.setAttribute("errorMessage", "problem with item rating feedback request. Try again.");
        }
        return viewURL;
    }
}
