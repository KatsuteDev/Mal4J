# Installation

Dependencies are hosted on Maven Central and compiled builds can be found in releases.

**Mal4J requires at least JDK 11.**

## Maven Central

Compiled binaries can be found on Maven Central → [![Maven Central](https://img.shields.io/maven-central/v/com.kttdevelopment/mal4j)](https://mvnrepository.com/artifact/com.kttdevelopment/mal4j)

## Local

For projects built locally, jars can be found in releases → [![Releases](https://img.shields.io/github/v/release/Katsute/Mal4J)](https://github.com/Katsute/Mal4J/releases)

Mal4J is a standalone library and requires no additional dependencies.

# API setup

## 1. Create new Client ID

In order to use the MyAnimeList API you must retrieve your own client ID. You can create a new Client ID at [https://myanimelist.net/apiconfig](https://myanimelist.net/apiconfig).

![Create ID](https://raw.githubusercontent.com/Katsute/Mal4J/main/setup_1.png)

## 2. Register application

Fill in all required fields and App Redirect URL, this is what we use to retrieve the OAuth token. For users using easy authentication ([below](#authenticate-with-client-id-easy)) set this to `http://localhost:5050` or whatever port you are using.

![Register application](https://raw.githubusercontent.com/Katsute/Mal4J/main/setup_2.png)

## 3. Retrieve Client ID

Copy client ID and client secret (if the application has a client secret), this will be used to authenticate the application with the API.

![Copy client id and client secret](https://raw.githubusercontent.com/Katsute/Mal4J/main/setup_3.png)

# Authentication

This library has a simplified authentication method based of the MyAnimeList [authorization documentation](https://myanimelist.net/apiconfig/references/authorization#client-registration). You can implement your own authentication methods or use the ones provided here.

This library also supports OAuth token refresh.

It is suggested that you save the OAuth key that is generated so you don't have to authenticate with MyAnimeList each time.

## Authenticate with OAuth2 (advanced)

For developers using their own [authorization](https://myanimelist.net/apiconfig/references/authorization#client-registration) you can simply use the OAuth key that you generate.
```java
MyAnimeList mal = MyAnimeList.withOAuthToken("oauth_token");
```

## Authenticate with client id (advanced)

For developers using their own [authorization](https://myanimelist.net/apiconfig/references/authorization#step-1-generate-a-code-verifier-and-challenge) methods you can use the [`MyAnimeListAuthenticator`](https://mal4j.kttdevelopment.com/com/kttdevelopment/mal4j/MyAnimeListAuthenticator.html) to generate an OAuth token from a client id and PKCE code challenge.

The client secret will be `null` if your application does not have one.

The URL to obtain the authorization code can be generated using [`MyAnimeListAuthenticator#getAuthorizationURL(String,String)`](https://mal4j.kttdevelopment.com/com/kttdevelopment/mal4j/MyAnimeListAuthenticator.html#getAuthorizationURL(java.lang.String,java.lang.String)).

```java
String authorization_url = MyAnimeListAuthenticator.getAuthorizationURL("client_id", "PKCE_code_challenge");

MyAnimeList mal = MyAnimeList.withAuthorization(new MyAnimeListAuthenticator("client_id", "client_secret", "authorization_code", "PKCE_code_challenge"));
```

## Authenticate with client id (easy)

For developers without domain for the app redirect url (using *localhost*), authorization can be completed using the [`MyAnimeListAuthenticator`](https://mal4j.kttdevelopment.com/com/kttdevelopment/mal4j/MyAnimeListAuthenticator.html).
The app redirect url should be `http://localhost:5050` or whatever port you set it as in [step 2](#2-register-application).

The client secret will be `null` if your application does not have one.

When this method is run it will launch your web browser to authenticate with MyAnimeList and then return with the OAuth key.

```java
MyAnimeList mal = MyAnimeList.withAuthorization(new MyAnimeListAuthenticator.LocalServerBuilder("client_id", "client_secret", 5050).openBrowser().build());
```
