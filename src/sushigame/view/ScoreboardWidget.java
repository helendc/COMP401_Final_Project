package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;

public class ScoreboardWidget extends JPanel implements BeltObserver, ActionListener {

	private SushiGameModel game_model;
	private JLabel display;
	private JComboBox<String> options;
	
	public ScoreboardWidget(SushiGameModel gm) {
		game_model = gm;
		game_model.getBelt().registerBeltObserver(this);
		
		setLayout(new BorderLayout());
		JLabel prompt = new JLabel();
		prompt.setVerticalAlignment(SwingConstants.TOP);
		prompt.setText("Select how to organize chefs: ");
		add(prompt, BorderLayout.CENTER);
		
		String[] orders = {"balance", "consumed weight", "spoiled weight"};
		
		options = new JComboBox<String>(orders);
		options.setPreferredSize(new Dimension(100, 20));
		options.setMaximumSize(new Dimension(100, 20));
		options.setSelectedIndex(0);
		options.setSelectedItem(orders[0]);
		options.setActionCommand("choose");
		options.addActionListener(this);
		options.setVisible(true);
		add(options, BorderLayout.NORTH);
		
		display = new JLabel();
		display.setVerticalAlignment(SwingConstants.TOP);
		add(display, BorderLayout.CENTER);
		display.setText(makeScoreboardHTML());
	}

	private String makeScoreboardHTML() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		
		Comparator<Chef> sorter = null;
		String selected_option = (String) options.getSelectedItem();

		switch (selected_option) {
			case ("balance"):
				sorter = new HighToLowBalanceComparator();
				break;
			case("consumed weight"):
				sorter = new HighToLowConsumedComparator();
				break;
			case("spoiled weight"):
				sorter = new LowToHighSpoiledComparator();
				break;
		}
		
		Arrays.sort(chefs, sorter);

		for (Chef c : chefs) {
			switch (selected_option) {
			case ("balance") :
				sb_html += c.getName() + " ($" + Math.round(c.getBalance()*100.0)/100.0 + ") <br>";
				break;
			case ("consumed weight"):
				sb_html += c.getName() + " (oz: " + Math.round(c.getAmountSoldByWeight()*100.0)/100.0 + ") <br>";
				break;
			case ("spoiled weight"):
				sb_html += c.getName() + " (oz: " + Math.round(c.getAmountSpoiled()*100.0)/100.0 + ") <br>";
				break;
			}
		}
		return sb_html;
	}

	
	public void refresh() {
		display.setText(makeScoreboardHTML());
	}
	
	@Override
	public void handleBeltEvent(BeltEvent e) {
		
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			refresh();
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("choose") ) {
			refresh();
		}
	}
		

}
