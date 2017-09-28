var television = require('./product');
var monitor = require('./product');
var computer = require('./product');
var desktop = require('./product');
var tablet = require('./product');
var laptop = require('./product');


var a = new television(1, "sonyViera", 40, 100, "sony","40x30");
var b = new monitor(2,"benqX9k", 10, 120, "benq", 27);
var c = new computer(3,"seymore",20,130,"sonyf", "ryzen", 8, 16, 2)
var d = new desktop(4,"razerrr",20,130,"sonyf", "ryzen", 8, 16, 2,"40x40x90")
a.toString();
b.toString();
c.toString();
d.toString();
console.log(d.dimensions);
