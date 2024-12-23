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
