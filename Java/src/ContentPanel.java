
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author edwinfinch
 */
public class ContentPanel extends javax.swing.JPanel {

    /**
     * Creates new form ContentPanel
     */
    public ContentPanel() {
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

        titleLabel = new javax.swing.JLabel();
        subtitleLabel = new javax.swing.JLabel();
        contentLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textContentArea = new javax.swing.JTextArea();
        shareOnFBLabel = new javax.swing.JLabel();
        shareToTwitterButton = new javax.swing.JLabel();
        shareToRedditButton = new javax.swing.JLabel();

        setLayout(null);

        titleLabel.setFont(new java.awt.Font("Shree Devanagari 714", 0, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Title");
        add(titleLabel);
        titleLabel.setBounds(10, 10, 543, 37);

        subtitleLabel.setFont(new java.awt.Font("Shree Devanagari 714", 0, 12)); // NOI18N
        subtitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subtitleLabel.setText("Subtitle");
        add(subtitleLabel);
        subtitleLabel.setBounds(10, 50, 543, 19);

        contentLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contentLabelMouseClicked(evt);
            }
        });
        add(contentLabel);
        contentLabel.setBounds(6, 74, 543, 361);

        jScrollPane1.setBorder(null);

        textContentArea.setEditable(false);
        textContentArea.setBackground(new java.awt.Color(238, 238, 238));
        textContentArea.setColumns(20);
        textContentArea.setRows(5);
        textContentArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textContentAreaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(textContentArea);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 70, 530, 360);

        shareOnFBLabel.setText(" ");
        shareOnFBLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shareOnFBLabelMouseClicked(evt);
            }
        });
        add(shareOnFBLabel);
        shareOnFBLabel.setBounds(20, 440, 120, 40);

        shareToTwitterButton.setText(" ");
        shareToTwitterButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shareToTwitterButtonMouseClicked(evt);
            }
        });
        add(shareToTwitterButton);
        shareToTwitterButton.setBounds(150, 430, 140, 60);

        shareToRedditButton.setText(" ");
        shareToRedditButton.setToolTipText("");
        shareToRedditButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shareToRedditButtonMouseClicked(evt);
            }
        });
        add(shareToRedditButton);
        shareToRedditButton.setBounds(310, 430, 190, 60);
    }// </editor-fold>//GEN-END:initComponents

    /*
    ContentPanel is the main hub for anything content related, from text posts to youtube posts, etc.
    It loads the content dynamically based on the currently loaded post (loaded from external source)
    and then refreshes it for you on request.
    */
    
    //If a photo is loaded (or youtube), and is clicked, open that according page depending on the type
    private void contentLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contentLabelMouseClicked
        try{
            switch(postContent.type){
                case "photo":
                    URL photoUrl = new URL(postContent.content);
                    framework.openWebPage(photoUrl);
                    break;
                case "youtube":
                    URL ytUrl = new URL("http://youtu.be/" + postContent.content);
                    framework.openWebPage(ytUrl);
                    break;
            }
        }catch(Exception e){}
    }//GEN-LAST:event_contentLabelMouseClicked

    /*
   Share functions all act the same, it passes in the type of URL you want to share to depending on the event and
    the share() function handles the rest
    */
    private void shareOnFBLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shareOnFBLabelMouseClicked
        share(SHARE_FACEBOOK);
    }//GEN-LAST:event_shareOnFBLabelMouseClicked

    private void shareToTwitterButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shareToTwitterButtonMouseClicked
        share(SHARE_TWITTER);
    }//GEN-LAST:event_shareToTwitterButtonMouseClicked

    private void shareToRedditButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shareToRedditButtonMouseClicked
        share(SHARE_REDDIT);
    }//GEN-LAST:event_shareToRedditButtonMouseClicked

    //If there is a link on the text content area then it opens that url
    private void textContentAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textContentAreaMouseClicked
        try{
            switch(postContent.type){
                case "link":
                    URL url = new URL(postContent.content);
                    framework.openWebPage(url);
                    break;
            }
        }
        catch(Exception e){}
    }//GEN-LAST:event_textContentAreaMouseClicked

    //Basic share URLS
    final String[] SHARE_URLS = {
        "https://twitter.com/share?url=", "https://facebook.com/share.php?u=", "http://www.reddit.com/submit?url="
    };
    //Share url keys
    final int SHARE_TWITTER = 0, SHARE_FACEBOOK = 1, SHARE_REDDIT = 2;
    
    //Share function, takes in the url given and then sets up the content depending on the post type
    public void share(int site){
        try{
            URL shareUrl = new URL("https://fedoratricks.com/error/");
            switch(postContent.type){
                case "photo":
                    shareUrl = new URL(SHARE_URLS[site] + postContent.content);
                    break;
                case "youtube":
                    shareUrl = new URL(SHARE_URLS[site] + "http://youtu.be/" + postContent.content);
                    break;
                case "link":
                    shareUrl = new URL(SHARE_URLS[site] + postContent.content);
                    break;
                case "text":
                    shareUrl = new URL(SHARE_URLS[site] + "https://fedoratricks.com/users/?u=" + framework.getUsername());
                    break;
            }
            framework.openWebPage(shareUrl);
        }catch(Exception e){ e.printStackTrace(); }
    }
    
    public Post postContent;
    public DataFramework framework;
    
    //Refreshes the content
    public void refreshContent(){
        //Grabs the time posted and sets it 
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(postContent.posted));
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String timePosted = "Posted: " + day + "/" + month+1 + "/" + year + " at " + hour + ":" + minute + ":" + second;
        switch(postContent.type){
            //Sets the icon of the label as the photo posted, sets the title as the extra the user inputted
            case "photo":
                textContentArea.setVisible(false);
                contentLabel.setVisible(true);
                StretchIcon icon = new StretchIcon(postContent.photoImage);
                contentLabel.setIcon(icon);
                titleLabel.setText(postContent.extra);
                break;
                //Has the youtube video image downloaded from the api on content window load and opens that image
                //accordingly
            case "youtube":
                textContentArea.setVisible(false);
                contentLabel.setVisible(true);
                StretchIcon yticon = new StretchIcon(postContent.youtubeImage);
                contentLabel.setIcon(yticon);
                titleLabel.setText("youtube.com/watch?v=" + postContent.content);
                subtitleLabel.setText(timePosted);
                break;
                //sets the link content
            case "link":
                contentLabel.setIcon(null);
                contentLabel.setVisible(false);
                textContentArea.setVisible(true);
                textContentArea.setText(postContent.content);
                titleLabel.setText(postContent.extra);
                subtitleLabel.setText(timePosted);
                break;
            case "text":
                //Sets the text content
                contentLabel.setIcon(null);
                contentLabel.setVisible(false);
                textContentArea.setVisible(true);
                textContentArea.setText(postContent.content);
                titleLabel.setText("Text Post");
                subtitleLabel.setText(timePosted);
                break;
            default:
                //Otherwise it's an error
                titleLabel.setText("Error");
                subtitleLabel.setText("Unsupported data type: " + postContent.type);
                break;
        }
        //Sets the share button's icons
        shareToTwitterButton.setIcon(new StretchIcon(getClass().getResource("/resources/twitter_share_button.png")));
        shareOnFBLabel.setIcon(new StretchIcon(getClass().getResource("/resources/fb_share_button.png")));
        shareToRedditButton.setIcon(new StretchIcon(getClass().getResource("/resources/reddit_share_button.png")));
    }
    
    //Sets the post and refreshes the content.
    public void loadPost(Post post){
        postContent = post;
        refreshContent();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel contentLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel shareOnFBLabel;
    private javax.swing.JLabel shareToRedditButton;
    private javax.swing.JLabel shareToTwitterButton;
    private javax.swing.JLabel subtitleLabel;
    private javax.swing.JTextArea textContentArea;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}