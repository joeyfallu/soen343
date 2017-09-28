/*
Server Routing Test
*/
var expect = require("chai").expect;
var request = require('request-promise');
var siteUrl = "http://localhost:3000";

describe("Server routing to Angular Router", function () {
   it("Loads the main page", (done) => {
      request.get(siteUrl)
      .then((res)=> {
      }).then(done,done)
      .catch((err) => {
         console.log(err);
      });
   });
});
