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

/**
 * Created by GosenK on 11/4/2015.
 */
public class NBAFragment extends Fragment{

    private View nbaView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        if(nbaView == null) {
            View view = inflater.inflate(R.layout.team_tab, container, false);

            TableLayout tableView = (TableLayout) view.findViewById(R.id.team_list);

            TableRow rowView = new TableRow(view.getContext());

            TextView textView = new TextView(view.getContext());
            textView.setText("NBA TEST");

            rowView.addView(textView);
            tableView.addView(rowView);

            nbaView = view;
        }

        return nbaView;
    }

}
