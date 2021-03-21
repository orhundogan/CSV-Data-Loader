import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class MainFrame {
	Model model;
    JFrame frame = new JFrame();

    JPanel buttonPanel = new JPanel();
    JPanel checkboxPanel = new JPanel();

    JTextArea textArea = new JTextArea();
    Border blackline = BorderFactory.createLineBorder(Color.black);

    JButton exitButton = new JButton();

    MainFrame(Model m){
    	this.model = m;
        textArea.setBounds(150, 0, 350, 400);
        textArea.setBorder(blackline);

        buttonPanel.setBounds(0, 400, 500, 100);
        buttonPanel.setBorder(blackline);
        buttonPanel.setBackground(new Color(0xFFCC99));
        buttonPanel.setLayout(null);

        checkboxPanel .setBounds(0, 0, 150, 400);
        checkboxPanel .setBorder(blackline);
        checkboxPanel .setBackground(new Color(0xFFCC99));
        checkboxPanel .setLayout(new BoxLayout(checkboxPanel, BoxLayout.PAGE_AXIS));
        
        

        exitButton.setFocusable(false);
        exitButton.setBounds(200,25,100,50);
        buttonPanel.add(exitButton);

        
        textArea.setText(this.model.getDataFrameValues());
        
        
        

        for (String s: model.getAllColumns()) {
        	JCheckBox cb = new JCheckBox(s,true); 
        	cb.addItemListener(new ItemListener() {    


				@Override
				public void itemStateChanged(ItemEvent arg0) {
					// TODO Auto-generated method stub
					if (arg0.getStateChange()==1) {
						model.add(cb.getText());
						//System.out.println(cb.getText());
					} else {
						model.remove(cb.getText());
					}
			        textArea.setText(model.getDataFrameValues());
					
					
				}    
             });  

        	
        	checkboxPanel.add(cb);

        }

        frame.add(textArea);
        frame.add(buttonPanel);
        frame.add(checkboxPanel );
        

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0xFFCC99));
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);  // Opens the frame at the middle of screen
    }

}

