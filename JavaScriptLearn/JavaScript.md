## The JavaScript language at a glance

- Interpreted language (code is executed line by line by an interpreter at runtime). By this nature, code can be run, and tested interactively, there is no need to wait for compile time when working with javascript on its own.
- Functions are first class citizens.

```javascript
// What it means for functions to be first class citizens

// 1. Function composition can occur
function sum(a, b){return a + b};
let result = sum(multiply(a,b), b);

// 2. Function assignment to variable
function multiply(a,b){return a * b};
let product = multiply;
    // product becomes an alias for multilpy
let result = product(a,b);  

    // Equivalently with anonymous function "() => {}"
let product = (a,b) =>{return a * b};
let result = product(a,b);

// 3. Function passed as argument
function composition(multiply, sum, a, b){
    return (multiply(a,b) + sum(a,b));
}

// 4. Functions as return values
function foo(){
    return () => {
        console.log("here");
    };
}
```

- JavaScript is prototype based. Whereby, each function is an object, and classes aswell as constructors do not need to be manually defined. JavaScript uses the prototype attribute in functions to assign new methods and attributes.

```javascript
function Animal(name) {
  this.name = name;
};

Animal.prototype.speak = function() {
  console.log(this.name + ' makes a sound');
};

function Dog(name) {
  Animal.call(this, name);  // Call the Animal constructor to inherit the 'name'
};

// Set Dog's prototype to an instance of Animal to inherit methods
Dog.prototype = Object.create(Animal.prototype);
Dog.prototype.constructor = Dog;  // Correct the constructor reference

/*By default, when you assign Dog.prototype = Object.create(Animal.prototype), the constructor property of Dog.prototype points to Animal, not Dog.
To fix this and ensure that the constructor property points back to Dog, we explicitly set it with Dog.prototype.constructor = Dog;.*/s

// Add specific methods to Dog
Dog.prototype.fetch = function() {
  console.log(this.name + ' is fetching the ball');
};

const dog = new Dog('Dog');
dog.speak();  // Output: Dog makes a sound
dog.fetch();  // Output: Dog is fetching the ball
// Or a new instance specific method on dog
dog.shake = function(){
    console.log("this dog instance is spazzed out");
};
dog.shake();


// Notice, at no point did we need to define a class and constructors
// ** DISCLAIMER, this syntax for ES6 standards is considered deprecated.

// By ES6 standards, JavaScript must now use classical inheritance
class Animal {
  constructor(name) {
    this.name = name;
  }

  speak() {
    console.log(this.name + ' makes a sound');
  }
}

class Dog extends Animal {
  constructor(name) {
    super(name);  // Call the parent constructor (Animal)
  }

  fetch() {
    console.log(this.name + ' is fetching the ball');
  }
}

const dog = new Dog('Dog');
dog.speak();  // Output: Dog makes a sound
dog.fetch();  // Output: Dog is fetching the ball


```

- JavaScript is multi-paradigm. This means it supports object oriented(programs consist of objects with states and behaviours), imperative(programs describe how computers should execute processes) and declarative (programs describe what processes should do rather than how).

- JavaScript is single threaded, meaning that code executes one sequence at a time. This may be bad because certain costly operations that take up longer cpu cycles can slow an entire application, and one infinite loop can hang the browser. JavaScript has a slight workaround to this with asynchronous machanisms like promises, callbacks, async/await. These mechanisms ensure that these processes can yield and switch so it does not consume too much cpu time.

Some somewhat unique JavaScript dynamic capabilities.

1. Dynamic typing. Variables can hold any type of value, their types are determined at runtime.
2. Dynamic script creation using eval("script"). Eval executes the script inside it.
3. Prototype based inheritance. Methods, and attributes of objects and prototypes can be altered at runtime.

## An intermediate look into JavaScript
### Object prototypes
<u>Learning Objectives</u>
```
- Prototype chains
- Shadowing properties
- Setting prototypes
- Prototypes and inheritance
```

<b> Prototype chains </b>
- All JavaScript objects have built in properties. These properties are prototypes. 
- Prototypes are themselves objects, and will have their own prototypes. 
- This chain can go forever, but this is essentially what prototype chaining is. 

Examples of prototypes are toString() methods that can be invoked by any object. (functions are objects aswell).

``` javascript
// In the browser enter the following and inspect its console output
function myFunc() {}
console.log(myFunc.prototype); 
```

There should be a list of all the methods for the prototype of myFunc. But where do these methods come from? They come from the Object object. This is the object that is inhereted from by all other objects.

