
var util = require('util');

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
function Product(id,model,weight,price,brand)
{
    this.id=id;
    this.model=model;
    this.weight=weight;
    this.price=price;
    this.brand=brand;
    this.toString=function(){
    console.log("id: "+this.id+" model: "+this.model+" weight: "+this.weight+" price: "+this.price+" brand: "+this.brand);
    }
};

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
function television(id,model,weight,price,brand,dimensions)
{
    television.super_.call(this,id,model,weight,price,brand);
    this.dimensions = dimensions;
    this.toString = function(){
      
        console.log("id: "+this.id+" model: "+this.model+" weight: "+this.weight+" price: "+this.price+" brand: "+this.brand+" dimensions: "+this.dimensions);
    }
};

util.inherits(television,Product);
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
function monitor(id,model,weight,price,brand,size)
{
    monitor.super_.call(this,id,model,weight,price,brand);
    this.size = size;
    this.toString = function(){
        console.log("id: "+this.id+" model: "+this.model+" weight: "+this.weight+" price: "+this.price+" brand: "+this.brand+" size: "+this.size);
    }
};

util.inherits(monitor,Product);
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
function computer(id,model,weight,price,brand,processorType,cpuCores,ram,hardDriveSize)
{
    computer.super_.call(this,id,model,weight,price,brand);
    this.processorType = processorType;
    this.cpuCores = cpuCores;
    this.ram= ram;
    this.hardDriveSize= hardDriveSize;
    this.toString = function(){
        console.log("id: "+this.id+" model: "+this.model+" weight: "+this.weight+" price: "+this.price+" brand: "+this.brand+" processorType: "
                    +this.processorType+" cpuCores: "+this.cpuCores+" ram: "+this.ram+" hardDriveSize: "+this.hardDriveSize);
    }
};

util.inherits(computer,Product);
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
function desktop(id,model,weight,price,brand,processorType,cpuCores,ram,hardDriveSize,dimensions)
{
    desktop.super_.call(this,id,model,weight,price,brand,processorType,cpuCores,ram,hardDriveSize);
    this.dimensions = dimensions;
    this.toString = function(){
        console.log("id: "+this.id+" model: "+this.model+" weight: "+this.weight+" price: "+this.price+" brand: "+this.brand+" processorType: "
                    +this.processorType+" cpuCores: "+this.cpuCores+" ram: "+this.ram+" hardDriveSize: "+this.hardDriveSize+" dimensions: "+this.dimensions);
    }
};

util.inherits(desktop,computer);
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
function tablet(id,model,weight,price,brand,processorType,cpuCores,ram,hardDriveSize,dimensions,batteryInfo,operatingSystem,cameraInfo)
{
    tablet.super_.call(this,id,model,weight,price,brand,processorType,cpuCores,ram,hardDriveSize);
    this.dimensions = dimensions;
    this.batteryInfo = batteryInfo;
    this.operatingSystem = operatingSystem;
    this.cameraInfo = cameraInfo;
    this.toString = function(){
        console.log("id: "+this.id+" model: "+this.model+" weight: "+this.weight+" price: "+this.price+" brand: "+this.brand+" processorType: "
                    +this.processorType+" cpuCores: "+this.cpuCores+" ram: "+this.ram+" hardDriveSize: "+this.hardDriveSize+" dimensions: "+this.dimensions
                    +" batteryInfo: "+this.batteryInfo+" operatingSystem: "+ this.operatingSystem+" cameraInfo: "+this.cameraInfo);
    }
};

util.inherits(tablet,computer);
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
function laptop(id,model,weight,price,brand,processorType,cpuCores,ram,hardDriveSize,size,batteryInfo,operatingSystem,camera,touchScreen)
{
    tablet.super_.call(this,id,model,weight,price,brand,processorType,cpuCores,ram,hardDriveSize);
    this.size = size;
    this.batteryInfo = batteryInfo;
    this.operatingSystem = operatingSystem;
    this.camera = camera;
    this.touchScreen = touchScreen;
    this.toString = function(){
        console.log("id: "+this.id+" model: "+this.model+" weight: "+this.weight+" price: "+this.price+" brand: "+this.brand+" processorType: "
                    +this.processorType+" cpuCores: "+this.cpuCores+" ram: "+this.ram+" hardDriveSize: "+this.hardDriveSize+" size: "+this.size
                    +" batteryInfo: "+this.batteryInfo+" operatingSystem: "+ this.operatingSystem+" camera: "+this.camera+" touchScreen: "+this.touchScreen);
    }
};

util.inherits(latop,computer);
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

var a = new television(1, "sonyViera", 40, 100, "sony","40x30");
var b = new monitor(2,"benqX9k", 10, 120, "benq", 27);
var c = new computer(3,"seymore",20,130,"sonyf", "ryzen", 8, 16, 2)
var d = new desktop(4,"razerrr",20,130,"sonyf", "ryzen", 8, 16, 2,"40x40x90")
a.toString();
b.toString();
c.toString();
d.toString();
console.log(d.dimensions);

