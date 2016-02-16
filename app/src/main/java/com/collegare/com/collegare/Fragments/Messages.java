package com.collegare.com.collegare.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.collegare.com.collegare.Activity.MessageSend;
import com.collegare.com.collegare.Activity.postSend;
import com.collegare.com.collegare.Managers.App_Config;
import com.collegare.com.collegare.Managers.DatabaseManager;
import com.collegare.com.collegare.Managers.InternetManager;
import com.collegare.com.collegare.Managers.MessageAdapter;
import com.collegare.com.collegare.Managers.RecyclerViewDecorator;
import com.collegare.com.collegare.Managers.SendListener;
import com.collegare.com.collegare.Models.CollegareFeed;
import com.collegare.com.collegare.Models.CollegareMessage;
import com.collegare.com.collegare.Models.Report;
import com.collegare.com.collegare.R;
import com.collegare.com.collegare.Managers.DataStore;

import java.util.ArrayList;

public class Messages extends Fragment implements SendListener{
    RecyclerView recyclerView;
    MessageAdapter adapter;
    InternetManager internetManager;
    TextView error;
    ArrayList<CollegareMessage> dataList;
    SwipeRefreshLayout swipeRefreshLayout;
    private DataStore dataStore;
    Report rp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter=new MessageAdapter(getActivity());
        //internetManager=new InternetManager(getActivity(),adapter);
      //  internetManager.getFeeds();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_message, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        error= (TextView) view.findViewById(R.id.errorPanel);
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.refresser);
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerAnonymous);
        recyclerView.addItemDecoration(new RecyclerViewDecorator(getActivity(),5,true,R.drawable.post_divider));
        dataStore= new DataStore(getActivity());
        /*
        *
        *       simulating data storage
        * */
        dataList=new ArrayList<>();
        dataList = dataStore.getMessages();
        if(dataList!=null){
            adapter.setMessageDataList(dataList);
        }


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.CYAN);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e("[msg] refress "," triggered");
               refressMessage();
            }
        });
        return view;
    }

    private void refressMessage() {

       if(InternetManager.getInstance(getActivity()).isConnectedToNet()){
           InternetManager.getInstance(getActivity()).getMessage();
           swipeRefreshLayout.setRefreshing(false);
       }
        else{
           Snackbar.make(swipeRefreshLayout, "No Connectivity", Snackbar.LENGTH_SHORT).show();
           swipeRefreshLayout.setRefreshing(false);
       }

    }

    @Override
    public void send() {
        startActivity(new Intent(getActivity(),MessageSend.class));

    }

    @Override
    public void alert(String msg,Context context) {

    }
}