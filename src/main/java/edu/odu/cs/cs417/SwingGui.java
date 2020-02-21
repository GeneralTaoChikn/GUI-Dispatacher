package edu.odu.cs.cs417;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
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
		JList <String> ReadyQ = new JList <String>();
		ReadyQ.setBounds(39, 43, 243, 157);
		frame.getContentPane().add(ReadyQ);
		
		JList <String> Running = new JList <String>();
		Running.setBounds(355, 43, 243, 157);
		frame.getContentPane().add(Running);
		
		JList <String> Blocked = new JList <String>();
		Blocked.setBounds(39, 247, 243, 157);
		frame.getContentPane().add(Blocked);
		
		/**
		 * textfield
		 */
		textField = new JTextField();
		textField.setBounds(38, 477, 142, 43);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	
		
		
		JButton AddProcess = new JButton("Add Process");
		AddProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String process = textField.getText();
				ReadyQ(process);
				
			}
		});
		AddProcess.setBounds(205, 477, 125, 43);
		frame.getContentPane().add(AddProcess);
		
		JButton btnTimeSlice = new JButton("TimeSlice");
		btnTimeSlice.setBounds(579, 479, 125, 39);
		frame.getContentPane().add(btnTimeSlice);
		
		JButton btnTerminate = new JButton("Terminate");
		btnTerminate.setBounds(579, 416, 125, 39);
		frame.getContentPane().add(btnTerminate);
		
		JButton btnBlock = new JButton("Block");
		btnBlock.setBounds(579, 362, 125, 39);
		frame.getContentPane().add(btnBlock);
		
		JButton btnRun = new JButton("Run");
		btnRun.setBounds(579, 304, 125, 39);
		frame.getContentPane().add(btnRun);
		
		JButton btnReady = new JButton("Ready");
		btnReady.setBounds(579, 247, 125, 39);
		frame.getContentPane().add(btnReady);
		

	}
}
