
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// import org.jdesktop.swingx.border.*;


public class MFrame extends JFrame implements Runnable{
    //#region Attributes
    JLabel titleLabel;
    JPanel panel;
    JRadioButton sjf, ps, pps;
    JPanel ButttonsPanel;
    JLabel title1Label;
    JLabel title2Label;
    JLabel title3Label;
    JLabel title4Label;
    JButton startButton;
    JButton exitButton;
    ButtonGroup group;

    JTextField input1;
    JTextField input2;
    JTextField input3;

    // DropShadowBorder shadow = new DropShadowBorder();
    Color panelColor = new Color(105, 129, 141);

    //Result Panel

    ResultPanel resultPanel;



    Thread t = new Thread(this);

    //#endregion

    public MFrame() {
        setTitle("CPU Scheduling");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.white);

        Color hoverColor = new Color(19, 46, 53);

        panel = new JPanel();
        panel.setBounds(0, 0, 1280, 720);
        panel.setLayout(null);
        panel.setBackground(new Color(45,74,83));
        add(panel);

        //#region Title
        titleLabel = new JLabel();
        ImageIcon titleImage = new ImageIcon("images/cpuscheduling.png");
        titleLabel.setIcon(titleImage);
        titleLabel.setBounds(460, 5, 400, 70);
        panel.add(titleLabel);


        ButttonsPanel = new JPanel();
        ButttonsPanel.setBounds(110, 95, 350, 510);
        ButttonsPanel.setBackground(panelColor);
        ButttonsPanel.setLayout(null);

        title1Label = new JLabel("Input");
        ImageIcon inputIcon = new ImageIcon("images/INPUT4.png");
        title1Label.setIcon(inputIcon);
        title1Label.setBounds(25, 20, 100, 40);
        ButttonsPanel.add(title1Label);

        title2Label = new JLabel();
        ImageIcon atImage = new ImageIcon("images/Arrival time.png");
        title2Label.setIcon(atImage);
        title2Label.setBounds(47,75,100,20);
        ButttonsPanel.add(title2Label);

        title3Label = new JLabel();
        ImageIcon btImage = new ImageIcon("images/Burst time.png");
        title3Label.setIcon(btImage);
        title3Label.setBounds(47,135,300,50);
        ButttonsPanel.add(title3Label);

        title4Label = new JLabel();
        ImageIcon pImage = new ImageIcon("images/priority.png");
        title4Label.setIcon(pImage);
        title4Label.setBounds(47,210,300,50);
        ButttonsPanel.add(title4Label);

        // shadow.setShadowColor(Color.BLACK);
        // shadow.setShadowSize(10);
        // shadow.setShadowOpacity(0.7f);
        // shadow.setShowRightShadow(true);
        // shadow.setShowBottomShadow(true);

        // ButttonsPanel.setBorder(shadow);

        panel.add(ButttonsPanel);

        //#endregion
        
        //#region Input Fields
        input1 = new JTextField();
        // input1.setFont(f);
        input1.setBounds(50, 100, 250, 45);
        input1.setFont(new Font("Montserrat", Font.PLAIN, 16));
        input1.setForeground(Color.BLACK);
        input1.setBackground(new Color(203,220,235));
        input1.setMargin(new Insets(13, 13, 13, 13));
        ButttonsPanel.add(input1);

