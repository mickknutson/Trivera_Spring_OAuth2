package com.springclass;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public class TestClient {

    private static final Logger logger = LoggerFactory
            .getLogger(TestClient.class);


    String baseUrl;

    String tokenUrl;

    String resourceId;

    String resourceClientId;

    String resourceClientSecret;


    //-----------------------------------------------------------------------//

    public static void main(String ...args){

        TestClient client = new TestClient();

        client.baseUrl = "http://localhost:8080";

        client.tokenUrl = "http://localhost:8080/oauth/token";

        client.resourceId = "microservice-test";

        client.resourceClientId = "oauthClient1";

        client.resourceClientSecret = "oauthClient1Password";


        OAuth2RestTemplate template =  client.template("user1");

        String url = client.baseUrl+"/events/my";
        String expected = "{\"currentUser\":[{\"id\":100,\"summary\":\"Birthday Party\",\"description\":\"This is going to be a great birthday\",\"when\":";

        logger.info(" CALLING: {}", url);
        String result = template.getForObject(url, String.class);
        logger.info(" RESULT: {}", result);

        client.printToken(template.getAccessToken());

        assertThat(result, startsWith(expected));


    }

    //-----------------------------------------------------------------------//

    private OAuth2RestTemplate template(String user){
        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
        resource.setAccessTokenUri(tokenUrl);
        resource.setId(resourceId);
        resource.setClientId(resourceClientId);
        resource.setClientSecret(resourceClientSecret);

        resource.setGrantType("password");

        resource.setScope(Arrays.asList("openid"));

        this.setResourceUser(resource, user);

        return new OAuth2RestTemplate(resource);
    }

    private void setResourceUser(ResourceOwnerPasswordResourceDetails resource,
                                 String user){
        if("admin1".equalsIgnoreCase(user)){
            resource.setUsername("admin1@example.com");
            resource.setPassword("admin1");
        } else if("user1".equalsIgnoreCase(user)) {
            resource.setUsername("user1@example.com");
            resource.setPassword("user1");
        }
    }


    private void printToken(OAuth2AccessToken token){
        logger.info("-------------------------------------------------------");
        logger.info("TokenType: {}", token.getTokenType());
        logger.info("Token Value: {}", token.getValue());
        logger.info("-------------------------------------------------------");
        logger.info("Scope: {}", token.getScope());
        logger.info("Expiration: {}", token.getExpiration());
        logger.info("ExpiresIn: {}", token.getExpiresIn());
        logger.info("RefreshToken: {}", token.getRefreshToken());
        logger.info("-------------------------------------------------------");
    }



} // The End...
