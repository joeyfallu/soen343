'use strict'
var mysql = require('mysql');

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
               //Login Successful
               //create a session (+ cookie)
               //Redirect
               console.log("Successful login");
               req.session.user = results[0];
               delete req.session.user.password;
               res.send("Successful login");
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
   Registration function to register a new user
   */
   register : function(req, res){

   },

   one : function(){
      return 1;
   }

};