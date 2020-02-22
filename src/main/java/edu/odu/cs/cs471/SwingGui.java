package edu.odu.cs.cs471;

import java.awt.EventQueue;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SwingGui {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingGui window = new SwingGui();
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
	public SwingGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		randomNumGen a = new randomNumGen();
		
		/**
		 * Used to input into JLists
		 */
		DefaultListModel <Pair<Integer, String>> ready = new DefaultListModel<Pair<Integer, String>>();
		DefaultListModel <Pair<Integer, String>> run = new DefaultListModel<Pair<Integer, String>>();
		DefaultListModel <Pair<Integer, String>> blocked = new DefaultListModel<Pair<Integer, String>>();		
		
		/**
		 * JLabels
		 */
		JLabel lblReadyQueue = new JLabel("Ready Queue");
		lblReadyQueue.setBounds(38, 24, 102, 16);
		frame.getContentPane().add(lblReadyQueue);
		
		JLabel lblRunning = new JLabel("Running");
		lblRunning.setBounds(355, 24, 56, 16);
		frame.getContentPane().add(lblRunning);
		
		JLabel lblBlocked = new JLabel("Blocked");
		lblBlocked.setBounds(49, 223, 56, 16);
		frame.getContentPane().add(lblBlocked);
		
		/**
		 * JLists
		 */
		JList <Pair<Integer, String>> ReadyQ = new JList <Pair<Integer, String>>(ready);
		ReadyQ.setBounds(39, 43, 243, 157);
		frame.getContentPane().add(ReadyQ);
		
		JList <Pair<Integer, String>> Running = new JList <Pair<Integer, String>>(run);
		Running.setBounds(355, 43, 243, 157);
		frame.getContentPane().add(Running);
		
		JList <Pair<Integer, String>> Blocked = new JList <Pair<Integer, String>>(blocked);
		Blocked.setBounds(39, 247, 243, 157);
		frame.getContentPane().add(Blocked);
		
		/**
		 * textfield
		 */
		textField = new JTextField();
		textField.setBounds(38, 477, 142, 43);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	
		
		/**
		 * Add Process Button
		 */
		JButton AddProcess = new JButton("Add Process");
		AddProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				


				Pair Process = new Pair<Integer, String>(a.Priority(),textField.getText());
				//TODO add to ReadyQueue
				ready.addElement(Process);
				a.bubbleSort(ready);
				
				textField.setText("");
			}
		});
		AddProcess.setBounds(205, 477, 125, 43);
		frame.getContentPane().add(AddProcess);
		
		
		/**
		 * Time Slice Button
		 */
		JButton btnTimeSlice = new JButton("TimeSlice");
		btnTimeSlice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!run.isEmpty()) {
					Pair Process1 = new Pair<Integer, String>
						(run.firstElement().getKey(), run.firstElement().getValue());
					run.clear();
					
					Pair Process2 = new Pair<Integer, String>
						(ready.firstElement().getKey(), ready.firstElement().getValue());

					ready.remove(0);
					ready.addElement(Process1);
					run.addElement(Process2);
					a.bubbleSort(ready);
				}
			}
		});
		btnTimeSlice.setBounds(579, 479, 125, 39);
		frame.getContentPane().add(btnTimeSlice);
		
		
		/**
		 * Terminate Button
		 */
		JButton btnTerminate = new JButton("Terminate");
		btnTerminate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blocked.remove(0);// remove first element in readylist
			}
		});
		btnTerminate.setBounds(579, 416, 125, 39);
		frame.getContentPane().add(btnTerminate);
		
		
		/**
		 * Block Button
		 */
		JButton btnBlock = new JButton("Block");
		btnBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pair Process = new Pair<Integer, String>
				(run.firstElement().getKey(), run.firstElement().getValue());

				blocked.addElement(Process);
				run.clear();
				
			}
		});
		btnBlock.setBounds(579, 362, 125, 39);
		frame.getContentPane().add(btnBlock);
		
		
		/**
		 * Run Button
		 */
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (run.isEmpty()) {
					
					Pair Process = new Pair<Integer, String>
						(ready.firstElement().getKey(), ready.firstElement().getValue());
					

					run.addElement(Process);
					ready.remove(0);
				}
			}
		});
		btnRun.setBounds(579, 304, 125, 39);
		frame.getContentPane().add(btnRun);
		
		
		/**
		 * Ready Button
		 */
		JButton btnReady = new JButton("Ready");
		btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Pair Process = new Pair<Integer, String>
					(blocked.firstElement().getKey(), blocked.firstElement().getValue());
				
				ready.addElement(Process);
				blocked.remove(0);
				a.bubbleSort(ready);
			}
		});
		btnReady.setBounds(579, 247, 125, 39);
		frame.getContentPane().add(btnReady);
		
		JLabel lblNewLabel = new JLabel("Priority = Process");
		lblNewLabel.setBounds(355, 272, 125, 83);
		frame.getContentPane().add(lblNewLabel);
		
	}//end initialize()
}
