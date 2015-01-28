import java.awt.Image;

/*
Post class is the heart of all sort of post related grabbing, it's used to store
all of the information fetched from a JSON response from the web. Stores type,
content, extra, posted, etc.
*/

public class Post {
    private final String user;
    private final DataFramework framework;
    public Image youtubeImage = null, photoImage = null;
    public String type, content, extra, posted;
    public int resourcesResult = -2;
    
    //Constructor sets up the post, creates a new framework for getting resources
    public Post(String TYPE, String CONTENT, String EXTRA, String POSTED, String USER){
        framework = new DataFramework();
        type = TYPE;
        content = CONTENT;
        extra = EXTRA;
        posted = POSTED;
        user = USER;
    }
    //Generates the resources and outputs the according result (if it was a success or not)
    public int generateResources(){
        int status = 0;
        switch(type){
            case "photo":
                photoImage = framework.getImage(content);
                if(photoImage == null){
                    status = -1;
                }
                break;
            case "youtube":
                youtubeImage = framework.getImage("http://img.youtube.com/vi/" + this.content + "/hqdefault.jpg");
                if(youtubeImage == null){
                    status = -1;
                }
                break;
        }
        return status;
    }
}
 