package argus.view;

import java.io.File;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FilenameUtils;
import argus.model.DataFilePair;
import argus.utils.SystemUtils;
import argus.utils.TauArgusUtils;

/**
 * View class of the open microdata screen.
 *
 * @author Statistics Netherlands
 */
public class DialogOpenMicrodata extends javax.swing.JDialog {

    private static final Logger logger = Logger.getLogger(DialogOpenMicrodata.class.getName());

    // ***** Dialog Return Values *****
    public static final int CANCEL_OPTION = 1;
    public static final int APPROVE_OPTION = 0;
    private int returnValue = CANCEL_OPTION;

    /**
     * Creates new form DialogOpenMicrodata.
     *
     * @param parent the Frame of the main frame.
     * @param modal boolean to set the modal status.
     */
    public DialogOpenMicrodata(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
    }

    /**
     * Sets the data file names.
     *
     * @param dataFileName String containing the data file name.
     * @param metadataFileName String containing the metadata file name.
     */
    public void setDataFileNames(String dataFileName, String metadataFileName) {
        this.textFieldMicrodata.setText(dataFileName);
        this.textFieldMetadata.setText(metadataFileName);
    }

    /**
     * Sets the data field labels.
     *
     * @param dataFileLabel String containing the data file label.
     * @param metadataFileLabel String containing the metadata file label.
     */
    public void setDataFieldLabels(String dataFileLabel, String metadataFileLabel) {
        this.labelMicrodata.setText(dataFileLabel);
        this.labelMetadata.setText(metadataFileLabel);
    }

    /**
     * Shows the dialog.
     *
     * @return Integer containig the return value.
     */
    public int showDialog() {
        setVisible(true);
        return this.returnValue;
    }

