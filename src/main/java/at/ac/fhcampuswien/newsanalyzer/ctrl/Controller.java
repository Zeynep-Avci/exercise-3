package at.ac.fhcampuswien.newsanalyzer.ctrl;

import at.ac.fhcampuswien.newsapi.NewsApi;
import at.ac.fhcampuswien.newsapi.NewsApiBuilder;
import at.ac.fhcampuswien.newsapi.beans.Article;
import at.ac.fhcampuswien.newsapi.beans.NewsResponse;
import at.ac.fhcampuswien.newsapi.beans.Source;
import at.ac.fhcampuswien.newsapi.enums.Category;
import at.ac.fhcampuswien.newsapi.enums.Country;
import at.ac.fhcampuswien.newsapi.enums.Endpoint;
import at.ac.fhcampuswien.newsapi.enums.Language;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {

	public static final String APIKEY = "428ce1e3cf64410ebde5dcd05cd4d3e9";  //TODO add your api key
	private List Article;
	private Object List;


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


		NewsResponse newsResponse = null;
		List<Article> articles = null;


		try {
			newsResponse = newsApi.getNews();
			if(newsResponse != null){
				articles = newsResponse.getArticles();
				articles.stream().forEach(article -> System.out.println(article.toString()));
			}
			System.out.println("List of Titles: ");
			System.out.println(longestTitle(articles));

			long articleNumber = analyzeSize(articles);
			System.out.println("");
			System.out.println("Number of articles in this category: " + articleNumber);

			System.out.println("Author with shortest name:  " + shortestName(articles));
		} catch (NewsApiException e) {
			e.printStackTrace();
		}


		//TODO implement methods for analysis


		System.out.println("End process");
	}


	public Object getData() {
		
		return null;
	}


	public long analyzeSize(List<Article> articles) {

		return articles.stream().count();
	}

	//https://www.logicbig.com/how-to/java-string/longest-and-shortest-string.html
	public String shortestName(List<Article> listofauthors ){
		Article names = listofauthors.stream().filter(article -> article.getAuthor()!=null).sorted(Comparator.comparing(article -> article.getAuthor().length())).findFirst().orElse(new Article());

		return names.getAuthor();
	}




	public List longestTitle(List<Article> titleList){
		List title = Collections.singletonList(titleList.stream().filter(article -> article.getTitle() != null).sorted(Comparator.comparingInt(article -> article.getTitle().length())).findFirst());

		Collections.sort(title);

		return title;
	}
}
