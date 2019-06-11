package com.noojman.testprepgradingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.noojman.testprepgradingapp.CustomRVItemTouchListener;
import com.noojman.testprepgradingapp.R;
import com.noojman.testprepgradingapp.RecyclerViewItemClickListener;
import com.noojman.testprepgradingapp.Recycler_View_Adapter;
import com.noojman.testprepgradingapp.RowData;

import java.util.ArrayList;
import java.util.List;

public class FindTestActivity extends AppCompatActivity {

    List<RowData> tests = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_test);

        //TODO GET FROM FIREBASE
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("tests")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                tests.add(new RowData(document.getData().get("bookTitle").toString(),
                                        document.getData().get("publisherName").toString(),
                                        Integer.parseInt(document.getData().get("testNum").toString()),
                                        Integer.parseInt(document.getData().get("numProblems").toString())));
                            }
                            setTestList();
                        } else {
                            Log.w(this.getClass().getSimpleName(), "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    void setTestList() {
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        Recycler_View_Adapter adapter = new Recycler_View_Adapter(tests, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);

        recyclerView.addOnItemTouchListener(new CustomRVItemTouchListener(this, recyclerView, new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                int numProblems = ((Recycler_View_Adapter) recyclerView.getAdapter()).getData().get(position).numProblems;
                int timerSeconds = 30; // TODO add timer seconds length as a test-specific field for each
                Intent intent = new Intent(FindTestActivity.this, BubbleSheetActivity.class);
                intent.putExtra("numProblems", numProblems);
                intent.putExtra("timerSeconds", timerSeconds);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
    }
}
