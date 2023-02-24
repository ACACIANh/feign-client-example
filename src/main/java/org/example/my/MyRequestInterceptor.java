package org.example.my;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class MyRequestInterceptor implements RequestInterceptor {

	@Override
	public void apply( RequestTemplate template ) {
		template.header( "interceptor header", "bar" );
	}
}
