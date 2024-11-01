import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import org.jdesktop.swingx.border.DropShadowBorder;



public class ResultPanel extends JPanel {

    DropShadowBorder shadow = new DropShadowBorder();
    Color panelColor = new Color(255, 250, 237);

    static java.util.List<Processes> SPI = new ArrayList<>();
    static java.util.List<GanttChart> GDC = new ArrayList<>();

    //#region Initialization Components
    JLabel titleLabel;
    JLabel sbh;

    JLabel algoTitle;
    java.util.List<JLabel> proc = new ArrayList<>();
    JLabel gctLabel;
    JLabel[] gcpLabel;
    JLabel jobLabel;
    JLabel atLabel;
    JLabel btLabel;
    JLabel ftLabel;
    JLabel tatLabel;
    JLabel watLabel;
    JLabel avgtatLabel;
    JLabel avgwatLabel;

    

    //#endregion

    public ResultPanel() {

        //#region Shadow
        shadow.setShadowColor(Color.BLACK);
        shadow.setShadowOpacity(0.7f);
        shadow.setShowRightShadow(true);
        shadow.setShowBottomShadow(true);
        //#endregion

        //#region Frame Attributes
        setBounds(470,130,700,450);
        setBackground(panelColor);
        setBorder(shadow);
        setLayout(null);
        setVisible(true);
        //#endregion
        
        //#region Title
        titleLabel = new JLabel("Output");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setBounds(50, 10, 200, 50);
        add(titleLabel);

        sbh = new JLabel("Gantt Chart and Table will be displayed here");
        sbh.setFont(new Font("Arial", Font.ITALIC, 16));
        sbh.setBounds(50, 55, 500, 50);
        add(sbh);
        
        
        
        //#endregion
        
        //#region Hidden Title
        algoTitle = new JLabel();
        gctLabel = new JLabel();
        jobLabel = new JLabel();
        atLabel = new JLabel();
        btLabel = new JLabel();
        ftLabel = new JLabel();
        tatLabel = new JLabel();
        watLabel = new JLabel();
        avgtatLabel = new JLabel();
        avgwatLabel = new JLabel();
        //#endregion
    }

    public void displayStats(String algo) {
        sbh.setVisible(false);
        algoTitle.setText(algo.toUpperCase());
        algoTitle.setFont(new Font("Arial", Font.BOLD, 16));
        algoTitle.setBounds(600, 10, 200, 50);
        add(algoTitle);

        //#region Gantt Chart
        gctLabel.setText("Gantt Chart");
        gctLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gctLabel.setBounds(305, 70, 200, 50);
        add(gctLabel);

        int y = 150;
        int height = 30;

        for (JLabel label : proc) {
            remove(label);
        }
        proc.clear();

        int containerWidth = getWidth();
        int labelCount = GDC.size();
        int margin = 50;
        int labelWidth = ((containerWidth - (labelCount - 1) * margin) / (labelCount > 0 ? labelCount : 1));
        System.out.println("Label Width: " + labelWidth);
        int totalLabelsWidth = labelWidth * labelCount + margin * (labelCount - 1);
        int startX = 100 + (containerWidth - totalLabelsWidth) / 2;

        for (int i = 0; i < GDC.size(); i++) {
            GanttChart g = GDC.get(i);
            JLabel jobLabel = new JLabel("P" + g.getJob());
            jobLabel.setFont(new Font("Arial", Font.PLAIN, 12));

            // Adjust the position and size of each label
            int x = startX + i * (labelWidth + margin);
            System.out.println("labelw: " + labelWidth);
            jobLabel.setBounds(x, y, labelWidth, height);
            add(jobLabel);
            proc.add(jobLabel);
        }

        revalidate();
        repaint();
    }

    public void ResultAlgo(String algo, int[] at, int[] bt, int[] prio) {
        SPI.clear();
        GDC.clear();

        int n = at.length;

        System.out.println("Algorithm: " + algo);
        for (int i = 0; i < at.length; i++) {
            System.out.println("P" + i + " " + at[i] + " " + bt[i] + " " + prio[i]);
        }

        // Initialize P
        int[][] p = new int[at.length][3];
        for (int i=0;i<at.length;i++) {
            p[i][0] = at[i];
            p[i][1] = bt[i];
            p[i][2] = prio[i];
        }

        //#region Algorithm
        
        if (algo.equals("sjf")) {
            SJF sjf = new SJF(p);
            SPI.addAll(sjf.getSPI());
            GDC.addAll(sjf.getGDC());
        } else if (algo.equals("nps")) {
            PS ps = new PS(p);
            SPI.addAll(ps.getSPI());
            GDC.addAll(ps.getGDC());
        } else if (algo.equals("pps")) {
            PPS pps = new PPS(p);
            SPI.addAll(pps.getSPI());
            GDC.addAll(pps.getGDC());
        }
        
        displayStats(algo);
        //#endregion
    }
}
