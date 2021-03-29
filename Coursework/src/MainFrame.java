import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class MainFrame implements ActionListener {

    Model model;
    JFrame frame;

    JPanel leftMenu;
    JPanel checkboxPanel;
    JPanel sortSearchPanel;
    JPanel buttonPanel;

    JTable jt;

    JScrollPane sp;

    Border checkBoxBorder;
    Border searchSortBorder;

    JButton exitButton;
    JButton resetButton;

    MainFrame(Model m) {

        this.model = m;

        frame = new JFrame();
        leftMenu = new JPanel();
        checkboxPanel = new JPanel();
        sortSearchPanel = new JPanel();
        buttonPanel = new JPanel();


        jt = new JTable(model.getTableData(), model.getTableColumns());

        for (int i = 0; i < this.jt.getColumnCount(); i++) {
           if (jt.getColumnName(i).equals("ID")||jt.getColumnName(i).equals("BIRTHPLACE") || jt.getColumnName(i).equals("ADDRESS")) { // ID - Birthplace - Address columns' width needs to be bigger
                jt.getColumnModel().getColumn(i).setPreferredWidth(400);
            } else {
                jt.getColumnModel().getColumn(i).setPreferredWidth(100);
            }
        }

        sp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        checkBoxBorder = BorderFactory.createTitledBorder("Columns");
        searchSortBorder = BorderFactory.createTitledBorder("Search-Sort");

        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        resetButton = new JButton("Reset");

        leftMenu.setBackground(new Color(0xFFCC99));
        leftMenu.setLayout(new BorderLayout());
        leftMenu.add(checkboxPanel, BorderLayout.NORTH);
        leftMenu.add(sortSearchPanel, BorderLayout.SOUTH);

        checkboxPanel.setBackground(new Color(0xFFCC99));
        checkboxPanel.setLayout(new GridLayout(model.getAllColumns().size(), 3));
        checkboxPanel.setBorder(checkBoxBorder);

        buttonPanel.setBackground(new Color(0xFFCC99));
        buttonPanel.add(exitButton);

        sortSearchPanel.setBackground(new Color(0xFFCC99));
        sortSearchPanel.setLayout(new GridLayout(1, 0));
        sortSearchPanel.add(resetButton);
        sortSearchPanel.setBorder(searchSortBorder);

        exitButton.setFocusable(false);

        resetButton.addActionListener(this);

        for (String s : model.getAllColumns()) {     // For each column, creates 1 checkbox and 2 buttons
            JCheckBox cb = new JCheckBox(s, true);
            JButton jGetMax = new JButton("Get Max");
            JButton jGetMin = new JButton("Get Min");
            JButton jsearch = new JButton("Search");

            jsearch.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String searchKeyword = JOptionPane.showInputDialog(frame, "Enter search keyword");
                    jt = new JTable(model.getTableData(searchKeyword, s), model.getTableColumns());
                    for (int i = 0; i < jt.getColumnCount(); i++) {
                        if (jt.getColumnName(i).equals("ID")||jt.getColumnName(i).equals("BIRTHPLACE") || jt.getColumnName(i).equals("ADDRESS")) { // ID - Birthplace - City
                            jt.getColumnModel().getColumn(i).setPreferredWidth(400);
                        } else {
                            jt.getColumnModel().getColumn(i).setPreferredWidth(100);
                        }
                    }
                    frame.remove(sp);
                    sp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    frame.add(sp);
                    frame.pack();
                }
            });

            jGetMax.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, model.getMax(s));    // Shows the result
                }
            });

            jGetMin.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, model.getMin(s));    // Shows the result
                }
            });

            cb.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent arg0) {
                    // TODO Auto-generated method stub
                    if (arg0.getStateChange() == 1) {     // If pressed
                        model.add(cb.getText());
                        jGetMax.setEnabled(true);
                        jGetMin.setEnabled(true);
                        jsearch.setEnabled(true);
                    } else {        // If not pressed
                        model.remove(cb.getText());
                        jGetMax.setEnabled(false);
                        jGetMin.setEnabled(false);
                        jsearch.setEnabled(false);


                    }
                    //textArea.setText(model.getDataFrameValues());
                    jt = new JTable(model.getTableData(), model.getTableColumns());
                    for (int i = 0; i < jt.getColumnCount(); i++) {
                        if (jt.getColumnName(i).equals("ID")||jt.getColumnName(i).equals("BIRTHPLACE") || jt.getColumnName(i).equals("ADDRESS")) { // ID - Birthplace - City
                            jt.getColumnModel().getColumn(i).setPreferredWidth(400);
                        } else {
                            jt.getColumnModel().getColumn(i).setPreferredWidth(100);
                        }
                    }
                    frame.remove(sp);
                    sp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    frame.add(sp);
                    frame.pack();

                }
            });
            checkboxPanel.add(cb);
            checkboxPanel.add(jGetMax);
            checkboxPanel.add(jGetMin);
            checkboxPanel.add(jsearch);
        }

        frame.setLayout(new BorderLayout());
        frame.add(leftMenu, BorderLayout.WEST);
        frame.add(sp, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0xFFCC99));
        frame.setVisible(true);
        frame.setSize(750, 750);
        frame.setLocationRelativeTo(null);  // Opens the frame at the middle of screen
        frame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {   // Resetting Table
            model.reset();
            jt = new JTable(model.getTableData(), model.getTableColumns());
            for (int i = 0; i < jt.getColumnCount(); i++) {
                if (jt.getColumnName(i).equals("ID")||jt.getColumnName(i).equals("BIRTHPLACE") || jt.getColumnName(i).equals("ADDRESS")) { // ID - Birthplace - City
                    jt.getColumnModel().getColumn(i).setPreferredWidth(400);
                } else {
                    jt.getColumnModel().getColumn(i).setPreferredWidth(100);
                }
            }
            frame.remove(sp);
            sp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            frame.add(sp);
            frame.pack();

        }

        if (e.getSource() == exitButton) {    // Exits
            frame.dispose();
            LaunchFrame launchFrame = new LaunchFrame(model);
        }

    }
}

