package org.dsanderson.password_generator.swing;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.text.NumberFormatter;

import org.dsanderson.password_generator.core.PasswordGenerator;
import org.dsanderson.password_generator.core.UserSettings;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import javax.swing.SwingConstants;

public class PasswordGeneratorSwing implements ClipboardOwner {

	private JFrame frame;
	private JTextField txtPasswordResult;
	private JFormattedTextField txtLength;
	private JTextField txtKeyword;
	private JMenuItem mntmCopyMenuItem;

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

	public void lostOwnership(Clipboard clipboard, Transferable transferable) {
		// do nothing
	}

	public void setClipboardContents(String clipboardText) {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection clipboardTextSelection = new StringSelection(
				clipboardText);
		clipboard.setContents(clipboardTextSelection, this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(
				new MigLayout("", "[][][grow]", "[][][][][][][][][]"));

		JLabel lblNewLabel = new JLabel("Enable");
		frame.getContentPane().add(lblNewLabel, "cell 0 0");

		JLabel lblNewLabel_1 = new JLabel("Weight");
		frame.getContentPane().add(lblNewLabel_1, "cell 2 0");

		final JCheckBox chckbxUpperCaseEnable = new JCheckBox("Upper Case");
		chckbxUpperCaseEnable.setSelected(true);
		frame.getContentPane().add(chckbxUpperCaseEnable, "cell 0 1");

		final JFormattedTextField txtUpperCaseWeight = new JFormattedTextField(
				new NumberFormatter());
		txtUpperCaseWeight.setText("1");
		frame.getContentPane().add(txtUpperCaseWeight, "cell 2 1,growx");

		final JCheckBox chckbxLowerCaseEnable = new JCheckBox("Lower Case");
		chckbxLowerCaseEnable.setSelected(true);
		frame.getContentPane().add(chckbxLowerCaseEnable, "cell 0 2");

		final JFormattedTextField txtLowerCaseWeight = new JFormattedTextField(
				new NumberFormatter());
		txtLowerCaseWeight.setText("1");
		frame.getContentPane().add(txtLowerCaseWeight, "cell 2 2,growx");

		final JCheckBox chckbxNumberEnable = new JCheckBox("Numbers");
		chckbxNumberEnable.setSelected(true);
		frame.getContentPane().add(chckbxNumberEnable, "cell 0 3");

		final JFormattedTextField txtNumberWeight = new JFormattedTextField(
				new NumberFormatter());
		txtNumberWeight.setText("1");
		frame.getContentPane().add(txtNumberWeight, "cell 2 3,growx");

		final JCheckBox chckbxSpecialCharEnable = new JCheckBox("Special Chars");
		frame.getContentPane().add(chckbxSpecialCharEnable, "cell 0 4");

		final JFormattedTextField txtSpecialCharWeight = new JFormattedTextField(
				new NumberFormatter());
		txtSpecialCharWeight.setText("1");
		frame.getContentPane().add(txtSpecialCharWeight, "cell 2 4,growx");

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				UserSettings settings = UserSettings.getInstance();

				settings.passwordLength = Integer.parseInt(txtLength.getText());

				settings.upperCaseEnabled = chckbxUpperCaseEnable.isSelected();
				settings.upperCaseWeight = Integer.parseInt(txtUpperCaseWeight
						.getText());

				settings.lowerCaseEnabled = chckbxLowerCaseEnable.isSelected();
				settings.lowerCaseWeight = Integer.parseInt(txtLowerCaseWeight
						.getText());

				settings.numericEnabled = chckbxNumberEnable.isSelected();
				settings.numericWeight = Integer.parseInt(txtNumberWeight
						.getText());

				settings.specialEnabled = chckbxSpecialCharEnable.isSelected();
				settings.specialWeight = Integer.parseInt(txtSpecialCharWeight
						.getText());

				settings.keyword = txtKeyword.getText();

				PasswordGenerator passwordGenerator = new PasswordGenerator();

				txtPasswordResult.setText(passwordGenerator.GeneratePassword());
				mntmCopyMenuItem.setEnabled(true);
			}
		});

		JLabel lblKeyword = new JLabel("Keyword");
		frame.getContentPane().add(lblKeyword, "cell 0 5,alignx trailing");

		txtKeyword = new JTextField();
		frame.getContentPane().add(txtKeyword, "cell 2 5,growx");
		txtKeyword.setColumns(10);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(txtKeyword, popupMenu);

		JMenuItem mntmCreateRandom = new JMenuItem("Create Random");
		mntmCreateRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtKeyword.setText("HelloWorld");
			}
		});
		popupMenu.add(mntmCreateRandom);

		JMenuItem mntmClear = new JMenuItem("Clear");
		mntmClear.setActionCommand("Clear");
		mntmClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtKeyword.setText("");
			}
		});
		popupMenu.add(mntmClear);

		final JLabel lblLength = new JLabel("Length");
		frame.getContentPane().add(lblLength, "cell 0 6,alignx trailing");

		txtLength = new JFormattedTextField(new NumberFormatter());
		txtLength.setText("10");
		frame.getContentPane().add(txtLength, "cell 2 6,growx");

		final JLabel lblResult = new JLabel("Result");
		frame.getContentPane().add(lblResult, "cell 0 7,alignx trailing");

		txtPasswordResult = new JTextField();
		txtPasswordResult.setEditable(false);
		frame.getContentPane().add(txtPasswordResult, "cell 2 7,growx");
		txtPasswordResult.setColumns(10);

		JPopupMenu resultPopupMenu = new JPopupMenu();
		addPopup(txtPasswordResult, resultPopupMenu);

		mntmCopyMenuItem = new JMenuItem("Copy");
		mntmCopyMenuItem.setEnabled(false);
		mntmCopyMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setClipboardContents(txtPasswordResult.getText());
			}
		});
		resultPopupMenu.add(mntmCopyMenuItem);
		frame.getContentPane().add(btnGenerate, "cell 2 8");
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
