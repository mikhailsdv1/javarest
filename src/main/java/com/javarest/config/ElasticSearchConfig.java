package com.javarest.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Configuration
@Component
public class ElasticSearchConfig {

    @Autowired
    private Environment envVar;

    Logger logger = LoggerFactory.getLogger(ElasticSearchConfig.class);


    @Bean(destroyMethod = "close")
    public RestHighLevelClient client() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(getUser(), getPwd()));
        logger.info("about to create RestClient, host:{}, port:{}", getHost(), getPort());
        RestClientBuilder builder = RestClient.builder(new HttpHost(getHost(), getPort(), getProtocol()))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                });

        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }

    //if APP_ENV is empty,  host: localhost, otherwise elasticsearch
    private String getHost() {
        String res = "localhost";
        String envHost = envVar.getProperty("ES_HOST_API");
        if (!StringUtils.isEmpty(envHost)) {
            res = envHost;
        }
        logger.info("ES host:{}", res);
        return res;
    }

    // TODO to create env variables ES_USER & ES_PWD
    private String getUser() {
        String res = "elastic";
        String prop = envVar.getProperty("ES_USER");
        if (!StringUtils.isEmpty(prop)) {
            res = prop;
        }
        logger.info("ES user:{}", res);
        return res;
    }

    private String getPwd() {
        String res = "";
        String prop = envVar.getProperty("ES_PWD");
        if (!StringUtils.isEmpty(prop)) {
            res = prop;
        }
        logger.info("ES pwd size:{}", res.length());
        return res;
    }


    private String getProtocol() {
        String res = "http";
        String envProto = envVar.getProperty("ES_PROTO_API");
        if (!StringUtils.isEmpty(envProto)) {
            res = envProto;
        }
        logger.info("ES protocol:{}", res);
        return res;
    }


    private int getPort() {
        int res = 9200;
        String resStr = envVar.getProperty("ES_PORT_API");
        if (!StringUtils.isEmpty(resStr)) {
            res = Integer.parseInt(resStr);
        }
        logger.info("ES port:{}", res);
        return res;
    }


}
