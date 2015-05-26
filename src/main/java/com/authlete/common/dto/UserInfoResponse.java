/*
 * Copyright (C) 2015 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.authlete.common.dto;


import com.authlete.common.util.Utils;



/**
 * Response from Authlete's {@code /auth/userinfo} API.
 *
 * <p>
 * Authlete's {@code /auth/userinfo} API returns JSON which can be mapped
 * to this class. The service implementation should retrieve the value of
 * {@code "action"} from the response and take the following steps
 * according to the value.
 * </p>
 *
 * <dl>
 * <dt><b>{@link Action#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "INTERNAL_SERVER_ERROR"},
 * it means that the request from the service implementation was wrong or
 * that an error occurred in Authlete.
 * </p>
 *
 * <p>
 * In either case, from the viewpoint of the client application, it is an
 * error on the server side. Therefore, the service implementation should
 * generate a response to the client application with the HTTP status of
 * {@code "500 Internal Server Error"}.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a string which describes the error
 * in the format of <a href="http://tools.ietf.org/html/rfc6750">RFC 6750</a>
 * (OAuth 2.0 Bearer Token Usage), so the <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfo">UserInfo
 * Endpoint</a> implementation of your service can use the string returned
 * from the method as the value of {@code WWW-Authenticate} header.
 * </p>
 *
 * <p>
 * The following is an example response which complies with RFC 6750.
 * Note that OpenID Connect Core 1.0 requires that an error response from
 * UserInfo endpoint comply with RFC 6750. See <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfoError"
 * >5.3.3. UserInfo Response</a> for details.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 500 Internal Server Error
 * WWW-Authenticate: <i>(The value returned from {@link #getResponseContent()})</i>
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 * </dd>
 *
 * <dt><b>{@link Action#BAD_REQUEST BAD_REQUEST}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "BAD_REQUEST"}, it means
 * that the request from the client application does not contain an access
 * token (= the request from the service implementation to Authlete does
 * not contain {@code "token"} parameter).
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a string which describes the error
 * in the format of <a href="http://tools.ietf.org/html/rfc6750">RFC 6750</a>
 * (OAuth 2.0 Bearer Token Usage), so the <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfo">UserInfo
 * Endpoint</a> implementation of your service can use the string returned
 * from the method as the value of {@code WWW-Authenticate} header.
 * </p>
 *
 * <p>
 * The following is an example response which complies with RFC 6750.
 * Note that OpenID Connect Core 1.0 requires that an error response from
 * UserInfo endpoint comply with RFC 6750. See <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfoError"
 * >5.3.3. UserInfo Response</a> for details.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 400 Bad Request
 * WWW-Authenticate: <i>(The value returned from {@link #getResponseContent()})</i>
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 * </dd>
 *
 * <dt><b>{@link Action#UNAUTHORIZED UNAUTHORIZED}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "UNAUTHORIZED"}, it means
 * that the access token does not exist, has expired, or is not associated
 * with any subject (= any user account).
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a string which describes the error
 * in the format of <a href="http://tools.ietf.org/html/rfc6750">RFC 6750</a>
 * (OAuth 2.0 Bearer Token Usage), so the <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfo">UserInfo
 * Endpoint</a> implementation of your service can use the string returned
 * from the method as the value of {@code WWW-Authenticate} header.
 * </p>
 *
 * <p>
 * The following is an example response which complies with RFC 6750.
 * Note that OpenID Connect Core 1.0 requires that an error response from
 * UserInfo endpoint comply with RFC 6750. See <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfoError"
 * >5.3.3. UserInfo Response</a> for details.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 401 Unauthorized
 * WWW-Authenticate: <i>(The value returned from {@link #getResponseContent()})</i>
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 * </dd>
 *
 * <dt><b>{@link Action#FORBIDDEN FORBIDDEN}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "FORBIDDEN"}, it means
 * that the access token does not include the {@code "openid"} scope.
 * </p>
 *
 * <p>
 * {@link #getResponseContent()} returns a string which describes the error
 * in the format of <a href="http://tools.ietf.org/html/rfc6750">RFC 6750</a>
 * (OAuth 2.0 Bearer Token Usage), so the <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfo">UserInfo
 * Endpoint</a> implementation of your service can use the string returned
 * from the method as the value of {@code WWW-Authenticate} header.
 * </p>
 *
 * <p>
 * The following is an example response which complies with RFC 6750.
 * Note that OpenID Connect Core 1.0 requires that an error response from
 * UserInfo endpoint comply with RFC 6750. See <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfoError"
 * >5.3.3. UserInfo Response</a> for details.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 403 Forbidden
 * WWW-Authenticate: <i>(The value returned from {@link #getResponseContent()})</i>
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 * </dd>
 *
 * <dt><b>{@link Action#OK OK}</b></dt>
 * <dd>
 * <p>
 * When the value of {@code "action"} is {@code "OK"}, it means that the
 * access token which the client application presented is valid. To be
 * concrete, it means that the access token exists, has not expired,
 * includes the {@code "openid"} scope, and is associated with a subject
 * (= a user account).
 * </p>
 *
 * <p>
 * What the <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfo">UserInfo
 * Endpoint</a> of your service should do next is to collect information
 * about the subject (user) from your database. The value of the subject
 * can be obtained from {@link #getSubject()}, and the names of data, i.e.,
 * the claims names can be obtained from {@link #getClaims()}.
 * For example, if {@code getSubject()} returns {@code "joe123"} and {@code
 * getClaims()} returns {@code ["given_name", "email"]}, you need to extract
 * information about {@code joe123}'s given name and email from your
 * database.
 * </p>
 *
 * <p>
 * Then, call Authlete's {@code /auth/userinfo/issue} API with the collected
 * information and the access token in order to make Authlete generate an
 * ID token. See the descriptions of {@link com.authlete.common.dto.UserInfoIssueRequest
 * UserInfoIssueRequest} and {@link com.authlete.common.dto.UserInfoIssueResponse
 * UserInfoIssueResponse} for details about {@code /auth/userinfo/issue} API.
 * </p>
 *
 * <p>
 * If an error occurred during the above steps, generate an error response
 * to the client. The response should comply with RFC 6750. For example,
 * if the subject associated with the access token does not exist in your
 * database any longer, you may feel like generating a response like below.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 400 Bad Request
 * WWW-Authenticate: Bearer error="{@link com.authlete.common.types.ErrorCode#invalid_token
 * invalid_token}",
 *  error_description="The subject associated with the access token does not exist."
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 *
 * <p>
 * Also, an error might occur on database access. If you treat the error
 * as an internal server error, then the response would be like the following.
 * </p>
 *
 * <pre style="border: solid 1px black; padding: 0.5em;">
 * HTTP/1.1 500 Internal Server Error
 * WWW-Authenticate: Bearer error="{@link com.authlete.common.types.ErrorCode#server_error
 * server_error}",
 *  error_description="Failed to extract information about the subject from the database."
 * Cache-Control: no-store
 * Pragma: no-cache</pre>
 *
 * </dd>
 * </dl>
 *
 * @author Takahiko Kawasaki
 */
