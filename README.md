# NewsAPI SDK for Java


**How to use**

**0**.Download the latest release here : https://github.com/VIad/News-API-java/releases and add the jar to your build path



**1**.Create the client object
```java
NewsApiClient client = new NewsApiClient("YOUR_API_KEY_HERE");
```
**2**.Create the service you require (sources / everything / top headlines)

*Example: get the top headlines from the united states using an asynchronous service*

```java
client.newTopHeadlinesServiceAsync()
      .country("us")
      .send(result -> {
          List<Article> articles = result.viewAsArticles().orElseGet(Collections::emptyList);
          //Do some processing on the articles
       },
           //Error occured, print it
            error -> System.err.println(error.getMessage()));
```

**You can also use a non-async flow and store the response in an ```APIResponse``` object**

*Example: get the news sources using a keyword and sort them by popularity*
```java
APIResponse response = client.newSourcesService()
                             .withKeyword("bulgaria")
                             .sortBy(SortBy.POPULARITY)
                             .send();
                             
/*
 * viewAs methods return an optional, since it's uncertain what the data type inside APIResponse.getData is
 */
List<NewsSource> newsSources = response.viewAsNewsSources().orElseGet(Collections::emptyList);
//Do some processing on the news sources
```

**Creating your own service**

**1**.Create a class and implement either ```IService``` or ```IAsyncService``` if you want either synchronous or asynchronous execution

*Example:*
```java
public class MyCustomAsyncService implements IAsyncService{
Remainder omitted...
}
```

**2**.Create the service using the ```NewsApiClient``` object

*Example: query all the headlines in english with keyword 'Youtube' from the last 7 days*

```java
client.newCustomAsyncService(new MyCustomAsyncService(), /*Specify an endpoint for your service*/ Endpoint.EVERYTHING)
      .withKeyword("Youtube")
      .language("en")
      .dateRange(DateRange::LAST_7_DAYS)
      .send(result -> {
           List<Article> articles = result.viewAsArticles().orElseGet(Collections::emptyList);
           //Do some processing on the articles
       },
            //Something went wrong, print the stack trace
            Throwable::printStackTrace);
```

Some request methods ```IService``` and ```IAsyncService``` provide

```java
withSources(String... sources) //Sets the lookup sources, such as bbc, abc-news, cnn
withDomains(String... domains) //Sets the lookup domains, such as techcrunch.com, bbc.co.uk
from(Date date) //Filters news from the specified date
to(Date date) //Filters news to the specified date
withKeyword(String keyword) //sets the searched keyword
withCategory(Category category) //Filters the news given a category
```

If you are unsure as to what a function does, you can always refer to the javadoc provided with the binary
