package facebookutil.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;


public abstract class Google20Example {

    private static final String NETWORK_NAME = "G+";
    private static final String PROTECTED_RESOURCE_URL =
            "https://www.googleapis.com/plus/v1/people/me";
    
    private static ResourceBundle mySecrets;
       
    
    public static void main (String ... args) {
        // Replace these with your client id and secret
        mySecrets = ResourceBundle.getBundle("facebookutil/secret");
        final String clientId = mySecrets.getString("googleId");
        final String clientSecret = mySecrets.getString("googleSecret");
        //final String secretState = "secret" + new Random().nextInt(999_999);
        final OAuth20Service service = new ServiceBuilder()
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .scope("https://www.googleapis.com/auth/plus.login") // replace with desired scope     
                //.state(secretState)
                .callback("https://github.com/duke-compsci308-spring2016/voogasalad_GitDepends")
                .build(GoogleApi20.instance());
        final Scanner in = new Scanner(System.in, "UTF-8");

        System.out.println("=== " + NETWORK_NAME + "'s OAuth Workflow ===");
        System.out.println();

        // Obtain the Authorization URL
        System.out.println("Fetching the Authorization URL...");
        // pass access_type=offline to get refresh token
        // https://developers.google.com/identity/protocols/OAuth2WebServer#preparing-to-start-the-oauth-20-flow
        final Map<String, String> additionalParams = new HashMap<>();
        additionalParams.put("access_type", "offline");
        // force to reget refresh token (if usera are asked not the first time)
        additionalParams.put("prompt", "consent");
        final String authorizationUrl = service.getAuthorizationUrl(additionalParams);
        System.out.println("Got the Authorization URL!");
        System.out.println("Now go and authorize ScribeJava here:");
        System.out.println(authorizationUrl);
        System.out.println("And paste the authorization code here");
        System.out.print(">>");
        final String code = in.nextLine();
        System.out.println();

        // Trade the Request Token and Verfier for the Access Token
        System.out.println("Trading the Request Token for an Access Token...");
        OAuth2AccessToken accessToken = service.getAccessToken(code);
        System.out.println("Got the Access Token!");
        System.out.println("(if your curious it looks like this: " + accessToken +
                           ", 'rawResponse'='" + accessToken.getRawResponse() + "')");

        System.out.println("Refreshing the Access Token...");
        accessToken = service.refreshAccessToken(accessToken.getRefreshToken());
        System.out.println("Refreshed the Access Token!");
        System.out.println("(if your curious it looks like this: " + accessToken +
                           ", 'rawResponse'='" + accessToken.getRawResponse() + "')");
        System.out.println();

        // Now let's go and ask for a protected resource!
        System.out.println("Now we're going to access a protected resource...");
        while (true) {
            System.out
                    .println("Paste fieldnames to fetch (leave empty to get profile, 'exit' to stop example)");
            System.out.print(">>");
            final String query = in.nextLine();
            System.out.println();

            final String requestUrl;
            if ("exit".equals(query)) {
                break;
            }
            else if (query == null || query.isEmpty()) {
                requestUrl = PROTECTED_RESOURCE_URL;
            }
            else {
                requestUrl = PROTECTED_RESOURCE_URL + "?fields=" + query;
            }

            final OAuthRequest request = new OAuthRequest(Verb.GET, requestUrl, service);
            service.signRequest(accessToken, request);
            final Response response = request.send();
            System.out.println();
            System.out.println(response.getCode());
            System.out.println(response.getBody());

            System.out.println();
        }
    }
}