    /**
     * Gets te microdata file pair.
     *
     * @return DataFilePair containing the microdata file pair.
     */
    public DataFilePair getMicrodataFilePair() {
        return new DataFilePair(this.textFieldMicrodata.getText(), this.textFieldMetadata.getText());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        labelMicrodata = new javax.swing.JLabel();
        textFieldMicrodata = new javax.swing.JTextField();
        buttonMicrodata = new javax.swing.JButton();
        labelMetadata = new javax.swing.JLabel();
        textFieldMetadata = new javax.swing.JTextField();
        buttonMetadata = new javax.swing.JButton();
        buttonOK = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();

        fileChooser.setDialogTitle("");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Open Microdata");
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                DialogClosing(evt);
            }
        });

        labelMicrodata.setLabelFor(textFieldMicrodata);
        labelMicrodata.setText("Microdata:");

        buttonMicrodata.setText("...");
        buttonMicrodata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMicrodataActionPerformed(evt);
            }
        });

        labelMetadata.setLabelFor(textFieldMetadata);
        labelMetadata.setText("Metadata (optional): ");

        buttonMetadata.setText("...");
        buttonMetadata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMetadataActionPerformed(evt);
            }
        });

        buttonOK.setText("OK");
        buttonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOKActionPerformed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelMicrodata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelMetadata, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textFieldMetadata, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                    .addComponent(textFieldMicrodata))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonMicrodata, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(buttonMetadata, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(428, Short.MAX_VALUE)
                .addComponent(buttonOK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCancel)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {buttonCancel, buttonOK});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonMicrodata, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelMicrodata)
                        .addComponent(textFieldMicrodata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMetadata)
                    .addComponent(textFieldMetadata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonMetadata))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonOK)
                    .addComponent(buttonCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonMicrodataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMicrodataActionPerformed
        String hs = SystemUtils.getRegString("general", "datadir", "");
        if (!hs.equals("")) {
            File file = new File(hs);
            this.fileChooser.setCurrentDirectory(file);
        }
        this.fileChooser.setDialogTitle("Open Microdata");
        this.fileChooser.setSelectedFile(new File(""));
        this.fileChooser.resetChoosableFileFilters();
        // filters are shown in order of declaration, setFileFilter sets the default filter
        this.fileChooser.setFileFilter(new FileNameExtensionFilter("Microdata (*.asc)", "asc"));
        this.fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Comma Separated File (*.csv)", "csv"));
        this.fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Microdata (*.dat)", "dat"));
        this.fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("SPSS system file (*.sav)", "sav"));
        if (this.fileChooser.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            this.textFieldMicrodata.setText(this.fileChooser.getSelectedFile().toString());
            hs = this.fileChooser.getSelectedFile().getPath();
            if (!hs.equals("")) {
                SystemUtils.putRegString("general", "datadir", hs);
            }
            setMetadataFileNameIfPossible();
        }
    }//GEN-LAST:event_buttonMicrodataActionPerformed

    private void buttonMetadataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMetadataActionPerformed
        String hs = SystemUtils.getRegString("general", "datadir", "");
        if (!hs.equals("")) {
            File file = new File(hs);
            this.fileChooser.setCurrentDirectory(file);
        }
        this.fileChooser.setDialogTitle("Open Metadata");
        this.fileChooser.setSelectedFile(new File(""));
        this.fileChooser.resetChoosableFileFilters();
        this.fileChooser.setFileFilter(new FileNameExtensionFilter("Metadata (*.rda)", "rda"));
        if (this.fileChooser.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            this.textFieldMetadata.setText(this.fileChooser.getSelectedFile().toString());
            hs = this.fileChooser.getSelectedFile().getPath();
            if (!hs.equals("")) {
                SystemUtils.putRegString("general", "datadir", hs);
            }
        }
    }//GEN-LAST:event_buttonMetadataActionPerformed

    private void buttonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOKActionPerformed
        if (this.textFieldMicrodata.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please specify microdata file.");
            return;
        }

        if (!TauArgusUtils.ExistFile(this.textFieldMicrodata.getText())) {
            JOptionPane.showMessageDialog(this, "Microdata file " + this.textFieldMicrodata.getText() + " does not exist.");
            return;
        }

        if (!this.textFieldMetadata.getText().trim().equals("")) {
            if (!TauArgusUtils.ExistFile(this.textFieldMetadata.getText())) {
                JOptionPane.showMessageDialog(this, "Metadata file " + this.textFieldMetadata.getText() + " does not exist.");
                return;
            }
        }
        SystemUtils.writeLogbook("Microdata file: " + this.textFieldMicrodata.getText() + " has been opened");
        if (!this.textFieldMetadata.getText().trim().equals("")) {
            SystemUtils.writeLogbook("Metadata file: " + this.textFieldMetadata.getText() + " has been opened");
        }
        this.returnValue = DialogOpenMicrodata.APPROVE_OPTION;
        setVisible(false);
    }//GEN-LAST:event_buttonOKActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        setVisible(false);
        this.returnValue = DialogOpenMicrodata.CANCEL_OPTION;
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void DialogClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_DialogClosing
        setVisible(false);
    }//GEN-LAST:event_DialogClosing

    /* 
     * In many situations the metadata filename has the same name as the 
     * microdata filename, only with another extension.
     */
    private void setMetadataFileNameIfPossible() {
        String fileName = this.textFieldMicrodata.getText();
        int extensionIndex = FilenameUtils.indexOfExtension(fileName);
        String baseFileName;
        if (extensionIndex == -1) {
            baseFileName = fileName;
        } else {
            baseFileName = fileName.substring(0, extensionIndex);
        }
        String metadataFileName = baseFileName + ".rda";
        File file = new File(metadataFileName);
        if (file.exists() && file.isFile()) {
            this.textFieldMetadata.setText(metadataFileName);
        }
    }

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
            // Anco 1.6
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        } catch (ClassNotFoundException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DialogOpenMicrodata dialog = new DialogOpenMicrodata(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.showDialog();
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonMetadata;
    private javax.swing.JButton buttonMicrodata;
    private javax.swing.JButton buttonOK;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel labelMetadata;
    private javax.swing.JLabel labelMicrodata;
    private javax.swing.JTextField textFieldMetadata;
    private javax.swing.JTextField textFieldMicrodata;
    // End of variables declaration//GEN-END:variables

}
