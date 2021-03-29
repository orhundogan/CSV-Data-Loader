import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LaunchFrame implements ActionListener {

    Model model;
    JFrame frame = new JFrame();
    File selectedFile;

    JLabel title = new JLabel("Java Coursework 2", SwingConstants.CENTER);
    JLabel infoLabel = new JLabel("By: Orhun Dogan", SwingConstants.CENTER);
    JButton openFileButton = new JButton("Open File");

    JFileChooser fc = new JFileChooser("lists");

    LaunchFrame(Model m) {
        this.model = m;
        title.setFont(new Font("Monoscaped", Font.BOLD, 45));
        title.setForeground(Color.BLUE);
        title.setBounds(0, 15, 500, 75);

        infoLabel.setFont(new Font("Monoscoped", Font.BOLD, 30));
        infoLabel.setForeground(Color.BLACK);
        infoLabel.setBounds(0, 100, 500, 75);

        openFileButton.setFocusable(false);
        openFileButton.setBounds(150, 200, 200, 50);
        openFileButton.addActionListener(this);

        frame.add(title);
        frame.add(infoLabel);
        frame.add(openFileButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0xFFCC99));
        frame.setLayout(new GridLayout(3, 0));
        frame.setVisible(true);
        frame.setSize(436, 184);
        frame.setLocationRelativeTo(null);  // Opens the frame at the middle of screen
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == openFileButton) {
            int returnValue = fc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFile = fc.getSelectedFile();    // Opened file
                String path = selectedFile.getAbsolutePath();
                this.model.loadData(path);  // Loading file
            }

            frame.dispose();
            MainFrame mainFrame = new MainFrame(this.model);    // Openning new frame
        }

    }
}