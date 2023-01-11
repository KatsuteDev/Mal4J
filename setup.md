## Installation

Mal4J requires at least Java 8. No additional dependencies/libraries are required.

Compiled binaries can be installed from:

 - [Maven Central](https://mvnrepository.com/artifact/dev.katsute/mal4j)
 - [GitHub Packages](https://github.com/KatsuteDev/Mal4J/packages/1104772)
 - [Releases](https://github.com/KatsuteDev/Mal4J/releases)

## API setup

#### 1. Create new Client ID

- In order to use the MyAnimeList API you must retrieve your own client ID.

    You can create a new Client ID at <https://myanimelist.net/apiconfig>.

    ![Create ID](https://raw.githubusercontent.com/KatsuteDev/Mal4J/main/assets/setup-1.png)

#### 2. Register application

 - Fill in all required fields and **App Redirect URL**, this is what we use to retrieve the authorization code.

   For users using local server authentication ([below](#authenticate-using-a-local-server)) set this to `http://localhost:5050` or whatever port you are using.

   ![Register application](https://raw.githubusercontent.com/KatsuteDev/Mal4J/main/assets/setup-2.png)

### 3. Retrieve Client ID

 - Copy **client ID** and **client secret** (if the application has a client secret), this will be used to generate an authorization code.

   ![Copy client id and client secret](https://raw.githubusercontent.com/KatsuteDev/Mal4J/main/assets/setup-3.png)

### 4. Authentication

This library has a simplified authentication method based of the MyAnimeList [authorization documentation](https://myanimelist.net/apiconfig/references/authorization#client-registration). You can implement your own authentication methods or use the ones provided here.

Four different ways to authenticate with MyAnimeList:

 - [Authenticate using client ID](#authenticate-using-client-id)
 - [Authenticate using token](#authenticate-using-token)
 - [Authenticate using OAuth 2.0](#authenticate-using-oauth-20)
 - [Authenticate using a local server](#authenticate-using-a-local-server)

#### Authenticate using client ID

 - Simply use the client ID for your application. Client secret is not required, even if your application has one.

   ```java
   MyAnimeList mal = MyAnimeList.withClientID("client_id");
   ```

   This authentication method only grants access to public data and READ operations.

   If you want to view users, or change anime/manga lists you must authenticate with a [token](#authenticate-using-token) or with [OAuth 2.0](#authenticate-using-oauth-20)

#### Authenticate using token

 - For developers using their own [authorization](https://myanimelist.net/apiconfig/references/authorization#client-registration) you can simply use the OAuth token that you generate.

   ```java
   MyAnimeList mal = MyAnimeList.withToken("Bearer oauth_token");
   ```

#### Authenticate using OAuth 2.0

The below method is massively simplified, for a more detailed explanation on OAuth2.0 works check this forum post: [OAuth2.0 authorization for MAL](https://myanimelist.net/blog.php?eid=835707).

For developers using their own [authorization](https://myanimelist.net/apiconfig/references/authorization#step-1-generate-a-code-verifier-and-challenge) methods you can use the [`MyAnimeListAuthenticator`](https://docs.katsute.dev/mal4j/Mal4J/dev/katsute/mal4j/MyAnimeListAuthenticator.html) to generate an OAuth token from a client id and PKCE code challenge.

 - The URL to obtain the authorization code can be generated using [`MyAnimeListAuthenticator.getAuthorizationURL(String,String)`](https://docs.katsute.dev/mal4j/Mal4J/dev/katsute/mal4j/MyAnimeListAuthenticator.html#getAuthorizationURL(java.lang.String,java.lang.String)).

   The client secret will be `null` if your application does not have one.

   This url is **not** your code.

 - After you authenticate with the above URL you will be redirected to the page you set in [step 2](#2-register-application). The URL that you are redirected to contains your **authorization code**.

   Example: `http://localhost:5050?code=AUTHORIZATION_CODE`

   Typically this is where you would have a local server to process the request and retrieve the code from the query.

   ```java
   String authorization_url = MyAnimeListAuthenticator.getAuthorizationURL("client_id", "PKCE_code_challenge");

   MyAnimeListAuthenticator authenticator = new MyAnimeListAuthenticator(new Authorization("client_id", "client_secret", "authorization_code", "PKCE_code_challenge"));
   MyAnimeList mal = MyAnimeList.withOAuth2(authenticator);
   ```

   It is suggested that you save the OAuth token that is generated so you don't have to authenticate with MyAnimeList each time.

The above methods is massively simplified, for a more detailed guide on how OAuth2.0 works check this blog post: [OAuth2.0 authorization for MAL](https://myanimelist.net/blog.php?eid=835707).

#### Authenticate using a local server

 - For developers without domain for the app redirect url (using *localhost*), authorization can be completed using the [`MyAnimeListAuthenticator`](https://docs.katsute.dev/mal4j/Mal4J/dev/katsute/mal4j/MyAnimeListAuthenticator.html).

   The app redirect url should be `http://localhost:5050` or whatever port you set it as in [step 2](#2-register-application).

   The client secret will be `null` if your application does not have one.

 - When this method is run it will launch your web browser to authenticate with MyAnimeList and then return with the OAuth key.

 - If [`openBrowser()`](https://docs.katsute.dev/mal4j/Mal4J/dev/katsute/mal4j/MyAnimeListAuthenticator.LocalServerBuilder.html#openBrowser()) is not supported then you can use [`setURLCallback(Consumer<String>)`](https://docs.katsute.dev/mal4j/Mal4J/dev/katsute/mal4j/MyAnimeListAuthenticator.LocalServerBuilder.html#setURLCallback(java.util.function.Consumer)) to handle the generated URL. Refer to [OAuth2.0 authentication](#authenticate-using-oauth-20) for steps on how to generate a token from the authorization URL.

   ```java
   MyAnimeListAuthenticator authenticator = new MyAnimeListAuthenticator
       .LocalServerBuilder("client_id", "client_secret", 5050)
       .openBrowser()
       .build();
   MyAnimeList mal = MyAnimeList.withOAuth2(authenticator);
   ```