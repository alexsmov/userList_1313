package com.example.userlist_1313;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class UserListFragment extends Fragment {
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    Button addUserBtn;
    Users users;
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        View view = layoutInflater.inflate(R.layout.fragment_user_list, viewGroup, false);
        addUserBtn = view.findViewById(R.id.addUserBtn);
        recyclerView = view.findViewById(R.id.recyclerView);
        users = new Users(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FormUserActivity.class);
                startActivity(intent);
            }
        });
        return  view;
    }
    @Override
    public void onResume(){
        super.onResume();
        userAdapter = new UserAdapter(users.getUserList());
        recyclerView.setAdapter(userAdapter);
    }

    public class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView itemTextView;
        String userName;
        int userPosition; // Для поиска пользователя в списке
        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.single_item, viewGroup, false));
            itemTextView = itemView.findViewById(R.id.itemTextView);
            itemView.setOnClickListener(this);
        }
        public void bind(String userName, int position){
            this.userName = userName;
            userPosition = position;
            itemTextView.setText(userName);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getContext(), InfoUserActivity.class);
            intent.putExtra("userPosition", userPosition);
            startActivity(intent);
        }
    }

    public class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        ArrayList<User> userList = new ArrayList<>();
        public UserAdapter(ArrayList<User> userList) {
            this.userList = userList;
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new UserHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) {
            User user = userList.get(position);
            String userName = user.getUserName()+" "+user.getUserLastName();
            userHolder.bind(userName, position);
        }

        @Override
        public int getItemCount() {
            return userList.size();
        }
    }
}
