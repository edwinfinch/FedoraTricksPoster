
import java.awt.Desktop;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import org.json.*;

/*
 * DataFramework is the complete heart of this application, it handles all of the communication between the
 * Internet and Java, handles the username/password/token stuff, and all other functions pertaining to data
 * like getting a user's posts.
 */

public class DataFramework {
    
    private final String CHECK_URL = "https://fedoratricks.com/users/check.php", LOGIN_URL = "https://fedoratricks.com/users/login/";
    private final String LOGOUT_URL = "https://fedoratricks.com/users/logout/", CREATE_URL = "https://fedoratricks.com/users/create/";
    private final String POST_URL = "https://fedoratricks.com/users/post/", GET_POSTS_URL = "https://fedoratricks.com/users/getposts/";
    public final String DEFAULT_YOUTUBE_IMAGE = "http://i.imgur.com/cQzweQr.png";
    
    private final String USER_AGENT = "Mozilla/5.0";
    public String sessionToken = "noTOKEN";
    private String currentUsername, currentPassword;
    
    public Image videoNotFound;

    //Main method for getting a result or posting, can be used very dynamically to input data to 
    //Fedoratricks.com or can be used to get a result (ie. the user exists)
    public String sendPost(String params, String url) throws Exception {
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        //Sets the properties
        con.setRequestMethod("POST");
        con.addRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        //Writes the bytes, sends it off
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(params);
        wr.flush();
        wr.close();
        //Gets the response
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        if(!params.contains("&password")){
            System.out.println("Post parameters : " + params);
        }
        else{
            System.out.println("Post parameters hidden (password included)");
        }
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        
        System.out.println("Response : " + response.toString());
        
        return response.toString();
    }
    //Gets the array of posts that the user has made in the past
    public Post[] getPosts(String username){
        //Site uses JSON format for API
        JSONObject jsonResponse = new JSONObject();
        Post[] posts = new Post[0];
        //Gets the JSON data
        try{
            jsonResponse = new JSONObject(sendPost("username=" + username + "&format=json", GET_POSTS_URL));
        
            //Gets the posts array
        JSONArray postArray = jsonResponse.getJSONArray("posts");
        posts = new Post[postArray.length()-1];
        /*
            Runs through each post in the array, sets the content, extra, etc. accordingly 
        */
        for(int i = 0; i < postArray.length()-1; i++){
            String content = postArray.getJSONObject(i).getString("content");
            if(content.replace(" ", "").equals("")){
                content = "NO_CONTENT";
            }
            String extra = postArray.getJSONObject(i).getString("extra");
            if(extra.replace(" ", "").equals("")){
                content = "NO_EXTRA";
            }
            //The posted time and the type are filled out regardless of the user wanting them to be :P
            String posted = postArray.getJSONObject(i).getString("posted");
            String type = postArray.getJSONObject(i).getString("type");
            
            //Sets the post and generates resources for that post in the index
            posts[i] = new Post(type, content, extra, posted, username);
            posts[i].resourcesResult = posts[i].generateResources();
        }}catch(Exception e){}
        
        return posts;
    }
    //Gets the current username 
    public String getUsername(){
        return currentUsername;
    }
    //Set the current username
    public void setUsername(String username){
        currentUsername = username;
    }
    //Opens the webpage, is private because this is only used internally
    private void openWebPage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Desktop not supported!");
        }
    }
    //Public open webpage for other classes to access
    public void openWebPage(URL url){
        try {
            openWebPage(url.toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Post to the site with the parameters inputted, and then return the result that the site returns
    public int post(String params){
        try{
            String result = sendPost(params, POST_URL);
            if(result.equals("invalid_parameters")){
                return -1;
            }
            else if(result.equals("invalid_credentialsV")){
                return -2;
            }
        }
        catch(Exception e){
            return -3;
        }
        return 0;
    }
    //All post functions are similar, they grab the content from the instance and then sets the parameters
    //Later sending it off to the post function above where it sends it to the site 
    public int postPhoto(String link, String subtitle){
        String parameters = "postType=photo&link=" + link + "&description=" + subtitle + "&username=" + currentUsername
                + "&password=" + currentPassword + "&sessionToken=" + sessionToken;
        return post(parameters);
    }
    
    public int postLink(String link, String description){
        String parameters = "postType=link&link=" + link + "&description=" + description + "&username=" + currentUsername
                + "&password=" + currentPassword + "&sessionToken=" + sessionToken;
        return post(parameters);
    }
    
    public int postVideo(String url){
        String parameters = "postType=youtube&youtubeVideo=" + getYouTubeShort(url) + "&username=" + currentUsername
                + "&password=" + currentPassword + "&sessionToken=" + sessionToken;
        return post(parameters);
    }
    
    public int postText(String text){
        String parameters = "postType=text&textContent=" + text + "&username=" + currentUsername
                + "&password=" + currentPassword + "&sessionToken=" + sessionToken;
        return post(parameters);
    }
    //End 
    
    //Checks if a user exists/username is taken
    public boolean usernameTaken(String username){
        try{
            String postResult = sendPost("username=" + username, CHECK_URL);
            if(postResult.equals("Username available")){
                return false;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
    
    //Logs in using the username inputted and password, grabs the session token as a result and then saves it for later
    public boolean login(String username, String password){
        try{
            this.sessionToken = sendPost("username=" + username + "&password=" + password, LOGIN_URL);
            if(this.sessionToken.equals("invalid")){
                return false;
            }
            this.currentUsername = username;
            this.currentPassword = password;
            videoNotFound = getImage("http://i.imgur.com/cQzweQr.png");
            //System.out.println("Session token " )
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    //Logs out of the current session, invalidates token server-side
    public boolean logout(){
        String parameters = "sessionToken=" + this.sessionToken + "&username=" + currentUsername;
        try{
            String response = sendPost(parameters, LOGOUT_URL);
            if(response.equals("loggedout")){
                return true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    //Creates an account with username and password inputted which are checked for syntax before
    public boolean createAccount(String username, String password){
        String parameters = "username=" + username + "&password=" + password;
        String result = "nothing";
        try{
            result = sendPost(parameters, CREATE_URL);
        }
        catch(Exception e){
            return false;
        }
        if(result.equals("error")){
            return false;
        }
        this.sessionToken = result;
        
        return true;
    }
    
    //Gets an image from a URL using ImageIO
    public Image getImage(String url){
        Image returnImage = null;
        try{
            URL actualURL = new URL(url);
            returnImage = ImageIO.read(actualURL);
        }
        catch(Exception e){ 
            e.printStackTrace();
        }
        return returnImage;
    }
    
    //Checks if a video exists through youtube API
    public boolean videoExists(String url){
        String shortName = getYouTubeShort(url);
        if(shortName.equals("tooShort")){
            return false;
        }
        if(getImage("http://img.youtube.com/vi/" + shortName + "/hqdefault.jpg") == null){
            return false;
        }
        return true;
    }
    
    //Condenses a youtube url inputted into a shortlink to simplify amount of data stored and not having
    //to break it up constantly
    public String getYouTubeShort(String url){
        String actualUrl = "error";
        int index = -100;
        if(url.contains("youtu.be")){
            System.out.println("using youtu.be");
            index = url.indexOf("e/");
            if(url.substring(index+2).length() > 10){
                actualUrl = url.substring(index+2, index+13);
            }
            else{
                return "tooShort";
            }
        }
        else{
            index = url.indexOf("=");
            if(url.substring(index+1).length() > 10){
                actualUrl = url.substring(index+1, index+12);
            }
            else{
                return "tooShort";
            }
        }
        return actualUrl;
    }
    //Gets the youtube image from the url using getImage()
    public Image getYoutubeImage(String url){
        Image image = null;
        String actualUrl = "";
        URL imageURL = null;
        try{
            actualUrl = getYouTubeShort(url);
            //System.out.println(actualUrl + " index: " + index);
            actualUrl = "http://img.youtube.com/vi/" + actualUrl + "/hqdefault.jpg";
            image = getImage(actualUrl);
        }
        catch(Exception e){
            //e.printStackTrace();
            return null;
        }
        return image;
    }
}
