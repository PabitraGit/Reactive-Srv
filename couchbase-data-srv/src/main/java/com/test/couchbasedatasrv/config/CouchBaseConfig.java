package com.test.couchbasedatasrv.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@Configuration
@EnableCouchbaseRepositories
public class CouchBaseConfig extends AbstractCouchbaseConfiguration{

	
	@Value("${spring.couchbase.connection-string}")
	private String host;

	@Value("${spring.data.couchbase.bucket-name}")
	private String bucketName;

	@Value("${spring.couchbase.password}")
	private String password;

	@Value("${spring.couchbase.username}")
	private String username;
	
	@Override
	public String getConnectionString() {
		return host;
	}

	@Override
	public String getUserName() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getBucketName() {
		return bucketName;
	}

	
}
