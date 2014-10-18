package us.screenshottr.java.render.settings;

import us.screenshottr.java.ScreenShottr;
import us.screenshottr.java.api.IConfiguration;
import us.screenshottr.java.config.ConfigKey;
import us.screenshottr.java.render.ShotPainter;

public class ShotSettingsFrame extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    private ShotPainter painter;
    private IConfiguration config;

    public ShotSettingsFrame(ShotPainter painter) {
        this.painter = painter;
        this.config = painter.getApp().getConfig();
        initComponents();
    }

    /*
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTitle = new javax.swing.JPanel();
        labelTitle = new javax.swing.JLabel();
        tabPane = new javax.swing.JTabbedPane();
        panelGeneral = new javax.swing.JPanel();
        scrollBarOpacity = new javax.swing.JScrollBar();
        labelOpacity = new javax.swing.JLabel();
        labelOpacityValue = new javax.swing.JLabel();
        panelURLOptions = new javax.swing.JPanel();
        checkBoxCopyClipboard = new javax.swing.JCheckBox();
        checkBoxURLLandingPage = new javax.swing.JCheckBox();
        checkBoxOpenBrowser = new javax.swing.JCheckBox();
        labelURLOptions = new javax.swing.JLabel();
        panelAdvanced = new javax.swing.JPanel();
        panelConnectionSettings = new javax.swing.JPanel();
        checkBoxURLEncrypt = new javax.swing.JCheckBox();
        checkBoxURLHTTPS = new javax.swing.JCheckBox();
        buttonResetSettings = new javax.swing.JButton();
        labelConnectionSettings = new javax.swing.JLabel();
        buttonSave = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(ScreenShottr.NAME + " - Settings");
        setAlwaysOnTop(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        panelTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setText("Settings");

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelTitleLayout.setVerticalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(labelTitle))
        );

        tabPane.setFocusable(false);

        scrollBarOpacity.setMinimum(10);
        scrollBarOpacity.setOrientation(javax.swing.JScrollBar.HORIZONTAL);
        scrollBarOpacity.setValue(20);
        scrollBarOpacity.setVisibleAmount(0);
        scrollBarOpacity.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                scrollBarOpacityAdjustmentValueChanged(evt);
            }
        });

        labelOpacity.setText("Selection Opacity:");

        labelOpacityValue.setText("20%");

        panelURLOptions.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        checkBoxCopyClipboard.setSelected(true);
        checkBoxCopyClipboard.setText("Copy image URL to Clipboard");

        checkBoxURLLandingPage.setText("Show image in landing page (browser)");

        checkBoxOpenBrowser.setSelected(true);
        checkBoxOpenBrowser.setText("Open image in browser");

        javax.swing.GroupLayout panelURLOptionsLayout = new javax.swing.GroupLayout(panelURLOptions);
        panelURLOptions.setLayout(panelURLOptionsLayout);
        panelURLOptionsLayout.setHorizontalGroup(
            panelURLOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelURLOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelURLOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkBoxCopyClipboard)
                    .addComponent(checkBoxOpenBrowser)
                    .addComponent(checkBoxURLLandingPage))
                .addContainerGap(241, Short.MAX_VALUE))
        );
        panelURLOptionsLayout.setVerticalGroup(
            panelURLOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelURLOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkBoxCopyClipboard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkBoxOpenBrowser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3, Short.MAX_VALUE)
                .addComponent(checkBoxURLLandingPage)
                .addContainerGap())
        );

        labelURLOptions.setText("URL Options");

        javax.swing.GroupLayout panelGeneralLayout = new javax.swing.GroupLayout(panelGeneral);
        panelGeneral.setLayout(panelGeneralLayout);
        panelGeneralLayout.setHorizontalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelURLOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollBarOpacity, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelGeneralLayout.createSequentialGroup()
                                .addComponent(labelOpacity)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelOpacityValue))
                            .addComponent(labelURLOptions))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelGeneralLayout.setVerticalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelOpacity)
                    .addComponent(labelOpacityValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollBarOpacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelURLOptions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelURLOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        tabPane.addTab("General settings", panelGeneral);

        panelConnectionSettings.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        checkBoxURLEncrypt.setSelected(true);
        checkBoxURLEncrypt.setText("Encrypt images");

        checkBoxURLHTTPS.setSelected(true);
        checkBoxURLHTTPS.setText("Send images over HTTPS");

        buttonResetSettings.setText("Reset All Setings");
        buttonResetSettings.setFocusable(false);
        buttonResetSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonResetSettingsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelConnectionSettingsLayout = new javax.swing.GroupLayout(panelConnectionSettings);
        panelConnectionSettings.setLayout(panelConnectionSettingsLayout);
        panelConnectionSettingsLayout.setHorizontalGroup(
            panelConnectionSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConnectionSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelConnectionSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkBoxURLEncrypt)
                    .addComponent(checkBoxURLHTTPS)
                    .addComponent(buttonResetSettings))
                .addContainerGap(299, Short.MAX_VALUE))
        );
        panelConnectionSettingsLayout.setVerticalGroup(
            panelConnectionSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConnectionSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkBoxURLEncrypt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkBoxURLHTTPS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addComponent(buttonResetSettings)
                .addContainerGap())
        );

        labelConnectionSettings.setText("Connection Settings");

        javax.swing.GroupLayout panelAdvancedLayout = new javax.swing.GroupLayout(panelAdvanced);
        panelAdvanced.setLayout(panelAdvancedLayout);
        panelAdvancedLayout.setHorizontalGroup(
            panelAdvancedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdvancedLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAdvancedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelConnectionSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelAdvancedLayout.createSequentialGroup()
                        .addComponent(labelConnectionSettings)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelAdvancedLayout.setVerticalGroup(
            panelAdvancedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdvancedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelConnectionSettings)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelConnectionSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabPane.addTab("Advanced", panelAdvanced);

        buttonSave.setText("Save");
        buttonSave.setFocusable(false);
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.setFocusable(false);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabPane, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void loadValues() {
        float opacity = config.getFloat(ConfigKey.OPACITY);
        scrollBarOpacity.setValue((int) ((opacity / ShotSettings.OPACITY_MAX) * 100f));
        labelOpacityValue.setText((int) (calcOpacity() * 100) + "%");

        boolean copyToClipboard = config.getBoolean(ConfigKey.COPY_TO_CLIBOARD);
        checkBoxCopyClipboard.setSelected(copyToClipboard);

        boolean openInBrowser = config.getBoolean(ConfigKey.OPEN_IN_BROWSER);
        checkBoxOpenBrowser.setSelected(openInBrowser);

        boolean urlHTTPS = config.getBoolean(ConfigKey.URL_HTTPS);
        checkBoxURLHTTPS.setSelected(urlHTTPS);

        boolean urlLandingPage = config.getBoolean(ConfigKey.URL_LANDING_PAGE);
        checkBoxURLLandingPage.setSelected(urlLandingPage);


        boolean urlEncrypt = config.getBoolean(ConfigKey.URL_ENCRYPT);
        checkBoxURLEncrypt.setSelected(urlEncrypt);
    }

    public void storeValues() {
        config.set(ConfigKey.OPACITY, calcOpacity());
        config.set(ConfigKey.COPY_TO_CLIBOARD, checkBoxCopyClipboard.isSelected());
        config.set(ConfigKey.OPEN_IN_BROWSER, checkBoxOpenBrowser.isSelected());
        config.set(ConfigKey.URL_HTTPS, checkBoxURLHTTPS.isSelected());
        config.set(ConfigKey.URL_LANDING_PAGE, checkBoxURLLandingPage.isSelected());
        config.set(ConfigKey.URL_ENCRYPT, checkBoxURLEncrypt.isSelected());
    }

    private void scrollBarOpacityAdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_scrollBarOpacityAdjustmentValueChanged
        labelOpacityValue.setText((int) (calcOpacity() * 100) + "%");
    }//GEN-LAST:event_scrollBarOpacityAdjustmentValueChanged

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        storeValues();
        config.save();
        super.setVisible(false);
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        super.setVisible(false);
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonResetSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResetSettingsActionPerformed
        config.resetAll();
        loadValues();
    }//GEN-LAST:event_buttonResetSettingsActionPerformed

    private float calcOpacity() {
        return Math.max((scrollBarOpacity.getValue() / 100f) * ShotSettings.OPACITY_MAX, ShotSettings.OPACITY_MIN);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonResetSettings;
    private javax.swing.JButton buttonSave;
    private javax.swing.JCheckBox checkBoxCopyClipboard;
    private javax.swing.JCheckBox checkBoxOpenBrowser;
    private javax.swing.JCheckBox checkBoxURLEncrypt;
    private javax.swing.JCheckBox checkBoxURLHTTPS;
    private javax.swing.JCheckBox checkBoxURLLandingPage;
    private javax.swing.JLabel labelConnectionSettings;
    private javax.swing.JLabel labelOpacity;
    private javax.swing.JLabel labelOpacityValue;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JLabel labelURLOptions;
    private javax.swing.JPanel panelAdvanced;
    private javax.swing.JPanel panelConnectionSettings;
    private javax.swing.JPanel panelGeneral;
    private javax.swing.JPanel panelTitle;
    private javax.swing.JPanel panelURLOptions;
    private javax.swing.JScrollBar scrollBarOpacity;
    private javax.swing.JTabbedPane tabPane;
    // End of variables declaration//GEN-END:variables
}