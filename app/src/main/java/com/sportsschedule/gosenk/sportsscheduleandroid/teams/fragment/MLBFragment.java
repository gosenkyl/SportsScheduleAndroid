package com.sportsschedule.gosenk.sportsscheduleandroid.teams.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.sportsschedule.gosenk.sportsscheduleandroid.R;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.IMLBListener;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.MLBAsyncTask;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.Team;

import java.util.List;

/**
 * Created by GosenK on 10/30/2015.
 */
public class MLBFragment extends Fragment implements IMLBListener {

    private View mlbView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        if(mlbView == null) {
            View view = inflater.inflate(R.layout.team_tab, container, false);

            MLBAsyncTask task = new MLBAsyncTask(this);
            task.execute();

            mlbView = view;
        }

        return mlbView;
    }

    @Override
    public void onCompleted(List<Team> mlbTeamList){

        TableLayout tableView = (TableLayout) mlbView.findViewById(R.id.team_list);

        TableRow rowView = new TableRow(mlbView.getContext());

        TextView textView = new TextView(mlbView.getContext());
        textView.setText("MLB TEST");

        rowView.addView(textView);
        tableView.addView(rowView);
    }

}