public class UserInfoResponse extends ApiResponse
{
    private static final long serialVersionUID = 1L;


    /**
     * The next action the service implementation should take.
     */
    public enum Action
    {
        /**
         * The request from the service implementation was wrong or
         * an error occurred in Authlete. The service implementation
         * should return {@code "500 Internal Server Error"} to the
         * client application.
         */
        INTERNAL_SERVER_ERROR,

        /**
         * The request does not contain an access token. The service
         * implementation should return {@code "400 Bad Request"} to
         * the client application.
         */
        BAD_REQUEST,

        /**
         * The access token does not exist or has expired. The service
         * implementation should return {@code "401 Unauthorized"} to
         * the client application.
         */
        UNAUTHORIZED,

        /**
         * The access token does not cover the required scopes. To be
         * concrete, the access token does not include the {@code
         * "openid"} scope. The service implementation should return
         * {@code "403 Forbidden"} to the client application.
         */
        FORBIDDEN,

        /**
         * The access token is valid. The service implementation should
         * collect information about requested claims and pass the
         * information to Authlete's {@code /auth/userinfo/issue}
         * endpoint in order to make Authlete generate an ID token.
         */
        OK
    }


    private static final String SUMMARY_FORMAT
        = "action=%s, clientId=%d, subject=%s, scopes=%s, claims=%s, "
        + "accessToken=%s"
        ;


    /**
     * The next action the service implementation should take.
     */
    private Action action;


    /**
     * The client ID.
     */
    private long clientId;


    /**
     * Resource owner's user account.
     */
    private String subject;


    /**
     * Scopes.
     */
    private String[] scopes;


    /**
     * Claims that are requested by the client application.
     */
    private String[] claims;


    /**
     * The access token that came along with the userinfo request.
     */
    private String token;


    /**
     * Entity body of the response to the client.
     */
    private String responseContent;


    /**
     * Get the next action the service implementation should take.
     */
    public Action getAction()
    {
        return action;
    }


    /**
     * Set the next action the service implementation should take.
     */
    public void setAction(Action action)
    {
        this.action = action;
    }


    /**
     * Get the client ID.
     */
    public long getClientId()
    {
        return clientId;
    }


    /**
     * Set the client ID.
     */
    public void setClientId(long clientId)
    {
        this.clientId = clientId;
    }


    /**
     * Get the subject (= resource owner's ID).
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject (= resource owner's ID).
     */
    public void setSubject(String subject)
    {
        this.subject = subject;
    }


    /**
     * Get the scopes covered by the access token.
     */
    public String[] getScopes()
    {
        return scopes;
    }


    /**
     * Set the scopes covered by the access token.
     */
    public void setScopes(String[] scopes)
    {
        this.scopes = scopes;
    }


    /**
     * Get the list of claims that the client application requests
     * to be embedded in the ID token. The value comes from
     * {@code "scope"} and {@code "claims"} request parameters of
     * the original authorization request.
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims"
     *      >OpenID Connect Core 1.0, 5.4. Requesting Claims using Scope Values</a>
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#ClaimsParameter"
     *      >OpenID Connect Core 1.0, 5.5. Requesting Claims using
     *      the "claims" Request Parameter</a>
     */
    public String[] getClaims()
    {
        return claims;
    }


    /**
     * Set the list of claims that the client application requests
     * to be embedded in the ID token.
     */
    public void setClaims(String[] claims)
    {
        this.claims = claims;
    }


    /**
     * Get the access token that came along with the userinfo request.
     */
    public String getToken()
    {
        return token;
    }


    /**
     * Set the access token that came along with the userinfo request.
     */
    public void setToken(String accessToken)
    {
        this.token = accessToken;
    }


    /**
     * Get the response content which can be used as a part of the
     * response to the client application.
     */
    public String getResponseContent()
    {
        return responseContent;
    }


    /**
     * Set the response content which can be used as a part of the
     * response to the client application.
     */
    public void setResponseContent(String responseContent)
    {
        this.responseContent = responseContent;
    }


    /**
     * Get the summary of this instance.
     */
    public String summarize()
    {
        return String.format(SUMMARY_FORMAT,
            action, clientId, subject, join(scopes), join(claims), token);
    }


    private String join(String[] strings)
    {
        return Utils.join(strings, " ");
    }
}
