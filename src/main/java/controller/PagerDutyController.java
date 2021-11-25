package controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.DeveloperDBHelper;
import dao.TeamDBHelper;
import exceptions.InvalidTeamDetailsException;
import model.Developers;
import model.Team;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PagerDutyController {

    public void insertTeamAndDeveloperDetails(String teamJson) throws InvalidTeamDetailsException {
        try {
            JsonObject json = new JsonParser().parse(teamJson).getAsJsonObject();
            JsonObject teamObject = json.get("team").getAsJsonObject();
            String teamName = teamObject.get("name").getAsString();
            Team team = TeamDBHelper.insertTeam(teamName);
            System.out.println("Team Details Done!");
            JsonArray developers = json.get("developers").getAsJsonArray();
            List<Developers> developersList = new ArrayList<>();
            for(JsonElement jsonElement:developers)
            {
                JsonObject record = jsonElement.getAsJsonObject();
                Developers devRecord = new Developers(team.getTeamId(),record.get("name").getAsString(),record.get("phone_number").getAsString());
                developersList.add(devRecord);
            }

            DeveloperDBHelper.addDevelopers(developersList);

            System.out.println("Developer Record Inserted");
        }
        catch (Exception e){
            throw new InvalidTeamDetailsException("Invalid Team Details");
        }

    }


    public void sendAlertToDevelopers(int teamId)
    {
       try {

           Developers developer = DeveloperDBHelper.selectDevelopers(teamId);
           CloseableHttpClient httpclient = HttpClients.createDefault();
           HttpPost httppost = new HttpPost("https://run.mocky.io/v3/fd99c100-f88a-4d70-aaf7-393dbbd5d99f");
           List<BasicNameValuePair> params = new ArrayList<>(2);
           params.add(new BasicNameValuePair("phone_number", developer.getPhoneNumber()));
           params.add(new BasicNameValuePair("message", "Too many 5xxx"));
           httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

           HttpResponse response = httpclient.execute(httppost);
           HttpEntity entity = response.getEntity();

           System.out.println(entity.getContent());
       }
       catch (Exception e){
          System.out.println(e.getMessage());
       }
    }
}
