package com.httpServer;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RequestParserTest {

    @Test
    public void isRootRequestReturnsTrueForRootRequest() {
        HashMap<String, String> rootRequest = new HashMap<String, String>();
        rootRequest.put("method", "GET");
        rootRequest.put("uri", "/");

        assertTrue(new RequestParser(rootRequest).isRootRequest());
    }

    @Test
    public void isRootRequestReturnsFalseForNonRootRequest() {
        HashMap<String, String> nonRootRequest = new HashMap<String, String>();
        nonRootRequest.put("method", "GET");
        nonRootRequest.put("uri", "/redirect");

        assertTrue(!new RequestParser(nonRootRequest).isRootRequest());
    }

    @Test
    public void isDirectoryFileReturnsTrueIfPublicDirectoryContainsUri() {
        HashMap<String, String> file1Request = new HashMap<String, String>();
        file1Request.put("method", "GET");
        file1Request.put("uri", "/file1");

        assertTrue(new RequestParser(file1Request).isGetDirectoryFile());
    }

    @Test
    public void isDirectoryFileReturnsFalseIfPublicDirectoryDoesNotContainUri() {
        HashMap<String, String> nonFileRequest = new HashMap<String, String>();
        nonFileRequest.put("method", "GET");
        nonFileRequest.put("uri", "/non-existent-resource");

        assertTrue(!new RequestParser(nonFileRequest).isGetDirectoryFile());
    }

    @Test
    public void isMethodOptionsReturnsTrueForGetMethodOptions() {
        HashMap<String, String> methodOptionsRequest = new HashMap<String, String>();
        methodOptionsRequest.put("method", "GET");
        methodOptionsRequest.put("uri", "/method_options");

        assertTrue(new RequestParser(methodOptionsRequest).isMethodOptions());
    }

    @Test
    public void isMethodOptionsReturnsFalseIfNotGetMethodOptions() {
        HashMap<String, String> nonMethodOptionsRequest = new HashMap<String, String>();
        nonMethodOptionsRequest.put("method", "GET");
        nonMethodOptionsRequest.put("uri", "/");

        assertTrue(!new RequestParser(nonMethodOptionsRequest).isMethodOptions());
    }

    @Test
    public void isParametersReturnsTrueForParameters() {
        HashMap<String, String> parametersRequest = new HashMap<String, String>();
        parametersRequest.put("method", "GET");
        parametersRequest.put("uri", "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C" +
                "%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F" +
                "&variable_2=stuff");

        assertTrue(new RequestParser(parametersRequest).isParameters());
    }

    @Test
    public void isParametersReturnsFalseForNotParameters() {
        HashMap<String, String> nonParametersRequest = new HashMap<String, String>();
        nonParametersRequest.put("method", "GET");
        nonParametersRequest.put("uri", "/");

        assertTrue(!new RequestParser(nonParametersRequest).isParameters());
    }

    @Test
    public void isGetPartialContentReturnsTrueForGetPartialContentRequest() {
        HashMap<String, String> getPartialContentRequest = new HashMap<String, String>();
        getPartialContentRequest.put("method", "GET");
        getPartialContentRequest.put("uri", "/partial_content.txt");

        assertTrue(new RequestParser(getPartialContentRequest).isGetPartialContent());
    }

    @Test
    public void isGetPartialContentReturnsFalseForNonGetPartialContentRequest() {
        HashMap<String, String> nonGetPartialContentRequest = new HashMap<String, String>();
        nonGetPartialContentRequest.put("method", "PUT");
        nonGetPartialContentRequest.put("uri", "/partial_content.txt");

        assertTrue(!new RequestParser(nonGetPartialContentRequest).isGetPartialContent());
    }

    @Test
    public void isRedirectReturnsTrueForGetRedirect() {
        HashMap<String, String> redirectRequest = new HashMap<String, String>();
        redirectRequest.put("method", "GET");
        redirectRequest.put("uri", "/redirect");

        assertTrue(new RequestParser(redirectRequest).isRedirect());
    }

    @Test
    public void isRedirectReturnsFalseForNonGetRedirect() {
        HashMap<String, String> nonRedirectRequest = new HashMap<String, String>();
        nonRedirectRequest.put("method", "GET");
        nonRedirectRequest.put("uri", "/");

        assertTrue(!new RequestParser(nonRedirectRequest).isRedirect());
    }

    @Test
    public void isUnauthorizedReturnsTrueForUnauthorizedGetLogs() {
        HashMap<String, String> unauthorizedLogsRequest = new HashMap<String, String>();
        unauthorizedLogsRequest.put("method", "GET");
        unauthorizedLogsRequest.put("uri", "/logs");

        assertTrue(new RequestParser(unauthorizedLogsRequest).isUnauthorized());
    }

    @Test
    public void isPatchRequestReturnsTrueForPatchPatchContentRequest() {
        HashMap<String, String> patchRequest = new HashMap<String, String>();
        patchRequest.put("method", "PATCH");
        patchRequest.put("uri", "/patch-content.txt");

        assertTrue(new RequestParser(patchRequest).isPatchRequest());
    }

    @Test
    public void isPatchRequestReturnsFalseIfNotPatchPatchContentRequest() {
        HashMap<String, String> nonPatchRequest = new HashMap<String, String>();
        nonPatchRequest.put("method", "PATCH");
        nonPatchRequest.put("uri", "/file1");

        assertTrue(!new RequestParser(nonPatchRequest).isPatchRequest());
    }

    @Test
    public void isNotAllowedReturnsTrueForNotAllowedRequest() {
        HashMap<String, String> notAllowedRequest = new HashMap<String, String>();
        notAllowedRequest.put("method", "PUT");
        notAllowedRequest.put("uri", "/file1");

        assertTrue (new RequestParser(notAllowedRequest).isNotAllowed());
    }

    @Test
    public void isNotAllowedReturnsFalseForAllowedRequest() {
        HashMap<String, String> allowedRequest = new HashMap<String, String>();
        allowedRequest.put("method", "PUT");
        allowedRequest.put("uri", "/form");

        assertTrue(!new RequestParser(allowedRequest).isNotAllowed());
    }

    @Test
    public void isEditFormReturnsTrueForPutFormRequest() {
        HashMap<String, String> putFormRequest = new HashMap<String, String>();
        putFormRequest.put("method", "PUT");
        putFormRequest.put("uri", "/form");

        assertTrue(new RequestParser(putFormRequest).isEditForm());
    }

    @Test
    public void isEditFormReturnsTrueForPostFormRequest() {
        HashMap<String, String> postFormRequest = new HashMap<String, String>();
        postFormRequest.put("method", "POST");
        postFormRequest.put("uri", "/form");

        assertTrue(new RequestParser(postFormRequest).isEditForm());
    }

    @Test
    public void isEditFormReturnsFalseForNonPutOrPostFormRequest() {
        HashMap<String, String> nonPutPostFormRequest = new HashMap<String, String>();
        nonPutPostFormRequest.put("method", "GET");
        nonPutPostFormRequest.put("uri", "/form");

        assertTrue(!new RequestParser(nonPutPostFormRequest).isEditForm());
    }

    @Test
    public void isDeleteFormReturnsTrueForDeleteFormRequest() {
        HashMap<String, String> deleteFormRequest = new HashMap<String, String>();
        deleteFormRequest.put("method", "DELETE");
        deleteFormRequest.put("uri", "/form");

        assertTrue(new RequestParser(deleteFormRequest).isDeleteForm());
    }

    @Test
    public void isDeleteFormReturnsFalseForNonDeleteFormRequest() {
        HashMap<String, String> nonDeleteFormRequest = new HashMap<String, String>();
        nonDeleteFormRequest.put("method", "DELETE");
        nonDeleteFormRequest.put("uri", "/file1");

        assertTrue(!new RequestParser(nonDeleteFormRequest).isDeleteForm());
    }
}