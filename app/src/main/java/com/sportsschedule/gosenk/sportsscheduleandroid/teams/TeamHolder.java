package com.sportsschedule.gosenk.sportsscheduleandroid.teams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportsschedule.gosenk.sportsscheduleandroid.dto.Game;
import com.sportsschedule.gosenk.sportsscheduleandroid.dto.Team;

public class TeamHolder {
    private static ObjectMapper objectMapper = new ObjectMapper();

    private static String host = "http://10.0.2.2:8081";
    private static String namespace = "api";

    private static List<Team> nflTeamList = new ArrayList<>();
    private static List<Team> mlbTeamList = new ArrayList<>();

    private static Map<String, Team> teamMap = new HashMap<>();

    public static List<Team> getNflTeamList() {
        return nflTeamList;
    }
    public static List<Team> getMlbTeamList() {
        return mlbTeamList;
    }

    public static Map<String, Team> getTeamMap() {
        return teamMap;
    }

    public static boolean initializeTeams(){
        boolean nflTeamsSuccess = loadNFLTeams();
        boolean mlbTeamsSuccess = loadMLBTeams();

        return nflTeamsSuccess && mlbTeamsSuccess;
    }

    public static boolean loadMLBTeams(){
        return loadTeams(mlbTeamList, "MLB");
    }

    public static boolean loadNFLTeams(){
        return loadTeams(nflTeamList, "NFL");
    }

    private static boolean loadTeams(List<Team> teamList, String leagueId){
        try{
            teamList.addAll(getTeams(leagueId));
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static List<Team> getTeams(String leagueId) throws Exception{
        List<Team> teamList = new ArrayList<>();

        String resource = "leagues";
        String urlStr = host +"/"+ namespace +"/"+ resource +"/"+ leagueId;
        URL url = new URL(urlStr);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();

        if(responseCode == HttpURLConnection.HTTP_OK) {
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);

            int cp;
            StringBuilder sb = new StringBuilder();
            while ((cp = br.read()) != -1) {
                sb.append((char) cp);
            }
            br.close();
            in.close();

            JSONObject league = new JSONObject(sb.toString());
            JSONArray teams = league.getJSONArray("teams");

            for(int i=0; i<teams.length(); i++) {
                JSONObject teamObj = (JSONObject) teams.get(i);
                Team team = objectMapper.readValue(teamObj.toString(), Team.class);

                teamList.add(team);
                teamMap.put(team.getId(), team);
            }
        }

        //Collections.sort(teamList);

        return teamList;
    }

    /*private TeamHolder(){
        nflTeamList = new ArrayList<Team>();
        loadNFLTeams();
    }

    public static TeamHolder getInstance(){
        if(instance == null){
            instance = new TeamHolder();
        }
        return instance;
    }

    public List<Team> getMlbTeamList(){

        if(mlbTeamList == null){
            mlbTeamList = new ArrayList<Team>();
            loadMLBTeams();
        }

        return mlbTeamList;
    }

    private void loadMLBTeams(){

        try {

            URL url = new URL("https://sports-schedule.herokuapp.com/mlb.php");
            URLConnection conn = url.openConnection();
            if(conn != null) {
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(in);

                int cp;
                StringBuilder sb = new StringBuilder();
                while ((cp = br.read()) != -1) {
                    sb.append((char) cp);
                }
                br.close();
                in.close();

                JSONArray jsonArr = new JSONArray(sb.toString());
                //JSONObject jsonObj = jsonArr.getJSONObject(0);

                for(int j=0; j<jsonArr.length(); j++) {
                    JSONObject obj = (JSONObject) jsonArr.get(j);

                    Team team = new Team();

                    team.setTeamId(obj.getInt("teamId"));
                    team.setCity(obj.getString("city"));
                    team.setMascot(obj.getString("mascot"));
                    team.setPrimaryColor(obj.getString("primaryColor"));
                    team.setSecondaryColor(obj.getString("secondaryColor"));

                    //team.setLonLocation(obj.getDouble("lonLocation"));
                    //team.setLatLocation(obj.getDouble("latLocation"));

                    team.setLogoURL("mlb_"+obj.getString("logoURL"));
                    team.setTeamAbbr(obj.getString("teamAbbr"));

                    JSONArray schedList = obj.getJSONArray("scheduleList");

                    List<Opponent> oppList = new ArrayList<Opponent>();
                    for(int i=0; i<schedList.length(); i++){
                        JSONObject sched = (JSONObject) schedList.get(i);

                        Opponent opp = new Opponent();

                        opp.setEid(sched.getString("eid"));
                        opp.setCity(sched.getString("city"));
                        opp.setMascot(sched.getString("mascot"));
                        opp.setDay(sched.getString("date"));
                        opp.setTime(sched.getString("time"));
                        opp.setLogoURL("mlb_" + sched.getString("logoURL"));

                        oppList.add(opp);
                    }

                    team.setScheduleList(oppList);

                    mlbTeamList.add(team);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }

    }


    public List<Team> getNflTeamList(){
        return nflTeamList;
    }

    private void loadNFLTeams(){

        try {

            URL url = new URL("https://sports-schedule.herokuapp.com/nfl.php");
            URLConnection conn = url.openConnection();
            if(conn != null) {
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(in);

                int cp;
                StringBuilder sb = new StringBuilder();
                while ((cp = br.read()) != -1) {
                    sb.append((char) cp);
                }
                br.close();
                in.close();

                String jsonStr = sb.toString();
                JSONObject json = new JSONObject(jsonStr);

                Iterator<String> jsonIt = json.keys();
                while (jsonIt.hasNext()) {
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

                    JSONArray schedList = obj.getJSONArray("scheduleList");

                    List<Opponent> oppList = new ArrayList<Opponent>();
                    for(int i=0; i<schedList.length(); i++){
                        JSONObject sched = (JSONObject) schedList.get(i);

                        Opponent opp = new Opponent();
                        String eid = sched.getString("eid");

                        String day = "BYE";
                        if(!eid.equals("BYE")) {
                            day = eid.substring(0, 8);
                        }

                        opp.setEid(eid);
                        opp.setCity(sched.getString("city"));
                        opp.setMascot(sched.getString("mascot"));
                        opp.setDay(day);
                        opp.setTime(sched.getString("time"));
                        opp.setLogoURL(sched.getString("logoURL"));

                        oppList.add(opp);
                    }

                    team.setScheduleList(oppList);

                    nflTeamList.add(team);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }*/

}
