package components;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.json.JSONArray;

public class GUI {

	public static void main(String[] args) {

		new GUI();

	}

	public static String URL = "https://countriesnow.space/api/v0.1/countries/capital";
	public static String URL2 = "https://raw.githubusercontent.com/samayo/country-json/master/src/country-by-languages.json";

	public static String capital;
	public static JLabel land = new JLabel("", SwingConstants.CENTER);
	public static JLabel message = new JLabel("");
	public static JLabel languages = new JLabel("");
	public static JLabel score = new JLabel("");

	
	public static ArrayList<JButton> capButtons = new ArrayList<JButton>();
	
	
	// Sätter upp alla komponenter och öppnar ett fönster
	public GUI() {

		JFrame frame = new JFrame();

		land.setFont(new Font("Serif", Font.ITALIC, 30));
		languages.setFont(new Font("Serif", Font.BOLD, 18));
		message.setFont(new Font("Serif", Font.BOLD, 15));

		JPanel topPanel = new JPanel();
		topPanel.setBounds(0, 0, 450, 300);

		JPanel midPanel = new JPanel();
		midPanel.setBounds(0, 300, 450, 200);

		JPanel botPanel = new JPanel();
		botPanel.setBounds(0, 550, 450, 60);

		topPanel.setLayout(new BorderLayout());
		midPanel.setLayout(new GridLayout(2, 2));

		topPanel.add(land, BorderLayout.NORTH);
		topPanel.add(languages, BorderLayout.CENTER);
		topPanel.add(message, BorderLayout.SOUTH);
		topPanel.add(score, BorderLayout.EAST);

		JButton btnCap1 = new JButton();
		midPanel.add(btnCap1);
		JButton btnCap2 = new JButton();
		midPanel.add(btnCap2);
		JButton btnCap3 = new JButton();
		midPanel.add(btnCap3);
		JButton btnCap4 = new JButton();
		midPanel.add(btnCap4);

		capButtons.add(btnCap1);
		capButtons.add(btnCap2);
		capButtons.add(btnCap3);
		capButtons.add(btnCap4);

		JButton btnRestart = new JButton("Restart");
		botPanel.add(btnRestart);
		JButton btnNext = new JButton("Start/Next");
		botPanel.add(btnNext);

		botPanel.setLayout(new GridLayout(1, 2));

		frame.add(topPanel);
		frame.add(midPanel);
		frame.add(botPanel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setTitle("What´s the Capital?");
		frame.setSize(450, 640);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);

		Controller.setConnection(URL);
		Controller.setConnection(URL2);

		btnNext.addActionListener(e -> Controller.getJSON());
		btnRestart.addActionListener(e -> Controller.restart());

		btnCap1.addActionListener(e -> Controller.checkBtn1());
		btnCap2.addActionListener(e -> Controller.checkBtn2());
		btnCap3.addActionListener(e -> Controller.checkBtn3());
		btnCap4.addActionListener(e -> Controller.checkBtn4());

	}
	
	// Hämtar land och dess huvudstad, även talade språk från CloudCenter och visar upp i fönstret
	public static void setNation(CloudCenter nation) {
		
		land.setText(nation.getName());
		
		capital = nation.getCapital();

		JSONArray lang = nation.getLanguages();
		String text = "";

		if (lang != null) {

			for (int x = 0; x < lang.length(); x++) {

				text += lang.get(x).toString() + ", ";
			}

			languages.setText("<html><p style=\"width:200px\">" + "Langugages spoken in " + nation.getName() + ": "
					+ text + "</p></html>");
		}
	}
	// Hämtar alternativa huvudstäder --> GUI
	public static void setAltCapitals(CloudCenter allCap) {

		ArrayList<String> allCapitals = allCap.getAllCapitals();

		for (int i = 0; i < capButtons.size(); i++) {

			int capId = Controller.random();

			while (allCapitals.get(capId).isEmpty()) {

				capId = Controller.random();

			}

			capButtons.get(i).setText(allCapitals.get(capId));

		}

		capButtons.get(randButton()).setText(capital);

	}
	// En metod för att hämta en random knapp av 4.
	public static Integer randButton() {

		int rand = (int) (Math.random() * 4) + 0;

		return rand;

	}
	// Visar poäng totalt
	public static void showScore(CloudCenter player) {
		// TODO Auto-generated method stub
		score.setText("Score:  "+player.getPoints().toString()+"p ");
		
	}

}
