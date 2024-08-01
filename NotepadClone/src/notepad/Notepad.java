package notepad;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;


public class Notepad extends JFrame implements ActionListener {
	
	JMenuItem nNew, open, print, save, cut, copy, paste, selectAll, exit, help;
	JTextArea area;
	String text;
	public Notepad() {
		setLayout(new BorderLayout());
		getContentPane().setBackground(Color.WHITE);
		setTitle("Notepad");
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("./icons/notepadicon.png"));
		Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
//		ImageIcon i3 = new ImageIcon(i3);
		setIconImage(i2);
		
		JMenuBar mb = new JMenuBar();

		mb.setBackground(Color.WHITE);
		
		JMenu file = new JMenu("File");
		file.setFont(new Font("Ariel", Font.PLAIN, 14));
		mb.add(file);
		
		
		
		
		//including Mnemonics functionality
		nNew = new JMenuItem("New");
		nNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		nNew.setFont(new Font("Ariel", Font.PLAIN, 12));
		nNew.addActionListener(this);
		file.add(nNew);
		
		open = new JMenuItem("Open");
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		open.setFont(new Font("Ariel", Font.PLAIN, 12));
		open.addActionListener(this);
		file.add(open);
		
		save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		save.setFont(new Font("Ariel", Font.PLAIN, 12));
		save.addActionListener(this);
		file.add(save);
		
		
		print = new JMenuItem("Print");
		print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		print.setFont(new Font("Ariel", Font.PLAIN, 12));
		print.addActionListener(this);
		file.add(print);
		
		
		exit = new JMenuItem("Exit");
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.CTRL_MASK));
		exit.setFont(new Font("Ariel", Font.PLAIN, 12));
		exit.addActionListener(this);
		file.add(exit);

		
		
		JMenu edit = new JMenu("Edit");
		edit.setFont(new Font("Ariel", Font.PLAIN, 14));
		mb.add(edit);
		
		
		cut = new JMenuItem("Cut");
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		cut.setFont(new Font("Ariel", Font.PLAIN, 12));
		cut.addActionListener(this);
		edit.add(cut);
		
		
		copy = new JMenuItem("Copy");
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		copy.setFont(new Font("Ariel", Font.PLAIN, 12));
		copy.addActionListener(this);
		edit.add(copy);
		
		paste = new JMenuItem("Paste");
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		paste.setFont(new Font("Ariel", Font.PLAIN, 12));
		paste.addActionListener(this);
		edit.add(paste);
		
		selectAll = new JMenuItem("Select All");
		selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		selectAll.setFont(new Font("Ariel", Font.PLAIN, 12));
		selectAll.addActionListener(this);
		edit.add(selectAll);
		
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setFont(new Font("Ariel", Font.PLAIN, 14));
		mb.add(helpMenu);
		
		
		help = new JMenuItem("About");
		help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		help.setFont(new Font("Ariel", Font.PLAIN, 12));
		help.addActionListener(this);
		helpMenu.add(help);
		
		setJMenuBar(mb);
		
		area = new JTextArea();
		area.setFont(new Font("sans-serif", Font.PLAIN, 18));
		area.setLineWrap(true); //line wrap 
		area.setWrapStyleWord(true); //word wrap
		
		JScrollPane scrollPane = new JScrollPane(area); // adding area inside pane
		scrollPane.setBorder(BorderFactory.createEmptyBorder()); // setting NO border
		add(scrollPane); // adding pane over Frame
		
		
		
		//to set the complete screen
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("New")) {
			new Notepad().setVisible(true);
		} else if(ae.getActionCommand().equals("Open")) {
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter restrictFiles = new FileNameExtensionFilter("Only .txt files", "txt");
			fileChooser.addChoosableFileFilter(restrictFiles);
			
			int action = fileChooser.showOpenDialog(this);
			
			if(action != JFileChooser.APPROVE_OPTION) {
				return;
			} 
			
			File selectedFile = fileChooser.getSelectedFile();
			
			try {
				BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
				area.read(reader, null);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			
			
		} else if(ae.getActionCommand().equals("Save")) {
			JFileChooser saveFile = new JFileChooser();
			saveFile.setApproveButtonText("Save");
			
			int action = saveFile.showOpenDialog(this);
			
			if(action != JFileChooser.APPROVE_OPTION) {
				return;
			}
			
			File savedFile = new File(saveFile.getSelectedFile() + ".txt");
			BufferedWriter writer = null;
			try {

				writer = new BufferedWriter(new FileWriter(savedFile));
				area.write(writer);
				
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		} else if(ae.getActionCommand().equals("Print")) {
			
			try {
				area.print();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			
		} else if(ae.getActionCommand().equals("Exit")) {
			
			System.exit(0);
			
		} else if(ae.getActionCommand().equals("Cut")) {
			text = area.getSelectedText();
			area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
			
		} else if(ae.getActionCommand().equals("Copy")) {
			
			text = area.getSelectedText();
			
		} else if(ae.getActionCommand().equals("Paste")) {
			
			area.insert(text, area.getCaretPosition());
			
		} else if(ae.getActionCommand().equals("Select All")) {
			area.selectAll();
		} else if(ae.getActionCommand().equals("About")) {
			new About().setVisible(true);
		}
		
		
	}
	
    public static void main(String[] args) {
        new Notepad();
    }
}