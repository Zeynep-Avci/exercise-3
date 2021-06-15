package at.ac.fhcampuswien.newsanalyzer.ctrl;

import at.ac.fhcampuswien.newsapi.NewsApi;
import at.ac.fhcampuswien.newsapi.NewsApiBuilder;
import at.ac.fhcampuswien.newsapi.beans.Article;
import at.ac.fhcampuswien.newsapi.beans.NewsResponse;
import at.ac.fhcampuswien.newsapi.enums.Category;
import at.ac.fhcampuswien.newsapi.enums.Country;
import at.ac.fhcampuswien.newsapi.enums.Endpoint;
import at.ac.fhcampuswien.newsapi.enums.Language;

import java.util.List;

public class Controller {

	public static final String APIKEY = "428ce1e3cf64410ebde5dcd05cd4d3e9";  //TODO add your api key

	public void process(String apiKey, String q, Endpoint endpoint, Country sourceCountry, Category sourceCategory, Language language) {
		System.out.println("Start process");

		//TODO implement Error handling

		//TODO load the news based on the parameters

		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(apiKey)
				.setQ(q)
				.setEndPoint(endpoint)
				.setSourceCountry(sourceCountry)
				.setSourceCategory(sourceCategory)
				.setLanguage(language)
				.createNewsApi();

		NewsResponse newsResponse = newsApi.getNews();
		if(newsResponse != null){
			List<Article> articles = newsResponse.getArticles();
			articles.stream().forEach(article -> System.out.println(article.toString()));
		}

		//TODO implement methods for analysis

		System.out.println("End process");
	}
	

	public Object getData() {
		
		return null;
	}
}
