'use strict'
const fs = require('fs');
var mysql = require('mysql');
var user = require('./user');

//For the amazon server mysql connection
var db = mysql.createConnection({
   host: "soen343-mysql.cutkgxnrwyh2.us-east-1.rds.amazonaws.com",
   user: "soen343team",
   password: "spiderman",
   database: "electronics"
});

db.connect(function(err) {
   if (err) throw err;
});


/*
Store module exports
*/
module.exports = {

   /*
   Login function authenticates the user
   Params:
   -req.body.email
   -req.body.password
   (the parameters email and password should be passed in the post request body)
   */
   login : function (req, res){
      var sql = "SELECT * FROM user WHERE email='" + req.body.email + "'";
      console.log("Query: " + sql);
      db.query(sql, function (error, results, fields){
         if (error)
         res.send(error.code);

         //The query should return one result (email should be unique)
         //result will hold one entry: result[0]
         //result[0] has result[0].Email and result[0].Password (or equivalent values in DB)
         if(results[0] != undefined){
            //email found
            console.log(results[0].email);
            if(results[0].password == req.body.password ){

               // Logging throws error, commented out for now.
               //   //get timestamp
               //   var timeStamp = Math.floor(Date.now() / 1000);
               //   //set the id variable for tracking active users
               //   let id = results[0].id;
               //   //write to file the info of the active user
               //   fs.writeFile('active.txt', timeStamp, id ,(err) => {
               //     if (err) throw err;
               //   console.log('Active user saved!');
               //   });
               //Login Successful
               //create a session (+ cookie)
               //Redirect

               console.log("Successful login");
               req.session.user = results[0];
               delete req.session.user.password;
               res.send(req.session.user);
            }
            else {
               //Login Failed - Wrong password
               console.log("Wrong Password");
               res.send("Wrong Password");
            }
         }
         else {
            //Login Failed - Email not found
            console.log("Email not found");
            res.send("Email not found");
         }
      });
   },
   /*
   Function to test if unit tests are working.. DELETE THIS LATER
   */
   one : function(){
      return 1;
   },


   registerAdmin : function (req,res){
     var isAdmin = 1;
     var errorMessage = "Email already in use.";
     var successMessage = "Successfully registered Admin.";
     var sql = "SELECT * FROM user WHERE email='" + req.body.email + "'";

	   db.query(sql, function (error, results, fields){
        if (error)
          res.send(error.code);
         //The query should return no result (email should be unique across users)
         //result will hold one entry: result[0]
         //result[0] has result[0].Email and result[0].Password (or equivalent values in DB)
        if(results[0] == undefined){


			var account = new user(req.body.firstName,req.body.lastName,req.body.address,req.body.phoneNumber,req.body.email,req.body.password,isAdmin)
            //email is unique
          var sql = "INSERT INTO user (firstName, lastName, address, phoneNumber, email, password, isAdmin) VALUES ('" + account.firstName + "', '" + account.lastName + "', '"
          + account.address + "','" + account.phoneNumber + "','" + account.email + "', '" + account.password + "', '" + account.isAdmin + "')";

          res.send(successMessage);

		    db.query(sql,function(error, result){
          if (error)
            res.send(error.code);
          else
            console.log(successMessage);
				});
        }
         else {
           //Registration Failed - Email already in use
            console.log(errorMessage);
            res.send(errorMessage);
         }
      });
   },
};
