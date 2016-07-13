package com.example.msvcdojo;

import com.example.msvcdojo.model.Subscription;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class JVConsultaController {

    private static final Logger logger = Logger.getLogger(JVConsultaController.class);
    private static String[] connSrRodo = {"jdbc:mysql://localhost:3306/endesa", "root", "root"};
    private static String[] connH2 = {"jdbc:h2:~/test://localhost:3306/testdb", "sa", "Pepamaca11"};
    private Connection srRodo, srh2;
    private PreparedStatement pstmt;
    private ResultSet rsIns;
    private Statement stmt;

    public void JVConsultaController() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            srRodo = DriverManager.getConnection(connSrRodo[0], connSrRodo[1], connSrRodo[2]);
            stmt = srRodo.createStatement();
        } catch (ClassNotFoundException e) {
            e.getMessage();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public boolean connectionH2() {
        String sql = "select * from user_subscription";
        boolean var = true;
        try {
            Class.forName("org.h2.Driver");
            srh2 = DriverManager.getConnection(connH2[0], connH2[1], connH2[2]);
            stmt = srh2.createStatement();
            var = stmt.execute(sql);
            Logger.getLogger("Result execution h2: " + stmt.execute(sql));
        } catch (ClassNotFoundException e) {
            e.getMessage();
        } catch (SQLException e) {
            e.getMessage();
        }
        return var;
    }

    @RequestMapping(value = "/info/{user}", method = RequestMethod.GET, produces = "application/json")
    public ModelAndView getSubscription(@PathVariable String user, ModelMap model) {
        ObjectMapper obj = new ObjectMapper();
        ModelAndView modelAndView = new ModelAndView("result");
        String sql = "SELECT e.user, e.type, e.substable from user_subscription e"
                + " where e.user=?";

        String jsonInString = "";
        List<Subscription> result = new ArrayList<Subscription>();

        try {
            Class.forName("org.h2.Driver");
//            srRodo = DriverManager.getConnection(connSrRodo[0], connSrRodo[1], connSrRodo[2]);
            srh2 = DriverManager.getConnection(connH2[0], connH2[1], connH2[2]);
            PreparedStatement pstmt = (PreparedStatement) srh2.prepareStatement(sql);
            pstmt.setString(1, user);
            rsIns = pstmt.executeQuery();

            while (rsIns.next()) {

                Subscription subscription = new Subscription();
                subscription.setUser(rsIns.getString(1));
                subscription.setType(rsIns.getString(2));
                subscription.setSubstable(rsIns.getString(3));
                result.add(subscription);
            }

            Logger.getLogger("Subscription: Rows " + result.size());

            jsonInString = obj.writeValueAsString(result);

//            model.addAttribute("result", jsonInString);

            modelAndView.addObject("result", jsonInString);

        } catch (JsonGenerationException e) {
            logger.error("JsonGenerationException :: ", e);
        } catch (JsonMappingException e) {
            logger.error("JsonGenerationException :: ", e);
        } catch (IOException e) {
            logger.error("IOException :: ", e);
        } catch (Exception e) {
            logger.error("Exception :: ", e);
        }
//        return "jsonTemplate";
//        return new ModelAndView("messages/subscription", "message", jsonInString);
        closeConn();
        return modelAndView;
    }

    /*public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }*/


    /* -------------------Create a User-------------------------------------------------------- */

    /*@RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void addComputer(@RequestBody Computer computer) {
        computerStorage.add(computer);
    }*/

    ////TODO

    //Test POST http://localhost:8080/SpringREST/rest/computer
    //Before sending the request, add an http header Content-Type with the button

    //Add Request Header of Rest client : Content-Type:application/json; charset=utf-8
    //&add a JSON content in the request body
    //  {"reference":"MAC","description":"Test","model":"MB Pro","brand":"Apple"}

	/*--------------------SUBSCRIPTIONS--------------------------------------------------------*/
    /*-----------------------------------------------------------------------------------------*/

	/* -------------------Create a Subscription---------------------------------------------- */

    //@RequestMapping(value = "/subscription/create", method = RequestMethod.POST)
    //public ResponseEntity<Void> createSubscription(@RequestBody Subscription subscription) {
        // check if subscription exist..

		/*if (true Subscription already exist.. userService.isUserExist(user)) {
            closeConn();
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        // Save subscription

        HttpHeaders headers = new HttpHeaders();
        PathTransitionBuilder ucBuilder = null;
        //headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		closeConn();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}*/

	/* -------------------Retrieve a Subscription---------------------------------------------- */

    /*@RequestMapping(value = "/subscription/read/{user}", method = RequestMethod.GET)
    public ResponseEntity<List> readSubscriptions(@PathVariable String user) { //List<Subscription>
		List<Subscription> subscriptions = new ArrayList<Subscription>(); //Get the subscriptions here!!!!
		String tmp = "";
		try {
			Class.forName("com.vertica.jdbc.Driver");
			Connection connSupp = DriverManager.getConnection(connH2[0], connH2[1], connH2[2]);
			stmt = connSupp.createStatement();
			Statement stmtSupp = connSupp.createStatement();
			ResultSet rsIns = null;
			rsIns = stmtSupp.executeQuery("SELECT USER, USR, TABLE_NAME, CONDITION_NOTIFICATION FROM CONDITIONS WHERE USR = 'user'");
			while (rsIns.next()) {
				Subscription s = new Subscription();
				s.setUser(rsIns.getString(1));
	        	s.setTable(rsIns.getString(2));
	        	s.setCondition(rsIns.getString(3));
				subscriptions.add(s);
	        }
		} catch (Exception e) { tmp = e.toString(); }

		/*if(subscriptions.isEmpty()){
            return new ResponseEntity<List<Subscription>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Subscription>>(subscriptions, HttpStatus.OK);

		String str = Integer.toString(subscriptions.size());
		str = tmp;
		closeConn();
        //return new ResponseEntity<List>(str, HttpStatus.OK);
        return null;
	}*/

    /*------------------- Update a User --------------------------------------------------------*/

	/*@RequestMapping(value = "/subscription/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Subscription> updateSubscription(@PathVariable("id") long id, @RequestBody Subscription subscription) {
    // The id exist?
      //User currentUser = userService.findById(id);

      if (true) {
            //try { conn.close();	} catch (Exception e) {	}
            return new ResponseEntity<Subscription>(HttpStatus.NOT_FOUND);
      } else {

		}

        // Set the subscription
        currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());
        currentUser.setSalary(user.getSalary());
        userService.updateUser(currentUser);

        closeConn();
        return new ResponseEntity<Subscription>(subscription, HttpStatus.OK);

	}*/

	/*------------------- Delete a User --------------------------------------------------------*/
    //@RequestMapping(value = "/subscription/delete/{id}", method = RequestMethod.DELETE)
    /*public ResponseEntity<Void> deleteSubscription(@PathVariable("id") long id) {
        // Find subscription
        Subscription subscription = null;
		//User user = userService.findById(id);

		if (subscription.getUser() != "" && subscription.getUser().equals(null)){
            //user == null) {
			closeConn();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            //
		}

        // Delete
		//userService.deleteUserById(id);
		closeConn();
        //return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
      return null;
	}
*/
    private void closeConn() {
        try {
            rsIns.close();
            pstmt.close();
            srRodo.close();
        } catch (SQLException e) {
            Logger.getLogger("Close connection: ");
            e.getMessage();
        }
    }
}
