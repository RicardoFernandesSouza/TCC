package com.example.ricardofernandes.tohome;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class EscolhaProjeto extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private ArrayList<Projeto> projetoArrayList;
    private FloatingActionButton feb;
    private boolean gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_projeto);
        projetoArrayList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyle_view);
        feb = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        setRecyclerViewData();
        adapter = new MyAdapter(this, projetoArrayList);
        recyclerView.setAdapter(adapter);
        feb.setOnClickListener(onAddingListener());
        setTheme(R.style.AppTheme_NoActionBar);

    }

    private void setRecyclerViewData() {
        projetoArrayList.add(new Projeto("Rua Sete de Setembro, 567, Centro - Salto/SP", false, ""));
        projetoArrayList.add(new Projeto("Rua 23 de Maio, 199, Centro - Salto/SP", true, ""));
    }

    private View.OnClickListener onAddingListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(EscolhaProjeto.this);
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle("Add a new friend");
                dialog.setCancelable(false);

                EditText name = (EditText) dialog.findViewById(R.id.name);
                EditText address = (EditText) dialog.findViewById(R.id.address);
                Spinner spnGender = (Spinner) dialog.findViewById(R.id.gender);

                View btnAdd = dialog.findViewById(R.id.btn_ok);
                View btnCancel = dialog.findViewById(R.id.btn_cancel);

                ArrayList<String> gendersList = new ArrayList<>();

                gendersList.add("Male");
                gendersList.add("Female");

                ArrayAdapter<String> spnAdapter = new ArrayAdapter<String>(EscolhaProjeto.this, android.R.layout.simple_spinner_dropdown_item, gendersList);

                spnGender.setAdapter(spnAdapter);
                spnGender.setOnItemSelectedListener(onItemSelectedListener());

                btnAdd.setOnClickListener(onConfirmListener(name, address, dialog));
                btnCancel.setOnClickListener(onCancelListener(dialog));

                dialog.show();
            }
        };
    }

    private AdapterView.OnItemSelectedListener onItemSelectedListener() {

        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {

                    gender = true;
                } else {
                    gender = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
    }

    private View.OnClickListener onConfirmListener(final EditText name, final EditText address, final Dialog dialog) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Projeto friend = new Projeto(name.getText().toString().trim(), gender, address.getText().toString().trim());
                projetoArrayList.add(friend);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        };
    }

    private View.OnClickListener onCancelListener(final Dialog dialog) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        };
    }
}