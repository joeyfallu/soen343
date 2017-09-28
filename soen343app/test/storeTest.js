/*
Store Modules tests
*/
var expect = require("chai").expect;
var store = require("../modules/store.js");
var request = require('request-promise');
var siteUrl = "http://localhost:3000";

describe("Store Class", function () {
   /*
   Login function test
   */
   describe("Login function", function(){
      it("Successful login request", (done) => {
         var options = {
            method: 'POST',
            uri: siteUrl + "/post/login",
            body: {
               email : 'test@c',
               password : '123'
            },
            headers: {
               /* 'content-type': 'application/x-www-form-urlencoded' */ // Is set automatically
            },
            json: true // Automatically stringifies the body to JSON
         };
         request.post(options)
         .then((body) => {
            expect(body.email).to.equal("test@c");
         })
         .then(done,done)
         .catch((err) => {
            console.log(err);
         });
      });

      it("Wrong Password login request", (done) => {
         var options = {
            method: 'POST',
            uri: siteUrl + "/post/login",
            body: {
               email : "test@c",
               password : "12344"
            },
            headers: {
               /* 'content-type': 'application/x-www-form-urlencoded' */ // Is set automatically
            },
            json: true // Automatically stringifies the body to JSON
         };
         request.post(options)
         .then((body) => {
            expect(body).to.equal("Wrong Password");
         })
         .then(done,done)
         .catch((err) => {
            console.log(err);
         });
      });

      it("Email not found login request", (done) => {
         var options = {
            method: 'POST',
            uri: siteUrl + "/post/login",
            body: {
               email : "wrong@c",
               password : "12344"
            },
            headers: {
               /* 'content-type': 'application/x-www-form-urlencoded' */ // Is set automatically
            },
            json: true // Automatically stringifies the body to JSON
         };
         request.post(options)
         .then((body) => {
            expect(body).to.equal("Email not found");
         })
         .then(done,done)
         .catch((err) => {
            console.log(err);
         });
      });


   });
   /*
   Register function test
   */
   describe("Register function",function(){

   });

   /*
   Example function to demonstrate simple unit testing
   To be removed later
   */
   describe("example function",function(){
      it("returns 1",function (){
         var res = store.one();
         expect(res).to.equal(1);
      });
   });

   /*
   Add other tests here
   */


});
