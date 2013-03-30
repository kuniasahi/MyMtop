package tk.yuguoxusblog;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;



class MtopUI extends JFrame{
	
	private Mtop mtop = new Mtop();
	private boolean mRunOnce = false;
	
	private DefaultListModel lItems = new DefaultListModel();
	private JList lst = new JList(lItems);
	private JButton b1 = new JButton("Connect");
	private JButton b2 = new JButton("Exit");
	private JButton b3 = new JButton("Refash");
	private ActionListener b1a = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(!mRunOnce){
				mt.start();
				mRunOnce = true;
			}
		}
	};
	private ActionListener b2a = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				mtop.disconnect();
				mRunOnce = false;
		}
	};
	private ActionListener b3a = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.print("ActionListener b3a");
			ArrayList al = mtop.getTotalProcess();
			for(int i=0;i<al.size();i++){
				System.out.print(al.get(i));
				lItems.add(i, al.get(i));
			}
		}
	};
	private ListSelectionListener ll = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()) return;
				for(Object item : lst.getSelectedValues()){
			    	   
			    }  	   
			}
	};
	private Thread mt = new Thread(new Runnable(){
		@Override
		public void run(){
			mtop.connect();
		}
	});
	private int count = 0;
	public MtopUI() {
	    setLayout(null);
	    // Create Borders for components:
	    Border brd = BorderFactory.createMatteBorder(
	      1, 1, 2, 2, Color.BLACK);
	    lst.setBorder(brd);
	    lst.reshape(0,0,300,400);
	    b1.reshape(400, 10,80 , 25);
	    b2.reshape(400, 60, 80, 25);
	    b3.reshape(400, 110, 80, 25);
	    // Add the first four items to the List
	    for(int i = 0; i < 4; i++)
	      lItems.addElement("");
	    add(lst);
	    add(b1);
	    add(b2);
	    add(b3);
	    lst.addListSelectionListener(ll);
	    b1.addActionListener(b1a);
	    b2.addActionListener(b2a);
	    b3.addActionListener(b3a);
	}
	public static void main(String[] args) {
	    run(new MtopUI(), 600, 480);
	}
	  
	public static void run(final JFrame f, final int width, final int height) {
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	        f.setTitle(f.getClass().getSimpleName());
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        f.setSize(width, height);
	        f.setVisible(true);
	      }
	    });
	  }
}