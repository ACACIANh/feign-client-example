package org.example;

import feign.Feign;
import feign.Logger;
import feign.Retryer;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.httpclient.ApacheHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.HttpClients;
import org.example.my.MyClient;
import org.example.my.MyModel;

@Slf4j
public class Main {
	public static void main( String[] args ) {

		String url = "http://localhost:8080";

		MyClient client = Feign.builder()
				.client( new ApacheHttpClient( HttpClients.createDefault() ) )
				.retryer( new Retryer.Default() )
				.encoder( new GsonEncoder() )
				.decoder( new GsonDecoder() )
				.requestInterceptor( e -> e.header( "lambda interceptor header", "bar" ) )
				.logger( new Slf4jLogger( MyClient.class ) )
				.logLevel( Logger.Level.BASIC )
				.target( MyClient.class, url );

		MyModel model = client.getModel( 1 );

		log.info( model.toString() );
	}
}