// Importing packages for Express ===================================
var express = require('express');
var http = require('http');
var path = require('path');
var favicon = require('serve-favicon');
var morgan = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var app = express();

//==================================================================
/*
Importing Packages
*/
var mysql = require('mysql');

//=====================================================================
/*
Our Modules
*/
var store = require('./modules/store');

//======================================================================
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

// route for user logout
/*
app.get('/get/logout', (req, res) => {
    if (req.session.user && req.cookies.user_sid) {
        res.clearCookie('user_sid');
        res.redirect('/');
    }
});
*/


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