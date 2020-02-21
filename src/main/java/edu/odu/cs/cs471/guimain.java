package edu.odu.cs.cs471;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.TouchListener;
import org.eclipse.swt.events.TouchEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class guimain {

	protected Shell shlDispatcher;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			guimain window = new guimain();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlDispatcher.open();
		shlDispatcher.layout();
		while (!shlDispatcher.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlDispatcher = new Shell();
		shlDispatcher.setSize(750, 600);
		shlDispatcher.setText("Dispatcher");
		///Added
		String process;
		
		text = new Text(shlDispatcher, SWT.BORDER);
		text.setBounds(26, 487, 153, 45);
		///Added	
		process = text.getText();
		
		ListViewer listViewer = new ListViewer(shlDispatcher, SWT.BORDER | SWT.V_SCROLL);
		List Ready_Queue = listViewer.getList();
		Ready_Queue.setBounds(45, 73, 216, 136);
		
		ListViewer listViewer_1 = new ListViewer(shlDispatcher, SWT.BORDER | SWT.V_SCROLL);
		List Running = listViewer_1.getList();
		Running.setBounds(280, 73, 216, 136);
		
		ListViewer listViewer_2 = new ListViewer(shlDispatcher, SWT.BORDER | SWT.V_SCROLL);
		List Blocked = listViewer_2.getList();
		Blocked.setBounds(45, 272, 216, 136);
		
		/**
		 * Add Process
		 */
		Button btnAddProcess = new Button(shlDispatcher, SWT.NONE);
		btnAddProcess.addTouchListener(new TouchListener() {
			public void touch(TouchEvent arg0) {
				listViewer.add(process);
			}
		});
		btnAddProcess.setBounds(207, 487, 107, 45);
		btnAddProcess.setText("Add Process");
		
				
		Button btnNewButton = new Button(shlDispatcher, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {

		});
		btnNewButton.setBounds(592, 487, 107, 45);
		btnNewButton.setText("Terminate");
		
		Button button = new Button(shlDispatcher, SWT.NONE);
		button.setText("Time Slice");
		button.setBounds(592, 420, 107, 45);
		
		Button btnBlock = new Button(shlDispatcher, SWT.NONE);
		btnBlock.setText("Block");
		btnBlock.setBounds(592, 354, 107, 45);
		
		Button btnRun = new Button(shlDispatcher, SWT.NONE);
		btnRun.setText("Run");
		btnRun.setBounds(592, 286, 107, 45);
		
		Label lblReadQueue = new Label(shlDispatcher, SWT.NONE);
		lblReadQueue.setBounds(45, 39, 81, 28);
		lblReadQueue.setText("Ready Queue");
		
		Label lblRunning = new Label(shlDispatcher, SWT.NONE);
		lblRunning.setBounds(280, 41, 55, 26);
		lblRunning.setText("Running");
		
		Label lblBlocked = new Label(shlDispatcher, SWT.NONE);
		lblBlocked.setBounds(45, 240, 65, 26);
		lblBlocked.setText("Blocked");

	}
}
