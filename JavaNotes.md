Soon to populate with java language notes



### Generic interface
Are interfaces that use generic types. It provides type flexibility to classes that implement the interface, allowing the interface to be more re-useable and flexible than non-generic interfaces. 

Examples of common generic interfaces in the java standard library are...
```java
List<T>		// ArrayList<E>
Set<T>		// HashSet<E>
```
- The T here is the generic type parameter for the interface. It is replaced by actual types when these interfaces are implemented.

- Classes that implement these generic interfaces are called generic classes. In comments above are just two examples of classes that implement the corresponding generic interface.

### Generic type parameter
Located in angle brackets. The letters can be anything really, but in many libraries the choice of lettering is deliberate. Use E for when you know the interface is dealing with elements of collections like lists of types. T is like the plain old, default name everyone uses. 

```
E - Element (used extensively by the Java Collections Framework)
K - Key
N - Number
T - Type
V - Value
```

### Generic Classes










Access modifiers
Arrays({}) and Arrays methods (copyOf, copyOfRange, toString, deepToString, sort)
Primitive and refrence datatypes
System class (arraycopy)
Creating new arrays(new primitive[size][size]) 
	and Lists<> (new ArrayList<>()) <- works for nested lists no matter how deep
Lits<> generic interface and classes that implement it and method of Lists (add)
Set<> interface classes that implement it and methods(add) 
Packages


