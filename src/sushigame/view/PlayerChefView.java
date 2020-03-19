package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.JTextField;


import comp401sushi.Avocado;
import comp401sushi.AvocadoPortion;
import comp401sushi.CrabPortion;
import comp401sushi.EelPortion;
import comp401sushi.IngredientPortion;
import comp401sushi.IngredientPortionImpl;
import comp401sushi.Nigiri;
import comp401sushi.Plate;
import comp401sushi.RedPlate;
import comp401sushi.Roll;
import comp401sushi.Sashimi;
import comp401sushi.SeaweedPortion;
import comp401sushi.ShrimpPortion;
import comp401sushi.Sushi;
import comp401sushi.TunaPortion;
import comp401sushi.YellowtailPortion;

public class PlayerChefView extends JPanel implements ActionListener {

	private List<ChefViewListener> listeners;

	private int belt_size;
	JComboBox<String>  sushi_list, position_list, color_list, nigiri_and_sashimi_list;
	private JSlider price_slider, avocado_slider, eel_slider, yellowtail_slider, crab_slider, tuna_slider, shrimp_slider;
	private String request_color;
	private Sushi request_sushi;
	private int request_position;
	private double request_price; 
	private double request_avocado, request_eel, request_yellowtail, request_crab, request_tuna, request_shrimp;
	private JTextField name_of_roll;

