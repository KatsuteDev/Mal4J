---
heading: |
    <h1 class="display-3">Setup</h1>
    <p class="mt-3 mb-5">
        Quick installation guide for new or experienced developers.
    </p>
    <a class="btn btn-outline-light" href="/documentation"><i class="fas fa-book"></i> Documentation</a>
    <a class="btn btn-outline-light" href="https://mvnrepository.com/artifact/com.kttdevelopment/myanimelist"><i class="fas fa-archive"></i> Maven Central</a>
    <a class="btn btn-outline-light" href="https://mvnrepository.com/artifact/com.kttdevelopment/myanimelist"><i class="fas fa-cloud-download-alt"></i> Releases</a>
---
# Library setup

This library requires at least JDK 11.

## Dependency Management

For users using Maven/Gradle etc. binaries can be found on Maven Central. [![maven central](https://img.shields.io/maven-central/v/com.kttdevelopment/MyAnimeList-Java-API)](https://mvnrepository.com/artifact/com.kttdevelopment/myanimelist)

## Local (not recommended)

For users developing locally, compiled binaries can be found in releases. [![releases](https://img.shields.io/github/v/release/ktt-development/MyAnimeList-Java-API)](https://github.com/Ktt-Development/MyAnimeList-Java-API/releases)

Required dependencies:
 - [retrofit2](https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit)
 - [retrofit2/converter-gson](https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-gson)

# API setup

## 1. Create new API key

Create a new API key at [https://myanimelist.net/apiconfig](https://myanimelist.net/apiconfig).

![Create ID](https://raw.githubusercontent.com/Ktt-Development/MyAnimeList-Java-API/main/setup_1.png)

## 2. Register API key

Fill in all required fields and App Redirect URL. For users using easy authentication ([below](#authenticate-with-client-id-easy) set this to `http://localhost:5050`).


![Register application](https://raw.githubusercontent.com/Ktt-Development/MyAnimeList-Java-API/main/setup_2.png)

## 3. Retrieve Client ID

Copy client ID and client secret (if the application has a client secret).

![Copy client id and client secret](https://raw.githubusercontent.com/Ktt-Development/MyAnimeList-Java-API/main/setup_3.png)

# Authentication

## Authenticate with OAuth2 (advanced)

For developers using their own [authorization](https://myanimelist.net/apiconfig/references/authorization#client-registration) you can simply use the OAuth key that you generate.
```java
MyAnimeList mal = MyAnimeList.withOAuthToken("oauth_token");
```

## Authenticate with client id (advanced)

For developers using their own [authorization](https://myanimelist.net/apiconfig/references/authorization#step-1-generate-a-code-verifier-and-challenge) methods you can use the `MyAnimeListAuthenticator` to generate an OAuth token.

The client secret will be null if your application does not have one.

The URL to obtain the authorization code can be generated using `MyAnimeListAuthenticator#getAuthorizationURL(String,String)`.

```java
String authorization_url = MyAnimeListAuthenticator.getAuthorizationURL("client_id", "PKCE_code_challenge");

MyAnimeList mal = MyAnimeList.withAuthorization(new MyAnimeListAuthenticator("client_id", "client_secret", "authorization_code", "PKCE_code_challenge"));
```

## Authenticate with client id (easy)

For developers without domain for the app redirect url (using *localhost*), authorization can be completed using the `MyAnimeListAuthenticator`.
The app redirect url should be `http://localhost:5050` or whatever port you set it as in [step 2](#2-register-api-key).

The client secret will be null if your application does not have one.

```java
MyAnimeList mal = MyAnimeList.withAuthorization(new MyAnimeListAuthenticator("client_id", "client_secret", (int) port));
```
