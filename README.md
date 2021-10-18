This is a sample project showing how to make using of Spring Boot to build a REST interface to access Elasticsearch.

Before running the application, you can use Kibana to create the following "employees" index:

POST employees/_bulk
{ "index" : { "_id" : "1" } }
{ "name" : "张三", "sex": "male", "salary": "10000", "occupation": "software developer" }
{ "index" : { "_id" : "2" } }
{ "name" : "李四", "sex": "female", "salary": "20000", "occupation": "manager" }
{ "index" : { "_id" : "3" } }
{ "name" : "王五", "sex": "male", "salary": "90000", "occupation": "test engineer" }

The Spring Boot application will create REST APIs to access the index.