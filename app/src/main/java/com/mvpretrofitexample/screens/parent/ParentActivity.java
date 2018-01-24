package com.mvpretrofitexample.screens.parent;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.mvpretrofitexample.R;
import com.mvpretrofitexample.screens.posts.PostFragment;
import com.mvpretrofitexample.screens.textspeach.SpeakingFragment;
import com.mvpretrofitexample.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ParentViewInterface {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;

    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        ButterKnife.bind(this);
        initToolbar();
        initDrawer();
        initListners();
}

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initDrawer(){
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initListners(){
        navigationView.setNavigationItemSelectedListener(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.parent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            addFragmentInContainer(new PostFragment(),true);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            addFragmentInContainer(new SpeakingFragment(),true);



        } else if (id == R.id.nav_slideshow) {

            Intent res = new Intent();
            String mPackage = "com.whatsapp";
            String mClass = ".ContactPicker";
            res.setComponent(new ComponentName(mPackage,mPackage+mClass));
            startActivity(res);


        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            doSocialShare("LINK","jhgasd jashgdjg ajhgajsd","www");

        } else if (id == R.id.nav_send) {

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void displayMessage(String msg) {

    }

    private void addFragmentInContainer(Fragment callto, boolean isAddtoBackStack){

        String fragName = callto.getClass().getName().toString();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        FrameLayout fl = (FrameLayout)findViewById(R.id.fl_MainContainer);
        fl.removeAllViews();

//        Bundle bundle = new Bundle();
//        if (headerTest != null && headerTest.length() > 0) {
//            bundle.putString(AppConstant.ExtraTag.HEADER_TEXT.name(), headerTest);
//            callto.setArguments(bundle);
//        }

        fragmentTransaction.replace(R.id.fl_MainContainer, callto, fragName);
        if (isAddtoBackStack) {
            fragmentTransaction.addToBackStack(fragName);
        }
        fragmentTransaction.commit();

    }

    int REQUEST_CODE_MY_PICK = 101;

    public void doSocialShare(String title, String text, String url){
        // First search for compatible apps with sharing (Intent.ACTION_SEND)
        List<Intent> targetedShareIntents = new ArrayList<Intent>();
        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        // Set title and text to share when the user selects an option.
        shareIntent.putExtra(Intent.EXTRA_TITLE, title);
        shareIntent.putExtra(Intent.EXTRA_TEXT, url);
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(shareIntent, 0);
        if (!resInfo.isEmpty()) {
            for (ResolveInfo info : resInfo) {
                Intent targetedShare = new Intent(android.content.Intent.ACTION_SEND);
                targetedShare.setType("text/plain"); // put here your mime type
                targetedShare.setPackage(info.activityInfo.packageName.toLowerCase());
                targetedShareIntents.add(targetedShare);
            }
            // Then show the ACTION_PICK_ACTIVITY to let the user select it
            Intent intentPick = new Intent();
            intentPick.setAction(Intent.ACTION_PICK_ACTIVITY);
            // Set the title of the dialog
            intentPick.putExtra(Intent.EXTRA_TITLE, title);
            intentPick.putExtra(Intent.EXTRA_INTENT, shareIntent);
            intentPick.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray());
            // Call StartActivityForResult so we can get the app name selected by the user
            this.startActivityForResult(intentPick, REQUEST_CODE_MY_PICK);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_MY_PICK) {
            if(data != null && data.getComponent() != null && !TextUtils.isEmpty(data.getComponent().flattenToShortString()) ) {
                String appName = data.getComponent().getClassName();
                // Now you know the app being picked.
                // data is a copy of your launchIntent with this important extra info added.

                // Start the selected activity
                Toast.makeText(this,appName,Toast.LENGTH_LONG).show();
                startActivity(data);
            }
        }
    }
}
