package org.example.my;

import feign.*;

import java.util.Map;

public interface MyClient {

	@RequestLine( "GET /users/{userId}" )
	MyModel getModel( @Param( "userId" ) int userId );

	@RequestLine( "POST /users/{userId}" )
	@Headers( { "Authorization: Bearer {authToken}", "Authorization: Foo", "Content-Type: application/json" } )
	@Body( "model" )
	MyModel getAuth( @Param( "authToken" ) String token,
	                 @Param( "userId" ) int userId,
	                 MyModel model,
	                 @HeaderMap Map< String, Object > headerMap );

	@RequestLine( "DELETE /users/{userId}?name={name}" )
	MyModel getDelete( @Param( "userId" ) int userId,
	                   @Param( "name" ) String name );

	@RequestLine( "PUT /users/{userId}" )
	@Headers( { "Content-Type: application/json" } )
	@Body( "myModel" )
	MyModel getPut( @Param( "userId" ) int userId, MyModel model );
}
