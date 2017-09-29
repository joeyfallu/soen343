// Importing packages for Express ===================================
var express = require('express');
var http = require('http');
var path = require('path');
var favicon = require('serve-favicon');
var morgan = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var Inventory = require('./modules/inventory')
var app = express();

//==================================================================
/*
Importing Packages
*/
var mysql = require('mysql');
var session = require('client-sessions');

//=====================================================================
/*
Our Modules
*/
var store = require('./modules/store');

//=====================================================================
//Mysql Initial connection

//For the amazon server mysql connection

var db = mysql.createConnection({
  host: "soen343-mysql.cutkgxnrwyh2.us-east-1.rds.amazonaws.com",
  user: "soen343team",
  password: "spiderman",
  database: "electronics"
});


//For local mysql connection
//
// var db = mysql.createConnection({
//   host: "localhost",
//   user: "root",
//   password: "root",
// });
db.connect(function(err) {
    if (err) throw err;
    console.log("Connected to the database!");
});

//====================================================================
/*
Sessions
*/
app.use(session({
  cookieName: 'session',
  secret: 'random_string_goes_here123',
  duration: 60 * 60 * 1000,
  activeDuration: 30 * 60 * 1000,
  cookie: {
    maxAge: 60000*30, // duration of the cookie in milliseconds, defaults to duration above
    ephemeral: false, // when true, cookie expires when the browser closes
    httpOnly: false, // when true, cookie is not accessible from javascript
    secure: false // when true, cookie will only be sent over SSL. use key 'secureProxy' instead if you handle SSL not in your node process
  }
}));


// uncomment after placing your favicon in /public
//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
app.use(morgan('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

//Routes
//===================================================================
/*
Get Requests
*/
//Returns all the entries from TestEntry Table in Database
app.get('/get/name',function(req, res){
    var sql = "SELECT * from TestEntry";
    db.query(sql, function (error, results, fields){
        if (error) res.send(error.code);
        res.send(results);
    })
});

//returns the products
app.get('/get/products', function(req,res){
  var products = [];
  var inventory = new Inventory(products);
  var sql = "SELECT model,type From inventory";
  db.query(sql, function (error, results, fields){
    if (error) res.send(error.code);
    for(var i = 0; i < results.length; i++){

      products.push(results[i]);
    }

    inventory.viewProducts(products, req, res).then(function(items){
      res.send(items)
    });
  });
});
// route for user logout

app.get('/get/logout', (req, res) => {
    if (req.session.user) {
        res.session.reset();
        res.redirect('/');
    }
});



// Handles all GET request paths except those handled above
app.get('/get/*',function(req, res){
    res.send("GET request received - server");
});

/*
Post Requests
*/
app.post('/post/name', function (req, res){
    var sql = "INSERT INTO TestEntry (Name) Values('" + req.body.name + "')";
    db.query(sql, function (error, results, fields) {
        if (error) res.send(error.code);
        console.log("1 record inserted");
        res.send(results);
    });
});


//Login route (needs email and password as parameters in the req.body)
app.post('/post/login', store.login);

//Login route (needs email and password as parameters in the req.body)
app.post('/post/register', store.registerAdmin);

//Handles all POST request paths except those handled above
app.post('/post/*', function (req, res){
    res.send(req.body);
});


// Routing to main page, Angular JS will handle the rest.
app.get('*', function(req, res) {
    res.sendFile(__dirname + '/public/index.html');  // load the single view file (angular will handle the page changes on the front-end)
});

//=====================================================================================
//Default NodeJS Express Configurations

//launch server
const port = process.env.PORT || 3000;
const server = http.createServer(app);
server.listen(port);
console.log('Server listening on:', port);
