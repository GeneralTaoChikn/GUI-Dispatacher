package edu.odu.cs.cs417;

import java.awt.EventQueue;

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
		
		/**
		 * Used to input into JLists
		 */
		DefaultListModel <String> ready = new DefaultListModel<String>();
		DefaultListModel <String> run = new DefaultListModel<String>();
		DefaultListModel <String> blocked = new DefaultListModel<String>();
		
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
		JList <String> ReadyQ = new JList <String>(ready);
		ReadyQ.setBounds(39, 43, 243, 157);
		frame.getContentPane().add(ReadyQ);
		
		JList <String> Running = new JList <String>(run);
		Running.setBounds(355, 43, 243, 157);
		frame.getContentPane().add(Running);
		
		JList <String> Blocked = new JList <String>(blocked);
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
		 * Add  Process Button
		 */
		JButton AddProcess = new JButton("Add Process");
		AddProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String process = textField.getText();
				System.out.println(process);
				//TODO add to ReadyQueue
				ready.addElement(process);
				
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
					String swap1 = run.firstElement();
					run.clear();
					String swap2 = ready.firstElement();
					ready.remove(0);
					ready.addElement(swap1);
					run.addElement(swap2);
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
				String toBlock = run.firstElement();
				blocked.addElement(toBlock);
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
					String toRun = ready.firstElement();
					run.addElement(toRun);
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
				String isReady = blocked.firstElement();
				ready.addElement(isReady);
				blocked.remove(0);
			}
		});
		btnReady.setBounds(579, 247, 125, 39);
		frame.getContentPane().add(btnReady);
		

	}//end initialize()
}