        input1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (input1.getText().equals("e.g 0 2 4 6 8") && input1.getForeground().equals(Color.GRAY)) {
                    input1.setText("");
                    input1.setFont(new Font("Montserrat", Font.PLAIN, 16));
                    input1.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (input1.getText().equals("")) {
                    input1.setText("e.g 0 2 4 6 8");
                    input1.setFont(new Font("Montserrat", Font.ITALIC, 12));
                    input1.setForeground(Color.GRAY);
                }
            }
        });

        input2 = new JTextField();
        input2.setText("e.g 2 4 6 8 10");
        input2.setFont(new Font("Montserrat", Font.ITALIC, 12));
        input2.setForeground(Color.GRAY);
        input2.setBounds(50, input1.getHeight() + input1.getY() + 30, 250, 45);
        input2.setMargin(new Insets(13, 13, 13, 13));
        input2.setBackground(new Color(203,220,235));
        ButttonsPanel.add(input2);

        input2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (input2.getText().equals("e.g 2 4 6 8 10") && input2.getForeground().equals(Color.GRAY)) {
                    input2.setText("");
                    input2.setFont(new Font("Montserrat", Font.PLAIN, 16));
                    input2.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (input2.getText().equals("")) {
                    input2.setText("e.g 2 4 6 8 10");
                    input2.setFont(new Font("Montserrat", Font.ITALIC, 12));
                    input2.setForeground(Color.GRAY);
                }
            }
        });

        input3 = new JTextField();
        input3.setText("# Lowest - Highest");
        input3.setFont(new Font("Montserrat", Font.ITALIC, 12));
        input3.setForeground(Color.GRAY);
        input3.setBounds(50, input2.getHeight() + input2.getY() + 30, 250, 45);
        input3.setMargin(new Insets(13, 13, 13, 13));
        input3.setBackground(new Color(203,220,235));
        ButttonsPanel.add(input3);

        input3.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (input3.getText().equals("# Lowest - Highest") && input3.getForeground().equals(Color.GRAY)) {
                    input3.setText("");
                    input3.setFont(new Font("Montserrat", Font.PLAIN, 16));
                    input3.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (input3.getText().equals("")) {
                    input3.setText("# Lowest - Highest");
                    input3.setFont(new Font("Montserrat", Font.ITALIC, 12));
                    input3.setForeground(Color.GRAY);
                }
            }
        });

        //#endregion

        //#region Radio Buttons
        sjf = new JRadioButton("Shortest Job First");
        sjf.setSelected(false);
        sjf.setFont(new Font("Calibri", Font.BOLD, 18));
        sjf.setForeground(Color.WHITE);
        sjf.setBackground(panelColor);
        sjf.setFocusable(false);
        sjf.setBounds(input1.getX(), 310, input1.getWidth(), 40);
        ButttonsPanel.add(sjf);

        ps = new JRadioButton("Priority (Non-Preemptive)");
        ps.setSelected(false);
        ps.setFont(new Font("Calibri", Font.BOLD, 18));
        ps.setBackground(panelColor);
        ps.setForeground(Color.WHITE);
        ps.setFocusable(false);
        ps.setBounds(input1.getX(), sjf.getHeight() + sjf.getY(), input1.getWidth(), 40);
        ButttonsPanel.add(ps);

        pps = new JRadioButton("Priority (Preemptive)");
        pps.setSelected(false);
        pps.setFont(new Font("Calibri", Font.BOLD, 18));
        pps.setBackground(panelColor);
        pps.setForeground(Color.WHITE);
        pps.setFocusable(false);
        pps.setBounds(input1.getX(), ps.getHeight() + ps.getY(), input1.getWidth(), 40);
        ButttonsPanel.add(pps);

        ActionListener radioButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sjf.isSelected()) {
                    input3.setEnabled(false);
                } else {
                    input3.setEnabled(true);
                }
            }
        };
        
        sjf.addActionListener(radioButtonListener);
        ps.addActionListener(radioButtonListener);
        pps.addActionListener(radioButtonListener);

        MouseListener hoverEffect = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JRadioButton button = (JRadioButton) e.getSource();
                button.setBackground(hoverColor); // Change to desired hover color
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                JRadioButton button = (JRadioButton) e.getSource();
                button.setBackground(panelColor); // Revert to original color
            }
        };

        sjf.addMouseListener(hoverEffect);
        ps.addMouseListener(hoverEffect);
        pps.addMouseListener(hoverEffect);

        group = new ButtonGroup();
        group.add(sjf);
        group.add(ps);
        group.add(pps);


        ImageIcon startEntered = new ImageIcon("images/startnotclick.png");
        ImageIcon start = new ImageIcon("images/startclicked.png");
        startButton = new JButton();
        startButton.setIcon(start);
        startButton.setBorder(null);
        startButton.setBounds(60, 445, 100, 40);
        startButton.setFocusable(false);
        ButttonsPanel.add(startButton);

        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setIcon(startEntered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setIcon(start);
            }
        });

        ImageIcon exit = new ImageIcon("images/exitclicked.png");
        ImageIcon exitEntered = new ImageIcon("images/exitnotclicked.png");
        exitButton = new JButton();
        exitButton.setIcon(exit);
        exitButton.setBorder(null);
        exitButton.setFocusable(false);
        exitButton.setBounds(185, 445, 100, 40);
        ButttonsPanel.add(exitButton);

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(exitEntered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setIcon(exit);
            }
        });


        //#endregion

        //#region Button Actions
        startButton.addActionListener(e -> {
            if (
                input1.getText().equals("e.g 0 2 4 6 8") 
                || input2.getText().equals("e.g 2 4 6 8 10") 
                || (!sjf.isSelected() && !ps.isSelected() && !pps.isSelected())
                || input1.getText().trim().isEmpty()
                || input2.getText().trim().isEmpty()
                || (ps.isSelected() && (input3.getText().trim().isEmpty() || input3.getText().equals("# Lowest - Highest")))
                || (pps.isSelected() && (input3.getText().trim().isEmpty() || input3.getText().equals("# Lowest - Highest")))
                ) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            //#region Input 1
            String p = input1.getText();

            String[] pstr = p.split(" ");

            int[] at = new int[pstr.length];
            for (int i = 0; i < pstr.length; i++) {
                at[i] = Integer.parseInt(pstr[i]);
            }
            //#endregion

            //#region Input 2
            String p2 = input2.getText();

            String[] p2str = p2.split(" ");

            int[] bt = new int[p2str.length];
            for (int i = 0; i < p2str.length; i++) {
                bt[i] = Integer.parseInt(p2str[i]);
            }
            //#endregion

            //#region Input 3
            int[] prio = new int[at.length];
            if (!sjf.isSelected()) {
                String p3 = input3.getText();

                String[] p3str = p3.split(" ");

                for (int i = 0; i < p3str.length; i++) {
                    prio[i] = Integer.parseInt(p3str[i]);
                }
            } else {    
                for (int i = 0; i < at.length; i++) {
                    prio[i] = 0;
                }
            }
            
            //#endregion

            // System.out.println("UNSORTED TABLE");
            // for (int i=0;i<at.length;i++) {
            //     System.out.println("P" + i + " " + at[i] + " " + bt[i] + " " + prio[i]);
            // }

            if (sjf.isSelected()) {
                resultPanel.ResultAlgo("sjf", at, bt, prio);
            } else if (ps.isSelected()) {
                resultPanel.ResultAlgo("nps",at, bt, prio);
            } else if (pps.isSelected()) {
                resultPanel.ResultAlgo("pps",at, bt, prio);
            }
        });

        //#endregion

        panel.requestFocusInWindow();

        //#region Result Panel
        resultPanel = new ResultPanel();
        panel.add(resultPanel);
        resultPanel.setVisible(true);
        //#endregion

        exitButton.addActionListener(e ->System.exit(0));

        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        start();

    }

    public void start() {
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                resultPanel.repaint();
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
