package at.ac.fhcampuswien.newsanalyzer.ui;


import at.ac.fhcampuswien.newsanalyzer.ctrl.Controller;
import at.ac.fhcampuswien.newsapi.NewsApi;
import at.ac.fhcampuswien.newsapi.NewsApiBuilder;
import at.ac.fhcampuswien.newsapi.enums.Category;
import at.ac.fhcampuswien.newsapi.enums.Country;
import at.ac.fhcampuswien.newsapi.enums.Endpoint;
import at.ac.fhcampuswien.newsapi.enums.Language;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UserInterface 
{
	private Controller ctrl = new Controller();

	public void getDataFromCtrl1(){
		System.out.println("Articles in sports with keyword 'ball'");

		ctrl.process("428ce1e3cf64410ebde5dcd05cd4d3e9", "ball", Endpoint.TOP_HEADLINES, Country.at, Category.sports, Language.de);
	}

	public void getDataFromCtrl2(){
		// TODO implement me
		System.out.println("Articles in business with keyword 'man'");

		ctrl.process("428ce1e3cf64410ebde5dcd05cd4d3e9", "man", Endpoint.TOP_HEADLINES, Country.at, Category.business, Language.de);
	}

	public void getDataFromCtrl3(){
		// TODO implement me
		System.out.println("Articles in health with keyword 'corona'");

		ctrl.process("428ce1e3cf64410ebde5dcd05cd4d3e9", "corona", Endpoint.TOP_HEADLINES, Country.at, Category.health, Language.de);
	}
	
	public void getDataForCustomInput() {
		// TODO implement me
		System.out.println("User Input");
		Scanner scanner = new Scanner(System.in);
		String ScanQ = scanner.next();
		Endpoint ScanEndPoint = Endpoint.valueOf(scanner.next());
		Country ScanCountry = Country.valueOf(scanner.next());
		Category ScanCategory = Category.valueOf(scanner.next());
		Language ScanLanguage = Language.valueOf(scanner.next());

		ctrl.process("428ce1e3cf64410ebde5dcd05cd4d3e9",ScanQ,ScanEndPoint, ScanCountry, ScanCategory, ScanLanguage);
	}


	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interface");
		menu.setTitle("Wählen Sie aus:");
		menu.insert("a", "Articles in sports with keyword 'ball'", this::getDataFromCtrl1);
		menu.insert("b", "Articles in business with keyword 'money'", this::getDataFromCtrl2);
		menu.insert("c", "Articles in health with keyword 'corona'", this::getDataFromCtrl3);
		menu.insert("d", "Choice User Input:",this::getDataForCustomInput);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		System.out.println("Program finished");
	}


    protected String readLine() {
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
        } catch (IOException ignored) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 	{
		Double number = null;
        while (number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                number = null;
				System.out.println("Please enter a valid number:");
				continue;
			}
            if (number < lowerlimit) {
				System.out.println("Please enter a higher number:");
                number = null;
            } else if (number > upperlimit) {
				System.out.println("Please enter a lower number:");
                number = null;
			}
		}
		return number;
	}
}
