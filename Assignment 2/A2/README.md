# Smells
The list of smells and their description such as location (package/class/method name) is mentioned below:


## Implementation Smells
* [Complex Method](#complex-method)
* [Long Parameter List](#long-parameter-list)
* [Magic Number](#magic-number)


## Design Smells
* [Deficient Encapsulation](#deficient-encapsulation)
* [Multifaceted Abstraction](#multifaceted-abstraction)
* [Insufficient Modularization](#insufficient-modularization)


## Architecture Smells
* [Cyclic Dependency](#cyclic-dependency)


#### Complex Method
There is 1 complex method in this project. This smell is located at 
```
Package name - secondpackage
Class name - SbiInterest
Method name - fdInterest
```
This method is called a complex method since it's Cyclomatic Complexity (CC) is 9.


#### Long Parameter List
There is 1 long parameter list present in one of the methods. This smell is located at
```
Package name - firstpackage
Class name - MathematicalCalculations
Method name - multiplication
```
This is detected as an smell because the number of parameters passed as arguments to this method are 10.


#### Magic Number
There are 56 magic numbers present in this code. These are located in multiple classes across multiple packages. So to break down to each package,

28 magic numbers are present in:
```
Package name - firstpackage
Class name - MathematicalCalculations
```
28 magic numbers are present in:
```
Package name - secondpackage
Class name - SbiInterest
```


#### Deficient Encapsulation
There is 1 deficient encapsulation smell present in the project. It is located at
```
Package name - firstpackage
Class name - MathematicalCalculations
```
I believe this smell occures because the above mentioned class exposed fields with public accessibility. The fields with public accessibility are: pi; square; cube; powerValue; root; temp; nearestInt; smallest; biggest; addValue; subtractValue; divisionValue; even; odd; evenCheck; oddCheck; palindrome; cal; prime; sInterest; cInterest.


#### Multifaceted Abstraction
There is 1 multifaceted abstraction design smell detected in our code at the location
```
Package name - firstpackage
Class name - MathematicalCalculations
```
The reason that this class shows this smell is because the cohesion among the methods of this class is low. LCOM of this class is 0.9523809523809523


#### Insufficient Modularization
There is 1 insufficient modularization design smell detected in our code at the location
```
Package name - firstpackage
Class name - MathematicalCalculations
```
The tool detected this smell in this class because the class has bloated interface i.e., large number of public methods. Total public methods in this class: 21 public methods


#### Cyclic Dependency
Cyclic Dependency occurs when two or more independent modules or components rely on each other to function properly. It is also called as Circular Dependency. The packages which are involved in cyclic dependency in our project are
```
Package name - firstpackage
Package name - secondpackage
Package name - thirdpackage
```
firstpackage depends on secondpackage for a method; secondpackage depends on thirdpackage for a method; and thirdpakage depends on firstpackage for a constant variable. Hence, these 3 packages are in Cyclic Dependency.
