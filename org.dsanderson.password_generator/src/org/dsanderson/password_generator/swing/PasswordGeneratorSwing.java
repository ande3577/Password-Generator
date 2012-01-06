package org.dsanderson.password_generator.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.text.NumberFormatter;

import org.dsanderson.password_generator.core.PasswordGenerator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PasswordGeneratorSwing {

	private JFrame frame;
	private JTextField txtPasswordResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordGeneratorSwing window = new PasswordGeneratorSwing();
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
	public PasswordGeneratorSwing() {
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
				new MigLayout("", "[][grow]", "[][][][][][][][]"));

		JLabel lblNewLabel = new JLabel("Enable");
		frame.getContentPane().add(lblNewLabel, "cell 0 0");

		JLabel lblNewLabel_1 = new JLabel("Weight");
		frame.getContentPane().add(lblNewLabel_1, "cell 1 0");

		final JCheckBox chckbxUpperCaseEnable = new JCheckBox("Upper Case");
		chckbxUpperCaseEnable.setSelected(true);
		frame.getContentPane().add(chckbxUpperCaseEnable, "cell 0 1");

		final JFormattedTextField txtUpperCaseWeight = new JFormattedTextField(new NumberFormatter());
		txtUpperCaseWeight.setText("1");
		frame.getContentPane().add(txtUpperCaseWeight, "cell 1 1,growx");

		final JCheckBox chckbxLowerCaseEnable = new JCheckBox("Lower Case");
		chckbxLowerCaseEnable.setSelected(true);
		frame.getContentPane().add(chckbxLowerCaseEnable, "cell 0 2");

		final JFormattedTextField txtLowerCaseWeight = new JFormattedTextField(new NumberFormatter());
		txtLowerCaseWeight.setText("1");
		frame.getContentPane().add(txtLowerCaseWeight, "cell 1 2,growx");

		final JCheckBox chckbxNumberEnable = new JCheckBox("Numbers");
		chckbxNumberEnable.setSelected(true);
		frame.getContentPane().add(chckbxNumberEnable, "cell 0 3");

		final JFormattedTextField txtNumberWeight = new JFormattedTextField(new NumberFormatter());
		txtNumberWeight.setText("1");
		frame.getContentPane().add(txtNumberWeight, "cell 1 3,growx");

		final JCheckBox chckbxSpecialCharEnable = new JCheckBox("Special Chars");
		chckbxSpecialCharEnable.setSelected(true);
		frame.getContentPane().add(chckbxSpecialCharEnable, "cell 0 4");

		final JFormattedTextField txtSpecialCharWeight = new JFormattedTextField(new NumberFormatter());
		txtSpecialCharWeight.setText("1");
		frame.getContentPane().add(txtSpecialCharWeight, "cell 1 4,growx");

		final JLabel lblLength = new JLabel("Length");
		frame.getContentPane().add(lblLength, "cell 0 5,alignx trailing");

		final JFormattedTextField txtLength = new JFormattedTextField(new NumberFormatter());
		txtLength.setText("12");
		frame.getContentPane().add(txtLength, "cell 1 5,growx");

		final JLabel lblResult = new JLabel("Result");
		frame.getContentPane().add(lblResult, "cell 0 6,alignx trailing");

		txtPasswordResult = new JTextField();
		txtPasswordResult.setEditable(false);
		frame.getContentPane().add(txtPasswordResult, "cell 1 6,growx");
		txtPasswordResult.setColumns(10);

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int length = Integer.parseInt(txtLength.getText());

				Boolean upperCaseEnable = chckbxUpperCaseEnable.isSelected();
				int upperCaseWeight = Integer.parseInt(txtUpperCaseWeight
						.getText());

				Boolean lowerCaseEnable = chckbxLowerCaseEnable.isSelected();
				int lowerCaseWeight = Integer.parseInt(txtLowerCaseWeight
						.getText());

				Boolean numberEnable = chckbxNumberEnable.isSelected();
				int numberWeight = Integer.parseInt(txtNumberWeight.getText());

				Boolean specialEnable = chckbxSpecialCharEnable.isSelected();
				int specialWeight = Integer.parseInt(txtSpecialCharWeight
						.getText());

				PasswordGenerator passwordGenerator = new PasswordGenerator(
						length, upperCaseEnable, upperCaseWeight,
						lowerCaseEnable, lowerCaseWeight, numberEnable,
						numberWeight, specialEnable, specialWeight);

				txtPasswordResult.setText(passwordGenerator.GeneratePassword());
			}
		});
		frame.getContentPane().add(btnGenerate, "cell 1 7");
	}

}
