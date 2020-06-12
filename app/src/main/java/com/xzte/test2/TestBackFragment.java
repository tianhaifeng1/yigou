package com.xzte.test2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trjx.tlibs.uils.Logger;
import com.xzte.R;

import java.util.ArrayList;
import java.util.List;

public class TestBackFragment extends Fragment implements BaseQuickAdapter.OnItemClickListener {

    private int code;
    private RecyclerView mRecyclerview;
    private TestBackAdapter backAdapter;

    public TestBackFragment(int code) {
        this.code = code;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_back, null);
        mRecyclerview = view.findViewById(R.id.recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        backAdapter = new TestBackAdapter(null);
        mRecyclerview.setLayoutManager(layoutManager);
        mRecyclerview.setAdapter(backAdapter);
        backAdapter.setOnItemClickListener(this);
        initData();


        return view;
    }

    public void initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("司法机关" + (i + 1));
        }
        backAdapter.setNewData(list);
        backAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivityForResult(new Intent(this.getContext(), TestToBackActivity.class), 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100&&resultCode==200){
            Logger.t("------------调用了");
            initData();
        }
    }
}
