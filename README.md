<p align="center">
    <a href="https://github.com/Ktt-Development/MyAnimeList-Java-API">
        <img src="https://raw.githubusercontent.com/Ktt-Development/MyAnimeList-Java-API/main/logo.png" alt="Logo" width="100" height="100">
    </a>
    <h3 align="center">MyAnimeList Java API</h3>
    <p align="center">
        📘 Java wrapper for the official MyAnimeList API
        <br />
        <a href="https://docs.kttdevelopment.com/myanimelist/">Docs</a>
        •
        <a href="https://wiki.kttdevelopment.com/myanimelist/">Wiki</a>
        •
        <a href="https://github.com/Ktt-Development/MyAnimeList-Java-API/issues">Issues</a>
    </p>
</p>

<p align="center">
    <a href="https://github.com/Ktt-Development/MyAnimeList-Java-API/actions?query=workflow%3ADeploy"><img title="Deploy" src="https://github.com/Ktt-Development/MyAnimeList-Java-API/workflows/Deploy/badge.svg"></a>
    <a href="https://github.com/Ktt-Development/MyAnimeList-Java-API/actions?query=workflow%3A%22Java+CI%22"><img title="Java CI" src="https://github.com/Ktt-Development/MyAnimeList-Java-API/workflows/Java%20CI/badge.svg"></a>
    <a href="https://mvnrepository.com/artifact/com.kttdevelopment/myanimelist"><img title="Maven Central" src="https://img.shields.io/maven-central/v/com.kttdevelopment/MyAnimeList-Java-API"></a>
    <a href="https://github.com/Ktt-Development/MyAnimeList-Java-API/releases"><img title="version" src="https://img.shields.io/github/v/release/ktt-development/MyAnimeList-Java-API"></a>
    <a href="https://github.com/Ktt-Development/MyAnimeList-Java-API/blob/main/LICENSE"><img title="license" src="https://img.shields.io/github/license/Ktt-Development/MyAnimeList-Java-API"></a>
</p>

---

# Setup

## API setup

### 1. Create new API key

Create a new API key at [https://myanimelist.net/apiconfig](https://myanimelist.net/apiconfig).

![Create ID](https://raw.githubusercontent.com/Ktt-Development/MyAnimeList-Java-API/main/setup_1.png)

### 2. Register API key

Fill in all required fields and App Redirect URL. For users using easy authentication ([below](#authenticate-with-client-id-easy) set this to `http://localhost:5050`.


![Create ID](https://raw.githubusercontent.com/Ktt-Development/MyAnimeList-Java-API/main/setup_2.png)

### 3. Retrieve Client ID

Copy client ID and client secret (if the application has a client secret).

## Library setup

### Dependency Management

For users using Maven/Gradle etc. binaries can be found on Maven Central. [![maven central](https://img.shields.io/maven-central/v/com.kttdevelopment/MyAnimeList-Java-API)](https://mvnrepository.com/artifact/com.kttdevelopment/myanimelist)

### Local (not recommended)

For users developing locally, compiled binaries can be found in releases. [![releases](https://img.shields.io/github/v/release/ktt-development/MyAnimeList-Java-API)](https://github.com/Ktt-Development/MyAnimeList-Java-API/releases).

This library also requires dependencies [retrofit2](https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit) and [retrofit2/converter-gson](https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-gson) to function properly.

## Authentication

### Authenticate with OAuth2 (advanced)

For developers using their own [authorization](https://myanimelist.net/apiconfig/references/authorization#client-registration) you can simply use the OAuth key that you generate.
```java
MyAnimeList mal = MyAnimeList.withOAuthToken("oauth_token");
```

### Authenticate with client id (advanced)

For developers using their own [authorization](https://myanimelist.net/apiconfig/references/authorization#step-1-generate-a-code-verifier-and-challenge) methods you can use the `MyAnimeListAuthenticator` to generate an OAuth token.

The client secret will be null if your application does not have one.

The URL to obtain the authorization code can be generated using `MyAnimeListAuthenticator#getAuthorizationURL(String,String)`.

```java
String authorization_url = MyAnimeListAuthenticator.getAuthorizationURL("client_id", "PKCE_code_challenge");

MyAnimeList mal = MyAnimeList.withAuthorization(new MyAnimeListAuthenticator("client_id", "client_secret", "authorization_code", "PKCE_code_challenge"));
```

### Authenticate with client id (easy)

For developers without domain for the app redirect url (using *localhost*), authorization can be completed using the `MyAnimeListAuthenticator`.
The app redirect url should be `http://localhost:5050` or what ever port you set it as in [step 2](#2-register-api-key)

The client secret will be null if your application does not have one.

```java
MyAnimeList mal = MyAnimeList.withAuthorization(new MyAnimeListAuthenticator("client_id", "client_secret", (int) port));
```