This is a sample spring-boot project which uses Basic Auth to secure the APIs.

It uses hsql db backed by a local file (specified by application.properties)
When application starts up, if no user exists, it will create a new user defaultUser with password password-defaultUser
Using this credentials, user can explore the APIs under
http://localhost:8080/swagger-ui/
