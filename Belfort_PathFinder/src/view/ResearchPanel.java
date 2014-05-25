package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ResearchPanel extends JPanel {

	private Box box_Definition;
	private Box box_Definition1;
	private Box box_Definition_Label;
	private Box box_Definition_Label1;
	private Box box_Definition_Text;
	private Box box_Definition_Text1;

	public ResearchPanel() {

		super(new BorderLayout(1, 1));
		setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		setBackground(Color.gray);
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();

		CustomSplitPane customSplitPane = new CustomSplitPane(false);
		customSplitPane.setSplit(.2);
		customSplitPane.setComponentOne(topPanel);

		box_Definition = Box.createHorizontalBox();
		box_Definition1 = Box.createHorizontalBox();
		box_Definition_Label = Box.createVerticalBox();

		JLabel er = new JLabel("Départ :");
		box_Definition_Label.add(er);
		box_Definition_Label.add(Box.createVerticalStrut(20));

		box_Definition_Label1 = Box.createVerticalBox();
		JLabel et = new JLabel("Arrivée :");

		Font font = new Font("Arial", Font.BOLD, 12);
		er.setFont(font);
		et.setFont(font);
		box_Definition_Label1.add(et);

		box_Definition_Label.setPreferredSize(new Dimension(100,
				box_Definition_Label.getPreferredSize().height));
		box_Definition.add(box_Definition_Label);
		box_Definition_Label1.setPreferredSize(new Dimension(100,
				box_Definition_Label1.getPreferredSize().height));
		box_Definition1.add(box_Definition_Label1);

		box_Definition_Text = Box.createVerticalBox();
		box_Definition_Text1 = Box.createVerticalBox();

		JComboBox c1 = new JComboBox();
		box_Definition_Text.add(c1);
		JComboBox c2 = new JComboBox();
		box_Definition_Text.add(c1);
		box_Definition_Text.add(Box.createVerticalStrut(20));

		box_Definition_Text1.add(c2);

		JButton btn = new JButton("Rechercher");

		box_Definition_Text.setPreferredSize(new Dimension(115,
				box_Definition_Text.getPreferredSize().height));
		box_Definition_Text1.setPreferredSize(new Dimension(115,
				box_Definition_Text1.getPreferredSize().height));

		box_Definition.add(box_Definition_Text);
		box_Definition1.add(box_Definition_Text1);

		topPanel.add(box_Definition);
		topPanel.add(box_Definition1);
		topPanel.add(btn);

		customSplitPane.setComponentTwo(bottomPanel);

		JTextArea view = new JTextArea("bonjour");
		view.setPreferredSize(new Dimension(250, 500));
		view.setFocusable(false);
		view.setBackground(Color.white);
		view.setLineWrap(true);
		view.setWrapStyleWord(true);

		JLabel msg = new JLabel("Feuille de route");
		msg.setFont(font);
		bottomPanel.add(msg);
		bottomPanel.add(view);
		add(customSplitPane, BorderLayout.CENTER);

	}

}
