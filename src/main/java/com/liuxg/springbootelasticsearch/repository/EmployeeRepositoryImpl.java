package com.liuxg.springbootelasticsearch.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuxg.springbootelasticsearch.entity.Employee;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Autowired
    private ObjectMapper objectMapper;

    public EmployeeRepositoryImpl() {
        makeConnection();
    }

    private static RestHighLevelClient client = null;

    private static synchronized RestHighLevelClient makeConnection() {
        final BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
        basicCredentialsProvider
                .setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "password"));

        if (client == null) {
            client = new RestHighLevelClient(
                    RestClient.builder(new HttpHost("localhost", 9200, "http"))
                            .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                                @Override
                                public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                                    httpClientBuilder.disableAuthCaching();
                                    return httpClientBuilder.setDefaultCredentialsProvider(basicCredentialsProvider);
                                }
                            })
            );
        }

        return client;
    }

    private static synchronized void closeConnection() throws IOException {
        client.close();
        client = null;
    }

    @Override
    public List<Employee> findAllEmployeeDetailsFromES() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("employees");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        List<Employee> list = new ArrayList<>();
        SearchResponse searchResponse = null;
        try {
            searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHit = searchResponse.getHits().getHits();
                for (SearchHit hit : searchHit) {
                    Map<String, Object> map = hit.getSourceAsMap();
                    list.add(objectMapper.convertValue(map, Employee.class));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Employee> findAllUserDataByNameFromES(String name) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("employees");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("name.keyword", name)));
        searchRequest.source(searchSourceBuilder);
        List<Employee> list = new ArrayList<>();

        try {
            SearchResponse searchResponse = null;
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHit = searchResponse.getHits().getHits();
                for (SearchHit hit : searchHit) {
                    Map<String, Object> map = hit.getSourceAsMap();
                    list.add(objectMapper.convertValue(map, Employee.class));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Employee> findAllUserDataByNameAndOccupationFromES(String name, String occupation) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("employees");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("name.keyword", name))
                .must(QueryBuilders.matchQuery("occupation", occupation)));
        searchRequest.source(searchSourceBuilder);
        List<Employee> list = new ArrayList<>();

        try {
            SearchResponse searchResponse = null;
            searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHit = searchResponse.getHits().getHits();
                for (SearchHit hit : searchHit) {
                    Map<String, Object> map = hit.getSourceAsMap();
                    list.add(objectMapper.convertValue(map, Employee.class));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
