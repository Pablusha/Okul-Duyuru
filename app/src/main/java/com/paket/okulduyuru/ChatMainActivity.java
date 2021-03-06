package com.paket.okulduyuru;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paket.okulduyuru.Model.Ogretmen;
import com.paket.okulduyuru.UI.LoginActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class ChatMainActivity extends AppCompatActivity {

    private ViewPager myViewPager;
    private TabLayout myTabLayout;
    private TabsAccessorAdapter myTabsAccessorAdapter;

    public FirebaseAuth firebaseAuth;
    private DatabaseReference RootRef;
    private String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_main);


        firebaseAuth = FirebaseAuth.getInstance();
        RootRef = FirebaseDatabase.getInstance().getReference();


        myViewPager = (ViewPager) findViewById(R.id.main_tabs_pager);
        myTabsAccessorAdapter = new TabsAccessorAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(myTabsAccessorAdapter);


        myTabLayout = (TabLayout) findViewById(R.id.main_tabs);
        myTabLayout.setupWithViewPager(myViewPager);

    }

    private void RequestNewGroup() {
        AlertDialog.Builder builder  = new AlertDialog.Builder(ChatMainActivity.this, R.style.AlertDialog);
        builder.setTitle("Grup Adı Belirleyin :");

        final EditText groupNameField = new EditText(ChatMainActivity.this);
        groupNameField.setHint("Orn: Bilgisayar Programcılığı");
        builder.setView(groupNameField);

        builder.setPositiveButton("Oluştur", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String groupName = groupNameField.getText().toString();

                if(TextUtils.isEmpty(groupName))
                {
                    Toast.makeText(ChatMainActivity.this, "Lütfen Bir Grup Adı Giriniz!..", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    CreateNewGroup(groupName);
                }
            }
        });

        builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    private void CreateNewGroup(final String groupName) {
        RootRef.child("Groups").child(groupName).setValue("")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(ChatMainActivity.this, groupName + " Grubu Oluşturuldu!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void SendUserToSettingsActivity() {
        Intent settingsIntent = new Intent(ChatMainActivity.this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    private void SendUserToLoginActivity() {
        Intent loginIntent = new Intent(ChatMainActivity.this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }

    private void SendUserToFindFriendsActivity() {
        Intent findFriendsIntent = new Intent(ChatMainActivity.this, FindFriendsActivity.class);
        startActivity(findFriendsIntent);
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser == null) {
            SendUserToLoginActivity();
        }
        else {
            updateUserStatus("online");

            VerifyUserExistance();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null)
        {
            updateUserStatus("offline");
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null)
        {
            updateUserStatus("offline");
        }
    }



    private void VerifyUserExistance() {
        String currentUserID = firebaseAuth.getCurrentUser().getUid();

        RootRef.child("Users").child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if((dataSnapshot.child("name").exists()))
                {

                }
                else
                {
                    SendUserToSettingsActivity();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.options_menu, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.main_logout_option) {
            updateUserStatus("offline");
            firebaseAuth.signOut();
            SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("hatirla","false");
            editor.apply();
            SharedPreferences preferences2 = getSharedPreferences("ogretmenCheckbox",MODE_PRIVATE);
            SharedPreferences.Editor editor2 = preferences2.edit();
            editor2.putString("ogretmenHatirla","false");
            editor2.apply();
            SendUserToLoginActivity();

        }
        if (item.getItemId() == R.id.main_settings_option) {
            SendUserToSettingsActivity();
        }
        if (item.getItemId() == R.id.main_create_group_option) {
            RequestNewGroup();
        }
        if (item.getItemId() == R.id.main_find_friends_option) {
            SendUserToFindFriendsActivity();
        }
        return true;
    }





    private void updateUserStatus(String state)
    {
        String saveCurrentTime, saveCurrentDate;

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd MMM, yyyy", new Locale("tr"));
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm", new Locale("tr"));
        saveCurrentTime = currentTime.format(calendar.getTime());

        HashMap<String, Object> onlineStateMap = new HashMap<>();
        onlineStateMap.put("time", saveCurrentTime);
        onlineStateMap.put("date", saveCurrentDate);
        onlineStateMap.put("state", state);

        currentUserID = firebaseAuth.getCurrentUser().getUid();

        RootRef.child("Users").child(currentUserID).child("userState")
                .updateChildren(onlineStateMap);

    }

}