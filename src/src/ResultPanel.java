import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.*;



public class ResultPanel extends JPanel {
    Color panelColor = new Color(19, 46, 53);

    static java.util.List<Processes> SPI = new ArrayList<>();
    static java.util.List<GanttChart> GDC = new ArrayList<>();

    //#region Initialization Components
    JLabel titleLabel;
    JLabel sbh;

    JTable processorTable;
    DefaultTableModel model;
    JScrollPane scrollPaneTable;

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

    Object[][] data;
    String[] columnNames;

    

    //#endregion

    public ResultPanel() {
        //#region Frame Attributes
        setBounds(450,130,700,450);
        setBackground(panelColor);
        setLayout(null);
        setVisible(true);
        //#endregion
        
        //#region Title
        titleLabel = new JLabel("Output");
        ImageIcon outputLabel = new ImageIcon(".idea/images/OUTPUT2.png");
        titleLabel.setIcon(outputLabel);
        titleLabel.setBounds(40, 10, 100, 40);
        add(titleLabel);

        sbh = new JLabel("Gantt Chart and Table will be displayed here");
        sbh.setForeground(Color.WHITE);
        sbh.setFont(new Font("Oswald", Font.ITALIC, 16));
        sbh.setBounds(50, 45, 500, 50);
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
        algoTitle.setFont(new Font("Calibri", Font.BOLD, 16));
        algoTitle.setForeground(Color.WHITE);
        algoTitle.setBounds(600, 10, 200, 50);
        add(algoTitle);

        //#region Gantt Chart
        gctLabel.setText("Gantt Chart");
        gctLabel.setFont(new Font("Calibri", Font.BOLD, 16));
        gctLabel.setForeground(Color.WHITE);
        gctLabel.setBounds(305, 70, 200, 50);
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

    public void displayTable() {
        if (processorTable != null) {
            model.setRowCount(0);
            model.setColumnCount(0);
        }

        Collections.sort(SPI, new Comparator<Processes>() {
            @Override
            public int compare(Processes o1, Processes o2) {
                return o1.getAt() - o2.getAt();
            }
        });
        
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
    
        processorTable.setBounds(50, 200, 600, 200);
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
        scrollPaneTable.setBounds(50, 200, 600, 200);
        scrollPaneTable.setBorder(BorderFactory.createEmptyBorder());
        //#endregion
        //#endregion
        
        
        
        add(scrollPaneTable);
    }
}
