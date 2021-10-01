package components;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JButton;

import org.json.JSONArray;
import org.json.JSONObject;

//import com.sun.beans.introspect.PropertyInfo.Name;

public class Controller {

	private static HttpURLConnection connection;

	private static BufferedReader reader;

	private static StringBuffer responseContent = new StringBuffer();
	private static StringBuffer responseContent2 = new StringBuffer();

	private static CloudCenter nation = new CloudCenter();
	private static CloudCenter allCap = new CloudCenter();
	private static CloudCenter player = new CloudCenter();

	private static ArrayList<JButton> capButtons = GUI.capButtons;

	private static boolean allCapButtonsActive = true;
	private static boolean nextBtnActive = true;
	
	
	// Hämta JSON API från mottaget URL.
	public static void setConnection(String inURL) {

		String line;

		try {
			URL url = new URL(inURL);
			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("GET");

			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while ((line = reader.readLine()) != null) {

				if (inURL.equals(GUI.URL)) {
					responseContent.append(line);
				} else {
					responseContent2.append(line);
				}
			}

			reader.close();
			line = "";
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			connection.disconnect();
		}

	}

	
	// Vid klick körs denna metod --> Hämtar värden från API:n
	public static void getJSON() {


		if (nextBtnActive == true) {

			resetButtons();

			// Get JSON from first API endpoint and set values
			JSONObject jsonObj = new JSONObject(responseContent.toString());
			JSONArray jsonArr = jsonObj.getJSONArray("data");

			JSONObject country = jsonArr.getJSONObject(random());

			while (country.getString("capital").isEmpty()) {

				country = jsonArr.getJSONObject(random());

			}
			
			String name = country.getString("name");
			String capital = country.getString("capital");

			nation.setName(name);
			nation.setCapital(capital);

			// Get JSON from second API and set values
			JSONArray jsonArr2 = new JSONArray(responseContent2.toString());

			for (int i = 0; i < jsonArr2.length(); i++) {

				JSONObject jsonObj2 = jsonArr2.getJSONObject(i);

				String name2 = jsonObj2.getString("country");

				if (name2.equals(nation.getName())) {

					nation.setLanguages(jsonObj2.getJSONArray("languages"));

				}

			}

			GUI.setNation(nation);
			GUI.showScore(player);
			getAltCapitals(jsonArr);
			

			nextBtnActive = false;

		}

	}
	// Återställer poäng 
	public static void restart() {
		
		player.clearPoints();
		GUI.score.setText("Score: 0p");
	}
	// Återställer meddelande och färg på knappar
	private static void resetButtons() {
		// TODO Auto-generated method stub

		allCapButtonsActive = true;
		GUI.message.setText("");

		for (int i = 0; i < capButtons.size(); i++) {
			capButtons.get(i).setForeground(Color.black);
		}
	}
	// Hämtar slumpmässiga huvudstäder från API
	private static void getAltCapitals(JSONArray jsonArr) {
		// TODO Auto-generated method stub

		ArrayList<String> allCapitals = new ArrayList<String>();

		for (int i = 0; i < jsonArr.length(); i++) {

			JSONObject country = jsonArr.getJSONObject(i);
			String capital = country.getString("capital");

			allCapitals.add(capital);

		}

		allCap.setAllCapitals(allCapitals);

		GUI.setAltCapitals(allCap);

	}
	// Hämtar ett slumpmässigt värde 
	public static Integer random() {

		int rand = (int) (Math.random() * 249) + 0;

		return rand;

	}
	
	
	public static void showMessage(JButton clickedCap) {

		if (clickedCap.getText().equals(nation.getCapital())) {

			GUI.message.setText("Correct!");
		} else {
			GUI.message.setText("Wrong! Correct Capital is " + "\"" + nation.getCapital() + "\"");
		}
	}

	public static void checkBtn1() {
		// TODO Auto-generated method stub

		if (allCapButtonsActive == true) {

			String check;

			check = capButtons.get(0).getText();

			if (check.equals(nation.getCapital())) {
				capButtons.get(0).setForeground(Color.GREEN);
				player.addPoints(25);
				
			} else {
				
				capButtons.get(0).setBackground(Color.RED);
				capButtons.get(0).setOpaque(true);
			}
			showMessage(capButtons.get(0));
		}
		allCapButtonsActive = false;
		nextBtnActive = true;

	}

	public static void checkBtn2() {
		// TODO Auto-generated method stub

		if (allCapButtonsActive == true) {
			String check;

			check = capButtons.get(1).getText();

			if (check.equals(nation.getCapital())) {
				capButtons.get(1).setForeground(Color.GREEN);
				player.addPoints(25);
			} else {

				capButtons.get(1).setForeground(Color.RED);
			}
			showMessage(capButtons.get(1));
		}
		allCapButtonsActive = false;
		nextBtnActive = true;

	}

	public static void checkBtn3() {
		// TODO Auto-generated method stub

		if (allCapButtonsActive == true) {
			String check;

			check = capButtons.get(2).getText();

			if (check.equals(nation.getCapital())) {
				capButtons.get(2).setForeground(Color.GREEN);
				player.addPoints(25);
			} else {

				capButtons.get(2).setForeground(Color.RED);
			}
			showMessage(capButtons.get(2));
		}
		allCapButtonsActive = false;
		nextBtnActive = true;

	}

	public static void checkBtn4() {
		// TODO Auto-generated method stub

		if (allCapButtonsActive == true) {
			String check;

			check = capButtons.get(3).getText();

			if (check.equals(nation.getCapital())) {
				capButtons.get(3).setForeground(Color.GREEN);
				player.addPoints(25);
			} else {

				capButtons.get(3).setForeground(Color.RED);
			}
			showMessage(capButtons.get(3));
		}
		allCapButtonsActive = false;
		nextBtnActive = true;

	}

	

}
