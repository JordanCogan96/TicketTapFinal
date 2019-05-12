package com.example.tickettap.DisplayData;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tickettap.Admin.User;
import com.example.tickettap.R;

import java.util.List;

public class UserList extends ArrayAdapter<User> {

    private Activity context;
    private List<User> userList;

    public UserList(Activity context, List<User> userList) {

        super(context, R.layout.list_layout, userList);
        this.context = context;
        this.userList = userList;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null,true);

        TextView textViewEmail = (TextView)listViewItem.findViewById(R.id.textViewEmail);
        TextView textViewRole = (TextView)listViewItem.findViewById(R.id.textViewRole);

        User user = userList.get(position);

        textViewEmail.setText(user.Email);
        textViewRole.setText(user.Role);

        return listViewItem;


    }
}
