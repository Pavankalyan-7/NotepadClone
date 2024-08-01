package notepad;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class About extends JFrame implements ActionListener {

	About() {
//		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		setTitle("About Notepad");
		setBounds(400, 100, 600, 500);
		ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("./icons/windows.png"));
		Image scaledImage = image.getImage().getScaledInstance(300, 60, Image.SCALE_DEFAULT);
		ImageIcon finalImage = new ImageIcon(scaledImage);
		
		JLabel windowIcon = new JLabel(finalImage);
		windowIcon.setBounds(70, 40, 400, 80);
		add(windowIcon);
		
		
		ImageIcon notepadImage = new ImageIcon(ClassLoader.getSystemResource("./icons/notepadicon.png"));
		Image scaledNotepadImage = notepadImage.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
		ImageIcon finalNotepadImage = new ImageIcon(scaledNotepadImage);
		
		JLabel notepadIcon = new JLabel(finalNotepadImage);
		notepadIcon.setBounds(50, 180, 70, 70);
		add(notepadIcon);
		
		
		JLabel text = new JLabel("<html> Microsoft Windows<br>Version 0.1.0 (OS Build 109RT3E) <br> Code with Java. All rights reserved <html>");
		text.setBounds(150, 100, 500, 200);
		text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		add(text);
		
		
		JButton ok = new JButton("OK");
		ok.setBounds(350, 350, 120, 25);
		ok.addActionListener(this);
		add(ok);
		
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	public static void main(String[] args) {
		new About();
	}

}