When accessing any methods like myFunc().toString(), JavaScript will go up the prototype chain in search for a toString method. It will use the first one it encounters, but if it cannot find it in an upper prototype it goes up the chain until it reaches the Object object. If it is not listed as a method in Object, then toString() is undefined and we get an error from the interpreter.

```
Her are some prototype methods

constructor
hasOwnProperty
isPrototypeOf
propertyIsEnumerable
toLocaleString
toString
valueOf
```
How can we inspect up the prototype chain? Consider the static method ```getPrototypeOf``` and looping using while loop up the prototype chain, then using call().
```javascript
// This code prints the next prototype until the last prototype (null) is reached.
const myDate = new Date();
let object = myDate;

do {
  object = Object.getPrototypeOf(object);
  console.log(object);
} while (object);
```

<b>Shadowing properties</b>
JavaScript's version of method overriding.
- It is used to label properties of the same name defined in both prototype and prototype instance.
- It can also be used to label properties of the same name defined in both prototype and the object that inherits the prototype.

```javascript
// Properties of the same name defined in both prototype and the prototype instance.
function Car(brand){
  this.brand = brand;
}

Car.prototype.printBrand = function(){
  console.log("old implementation");
}

let corvette = new Car("Corvette");
corvette.printBrand = function(){
  console.log("NEW IMPLEMENTATION")
}

corvette.printBrand();    // Notice how this prints new implementation instead of old implementation

// Properties of the same name defined in both prorotype and the objects that inhreits the prototype.
function Animal(name) {
  this.name = name; 
}

Animal.prototype.speak = function() {
  console.log("Animal is making sounds");
}

function Zebra(name) {
  Animal.call(this, name); 
}

Zebra.prototype = Object.create(Animal.prototype);
Zebra.prototype.constructor = Zebra; 

Zebra.prototype.speak = function() {
  console.log("Zebra is roaring");
}

let zebraInstance = new Zebra("Bob");
zebraInstance.speak();   // Notice how the output here is the Zebras implementation of speak
```
<b> Setting prototypes </b>
Two ways to do this
1. Object.create(\<prototype>)
  This creates a new object in its  parentheses, which we can assign to the prototype of another object.
2. Object.assign(Person.prototype, personPrototype) so now any instance of Person() will have the Person.protoype as its prototype, that inherets from personPrototype.

<b>Own properties</b>
Properties that are unique to instances are called own properties.
Checking own properties can be accomplished using ```Object.hasOwn(instanceName, ownPropertyValue)```

### Classical syntax as an alternative to prototypes
The class syntax is syntactic sugar for the prototype as aforementioned under the hood. Some clowns just cant stand using a particular syntax. Personally I say fuck em, but here we are I guess. The following example showcases pretty much most of what we can do with the classical syntax. 

```javascript

class food {
  expiryDate = null; // with default value
  name;               // without default value
  #profitMargin;      // encapsulation. Private attributes

  constructor(name, expiryDate){
    this.name = name;
    this.expiryDate;
  }

  // public methods
  getExpiry(){
    this.calculateExpiryDate();
  }

  // private methods
  #calculateExpiryDate(){
    console.log(this.expiryDate);
  }
}

class burger extends food { // inheritance
  expiryDate;
  #profitMargin = 2;
  
  constructor(name, expiryDate){
    super(name) // inherit the specified attributes from food and set its value to name
    expiryDate = "1/1/2000";
  }

  // The methods are inherited, unless you want to override it,
  // then re-implment the method here.
}

```

```javascript
function human(name, weight, height){
  this.name = name;
  this.weight = weight;
  this.height = height;
}

human.protoype.getDetails = function(){
  console.log(this.name);
  console.log(this.weight);
  console.log(this.height);
}

function individual(name, weight, height, country){
  // inheret the "constructor"
  human.call(this, name, weight, height);
}

individual.prototype.getDetails = function(){
  console.log(this.name);
  console.log(this.weight);
  console.log(this.height);
  console.log(this.country);
}

let me = individual("Bob", 50, 50, "us");
me.getDetails()
// BUT what if we want to use the getDetails implmeneted in the human.prototype?
// simply use human.prototype.getDetails.call(this);

// BUT what if you want to climb further up the chain until you find the right prototype you want? 
// in this case use the getPrototypeOf() method that should return <prototypename>.prototype which you can then use === to make sure it is the correct prototype, then use <prototypename>.prototype.<method you want>.call(this) <-- if your method requires instance attributes. 
```
