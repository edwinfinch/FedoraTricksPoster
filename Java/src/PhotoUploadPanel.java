

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author edwinfinch
 */
public class PhotoUploadPanel extends javax.swing.JPanel {

    /**
     * Creates new form PhotoUploadPanel
     */
    public PhotoUploadPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        photoTitle = new javax.swing.JLabel();
        linkInput = new javax.swing.JTextField();
        linkSubtitle = new javax.swing.JLabel();
        subtitleSubtitle = new javax.swing.JLabel();
        subtitleInput = new javax.swing.JTextField();
        hintSubtitle = new javax.swing.JLabel();
        postButton = new javax.swing.JButton();

        photoTitle.setFont(new java.awt.Font("Shree Devanagari 714", 0, 18)); // NOI18N
        photoTitle.setText("Photo Poster");

        linkSubtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        linkSubtitle.setText("Direct Link to Photo");
        linkSubtitle.setToolTipText("");

        subtitleSubtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subtitleSubtitle.setText("Subtitle");

        hintSubtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hintSubtitle.setText("Photos are embedded inside of the webpage.");

        postButton.setText("Post");
        postButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(photoTitle)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(linkInput)
                            .addComponent(subtitleInput)
                            .addComponent(hintSubtitle, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                            .addComponent(linkSubtitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(subtitleSubtitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(postButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(photoTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linkSubtitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linkInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(subtitleSubtitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subtitleInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hintSubtitle)
                .addGap(18, 18, 18)
                .addComponent(postButton)
                .addContainerGap(8, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public DataFramework framework;
    
    //Takes the data from the post, and sends it to the framework, outputs result for user
    private void postButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postButtonActionPerformed
        int result = framework.postPhoto(linkInput.getText(), subtitleInput.getText());
        switch(result){
            case 0:
                photoTitle.setText("Post success");
                break;
            case -1:
                photoTitle.setText("Failed: Invalid parameters");
                break;
            case -2:
                photoTitle.setText("Failed: Invalid credentials");
                break;
            case -3:
                photoTitle.setText("Failed: Internal error");
                break;
        }
    }//GEN-LAST:event_postButtonActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel hintSubtitle;
    private javax.swing.JTextField linkInput;
    private javax.swing.JLabel linkSubtitle;
    private javax.swing.JLabel photoTitle;
    private javax.swing.JButton postButton;
    private javax.swing.JTextField subtitleInput;
    private javax.swing.JLabel subtitleSubtitle;
    // End of variables declaration//GEN-END:variables
}