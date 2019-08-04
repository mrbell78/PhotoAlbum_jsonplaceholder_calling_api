package com.mrbell.photoalbum_retrofit2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mrbell.photoalbum_retrofit2.activity.AlbumActivity;
import com.mrbell.photoalbum_retrofit2.activity.BaseActivity;
import com.mrbell.photoalbum_retrofit2.adapter.UserAdapter;
import com.mrbell.photoalbum_retrofit2.api.ApiInterface;
import com.mrbell.photoalbum_retrofit2.api.RetrofitClient;
import com.mrbell.photoalbum_retrofit2.appconstant.AppConstant;
import com.mrbell.photoalbum_retrofit2.listener.OnItemClickListener;
import com.mrbell.photoalbum_retrofit2.model.User;
import com.mrbell.photoalbum_retrofit2.utility.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager lytManagerWord;
    private Activity mActivity;
    private ArrayList<User> userList;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVariable();
        initView();
      /*  recyclerView = findViewById(R.id.recycler_user);
        recyclerView.setHasFixedSize(true);
        lytManagerWord = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(lytManagerWord);
       recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,lytManagerWord,16));
        userAdapter = new UserAdapter(userList, mActivity);
        recyclerView.setAdapter(userAdapter);*/
        initLoader();
        getDataFromServer();
        initListener();
    }

    private void initVariable() {
        mActivity = MainActivity.this;
        userList = new ArrayList<>();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_user);
        recyclerView.setHasFixedSize(true);
        lytManagerWord = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(lytManagerWord);
        //recyclerView.addItemDecoration(new DividerItemDecoration();
        userAdapter = new UserAdapter(userList, mActivity);
        recyclerView.setAdapter(userAdapter);
    }

    public void getDataFromServer() {
        ApiInterface api = RetrofitClient.getClient().create(ApiInterface.class);
        Call<List<User>> call = api.getUser(1);

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!userList.isEmpty()) {
                    userList.clear();
                }
                userList.addAll(response.body());
                hideLoader();
                if (userList.isEmpty()) {
                    showEmptyView();
                }
                userAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                if (userList.isEmpty()) {
                    showEmptyView();
                }
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initListener() {
        userAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void itemClickListener(View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_title:
                        sendDataViaIntent(position);
                        break;
                    case R.id.m_layout:
                        sendDataViaIntent(position);
                        break;

                    default:
                        sendDataViaIntent(position);
                        break;
                }

            }
        });
    }

    private void sendDataViaIntent(int position) {
        Intent intent = new Intent(mActivity, AlbumActivity.class);
        intent.putExtra(AppConstant.INTENT_KEY, userList.get(position).getId());
        startActivity(intent);
    }
}
