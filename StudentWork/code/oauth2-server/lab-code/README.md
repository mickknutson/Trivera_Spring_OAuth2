# README #

### Microservice Security with OAuth2 and JSON Web Tokens ###

Not Done


### Sections ###



## OAuth2 Resource Server Example

Running Example
-
mvn spring-boot:run -e

Running Unit Tests
-
mvn clean test -e


### Request Token:
$ http -a oauthClient1:oauthClient1Password -f POST localhost:8080/oauth/token grant_type=password username=user1@example.com password=user1

$ curl oauthClient1:oauthClient1Password@localhost:8080/oauth/token -d grant_type=password -d username=user1@example.com -d password=user1 -v

or:

$ curl oauthClient1:oauthClient1Password@localhost:8080/oauth/token -d "grant_type=password&username=user1@example.com&password=user1" -v

### Use Token:

$ http localhost:8080/ "Authorization: Bearer [access_token]"

$ curl -H "Authorization: Bearer XXX" localhost:8080/ -v

or

$ curl -H "Authorization: Bearer [token]" oauthClient1:oauthClient1Password@localhost:8080/ -v
