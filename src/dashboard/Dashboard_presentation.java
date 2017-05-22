/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import Cartridge_pkg.Cartridge;
import Instrument_pkg.Instrument;
import JDBCqueries_pkg.JDBCqueries;
import TestInstance_pkg.TestInstance;
import Errors_pkg.Errors;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 *
 * @author Owner
 */
public class Dashboard_presentation extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard_presentation
     */
    public Dashboard_presentation() {
        initComponents();

        try {
            //  set up schedule to update the lower text area
            ScheduledExecutorService execService
                    = Executors.newScheduledThreadPool(1);

            execService.scheduleAtFixedRate(() -> {

                //The repetitive task... 
                this.updateLowerTextArea();

            }, 0, 225L, TimeUnit.MILLISECONDS);
            /////////////////////////////////////////////////

            // set up schedule to update all upper text area panels
            ScheduledExecutorService execService2
                    = Executors.newScheduledThreadPool(1);

            execService2.scheduleAtFixedRate(() -> {

                //The repetitive task... 
                this.updateInstrumentPanel();
                this.updateCartridgePanel();
                this.updateTestsPanel();
                this.updateErrorsPanel();

            }, 0, 1, TimeUnit.SECONDS);
            ////////////////////////////////////////////////////////

        } catch (Exception e) {
            // handle the error
            System.out.println("\n" + "Dashboard_presentation General Exception " + e.getMessage());
            System.exit(0);
        } finally {
            //finally block used to close resources

        }   //end finally
    }

    static int loop = 0;

    private void updateLowerTextArea() {

        int selection = loop % 4;

        switch (selection) {
            case 0:
                Instrument instrument = new Instrument();
                ArrayList arrayList = new ArrayList<String>();
                JDBCqueries queries = new JDBCqueries();

                try {

                    arrayList = queries.getAllInstrumentIDs();

                    // randomly picj an instrument and display it's info
                    int randomInstrIndex = (int) (arrayList.size() * Math.random());
                    String randomInstrID = (String) arrayList.get(randomInstrIndex);
                    queries.getInstrumentMfgInfo(randomInstrID, instrument);
                    queries.getInstrumentDeploymentInfo(randomInstrID, instrument);

                    lowerLeftTextArea.setText(loop + " 0: " + new java.util.Date());
                    lowerRightTextArea.setText(instrument.toString());

                } catch (Exception e) {
                    // handle the error
                    System.out.println("\n" + "updateLowerTextArea case 0, General Exception " + e.getMessage());
                    System.exit(0);
                } finally {
                    instrument = null;
                    arrayList = null;
                    queries = null;
                    break;

                }   //end finally

            case 1:
                try {
                    lowerLeftTextArea.setText(loop + " 1: " + new java.util.Date());
                } catch (Exception e) {
                    // handle the error
                    System.out.println("\n" + "updateLowerTextArea case 1, General Exception " + e.getMessage());
                    System.exit(0);
                } finally {

                    break;

                }   //end finally

            case 2:
                try {
                    lowerLeftTextArea.setText(loop + " 2: " + new java.util.Date());
                } catch (Exception e) {
                    // handle the error
                    System.out.println("\n" + "updateLowerTextArea case 2, General Exception " + e.getMessage());
                    System.exit(0);
                } finally {

                    break;

                }   //end finallybreak;
            case 3:
                try {
                    lowerLeftTextArea.setText(loop + " 3: " + new java.util.Date());
                } catch (Exception e) {
                    // handle the error
                    System.out.println("\n" + "updateLowerTextArea case 3, General Exception " + e.getMessage());
                    System.exit(0);
                } finally {

                    break;

                }   //end finallybreak;
        }

        loop++;
    }

    private void updateInstrumentPanel() {
        JDBCqueries queries = new JDBCqueries();
        ArrayList<String> arrayList = new ArrayList<String>();

        try {

            arrayList = queries.getAllInstrumentIDs();

            instrumentTextArea.setText("Total Instruments: " + arrayList.size() + "\n");

            for (String ID : arrayList) {
                instrumentTextArea.setText(instrumentTextArea.getText() + "\t" + ID + "\n");
            }
            instrumentTextArea.setText(instrumentTextArea.getText() + new java.util.Date());

        } catch (Exception e) {
            // handle the error
            System.out.println("\n" + "updateInstrumentPanel General Exception " + e.getMessage());
            System.exit(0);
        } finally {
            arrayList = null;
            queries = null;

        }   //end finally

    }

    private void updateCartridgePanel() {

        JDBCqueries queries = new JDBCqueries();
        ArrayList<String> arrayList = new ArrayList<String>();

        try {
            arrayList = queries.getAllCartridgeIDs();

            cartridgeTextArea.setText("Total Cartridges: " + arrayList.size() + "\n");

            for (String ID : arrayList) {
                cartridgeTextArea.setText(cartridgeTextArea.getText() + "\t" + ID + "\n");
            }

        } catch (Exception e) {
            // handle the error
            System.out.println("\n" + "updateCartridgePanel General Exception " + e.getMessage());
            System.exit(0);
        } finally {
            arrayList = null;
            queries = null;

        }   //end finally

    }

    private void updateTestsPanel() {

        JDBCqueries queries = new JDBCqueries();
        ArrayList<String> arrayList = new ArrayList<String>();

        try {
            arrayList = queries.getAllTestInstanceIDs();

            testsTextArea.setText("Total SensoDx Tests: " + arrayList.size() + "\n");

            for (String ID : arrayList) {
                testsTextArea.setText(testsTextArea.getText() + "\t" + ID + "\n");
            }

        } catch (Exception e) {
            // handle the error
            System.out.println("\n" + "updateTestsPanel General Exception " + e.getMessage());
            System.exit(0);
        } finally {
            arrayList = null;
            queries = null;

        }   //end finally

    }

    private void updateErrorsPanel() {

        JDBCqueries queries = new JDBCqueries();
        ArrayList<String> arrayList = new ArrayList<String>();

        try {
            arrayList = queries.getAllErrorIDs();

            errorsTextArea.setText("Total SensoDx Errors: " + arrayList.size() + "\n");

            for (String ID : arrayList) {
                errorsTextArea.setText(errorsTextArea.getText() + "\t" + ID + "\n");
            }

        } catch (Exception e) {
            // handle the error
            System.out.println("\n" + "updateErrorsPanel General Exception " + e.getMessage());
            System.exit(0);
        } finally {
            arrayList = null;
            queries = null;

        }   //end finally

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        instrumentPanel = new javax.swing.JPanel();
        instrumentLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        instrumentTextArea = new javax.swing.JTextArea();
        cartridgePanel = new javax.swing.JPanel();
        cartridgeLabel = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        cartridgeTextArea = new javax.swing.JTextArea();
        Tests = new javax.swing.JPanel();
        testLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        testsTextArea = new javax.swing.JTextArea();
        ErrorPanel = new javax.swing.JPanel();
        errorLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        errorsTextArea = new javax.swing.JTextArea();
        lowerPanel = new javax.swing.JPanel();
        lowerLeftPanel = new javax.swing.JPanel();
        lowerLeftScrollPanel = new javax.swing.JScrollPane();
        lowerLeftTextArea = new javax.swing.JTextArea();
        lowerRightPanel = new javax.swing.JPanel();
        lowerRightScrollPanel = new javax.swing.JScrollPane();
        lowerRightTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SensoDx Dashboard");
        getContentPane().setLayout(new java.awt.GridLayout(2, 0));

        topPanel.setBackground(new java.awt.Color(255, 255, 102));
        topPanel.setLayout(new java.awt.GridLayout(1, 0));

        instrumentPanel.setBackground(new java.awt.Color(204, 204, 255));
        instrumentPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        instrumentLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        instrumentLabel.setText("Instrument");

        instrumentTextArea.setColumns(20);
        instrumentTextArea.setRows(5);
        jScrollPane1.setViewportView(instrumentTextArea);

        javax.swing.GroupLayout instrumentPanelLayout = new javax.swing.GroupLayout(instrumentPanel);
        instrumentPanel.setLayout(instrumentPanelLayout);
        instrumentPanelLayout.setHorizontalGroup(
            instrumentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(instrumentPanelLayout.createSequentialGroup()
                .addGroup(instrumentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(instrumentPanelLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(instrumentLabel))
                    .addGroup(instrumentPanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        instrumentPanelLayout.setVerticalGroup(
            instrumentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(instrumentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(instrumentLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        instrumentLabel.getAccessibleContext().setAccessibleName("Instruments");

        topPanel.add(instrumentPanel);

        cartridgePanel.setBackground(new java.awt.Color(204, 204, 255));
        cartridgePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cartridgeLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cartridgeLabel.setText("Cartridges");

        cartridgeTextArea.setColumns(20);
        cartridgeTextArea.setRows(5);
        jScrollPane4.setViewportView(cartridgeTextArea);

        javax.swing.GroupLayout cartridgePanelLayout = new javax.swing.GroupLayout(cartridgePanel);
        cartridgePanel.setLayout(cartridgePanelLayout);
        cartridgePanelLayout.setHorizontalGroup(
            cartridgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartridgePanelLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(cartridgeLabel)
                .addContainerGap(86, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cartridgePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        cartridgePanelLayout.setVerticalGroup(
            cartridgePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartridgePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cartridgeLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        topPanel.add(cartridgePanel);

        Tests.setBackground(new java.awt.Color(204, 204, 255));
        Tests.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        testLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        testLabel.setText("Tests");

        testsTextArea.setColumns(20);
        testsTextArea.setRows(5);
        jScrollPane2.setViewportView(testsTextArea);

        javax.swing.GroupLayout TestsLayout = new javax.swing.GroupLayout(Tests);
        Tests.setLayout(TestsLayout);
        TestsLayout.setHorizontalGroup(
            TestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TestsLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(testLabel)
                .addContainerGap(79, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TestsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        TestsLayout.setVerticalGroup(
            TestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TestsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(testLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        topPanel.add(Tests);

        ErrorPanel.setBackground(new java.awt.Color(204, 204, 255));
        ErrorPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        errorLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        errorLabel.setText("Errors");

        errorsTextArea.setColumns(20);
        errorsTextArea.setRows(5);
        jScrollPane3.setViewportView(errorsTextArea);

        javax.swing.GroupLayout ErrorPanelLayout = new javax.swing.GroupLayout(ErrorPanel);
        ErrorPanel.setLayout(ErrorPanelLayout);
        ErrorPanelLayout.setHorizontalGroup(
            ErrorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ErrorPanelLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(errorLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ErrorPanelLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        ErrorPanelLayout.setVerticalGroup(
            ErrorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ErrorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(errorLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        topPanel.add(ErrorPanel);

        getContentPane().add(topPanel);

        lowerPanel.setBackground(new java.awt.Color(204, 204, 255));
        lowerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lowerPanel.setLayout(new java.awt.GridLayout(1, 2));

        lowerLeftPanel.setBackground(new java.awt.Color(225, 225, 225));

        lowerLeftScrollPanel.setBorder(null);

        lowerLeftTextArea.setBackground(new java.awt.Color(225, 225, 225));
        lowerLeftTextArea.setColumns(20);
        lowerLeftTextArea.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lowerLeftTextArea.setRows(5);
        lowerLeftTextArea.setBorder(null);
        lowerLeftScrollPanel.setViewportView(lowerLeftTextArea);

        javax.swing.GroupLayout lowerLeftPanelLayout = new javax.swing.GroupLayout(lowerLeftPanel);
        lowerLeftPanel.setLayout(lowerLeftPanelLayout);
        lowerLeftPanelLayout.setHorizontalGroup(
            lowerLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lowerLeftPanelLayout.createSequentialGroup()
                .addGap(0, 66, Short.MAX_VALUE)
                .addComponent(lowerLeftScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        lowerLeftPanelLayout.setVerticalGroup(
            lowerLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lowerLeftPanelLayout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(lowerLeftScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        lowerPanel.add(lowerLeftPanel);

        lowerRightPanel.setBackground(new java.awt.Color(225, 225, 225));

        lowerRightScrollPanel.setBorder(null);

        lowerRightTextArea.setBackground(new java.awt.Color(225, 225, 225));
        lowerRightTextArea.setColumns(20);
        lowerRightTextArea.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        lowerRightTextArea.setRows(5);
        lowerRightTextArea.setBorder(null);
        lowerRightScrollPanel.setViewportView(lowerRightTextArea);

        javax.swing.GroupLayout lowerRightPanelLayout = new javax.swing.GroupLayout(lowerRightPanel);
        lowerRightPanel.setLayout(lowerRightPanelLayout);
        lowerRightPanelLayout.setHorizontalGroup(
            lowerRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowerRightPanelLayout.createSequentialGroup()
                .addComponent(lowerRightScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        lowerRightPanelLayout.setVerticalGroup(
            lowerRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lowerRightPanelLayout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(lowerRightScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        lowerPanel.add(lowerRightPanel);

        getContentPane().add(lowerPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard_presentation.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard_presentation.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard_presentation.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard_presentation.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard_presentation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ErrorPanel;
    private javax.swing.JPanel Tests;
    private javax.swing.JLabel cartridgeLabel;
    private javax.swing.JPanel cartridgePanel;
    private javax.swing.JTextArea cartridgeTextArea;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JTextArea errorsTextArea;
    private javax.swing.JLabel instrumentLabel;
    private javax.swing.JPanel instrumentPanel;
    private javax.swing.JTextArea instrumentTextArea;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel lowerLeftPanel;
    private javax.swing.JScrollPane lowerLeftScrollPanel;
    private javax.swing.JTextArea lowerLeftTextArea;
    private javax.swing.JPanel lowerPanel;
    private javax.swing.JPanel lowerRightPanel;
    private javax.swing.JScrollPane lowerRightScrollPanel;
    private javax.swing.JTextArea lowerRightTextArea;
    private javax.swing.JLabel testLabel;
    private javax.swing.JTextArea testsTextArea;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
