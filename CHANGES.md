CHANGES
=======

1.38 (2016-09-20)
-----------------

- Added `GrantedScopesGetResponse` class.


1.37 (2016-09-12)
-----------------

- Added `ClientAuthorizationUpdateRequest` class.


1.36 (2016-09-06)
-----------------

- Added `AuthorizedClientListResponse` class.


1.35 (2016-08-15)
-----------------

- `AuthorizationIssueRequest`
    * Added `sub` field to adjust the value of the `sub` claim.

- `UserInfoIssueRequest`
    * Added `sub` field to adjust the value of the `sub` claim.


1.34 (2016-07-30)
-----------------

- New classes
    * Added `TokenUpdateRequest` class.
    * Added `TokenUpdateResponse` class.

- `AuthleteApi`
    * Added `tokenUpdate(TokenUpdateRequest)` method.
    * Added `getRequestableScopes(long clientId)` method.
    * Added `setRequestableScopes(long clientId, String[] scopes)` method.
    * Added `deleteRequestableScopes(long clientId)` method.

- `AuthorizationIssueRequest`
    * Added `scopes` field to replace the scopes contained in
      the original authorization request.

- `AuthorizationIssueResponse`
    * Added `accessToken` field.
    * Added `accessTokenExpiresAt` field.
    * Added `accessTokenDuration` field.
    * Added `idToken` field.
    * Added `authorizationCode` field.

- `AuthorizationResponse`
    * Added `prompts` field which represents the value of `prompt`
      request parameter in the original authorization request.

- `TokenCreateResponse`
    * Added `properties` field.

- `TokenIssueResponse`
    * Added `accessToken` field.
    * Added `accessTokenExpiresAt` field.
    * Added `accessTokenDuration` field.
    * Added `refreshToken` field.
    * Added `refreshTokenExpiresAt` field.
    * Added `refreshTokenDuration` field.

- `TokenResponse`
    * Added `accessToken` field.
    * Added `accessTokenExpiresAt` field.
    * Added `accessTokenDuration` field.
    * Added `refreshToken` field.
    * Added `refreshTokenExpiresAt` field.
    * Added `refreshTokenDuration` field.
    * Added `idToken` field.
