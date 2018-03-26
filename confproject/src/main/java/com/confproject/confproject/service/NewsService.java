package com.confproject.confproject.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import com.confproject.confproject.dao.NewsDAO;
import com.confproject.confproject.model.News;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.InputStreamReader;


@Service
@Transactional
public class NewsService {
	
	private final NewsDAO newsdao;
	
	public NewsService(NewsDAO newsDAO) {
		this.newsdao = newsDAO;
	}
	
	public List<News> findAll(){
		List<News> lstnews = new ArrayList<>();
		for(News news : newsdao.findAll()){
			lstnews.add(news);
		}
		return lstnews;
	}
	
	public News findNews(int id){
		return newsdao.findById(id).get();
	}
	
	public void save(News news){
		newsdao.save(news);
	}
	
	public void delete(int id){
		newsdao.deleteById(id);
	}	
	
	
	//////////////////////////////////////////////
	/** Application name. */
    private static final String APPLICATION_NAME =
        "Drive API Java Quickstart";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/drive-java-quickstart");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/drive-java-quickstart
     */
    private static final List<String> SCOPES =
        Arrays.asList(DriveScopes.DRIVE);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            //System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     /@throws Exception 
     */
    public static Credential authorize() throws Exception {
        // Load client secrets.
        InputStream in =
            NewsService.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Drive client service.
     * @return an authorized Drive client service
     * @throws Exception 
     */
    public static Drive getDriveService() throws Exception {
        Credential credential = authorize();
        return new Drive.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
    ////////////////////////////////////
    
    public String uploadimg(MultipartFile file, java.io.File filePath) throws Exception
	{

		Drive service = getDriveService();    	
    	File fileMetadata = new File();
    	fileMetadata.setTitle(file.getOriginalFilename());
    	FileContent mediaContent = new FileContent(file.getContentType(), filePath);
    	try {
            File file1 = service.files().insert(fileMetadata, mediaContent)
                    .setFields("id")
                    .execute();
            //return "https://drive.google.com/open?id="+file1.getId().toString();
            return "https://drive.google.com/uc?id="+file1.getId().toString()+"&export=download";
        }
        catch (Exception e){
            System.out.println();
        }
    	return "";
	}
}
