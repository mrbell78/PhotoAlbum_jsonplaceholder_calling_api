package com.mrbell.photoalbum_retrofit2.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mrbell.photoalbum_retrofit2.R;
import com.mrbell.photoalbum_retrofit2.adapter.AlbumAdapter;
import com.mrbell.photoalbum_retrofit2.model.Album;

import java.util.ArrayList;

public class AlbumActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager lytManager;
    private Activity mActivity;
    private ArrayList<Album> albumList;
    private AlbumAdapter albumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
    }
}
