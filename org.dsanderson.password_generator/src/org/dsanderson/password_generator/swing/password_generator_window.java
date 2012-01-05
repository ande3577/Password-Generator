package org.dsanderson.password_generator.swing;

import java.awt.Dialog;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.Popup;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.dsanderson.password_generator.core.*;

public class password_generator_window {

	private JFrame frame;
	private JTextField passwordResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					password_generator_window window = new password_generator_window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public password_generator_window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), }, new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblNewLabel_1 = new JLabel("Enabled");
		frame.getContentPane().add(lblNewLabel_1, "2, 1");

		JLabel lblNewLabel_2 = new JLabel("Weight");
		frame.getContentPane().add(lblNewLabel_2, "4, 1");

		JCheckBox upperCaseEnable = new JCheckBox("Upper Case");
		upperCaseEnable.setSelected(true);
		frame.getContentPane().add(upperCaseEnable, "2, 2");

		JFormattedTextField upperCaseWeight = new JFormattedTextField();
		upperCaseWeight.setText("4");
		frame.getContentPane().add(upperCaseWeight, "4, 2, fill, default");

		JCheckBox lowerCaseEnable = new JCheckBox("Lower Case");
		lowerCaseEnable.setSelected(true);
		lowerCaseEnable.setHorizontalAlignment(SwingConstants.TRAILING);
		frame.getContentPane().add(lowerCaseEnable, "2, 4");

		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setText("4");
		frame.getContentPane().add(formattedTextField, "4, 4, fill, default");

		JCheckBox numberEnable = new JCheckBox("Numbers");
		numberEnable.setSelected(true);
		frame.getContentPane().add(numberEnable, "2, 6");

		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setText("4");
		frame.getContentPane().add(formattedTextField_1, "4, 6, fill, default");

		JCheckBox specialCharacterEnable = new JCheckBox("Special");
		frame.getContentPane().add(specialCharacterEnable, "2, 8");

		JFormattedTextField formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setText("1");
		frame.getContentPane().add(formattedTextField_2, "4, 8, fill, default");

		JLabel lblLength = new JLabel("Length");
		frame.getContentPane().add(lblLength, "2, 10, right, default");

		JFormattedTextField formattedTextField_3 = new JFormattedTextField();
		formattedTextField_3.setText("10");
		frame.getContentPane()
				.add(formattedTextField_3, "4, 10, fill, default");

		JLabel lblNewLabel = new JLabel("Result");
		frame.getContentPane().add(lblNewLabel, "2, 12");

		passwordResult = new JTextField();
		passwordResult.setEditable(false);
		frame.getContentPane().add(passwordResult, "4, 12, fill, default");
		passwordResult.setColumns(10);

		JButton generateButton = new JButton("Generate");
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PasswordGenerator passwordGenerator = new PasswordGenerator(10,
						true, 4, true, 4, true, 4, true, 1);

				passwordResult.setText(passwordGenerator.GeneratePassword());
			}
		});
		frame.getContentPane().add(generateButton, "4, 14");
	}

}
