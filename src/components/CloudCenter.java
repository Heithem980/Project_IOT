package components;

import java.util.ArrayList;

import org.json.JSONArray;


// I denna klass sparas alla värden från API:n för att sedan hämtas i GUI klassen.
public class CloudCenter {
	
	
	private String name;
	private String capital;
	private int points;


	private ArrayList<String> allCapitals;
	private JSONArray languages;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public ArrayList<String> getAllCapitals() {
		return allCapitals;
	}

	public void setAllCapitals(ArrayList<String> allCapitals) {
		this.allCapitals = allCapitals;
	}

	public JSONArray getLanguages() {
		return languages;
	}

	public void setLanguages(JSONArray jsonArray) {
		this.languages = jsonArray;
	}

	public void addPoints(int points) {
		// TODO Auto-generated method stub
		this.points += points;
	}

	public Integer getPoints() {
		// TODO Auto-generated method stub
		
		return points;
	}
	public void clearPoints() {
		this.points = 0;
	}

}
