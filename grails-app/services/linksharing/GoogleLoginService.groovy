package linksharing

import com.google.api.client.http.HttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import grails.transaction.Transactional
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.json.gson.GsonFactory;



@Transactional
class GoogleLoginService {

    def getUserDetails(String idTokenString){
        HttpTransport  transport = new NetHttpTransport()
        JsonFactory jsonFactory = new GsonFactory()
        String[] ids = {"129731413523-r17lmi4ndikrmffuos8sqmqon0m7dj00.apps.googleusercontent.com"}
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Arrays.asList(ids))
        // If you retrieved the token on Android using the Play Services 8.3 API or newer, set
        // the issuer to "https://accounts.google.com". Otherwise, set the issuer to
        // "accounts.google.com". If you need to verify tokens from multiple sources, build
        // a GoogleIdTokenVerifier for each issuer and try them both.
                .setIssuer("https://accounts.google.com")
                .build();



        GoogleIdToken idToken = GoogleIdToken.parse(jsonFactory,idTokenString)
        println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
        println idToken
        println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"

            Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");
            println email
            println name
            println familyName
            String uuid =UUID.randomUUID().toString()
            User user = new User(firstName: givenName,lastName: familyName,password: uuid,confirmPassword: uuid,userName: givenName+uuid,active: true,admin: false)
        return  user

            // Use or store profile information
            // ...
    }
}