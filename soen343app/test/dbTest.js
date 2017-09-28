/*
Test Database
*/
var expect = require("chai").expect;
//Note this test uses promise-mysql instead of mysql, since I found it easier to test properly with promises (instead of callback functions)
var mysql = require('promise-mysql');

describe("Database", function () {
   it("Connects to the database", (done) => {
      //For the amazon server mysql connection
      var db = mysql.createConnection({
         host: "soen343-mysql.cutkgxnrwyh2.us-east-1.rds.amazonaws.com",
         user: "soen343team",
         password: "spiderman",
         database: "electronics"
      })
      .then((conn) => {
         conn.end();
      })
      .then(done,done) //Calls done twice when the connection has been established.
      //The first ‘done’ will signal the completion of a successful test to mocha. The second done is called in case of a promise rejection.
      .catch((err) => {
         if (conn && conn.end){
            conn.end();
         }
         console.log(err);
      });
   });
});
