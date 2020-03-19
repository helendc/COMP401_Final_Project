package sushigame.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import comp401sushi.Plate;
import comp401sushi.Roll;
import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;

public class PlateViewWidget extends JPanel implements BeltObserver, ActionListener{
	private Belt belt;
	//private JLabel[] sushi_labels;
	private JButton[] sushi_labels;
	private String label_string, name, chef, ingredients, age;
	private String[] ing_array;
	
	// constructor 
	public PlateViewWidget(Belt belt) {
		this.belt = belt;
		belt.registerBeltObserver(this);
		ing_array = new String[belt.getSize()];
		
		setLayout(new GridLayout(10, 2, 1, 1));
		sushi_labels = new JButton[belt.getSize()];
		label_string = "";

		for (int i = 0; i < belt.getSize(); i++) {
			
			JButton sushi_label = new JButton("");
			sushi_label.setMinimumSize(new Dimension(300, 90));
			sushi_label.setPreferredSize(new Dimension(300, 90));
		
			sushi_label.setBackground(Color.GRAY);
			
			sushi_label.setActionCommand("sushi position " + i);
			
			sushi_label.addActionListener(this);
			
			sushi_labels[i] = sushi_label;
			
		}
		for (int i = 0; i < belt.getSize() / 2; i++) {
			add(sushi_labels[i]);
			add(sushi_labels[i + 10]);
		}
		refresh();
	}
	
	@Override
	public void handleBeltEvent(BeltEvent e) {
		refresh();
	}
	
