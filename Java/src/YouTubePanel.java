/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author edwinfinch
 */
public class YouTubePanel extends javax.swing.JPanel {

    /**
     * Creates new form YouTubePanel
     */
    public YouTubePanel() {
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

        youtubeTitle = new javax.swing.JLabel();
        videoInput = new javax.swing.JTextField();
        resultLabel = new javax.swing.JLabel();
        postButton = new javax.swing.JButton();
        extraMessage = new javax.swing.JLabel();

        youtubeTitle.setFont(new java.awt.Font("Shree Devanagari 714", 0, 18)); // NOI18N
        youtubeTitle.setText("YouTube Video");

        resultLabel.setFont(new java.awt.Font("Shree Devanagari 714", 0, 14)); // NOI18N
        resultLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resultLabel.setText("No video inputted.");

        postButton.setText("Post");
        postButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postButtonActionPerformed(evt);
            }
        });

        extraMessage.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        extraMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        extraMessage.setText("YouTube videos will be embedded in the web page.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(postButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(videoInput)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(youtubeTitle)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(extraMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)))
                    .addComponent(resultLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(youtubeTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(videoInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resultLabel)
                .addGap(15, 15, 15)
                .addComponent(extraMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(postButton)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    //When the post button is pushed, get the input text
    private void postButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postButtonActionPerformed
        String inputText = videoInput.getText();
        //check if it's a valid format
        if(!inputText.contains("v=") && inputText.length() < 25){
            extraMessage.setText("Link too short.");
            return;
        }
        //check if it exists through the data framework and handle accordingly
        if(tempFramework.videoExists(inputText)){
            extraMessage.setText("Exists. Posting...");
            System.out.println("Exists!");
        }
        else{
            extraMessage.setText("Doesn't exist.");
            return;
        }
        //Print result of post
        int result = tempFramework.postVideo(inputText);
        switch(result){
            case 0:
                youtubeTitle.setText("Post success");
                break;
            case -1:
                youtubeTitle.setText("Failed: Invalid parameters");
                break;
            case -2:
                youtubeTitle.setText("Failed: Invalid credentials");
                break;
            case -3:
                youtubeTitle.setText("Failed: Internal error");
                break;
        }
    }//GEN-LAST:event_postButtonActionPerformed
    
    DataFramework tempFramework = new DataFramework();
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel extraMessage;
    private javax.swing.JButton postButton;
    private javax.swing.JLabel resultLabel;
    private javax.swing.JTextField videoInput;
    private javax.swing.JLabel youtubeTitle;
    // End of variables declaration//GEN-END:variables
}