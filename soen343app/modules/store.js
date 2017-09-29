'use strict'
const fs = require('fs');
var mysql = require('mysql');
var dateFormat = require('dateformat');
var user = require('./user');
var inventory = require('./inventory');
var inv = new inventory();

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
               //get timestamp
              var timeStamp=new Date(2015,1,3,15,30).toLocaleString();
              //set the id variable for tracking active users
              let id = results[0].id;
              //write to file the info of the active user
              fs.appendFile('logs/active-users.log' ,timeStamp+" ",(err) => {
              if (err) throw err;
              fs.appendFile('logs/active-users.log' ,id+" Logged in\r\n",(err) => {
              if (err) throw err;
              });
              });

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
   
   
   addItem : function (req,res){
	   
	   if(req.body.type == "desktop"){
		  var dataArray = ["desktop",req.body.id, req.body.model , req.body.weight, req.body.price, req.body.brand, req.body.processorType, req.body.cpuCores, req.body.ram, req.body.hardDriveSize, req.body.dimensions];
          inv.addProduct(dataArray);
      }
      else if(req.body.type == "television"){
		  var dataArray = ["television",req.body.id , req.body.model , req.body.weight, req.body.price, req.body.brand, req.body.dimensions];
       // var d1 = new television(req.body.id , req.body.model , req.body.weight, req.body.price, req.body.brand, req.body.dimensions);
       // var dataArray = ["television", d1.model, d1.weight, d1.price, d1.brand, d1.dimensions];
		  inv.addProduct(dataArray);
      }
      else if(req.body.type == "monitor"){
		  var dataArray = ["monitor",req.body.id , req.body.model , req.body.weight, req.body.price, req.body.brand, req.body.size];
       // var d1 = new monitor(req.body.id , req.body.model , req.body.weight, req.body.price, req.body.brand, req.body.size);
       // var dataArray = ["monitor", d1.model, d1.weight, d1.price, d1.brand, d1.size];
		  inv.addProduct(dataArray);
      }
      else if(req.body.type == "tablet"){
		  var dataArray = ["tablet",req.body.id , req.body.model , req.body.weight, req.body.price, req.body.brand, req.body.processorType, req.body.cpuCores, req.body.ram, req.body.hardDriveSize, req.body.dimensions, req.body.batteryInfo, req.body.operatingSystem, req.body.cameraInfo];
       // var d1 = new tablet(req.body.id , req.body.model , req.body.weight, req.body.price, req.body.brand, req.body.processorType, req.body.cpuCores, req.body.ram, req.body.hardDriveSize, req.body.dimensions, req.body.batteryInfo, req.body.operatingSystem, req.body.cameraInfo);
       // var dataArray = ["tablet", d1.model, d1.weight, d1.price, d1.brand, d1.processorType, d1.cpuCores, d1.ram, d1.hardDriveSize, d1.dimensions, d1.batteryInfo, d1.operatingSystem, d1.cameraInfo];
          inv.addProduct(dataArray);
      }
      else if(req.body.type == "laptop"){
		  var dataArray = ["laptop",req.body.id , req.body.model , req.body.weight, req.body.price, req.body.brand, req.body.processorType, req.body.cpuCores, req.body.ram, req.body.hardDriveSize, req.body.size, req.body.batteryInfo, req.body.operatingSystem, req.body.camera, req.body.touchScreen];
        //var d1 = new laptop(req.body.id , req.body.model , req.body.weight, req.body.price, req.body.brand, req.body.processorType, req.body.cpuCores, req.body.ram, req.body.hardDriveSize, req.body.size, req.body.batteryInfo, req.body.operatingSystem, req.body.camera, req.body.touchScreen);
        //var dataArray = ["laptop", d1.model, d1.weight, d1.price, d1.brand, d1.processorType, d1.cpuCores, d1.ram, d1.hardDriveSize, d1.size, d1.batteryInfo, d1.operatingSystem, d1.camera, d1.touchScreen];
        inv.addProduct(dataArray);
      }
   }
   
   
   
};
