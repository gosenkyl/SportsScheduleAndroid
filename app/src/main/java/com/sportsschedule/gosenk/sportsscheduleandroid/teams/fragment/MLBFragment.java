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
 * Created by GosenK on 10/30/2015.
 */
public class MLBFragment extends Fragment {

    private View mlbView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        if(mlbView == null) {
            View view = inflater.inflate(R.layout.team_tab, container, false);

            TableLayout tableView = (TableLayout) view.findViewById(R.id.team_list);

            TableRow rowView = new TableRow(view.getContext());

            TextView textView = new TextView(view.getContext());
            textView.setText("MLB TEST");

            rowView.addView(textView);
            tableView.addView(rowView);

            mlbView = view;
        }

        return mlbView;
    }

}
