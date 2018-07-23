package jp.co.chat.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;

public class GoogleOauth2 {

	private static final String APPLICATION_NAME = "DialogFlow Sheets Cooperation";

    private JsonFactory JSON_FACTORY;
    private HttpTransport HTTP_TRANSPORT;

    private final List<String> SCOPES =
        //Arrays.asList(ReportsScopes.ADMIN_REPORTS_AUDIT_READONLY);
    	Arrays.asList(SheetsScopes.SPREADSHEETS_READONLY);
    	/*
    	Arrays.asList(ReportsScopes.ADMIN_REPORTS_AUDIT_READONLY,
    			SheetsScopes.SPREADSHEETS,
    			GmailScopes.GMAIL_READONLY);
		*/
    private String REDIRECT_URL;

    // JSON認証でない場合
    // start
    /*
    private String CLIENT_ID;
    private String CLIENT_SECRET;
    */
    // end

    public GoogleOauth2() throws GeneralSecurityException, IOException {

    	HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    	JSON_FACTORY = JacksonFactory.getDefaultInstance();


        // JSON認証でない場合
        // start
    	/*
    	REDIRECT_URL = "";
    	CLIENT_ID = "";
    	CLIENT_SECRET = "";
    	*/
        // end

    }

    public GoogleAuthorizationCodeFlow getFlow() throws IOException {

    	//JSON認証
    	//start
    	FileInputStream jsonFile = new FileInputStream("WEB-INF/json/client_secret.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(jsonFile));
		REDIRECT_URL = clientSecrets.getDetails().getRedirectUris().get(0);

    	GoogleAuthorizationCodeFlow flow =
    			new GoogleAuthorizationCodeFlow.Builder(
    					HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
    			.setAccessType("offline").setApprovalPrompt("force")
    			.build();
    	//end


    	// JSON認証でない場合
        // start
    	/*
    	GoogleAuthorizationCodeFlow flow =
    			new GoogleAuthorizationCodeFlow.Builder(
    					HTTP_TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, SCOPES)
    			.setAccessType("offline").setApprovalPrompt("force")
    			.build();
    			*/
        // end

    	return flow;
    }

    /**
     * 初回認証
     * @return
     * @throws IOException
     */
    public String authorize() throws IOException {

    	GoogleAuthorizationCodeFlow flow = getFlow();

       return flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URL).build();
    }

    /**
     * コールバック
     * @param code
     * @return
     * @throws IOException
     */
    public String oauth2CallBack(String code) throws IOException {

    	GoogleAuthorizationCodeFlow flow = getFlow();

    	GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URL).execute();

    	return response.getRefreshToken();
    }

    /**
     * リフレッシュトークン
     * @param refreshToken
     * @return
     * @throws IOException
     */
    public GoogleCredential getCredetionl(String refreshToken) throws IOException {

    	//JSON認証
    	//start

    	FileInputStream jsonFile = new FileInputStream("WEB-INF/json/client_secret.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(jsonFile));

    	GoogleCredential credential =
    			new GoogleCredential.Builder()
    			.setTransport(HTTP_TRANSPORT)
    			.setJsonFactory(JSON_FACTORY)
    			.setClientSecrets(clientSecrets)
    			.build();

    	//end

    	// JSON認証でない場合
        // start
    	/*
    	GoogleCredential credential =
    			new GoogleCredential.Builder()
    			.setTransport(HTTP_TRANSPORT)
    			.setJsonFactory(JSON_FACTORY)
    			.setClientSecrets(CLIENT_ID, CLIENT_SECRET)
    			.build();
    			*/
        // end

    	credential.setRefreshToken(refreshToken);

    	credential.refreshToken();

    	return credential;

    }

    public Sheets getSheetsService(Credential credential) throws IOException {

	    return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
	            .setApplicationName(APPLICATION_NAME)
	            .build();
    }

}
