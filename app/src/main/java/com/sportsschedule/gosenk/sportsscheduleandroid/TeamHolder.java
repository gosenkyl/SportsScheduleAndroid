package com.sportsschedule.gosenk.sportsscheduleandroid;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TeamHolder {

    private static TeamHolder instance = null;
    private List<Team> nflTeamList = null;

    private TeamHolder(JSONObject json){
        nflTeamList = new ArrayList<Team>();
        loadNFLTeams(json);
    }

    public static TeamHolder getInstance(JSONObject json){
        if(instance == null){
            instance = new TeamHolder(json);
        }
        return instance;
    }

    public List<Team> getNflTeamList(){
        return nflTeamList;
    }

    private void loadNFLTeams(JSONObject json){

        try {

            Iterator<String> jsonIt = json.keys();
            while(jsonIt.hasNext()){
                String key = jsonIt.next();
                JSONObject obj = json.getJSONObject(key);

                Team team = new Team();

                team.setTeamId(obj.getInt("teamId"));
                team.setCity(obj.getString("city"));
                team.setMascot(obj.getString("mascot"));
                team.setPrimaryColor(obj.getString("primaryColor"));
                team.setSecondaryColor(obj.getString("secondaryColor"));

                //team.setLonLocation(obj.getDouble("lonLocation"));
                //team.setLatLocation(obj.getDouble("latLocation"));

                team.setLogoURL(obj.getString("logoURL"));
                team.setTeamAbbr(obj.getString("teamAbbr"));

                nflTeamList.add(team);
            }

        } catch(Exception e){
            e.printStackTrace();;
        }
    }



}
