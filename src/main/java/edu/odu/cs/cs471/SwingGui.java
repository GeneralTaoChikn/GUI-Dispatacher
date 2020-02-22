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
import java.awt.Font;
import javax.swing.border.CompoundBorder;
import java.awt.Color;
import javax.swing.ListModel;
import javax.swing.UIManager;

public class SwingGui {

	private JFrame frmGuiDispatcher;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingGui window = new SwingGui();
					window.frmGuiDispatcher.setVisible(true);
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
		frmGuiDispatcher = new JFrame();
		frmGuiDispatcher.setTitle("Gui Dispatcher");
		frmGuiDispatcher.setBounds(100, 100, 750, 600);
		frmGuiDispatcher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGuiDispatcher.getContentPane().setLayout(null);
		
		randomNumGen a = new randomNumGen();
		
		/**
		 * Used to input into JLists
		 */
		DefaultListModel <Pair<Integer, String>> ready = new DefaultListModel<Pair<Integer, String>>();
		DefaultListModel <Pair<Integer, String>> run = new DefaultListModel<Pair<Integer, String>>();
		DefaultListModel <Pair<Integer, String>> blocked = new DefaultListModel<Pair<Integer, String>>();
		DefaultListModel <Pair<Integer, String>> terminated = new DefaultListModel<Pair<Integer, String>>();
		
		/**
		 * JLabels
		 */
		JLabel lblReadyQueue = new JLabel("Ready Queue");
		lblReadyQueue.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblReadyQueue.setBounds(38, 24, 117, 16);
		frmGuiDispatcher.getContentPane().add(lblReadyQueue);
		
		JLabel lblRunning = new JLabel("Running Processes");
		lblRunning.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRunning.setBounds(306, 24, 155, 16);
		frmGuiDispatcher.getContentPane().add(lblRunning);
		
		JLabel lblBlocked = new JLabel("Processes Blocked");
		lblBlocked.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBlocked.setBounds(38, 223, 155, 16);
		frmGuiDispatcher.getContentPane().add(lblBlocked);
		
		/**
		 * JLists
		 */
		JList <Pair<Integer, String>> ReadyQ = new JList <Pair<Integer, String>>(ready);
		ReadyQ.setBackground(Color.BLACK);
		ReadyQ.setForeground(new Color(50, 205, 50));
		ReadyQ.setBounds(39, 43, 243, 157);
		frmGuiDispatcher.getContentPane().add(ReadyQ);
		
		JList <Pair<Integer, String>> Running = new JList <Pair<Integer, String>>(run);
		Running.setBackground(Color.BLACK);
		Running.setForeground(new Color(50, 205, 50));
		Running.setBounds(306, 43, 243, 157);
		frmGuiDispatcher.getContentPane().add(Running);
		
		JList <Pair<Integer, String>> Blocked = new JList <Pair<Integer, String>>(blocked);
		Blocked.setBackground(Color.BLACK);
		Blocked.setForeground(new Color(50, 205, 50));
		Blocked.setBounds(39, 247, 243, 157);
		frmGuiDispatcher.getContentPane().add(Blocked);
		
		JList<Pair<Integer, String>> Terminated = new JList<Pair<Integer, String>>(terminated);
		Terminated.setForeground(new Color(50, 205, 50));
		Terminated.setBackground(Color.BLACK);
		Terminated.setBounds(306, 247, 243, 157);
		frmGuiDispatcher.getContentPane().add(Terminated);
		
		/**
		 * textfield
		 */
		textField = new JTextField();
		textField.setBounds(38, 477, 142, 43);
		frmGuiDispatcher.getContentPane().add(textField);
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
		AddProcess.setBounds(219, 477, 125, 43);
		frmGuiDispatcher.getContentPane().add(AddProcess);
		
		
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
		frmGuiDispatcher.getContentPane().add(btnTimeSlice);
		
		
		/**
		 * Terminate Button
		 */
		JButton btnTerminate = new JButton("Terminate");
		btnTerminate.setBackground(new Color(178, 34, 34));
		btnTerminate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				terminated.addElement(blocked.firstElement());
				blocked.remove(0);// remove first element in readylist
			}
		});
		btnTerminate.setBounds(579, 416, 125, 39);
		frmGuiDispatcher.getContentPane().add(btnTerminate);
		
		
		/**
		 * Block Button
		 */
		JButton btnBlock = new JButton("Block");
		btnBlock.setBackground(new Color(255, 255, 0));
		btnBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pair Process = new Pair<Integer, String>
				(run.firstElement().getKey(), run.firstElement().getValue());

				blocked.addElement(Process);
				run.clear();
				
			}
		});
		btnBlock.setBounds(579, 362, 125, 39);
		frmGuiDispatcher.getContentPane().add(btnBlock);
		
		
		/**
		 * Run Button
		 */
		JButton btnRun = new JButton("Run");
		btnRun.setBackground(new Color(34, 139, 34));
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
		frmGuiDispatcher.getContentPane().add(btnRun);
		
		
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
		frmGuiDispatcher.getContentPane().add(btnReady);
		
		JLabel lblTerminatedProcesses = new JLabel("Terminated Processes");
		lblTerminatedProcesses.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTerminatedProcesses.setBounds(306, 224, 191, 16);
		frmGuiDispatcher.getContentPane().add(lblTerminatedProcesses);
		
		/**
		 * Pre-Populate button
		 */
		JButton btnPrepopulate = new JButton("Pre-Populate");
		btnPrepopulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				a.prePopulate(ready);
			}
		});
		btnPrepopulate.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnPrepopulate.setBounds(205, 31, 77, 9);
		frmGuiDispatcher.getContentPane().add(btnPrepopulate);
		
		
	}//end initialize()
}
