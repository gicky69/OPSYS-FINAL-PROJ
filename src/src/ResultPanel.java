import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class ResultPanel extends JPanel {
    Color panelColor = new Color(19, 46, 53);

    List<Processes> SPI = new ArrayList<>();
    static List<GanttChart> GDC = new ArrayList<>();

    //#region Initialization Components
    JLabel titleLabel;
    JLabel sbh;

    JTable processorTable;
    DefaultTableModel model;
    JScrollPane scrollPaneTable;

    JLabel algoTitle;
    List<JLabel> proc = new ArrayList<>();
    JLabel gctLabel;

    JLabel avgtatLabel;
    JLabel avgwatLabel;
    JLabel avgLabel;

    Object[][] data;
    String[] columnNames;

    List<Integer> sjob = new ArrayList<>();
    List<Integer> end = new ArrayList<>();
    //#endregion

    public ResultPanel() {
        //#region Frame Attributes
        setBounds(450,80,700,510);
        setBackground(panelColor);
        setLayout(null);
        setVisible(true);
        //#endregion
        
        //#region Title
        titleLabel = new JLabel("Output");
        ImageIcon outputLabel = new ImageIcon("OPSYS-FINAL-PROJ/images/OUTPUT2.png");
        titleLabel.setIcon(outputLabel);
        titleLabel.setBounds(40, 20, 100, 40);
        add(titleLabel);

        sbh = new JLabel("Gantt Chart and Table will be displayed here");
        sbh.setForeground(Color.WHITE);
        sbh.setFont(new Font("Montserrat", Font.ITALIC, 14));
        sbh.setBounds(47, 50, 500, 50);
        add(sbh);
        
        //#endregion
        
        //#region Hidden Title
        algoTitle = new JLabel();
        gctLabel = new JLabel();
        avgtatLabel = new JLabel();
        avgLabel = new JLabel();
        avgwatLabel = new JLabel();
        //#endregion
    
        processorTable = new JTable();
        model = new DefaultTableModel(new Object[][]{}, new String[]{}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        scrollPaneTable = new JScrollPane();
    }

    public void displayStats(String algo) {

        //#region
        sbh.setVisible(false);
        algoTitle.setText(algo.toUpperCase());
        algoTitle.setFont(new Font("Calibri", Font.BOLD, 24));
        algoTitle.setForeground(Color.WHITE);
        algoTitle.setBounds(600, 10, 200, 50);
        add(algoTitle);

        //#region Gantt Chart
        gctLabel.setText("Gantt Chart");
        gctLabel.setFont(new Font("Calibri", Font.BOLD, 24));
        gctLabel.setForeground(Color.WHITE);
        gctLabel.setBounds(300, 50, 200, 50);
        add(gctLabel);
        //#endregion
        //#endregion
        displayTable();
        revalidate();
        repaint();
    }

    public void ResultAlgo(String algo, int[] at, int[] bt, int[] prio) {
        SPI.clear();
        GDC.clear();
        sjob.clear();
        end.clear();

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
        
        switch (algo) {
            case "sjf":
                SJF sjf = new SJF(p);
                SPI.addAll(SJF.getSPI());
                GDC.addAll(SJF.getGDC());
                break;
            case "nps":
                PS ps = new PS(p);
                SPI.addAll(PS.getSPI());
                GDC.addAll(PS.getGDC());
                break;
            case "pps":
                PPS pps = new PPS(p);
                SPI.addAll(PPS.getSPI());
                GDC.addAll(PPS.getGDC());
                break;
        }


        sjob.add(GDC.get(0).getJob());
        end.add(GDC.get(0).getEnd());

        for (int i = 1; i < GDC.size(); i++) {
            Integer currentJob = GDC.get(i).getJob();
            Integer currentEnd = GDC.get(i).getEnd();
            Integer previousJob = GDC.get(i - 1).getJob();
            Integer previousEnd = GDC.get(i - 1).getEnd();
            if (!currentJob.equals(previousJob)) {
                sjob.add(currentJob);
            }

            if (!currentEnd.equals(previousEnd)) {
                end.add(currentEnd);
            }
        }

        displayStats(algo);
        //#endregion
    }

    public void displayTable() {
        if (processorTable != null) {
            model.setRowCount(0);
            model.setColumnCount(0);
        }

        Collections.sort(SPI, (o1, o2) -> o1.getAt() - o2.getAt());
        
        //#region Table
        columnNames = new String[] {"Job", "Arrival Time", "Burst Time", "Finish Time", "Turnaround Time", "Waiting Time"};
        data = new Object[SPI.size()][6];
        for (int i = 0; i < SPI.size(); i++) {
            data[i][0] = SPI.get(i).getJob();
            data[i][1] = SPI.get(i).getAt();
            data[i][2] = SPI.get(i).getBt();
            data[i][3] = SPI.get(i).getFt();
            data[i][4] = SPI.get(i).getTat();
            data[i][5] = SPI.get(i).getWat();
        }

        //#region Table att
        scrollPaneTable.setViewportView(processorTable);
        model.setDataVector(data, columnNames);
        processorTable.setModel(model);
    
        processorTable.setBounds(50, 200, 600, 150);
        processorTable.setBackground(panelColor);
        processorTable.setForeground(Color.WHITE);
        processorTable.setFont(new Font("ARIAL", Font.BOLD, 14));
        processorTable.setRowHeight(30);
        processorTable.setGridColor(Color.BLACK);
        processorTable.setShowGrid(true);
        processorTable.setFocusable(false);
        processorTable.setShowHorizontalLines(true);
        processorTable.setShowVerticalLines(true);
        processorTable.setRowSelectionAllowed(false);
        processorTable.setColumnSelectionAllowed(false);
        processorTable.setCellSelectionEnabled(false);
        processorTable.setDragEnabled(false);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(panelColor);
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setFont(new Font("ARIAL", Font.BOLD, 14));
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < processorTable.getColumnModel().getColumnCount(); i++) {
            processorTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        processorTable.getTableHeader().setReorderingAllowed(false);
    
        
        scrollPaneTable.setBackground(panelColor);
        scrollPaneTable.setForeground(Color.WHITE);
        scrollPaneTable.setFont(new Font("ARIAL", Font.BOLD, 14));
        scrollPaneTable.setOpaque(false);
        scrollPaneTable.getViewport().setBackground(panelColor);
        scrollPaneTable.setBounds(50, 200, 600, 150);
        scrollPaneTable.setBorder(BorderFactory.createEmptyBorder());
        //#endregion
        //#endregion

        //#region Averages
        int totalTAT = 0;
        int totalWAT = 0;
        for (Processes p : SPI) {
            totalTAT += p.getTat();
            totalWAT += p.getWat();
        }

        double avgTAT = (double) totalTAT / SPI.size();
        double avgWAT = (double) totalWAT / SPI.size();

        avgLabel.setText("Average: ");
        avgLabel.setFont(new Font("Calibri", Font.ITALIC, 16));
        avgLabel.setForeground(Color.WHITE);
        avgLabel.setBounds(395, processorTable.getHeight() + processorTable.getY(), 100, 50);

        avgtatLabel.setText(String.format("%.2f", avgTAT));
        avgtatLabel.setFont(new Font("Calibri", Font.ITALIC, 16));
        avgtatLabel.setForeground(Color.WHITE);
        avgtatLabel.setBounds(avgLabel.getWidth() + avgLabel.getY() + 15, processorTable.getHeight() + processorTable.getY(), 50, 50);

        avgwatLabel.setText(String.format("%.2f", avgWAT));
        avgwatLabel.setFont(new Font("Calibri", Font.ITALIC, 16));
        avgwatLabel.setForeground(Color.WHITE);
        avgwatLabel.setBounds(618, processorTable.getHeight() + processorTable.getY(), 50, 50);

        add(avgLabel);
        add(avgtatLabel);
        add(avgwatLabel);
        //#endregion
        
        add(scrollPaneTable);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);

        Rectangle labelBounds = gctLabel.getBounds();

        int rectWidth = 42;
        int rectHeight = 42;
        int totalWidth = sjob.size() * rectWidth;

        int rectX = labelBounds.x + ((labelBounds.width - totalWidth) - 85) / 2;
        int rectY = labelBounds.y + (labelBounds.height + 50) / 2;

        for (int i = 0; i < sjob.size(); i++) {
            g.setFont(new Font("Montserrat", Font.ITALIC, 12));
            g.drawRect(rectX + i * rectWidth, rectY, rectWidth, rectHeight);
        }

        // Print Job Number
        for (int i =0;i<sjob.size();i++) {
            g.setFont(new Font("Montserrat", Font.BOLD, 14));
            g.drawString("P" + sjob.get(i), rectX + i * rectWidth + 13, rectY + 25);
        }

        // Print start and End
        int start = 0; 
        int eend = 0;
        for (int i = 0; i < sjob.size(); i++) {
            if (i == 0) { 
                start = GDC.get(i).getStart();
                eend = GDC.get(i).getEnd();
            } else {
                eend = end.get(i);
            }
            
            int job = sjob.get(i);

            int x = rectX + i * rectWidth;
            int y = rectY + 20;

            g.setFont(new Font("Montserrat", Font.BOLD, 14));
            if (i == 0) {
                g.drawString(String.valueOf(start), x - 5, y + 40);
            }
            
            g.drawString(String.valueOf(eend), x + rectWidth - 5, y + 40);
        }
    }

}
