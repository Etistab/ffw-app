package com.example.fightfoodwaste.Utils;

public class HttpClient {

    public interface Listeners {
        void onPostExecute(String result);
    }

    private String baseURL;
    private static final int URL = 0;
    private static final int METHOD = 1;
    private static final int BODY = 2;
    private static final String[] defaultParams = {
            "",
            "GET",
            ""
    };

    public HttpClient(String URI) {
        this.setBaseURI(URI);
    }

    public void setBaseURI(String URL) {
        this.baseURL = URL;
    }

    public String getBaseURL() {
        return this.baseURL;
    }

    public int fetch(Listeners callback, String... params) {
        String[] finalParams = this.checkParameters(params);
        String finalURL = this.baseURL + finalParams[URL];

        HttpRequest request = new HttpRequest(callback);
        request.execute(finalURL,
                        finalParams[METHOD],
                        finalParams[BODY]);

        return request.getResponseCode();
    }

    private String[] checkParameters(String... params) {
        String[] finalParams = new String[defaultParams.length];

        for(int i = 0; i < defaultParams.length; i++) {
            if(params.length > i) {
                finalParams[i] = params[i];
            }
            else {
                finalParams[i] = defaultParams[i];
            }
        }

        return finalParams;
    }
}
