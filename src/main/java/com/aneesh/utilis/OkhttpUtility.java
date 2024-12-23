package com.aneesh.utilis;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkhttpUtility {
    private static final OkHttpClient client = new OkHttpClient();
    private static final ObjectMapper objectmapper = new ObjectMapper();

    static {
        objectmapper.findAndRegisterModules();
    }

    // Generic method to perform HTTP GET requests
    public static <T> T getRequest(String url, Class<T> responseType) throws IOException {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                return objectmapper.readValue(responseBody, responseType);
            } else {
                throw new IOException("Request failed: " + response.message());
            }
        }
    }
}
