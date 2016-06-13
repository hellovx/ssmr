/**
 *    Copyright 2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.zh.common.redis;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class RedisConfig extends JedisPoolConfig {

	private String redis_host = Protocol.DEFAULT_HOST;
	private int redis_port = Protocol.DEFAULT_PORT;
	private int redis_connectionTimeout = Protocol.DEFAULT_TIMEOUT;
	private int redis_soTimeout = Protocol.DEFAULT_TIMEOUT;
	private String redis_password;
	private int redis_database = Protocol.DEFAULT_DATABASE;
	private String redis_clientName;
	public String getRedis_host() {
		return redis_host;
	}
	public void setRedis_host(String redis_host) {
		this.redis_host = redis_host;
	}
	public int getRedis_port() {
		return redis_port;
	}
	public void setRedis_port(int redis_port) {
		this.redis_port = redis_port;
	}
	public int getRedis_connectionTimeout() {
		return redis_connectionTimeout;
	}
	public void setRedis_connectionTimeout(int redis_connectionTimeout) {
		this.redis_connectionTimeout = redis_connectionTimeout;
	}
	public int getRedis_soTimeout() {
		return redis_soTimeout;
	}
	public void setRedis_soTimeout(int redis_soTimeout) {
		this.redis_soTimeout = redis_soTimeout;
	}
	public String getRedis_password() {
		return redis_password;
	}
	public void setRedis_password(String redis_password) {
		this.redis_password = redis_password;
	}
	public int getRedis_database() {
		return redis_database;
	}
	public void setRedis_database(int redis_database) {
		this.redis_database = redis_database;
	}
	public String getRedis_clientName() {
		return redis_clientName;
	}
	public void setRedis_clientName(String redis_clientName) {
		this.redis_clientName = redis_clientName;
	}
	
	

}