	public PlayerChefView(int belt_size) {
		this.request_color = "";
		this.request_position = 0;
		this.request_price = 0.0;

		this.belt_size = belt_size;
		listeners = new ArrayList<ChefViewListener>();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel sushi_label = new JLabel("Choose sushi type");
		add(sushi_label);
		
		String[] sushis = {"Roll", "Sashimi", "Nigiri"};
		sushi_list = new JComboBox<String>(sushis);
		sushi_list.setMinimumSize(new Dimension(100, 20));
		sushi_list.setPreferredSize(new Dimension(100, 20));
		sushi_list.setSelectedIndex(0);
		sushi_list.addActionListener(this);
		sushi_list.setVisible(true);
		add(sushi_list);
		
		JLabel color_label = new JLabel("Choose plate color");
		add(color_label);
		
		String[] colors = {"Red", "Blue", "Green", "Gold"};
		color_list = new JComboBox<String>(colors);
		color_list.setMinimumSize(new Dimension(100, 20));
		color_list.setPreferredSize(new Dimension(100, 20));
		color_list.setSelectedIndex(0);
		color_list.addActionListener(this);
		color_list.setVisible(true);
		add(color_list);
		

		JLabel position_label = new JLabel("Choose position");
		add(position_label);
		
		String[] positions = new String[belt_size];
		for (int i = 0; i < belt_size; i++) {
			positions[i] = "" + (i + 1);
		}
		
		position_list = new JComboBox<String>(positions);
		position_list.setMinimumSize(new Dimension(100, 20));
		position_list.setPreferredSize(new Dimension(100, 20));
		position_list.setSelectedIndex(0);
		position_list.addActionListener(this);
		position_list.setVisible(true);
		add(position_list);
			
		JLabel nigiri_label = new JLabel("If making a nigiri or sashimi, choose type");
		add(nigiri_label);
		
		String[] nigiri_and_sashimi_ingredients = {"tuna", "yellowtail", "eel", "crab", "shrimp"};
		nigiri_and_sashimi_list = new JComboBox<String>(nigiri_and_sashimi_ingredients);
		nigiri_and_sashimi_list.setMinimumSize(new Dimension(100, 20));
		nigiri_and_sashimi_list.setPreferredSize(new Dimension(100, 20));
		nigiri_and_sashimi_list.setSelectedIndex(0);
		nigiri_and_sashimi_list.addActionListener(this);
		nigiri_and_sashimi_list.setVisible(true);
		add(nigiri_and_sashimi_list);
		
		JLabel ing_amount_label = new JLabel("If making a roll, choose ingredient and amount");
		add(ing_amount_label);
		
		JPanel ingredient_slider_panel = new JPanel();
		ingredient_slider_panel.setLayout(new GridLayout(3,1));
		
		Hashtable<Integer, JLabel> ing_table = new Hashtable<Integer, JLabel>();
	    ing_table.put (0, new JLabel("0 oz"));
	    ing_table.put (1, new JLabel("0.5 oz"));
	    ing_table.put (2, new JLabel("1 oz"));
	    ing_table.put (3, new JLabel("1.5 oz"));
	    
	    JLabel avocado_label = new JLabel("avocado");
		add(avocado_label);
		avocado_slider = new JSlider(0, 3, 0);
		avocado_slider.setPreferredSize(new Dimension(100, 30));
		avocado_slider.setLabelTable (ing_table);
		avocado_slider.setPaintLabels(true);
		add(avocado_slider);
		
		JLabel crab_label = new JLabel("crab");
		add(crab_label);
		crab_slider = new JSlider(0, 3, 0);
		crab_slider.setPreferredSize(new Dimension(100, 30));
		crab_slider.setLabelTable (ing_table);
		crab_slider.setPaintLabels(true);
		add(crab_slider);

		JLabel eel_label = new JLabel("eel");
		add(eel_label);
		eel_slider = new JSlider(0, 3, 0);
		eel_slider.setPreferredSize(new Dimension(100, 30));
		eel_slider.setLabelTable (ing_table);
		eel_slider.setPaintLabels(true);
		add(eel_slider);

		JLabel shrimp_label = new JLabel("shrimp");
		add(shrimp_label);
		shrimp_slider = new JSlider(0, 3, 0);
		shrimp_slider.setPreferredSize(new Dimension(100, 30));
		shrimp_slider.setLabelTable (ing_table);
		shrimp_slider.setPaintLabels(true);
		add(shrimp_slider);

		JLabel tuna_label = new JLabel("tuna");
		add(tuna_label);
		tuna_slider = new JSlider(0, 3, 0);
		tuna_slider.setPreferredSize(new Dimension(100, 30));
		tuna_slider.setLabelTable (ing_table);
		tuna_slider.setPaintLabels(true);
		add(tuna_slider);

		JLabel yellowtail_label = new JLabel("yellowtail");
		add(yellowtail_label);
		yellowtail_slider = new JSlider(0, 3, 0);
		yellowtail_slider.setPreferredSize(new Dimension(100, 30));
		yellowtail_slider.setLabelTable (ing_table);
		yellowtail_slider.setPaintLabels(true);
		add(yellowtail_slider);

		ingredient_slider_panel.setVisible(true);
		add(ingredient_slider_panel, BorderLayout.SOUTH);

		JLabel name_your_roll = new JLabel("If making a roll, name your roll");
		add(name_your_roll);
		
		name_of_roll = new JTextField();
		name_of_roll.setPreferredSize(new Dimension(200, 30));
		name_of_roll.setMaximumSize(new Dimension(200, 30));
		add(name_of_roll);
		
		JLabel gold_label = new JLabel("If gold, choose price");
		add(gold_label);
		
		JPanel price_slider_panel = new JPanel();
		price_slider_panel.setLayout(new GridLayout(3,1));
		price_slider = new JSlider(0, 5, 0);
		price_slider.setPreferredSize(new Dimension(100, 20));
			    
	    Hashtable<Integer, JLabel> price_table = new Hashtable<Integer, JLabel>();
	    price_table.put (0, new JLabel("$5"));
	    price_table.put (1, new JLabel("$6"));
	    price_table.put (2, new JLabel("$7"));
	    price_table.put (3, new JLabel("$8"));
	    price_table.put (4, new JLabel("$9"));
	    price_table.put (5, new JLabel("$10"));
	    
	    price_slider.setLabelTable (price_table);
		price_slider.setPaintLabels(true);
		price_slider_panel.add(price_slider);
		price_slider_panel.setVisible(true);
		add(price_slider_panel, BorderLayout.SOUTH);
		
		JLabel ready_label = new JLabel("Now create your sushi");
		add(ready_label);
		
		JButton create_button = new JButton("Create");
		create_button.setActionCommand("create");
		create_button.addActionListener(this);
		add(create_button);
	}

