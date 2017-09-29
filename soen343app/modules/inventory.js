var mysql = require('mysql');
var util = require('util');
var Product = require('./product');
var television = Product.television;
var monitor = Product.monitor;
var desktop = Product.desktop;
var tablet = Product.tablet;
var laptop = Product.laptop;
 
var db = mysql.createConnection({
  host: "soen343-mysql.cutkgxnrwyh2.us-east-1.rds.amazonaws.com",
  user: "soen343team",
  password: "spiderman",
  database: "electronics"
});
 
db.connect(function(err) {
  if (err) throw err;;
});
 
 
function Inventory(){
 
  this.products = [];
 
  this.viewProducts=function(products, req, res){
    var items = [];
 
    return new Promise(function(resolve, reject) {
      products.forEach(function(product, i){
        var sql = "SELECT * from " + product.type + " WHERE model =" + product.model;
        db.query(sql, function(error, results, fields){
          if(error) return error.code;
          switch (product.type) {
            case 'television':
              var televisionItem = new television(results[0].id,results[0].model,results.weight,results[0].price,results[0].brand,results[0].dimensions);
              items.push(televisionItem);
              break;
            case 'monitor':
              var monitorItem = new monitor(results[0].id,results[0].model,results[0].weight,results[0].price,results[0].brand,results[0].size);
              items.push(monitorItem);
              break;
 
            case 'desktop':
              var desktopItem = new desktop(results[0].id,results[0].model,results[0].weight,results[0].price,results[0].brand,results[0].processorType,results[0].cpuCores,results[0].ram,results[0].hardDriveSize,results[0].dimensions);
              items.push(desktopItem);
              break;
 
            case 'tablet':
              var tabletItem = new tablet(results[0].id,results[0].model,results[0].weight,results[0].price,results[0].brand,results[0].processorType,results[0].cpuCores,results[0].ram,results[0].hardDriveSize,results[0].dimensions,results[0].batteryInfo,results[0].operatingSystem,results[0].camera);
              items.push(tabletItem);
              break;
 
            case 'laptop':
              var laptopItem = new laptop(results[0].id,results[0].model,results[0].weight,results[0].price,results[0].brand,results[0].processorType,results[0].cpuCores,results[0].ram,results[0].hardDriveSize,results[0].size,results[0].batteryInfo,results[0].operatingSystem,results[0].camera,results[0].touchScreen)
              items.push(laptopItem);
              break;
 
            default:
              console.log('error');
          }
          console.log(i)
          if(i == (products.length - 1)){
            resolve(items);
          }
        })
      })
    })
  }
     this.addProduct=function(a){
      
		addaddToDb(a);
       function addToDb(a){
   
        console.log("Connected!");
        //this inserts the model number as well as the type into the inventory table
        var sql2 = "INSERT INTO inventory (model,type) VALUES ('"+a[1]+"','"+a[0]+"')";
        //this inserts the rest of the product info into the correct table corresponding to the type of product
        switch(a[0]){
            case 'television':
                var sql = "INSERT INTO "+a[0]+" (model, weight, price, brand, dimensions) VALUES ('"+a[1]+"','"+a[2]+"','"+a[3]+"','"+a[4]+"','"+a[5]+"')";
                break;
            case 'monitor':
                var sql = "INSERT INTO "+a[0]+" (model, weight, price, brand, size) VALUES ('"+a[1]+"','"+a[2]+"','"+a[3]+"','"+a[4]+"','"+a[5]+"')";
                break;
            case 'desktop':
                var sql = "INSERT INTO "+a[0]+" (model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize,dimensions) VALUES ('"+a[1]+"','"+a[2]+"','"+a[3]+"','"+a[4]+"','"+a[5]+"','"+a[6]+"','"+a[7]+"','"+a[8]+"','"+a[9]+"')";
                break;
            case 'tablet':
                var sql = "INSERT INTO "+a[0]+" (model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize,dimensions,batteryInfo,operatingSystem,cameraInfo) VALUES ('"+a[1]+"','"
                +a[2]+"','"+a[3]+"','"+a[4]+"','"+a[5]+"','"+a[6]+"','"+a[7]+"','"+a[8]+"','"+a[9]+"','"+a[10]+"','"+a[11]+"','"+a[12]+"')";
                break;
            case 'laptop':
                var sql = "INSERT INTO "+a[0]+" (model, weight, price, brand, processorType, cpuCores, ram, hardDriveSize,size,batteryInfo,operatingSystem,camera,touchScreen) VALUES ('"+a[1]+"','"
                +a[2]+"','"+a[3]+"','"+a[4]+"','"+a[5]+"','"+a[6]+"','"+a[7]+"','"+a[8]+"','"+a[9]+"','"+a[10]+"','"+a[11]+"','"+a[12]+"','"+a[13]+"')";
                break;
            default:
                return 'error';
           
       
        }
        db.query(sql, function (err, result) {
            if (err);
            console.log("1 record inserted");
        });
       
        db.query(sql2, function (err, result) {
            if (err);
            console.log("1 record inserted");
        });
   
    }
   }
 
 
}
 
module.exports = Inventory;