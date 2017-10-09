/*
This file reads the new user form and constructs an array of values, 
that gets passed to the registerUser method in the UserCatalog class,
for the purpose of adding the user info to the database, generating a user ID
and adding the user/id pair to the HashMap of the system users. 
*/

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.myapp.Store;

@WebServlet("/registrationServlet")
public class RegistrationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        //extracting user info from the html form
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phonenumber = request.getParameter("phonenumber");
        String password = request.getParameter("password");
        String adminstatus = request.getParameter("Admin status");
        String isAdmin = "-1";

        //determining admin status
        if(adminstatus.equals("User"))
        {
          isAdmin="0";
        }
          else 
          {
          isAdmin="1";
          }

        //Creating user info array
        String[] userInfo = new String[firstname,lastname,address,phonenumber,email,password,isAdmin];


        //Creating new store 
        //THIS HAS TO BE CHANGED WHEN MORE CODE HAS BEEN IMPLEMENTED SO THAT IT DOES NOT CREAT A NEW STORE EVERYTIME IT WANTS TO REGISTER A NEW USER
        Store store = new Store;
        store.addNewUser(userInfo);

        //Creating and displaying a response
        PrintWriter writer = response.getWriter();
         
        String htmlRespone = "<html>";
        if(isAdmin==0)
          htmlRespone += "<h2>A new user has been added to the database</h2>";
          else
          htmlRespone += "<h2>A new administrator has been added to the database</h2>";
        htmlRespone += "</html>";
         
        writer.println(htmlRespone);
    }
}