	public void refresh() {
		for (int i=0; i<belt.getSize(); i++) {
			
			Plate p = belt.getPlateAtPosition(i);
			JButton sushi_label = sushi_labels[i];
			
			if (p == null) {
			
				sushi_label.setText("Empty Plate");
				sushi_label.setBackground(Color.GRAY);

			} else {
				
				switch (p.getColor()) {
				case RED:
					sushi_label.setBackground(Color.RED); break;
				case GREEN:
					sushi_label.setBackground(Color.GREEN); break;
				case BLUE:
					sushi_label.setBackground(Color.BLUE); break;
				case GOLD:
					sushi_label.setBackground(Color.YELLOW); break;
				}
				
					if(p.getContents() instanceof Roll)	{
						
						String ing_string = "";
						DecimalFormat df = new DecimalFormat("#.##");
						
						for (int j = 0; j < p.getContents().getIngredients().length; j++) {
							ing_string += " " + df.format(p.getContents().getIngredients()[j].getAmount()) + " oz " +
									p.getContents().getIngredients()[j].getName() + "\n";
						}
						ingredients = ing_string;
					} else {
						ingredients = "";
					}
				
				
				name = p.getContents().getName();
				if(name.equals("")) { name = "Player's Special";}
				chef = "Made by " + p.getChef().getName();				
				age = "Age: " + belt.getAgeOfPlateAtPosition(i);
				label_string = name + "\n" + chef + "\n";
				label_string += ingredients + age;
				ing_array[i] = label_string;
				
				String display = "<html>" + name + "." + "\n" + " Click to see more sushi info!" + "</html>";
				sushi_label.setText(display);
				sushi_label.setHorizontalAlignment(SwingConstants.CENTER);
				
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case ("sushi position 0") :
			if (belt.getPlateAtPosition(0) == null) {
				JOptionPane.showMessageDialog(null, "no sushi here"); break;
			}
			JOptionPane.showMessageDialog(null, ing_array[0]); break;
		case ("sushi position 1") :
			if (belt.getPlateAtPosition(1) == null) {
				JOptionPane.showMessageDialog(null, "no sushi here"); break;
			}
			JOptionPane.showMessageDialog(null, ing_array[1]); break;
		case ("sushi position 2") :
			if (belt.getPlateAtPosition(2) == null) {
				JOptionPane.showMessageDialog(null, "no sushi here"); break;
			}
			JOptionPane.showMessageDialog(null, ing_array[2]); break;
		case ("sushi position 3") :
			if (belt.getPlateAtPosition(3) == null) {
				JOptionPane.showMessageDialog(null, "no sushi here"); break;
			}
			JOptionPane.showMessageDialog(null, ing_array[3]); break;
		case ("sushi position 4") :
			if (belt.getPlateAtPosition(4) == null) {
				JOptionPane.showMessageDialog(null, "no sushi here"); break;
			}
			JOptionPane.showMessageDialog(null, ing_array[4]); break;
		case ("sushi position 5") :
			if (belt.getPlateAtPosition(5) == null) {
				JOptionPane.showMessageDialog(null, "no sushi here"); break;
			}
			JOptionPane.showMessageDialog(null, ing_array[5]); break;
		case ("sushi position 6") :
			if (belt.getPlateAtPosition(6) == null) {
				JOptionPane.showMessageDialog(null, "no sushi here"); break;
			}
			JOptionPane.showMessageDialog(null, ing_array[6]); break;
		case ("sushi position 7") :
			if (belt.getPlateAtPosition(7) == null) {
				JOptionPane.showMessageDialog(null, "no sushi here"); break;
			}
			JOptionPane.showMessageDialog(null, ing_array[7]); break;
		case ("sushi position 8") :
			if (belt.getPlateAtPosition(8) == null) {
				JOptionPane.showMessageDialog(null, "no sushi here"); break;
			}
			JOptionPane.showMessageDialog(null, ing_array[8]); break;
		case ("sushi position 9") :
			if (belt.getPlateAtPosition(9) == null) {
				JOptionPane.showMessageDialog(null, "no sushi here"); break;
			}
			JOptionPane.showMessageDialog(null, ing_array[9]); break;
		case ("sushi position 10") :
			if (belt.getPlateAtPosition(10) == null) {
				JOptionPane.showMessageDialog(null, "no sushi here"); break;
			}
			JOptionPane.showMessageDialog(null, ing_array[10]); break;
		case ("sushi position 11") :
			if (belt.getPlateAtPosition(11) == null) {
				JOptionPane.showMessageDialog(null, "no sushi here"); break;
			}
			JOptionPane.showMessageDialog(null, ing_array[11]); break;
		case ("sushi position 12") :
			if (belt.getPlateAtPosition(12) == null) {
				JOptionPane.showMessageDialog(null, "no sushi here"); break;
			}
			JOptionPane.showMessageDialog(null, ing_array[12]); break;
		case ("sushi position 13") :
			if (belt.getPlateAtPosition(13) == null) {
				JOptionPane.showMessageDialog(null, "no sushi here"); break;
			}
			JOptionPane.showMessageDialog(null, ing_array[13]); break;
		case ("sushi position 14") :
			if (belt.getPlateAtPosition(14) == null) {
				JOptionPane.showMessageDialog(null, "no sushi here"); break;
			}
			JOptionPane.showMessageDialog(null, ing_array[14]); break;
		case ("sushi position 15") :
			if (belt.getPlateAtPosition(15) == null) {
				JOptionPane.showMessageDialog(null, "no sushi here"); break;
			}
			JOptionPane.showMessageDialog(null, ing_array[15]); break;
		case ("sushi position 16") :
			if (belt.getPlateAtPosition(16) == null) {
				JOptionPane.showMessageDialog(null, "no sushi here"); break;
			}
			JOptionPane.showMessageDialog(null, ing_array[16]); break;
		case ("sushi position 17") :
			if (belt.getPlateAtPosition(17) == null) {
				JOptionPane.showMessageDialog(null, "no sushi here"); break;
			}
			JOptionPane.showMessageDialog(null, ing_array[17]); break;
		case ("sushi position 18") :
			if (belt.getPlateAtPosition(18) == null) {
				JOptionPane.showMessageDialog(null, "no sushi here"); break;
			}
			JOptionPane.showMessageDialog(null, ing_array[18]); break;
		case ("sushi position 19") :
			if (belt.getPlateAtPosition(19) == null) {
				JOptionPane.showMessageDialog(null, "no sushi here"); break;
			}
			JOptionPane.showMessageDialog(null, ing_array[19]); break;
		}
		
	}
	
}
