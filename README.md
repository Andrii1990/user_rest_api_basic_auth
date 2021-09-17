## Starting the application
   `mvn package`
This will create a single fat jar which can be executed
   with `java -jar target/tech1-0.0.1-SNAPSHOT.jar`
Note:
   the database will be deployed automatically and filled with test data

## Using the REST API

Please import tech1.postman_collection.json file to your Postman

### Basic Auth
Basic auth "key": "x-csrf-token",
Basic auth "value" you can get using "get auth" request


### Users

#### Create  user

you can create new user use "save user" request

#### Get  users

you can get all users use "find all user"  request

#### Get  users (with parameters)

you can get all users with different parameter use "user search"  request
f.e.
/users/search?articles=3 get all users which have more than 3 articles
/users/search?color=red get all users which have article with red color

/users/search?search=age>28 get all users over 28 years old
/users/search?search=age<28 get all users under 28 years old
/users/search?search=age:28 get all users who are 28 years old

/users/search?search=name:James get all users named James
#### Delete user
you can delete user use "delete user" request

### Articles

#### Create  article
you can create new article  use "save article" request

#### Get  articles
you can get all articles  use "get all article" request