	public void registerChefListener(ChefViewListener cl) {
		listeners.add(cl);
	}

	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleRedPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleGreenPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleBluePlateRequest(plate_sushi, plate_position);
		}
	}
	
	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		for (ChefViewListener l : listeners) {
			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object selected_color = color_list.getSelectedItem();
		
		if (selected_color.toString().equals("Red")) {
			request_color = "Red";
		}else if (selected_color.toString().equals("Blue")) {
			request_color = "Blue";
		}else if (selected_color.toString().equals("Green")) {
			request_color = "Green";
		}else if (selected_color.toString().equals("Gold")) {
			request_color = "Gold";
		}
		
		int num_of_ingredients = 0;
		
		int selected_avocado = avocado_slider.getValue();
		
		if(selected_avocado == 0) {
			request_avocado = 0.0;
		}else if(selected_avocado == 1) {
			request_avocado = 0.5;
			num_of_ingredients++;
		}else if(selected_avocado == 2) {
			request_avocado = 1.0;
			num_of_ingredients++;
		}else if(selected_avocado == 3) {
			request_avocado = 1.5;
			num_of_ingredients++;
		}
		
		int selected_crab = crab_slider.getValue();
		
		if(selected_crab == 0) {
			request_crab = 0.0;
		}else if(selected_crab == 1) {
			request_crab = 0.5;
			num_of_ingredients++;
		}else if(selected_crab == 2) {
			request_crab = 1.0;
			num_of_ingredients++;
		}else if(selected_crab == 3) {
			request_crab = 1.5;
			num_of_ingredients++;
		}
		
		int selected_eel = (eel_slider.getValue());
		
		if(selected_eel == 0) {
			request_eel = 0.0;
		}else if(selected_eel == 1) {
			request_eel = 0.5;
			num_of_ingredients++;
		}else if(selected_eel == 2) {
			request_eel = 1.0;
			num_of_ingredients++;
		}else if(selected_eel == 3) {
			request_eel = 1.5;
			num_of_ingredients++;
		}
		
		int selected_tuna = tuna_slider.getValue();
		
		if(selected_tuna == 0) {
			request_tuna = 0.0;
		}else if(selected_tuna == 1) {
			request_tuna = 0.5;
			num_of_ingredients++;
		}else if(selected_tuna == 2) {
			request_tuna = 1.0;
			num_of_ingredients++;
		}else if(selected_tuna == 3) {
			request_tuna = 1.5;
			num_of_ingredients++;
		}
		
		int selected_shrimp = shrimp_slider.getValue();
		
		if(selected_shrimp == 0) {
			request_shrimp = 0.0;
		}else if(selected_shrimp == 1) {
			request_shrimp = 0.5;
			num_of_ingredients++;
		}else if(selected_shrimp == 2) {
			request_shrimp = 1.0;
			num_of_ingredients++;
		}else if(selected_shrimp == 3) {
			request_shrimp = 1.5;
			num_of_ingredients++;
		}
		
		int selected_yellowtail = (yellowtail_slider.getValue());
		
		if(selected_yellowtail == 0) {
			request_yellowtail = 0.0;
		}else if(selected_yellowtail == 1) {
			request_yellowtail = 0.5;
			num_of_ingredients++;
		}else if(selected_yellowtail == 2) {
			request_yellowtail = 1.0;
			num_of_ingredients++;
		}else if(selected_yellowtail == 3) {
			request_yellowtail = 1.5;
			num_of_ingredients++;
		}
		
		String selected_sushi = sushi_list.getSelectedItem().toString();
		
		if (selected_sushi.equals("Roll")) {

			IngredientPortion[] ing_array = new IngredientPortion[num_of_ingredients++]; 
			int index = 0;
			
			if(request_avocado > 0.0) {
				ing_array[index] = new AvocadoPortion(request_avocado);
				index++;
			}
			if(request_crab > 0.0) {
				ing_array[index] = new CrabPortion(request_crab);
				index++;
			}
			if(request_eel > 0.0) {
				ing_array[index] = new EelPortion(request_eel);
				index++;
			}
			if(request_shrimp > 0.0) {
				ing_array[index] = new ShrimpPortion(request_shrimp);
				index++;
			}
			if(request_tuna > 0.0) {
				ing_array[index] = new TunaPortion(request_tuna);
				index++;
			}
			if(request_yellowtail > 0.0) {
				ing_array[index] = new YellowtailPortion(request_yellowtail);
				index++;
			}
			
			String typed_name_of_roll = name_of_roll.getText();
			request_sushi = new Roll(typed_name_of_roll, ing_array); 
			
		} else if (selected_sushi.contains("Nigiri")) {
			
			if(nigiri_and_sashimi_list.getSelectedItem().equals("tuna")) {
				request_sushi = new Nigiri(Nigiri.NigiriType.TUNA);
			}
			if(nigiri_and_sashimi_list.getSelectedItem().equals("yellowtail")) {
				request_sushi = new Nigiri(Nigiri.NigiriType.YELLOWTAIL);
			}
			if(nigiri_and_sashimi_list.getSelectedItem().equals("eel")) {
				request_sushi = new Nigiri(Nigiri.NigiriType.EEL);
			}
			if(nigiri_and_sashimi_list.getSelectedItem().equals("crab")) {
				request_sushi = new Nigiri(Nigiri.NigiriType.CRAB);
			}
			if(nigiri_and_sashimi_list.getSelectedItem().equals("shrimp")) {
				request_sushi = new Nigiri(Nigiri.NigiriType.SHRIMP);
			}
		} else if (selected_sushi.contains("Sashimi")) {
			if(nigiri_and_sashimi_list.getSelectedItem().equals("tuna")) {
				request_sushi = new Sashimi(Sashimi.SashimiType.TUNA);
			}
			if(nigiri_and_sashimi_list.getSelectedItem().equals("yellowtail")) {
				request_sushi = new Sashimi(Sashimi.SashimiType.YELLOWTAIL);
			}
			if(nigiri_and_sashimi_list.getSelectedItem().equals("eel")) {
				request_sushi = new Sashimi(Sashimi.SashimiType.EEL);
			}
			if(nigiri_and_sashimi_list.getSelectedItem().equals("crab")) {
				request_sushi = new Sashimi(Sashimi.SashimiType.CRAB);
			}
			if(nigiri_and_sashimi_list.getSelectedItem().equals("shrimp")) {
				request_sushi = new Sashimi(Sashimi.SashimiType.SHRIMP);
			}
		}
		
		int selected_position = position_list.getSelectedIndex();

		for (int n = 0; n < belt_size; n++) { 
			if (selected_position - 1 == n) {
				request_position = n + 1;
				break;
			}
		}
		
		int selected_price = price_slider.getValue();
		if(selected_price == 0) {
			request_price = 5.00;
		}else if(selected_price == 1) {
			request_price = 6.00;
		}else if(selected_price == 2) {
			request_price = 7.00;
		}else if(selected_price == 3) {
			request_price = 8.00;
		}else if(selected_price == 4) {
			request_price = 9.00;
		}else if(selected_price == 5) {
			request_price = 10.00;
		}
		
		switch(e.getActionCommand()) {
		case "create":
			if(request_color.equals("Red")) {
				makeRedPlateRequest(request_sushi, request_position);
			}
			if(request_color.equals("Blue")) {
				makeBluePlateRequest(request_sushi, request_position);
			}
			if(request_color.equals("Green")) {
				makeGreenPlateRequest(request_sushi, request_position);
			}
			if(request_color.equals("Gold")) {
				makeGoldPlateRequest(request_sushi, request_position, request_price);
			}
			
			// reset JSliders and JComboBoxes
			price_slider.setValue(0);
			avocado_slider.setValue(0);
			eel_slider.setValue(0);
			yellowtail_slider.setValue(0);
			crab_slider.setValue(0);
			tuna_slider.setValue(0);
			shrimp_slider.setValue(0);
			
			sushi_list.setSelectedIndex(0);
			position_list.setSelectedIndex(0);
			color_list.setSelectedIndex(0);
			nigiri_and_sashimi_list.setSelectedIndex(0);
			
			name_of_roll.setText("");
		}
	
	}
}
