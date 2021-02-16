package com.kd;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        fetchFromWordsApi();
    }

    public static void fetchFromWordsApi() {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.print("Enter word : ");

        String word = scanner.nextLine();  // Read user input

        // Fetch and print definitions
        fetchDefinitions(word);
        // Fetch and print synonyms
        fetchSynonyms(word);
        // Fetch and print rhymes
        fetchRhymes(word);
    }

    public static void fetchDefinitions(String word) {
        try {
            System.out.println("Fetching definitions for : " + word);
            // Make the connection
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://wordsapiv1.p.rapidapi.com/words/" + word + "/definitions")
                    .get()
                    .addHeader("x-rapidapi-key", "c98ed5818bmsh405bfbf0b3dcdcdp14d868jsn24f912d77303")
                    .addHeader("x-rapidapi-host", "wordsapiv1.p.rapidapi.com")
                    .build();

            // Send request to api
            Response response = client.newCall(request).execute();
            // Parsing input stream into a text string.
            String jsonData = response.body().string();
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("definitions");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                System.out.println("Part of speech : " + object.get("partOfSpeech") + ", Definition : " + object.get("definition"));
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public static void fetchSynonyms(String word) {
        try {
            System.out.println();
            System.out.println("Fetching synonyms for : " + word);
            // Make the connection
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://wordsapiv1.p.rapidapi.com/words/" + word + "/synonyms")
                    .get()
                    .addHeader("x-rapidapi-key", "c98ed5818bmsh405bfbf0b3dcdcdp14d868jsn24f912d77303")
                    .addHeader("x-rapidapi-host", "wordsapiv1.p.rapidapi.com")
                    .build();

            // Send request to api
            Response response = client.newCall(request).execute();
            // Parsing input stream into a text string.
            String jsonData = response.body().string();
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("synonyms");

            for (int i = 0; i < jsonArray.length(); i++) {
                if (i != 0) {
                    System.out.print(", ");
                }
                System.out.print(jsonArray.get(i));
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public static void fetchRhymes(String word) {
        try {
            System.out.println();
            System.out.println("Fetching rhymes for : " + word);
            // Make the connection
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://wordsapiv1.p.rapidapi.com/words/" + word + "/rhymes")
                    .get()
                    .addHeader("x-rapidapi-key", "c98ed5818bmsh405bfbf0b3dcdcdp14d868jsn24f912d77303")
                    .addHeader("x-rapidapi-host", "wordsapiv1.p.rapidapi.com")
                    .build();

            // Send request to api
            Response response = client.newCall(request).execute();
            // Parsing input stream into a text string.
            String jsonData = response.body().string();
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONObject("rhymes").getJSONArray("all");

            for (int i = 0; i < jsonArray.length(); i++) {
                if (i != 0) {
                    System.out.print(", ");
                }
                System.out.print(jsonArray.get(i));
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

}
