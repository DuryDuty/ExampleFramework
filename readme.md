# Basic Framework for training & job applications

**Purpose:**
For learning how frameworks are built and implemented for automation purposes. 

Current implementation includes Selenium and RESTAssured, and both rely on Cucumber BDD

**Selenium:**
The selenium example uses eBay to search for an item, adds it to the cart, then removes it Making relevant assertions where necessary.

**RESTAssured:**
Followed a udemy course for implementing an API using BDD. Currently it adds an object through a POST, then confirms with GET request.

Execution can be run through a maven command: 
**mvn test**

It can also be extended with tags:


**mvn test -Dcucumber.options="--tags @API"**
