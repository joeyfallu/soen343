
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
   
   registerAdmin : function (req,res){
	   var email = req.body.email;
	   var pass = req.body.pass;
	   
	   var sql = "SELECT * FROM user WHERE email='" + req.body.email + "'";
	   console.log("Query: " + sql);
	   db.query(sql, function (error, results, fields){
         if (error)
         res.send(error.code);

         //The query should return no result (email should be unique across users)
         //result will hold one entry: result[0]
         //result[0] has result[0].Email and result[0].Password (or equivalent values in DB)
         if(results[0] == undefined){
            //email is unique
            var sql = "INSERT INTO user (email, password) VALUES ('" + req.body.email + "', '" + req.body.password + "')";
			db.query(sql,function(error, result){
                if (error)
                    res.send(error.code);
				console.log("user inserted");
				});
         }
         else {
           //Registration Failed - Email already in use
            console.log("Email already in use");
            res.send("Email already in use");
         }
      });
   },

};
