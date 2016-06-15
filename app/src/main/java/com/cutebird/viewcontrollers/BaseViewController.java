package com.cutebird.viewcontrollers;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cutebird.R;
import com.cutebird.RingdroidSelectActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.HashMap;

public class BaseViewController extends AppCompatActivity {

    public final String MenuPositionTag = "MenuPosition";
    Toolbar toolbar;
    Drawer drawer;
    HashMap<Integer, Integer> menuMap = new HashMap<>();
    int menuIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_base);
    }

    /**
     * set toolbar title
     *
     * @param title
     */
    protected void setTitle(String title) {
        toolbar.setTitle(title);
    }

    /**
     * add navigation drawer to the activity
     */
    protected void addNavigationDrawer() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_menu);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (drawer.isDrawerOpen()) {
                            drawer.closeDrawer();
                        } else {
                            drawer.openDrawer();
                        }
                    }
                }
        );

        drawer = new DrawerBuilder().withActivity(this).build();

        drawer.addItem(getMenuItem(R.string.main, RingdroidSelectActivity.class));
        // drawer.addItem(addMenuItem(R.string.education_material, AboutUsViewController.class));
        //__________________________________________________________________________

        drawer.addItem(new DividerDrawerItem());
        //  drawer.addItem(getChangeLanguageItem());
        // drawer.addItem(new SecondaryDrawerItem().withName(R.string.change_language).withIconColor(Color.BLUE));
        //    drawer.addItem(getLogOutItem());
        //  drawer.openDrawer();
        drawer.setSelectionAtPosition(0);
    }

    /**
     * init menu item to the navigation drawer
     *
     * @param name       menu item name
     * @param controller the destination view controller
     * @return
     */
    private PrimaryDrawerItem getMenuItem(int name, final Class<?> controller) {
        menuMap.put(name, menuIndex);
        menuIndex++;
        return new PrimaryDrawerItem().withName(name).withIconColor(Color.BLACK).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                if (drawer.getCurrentSelection() != position) {
                    Intent mainIntent = new Intent(BaseViewController.this, controller);
                    startActivity(mainIntent);
                    finish();
                }
                return false;
            }
        });
    }

    /**
     * init language switch to navigation drawer
     *
     * @return
     */
    /*private PrimaryDrawerItem getChangeLanguageItem() {
        return new PrimaryDrawerItem().withName(R.string.change_language).withIconColor(Color.BLACK).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                if (drawer.getCurrentSelection() != position) {
                    Util.getInstance().changeLanguage(BaseViewController.this);
                }
                return false;
            }
        });
    }*/

    /**
     * init logout item to navigation drawer
     *
     * @return
     */
    /*private PrimaryDrawerItem getLogOutItem() {
        return new PrimaryDrawerItem().withName(R.string.logout).withIconColor(Color.BLACK).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                if (drawer.getCurrentSelection() != position) {
                    Toast.makeText(getApplicationContext(), getString(R.string.logout), Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
    }*/

    /**
     * set selected menu item
     *
     * @param name use the name of menu item
     */
    protected void setMenuIndex(@StringRes int name) {
        drawer.setSelectionAtPosition(menuMap.get(name));
    }

    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(BaseViewController.this, RingdroidSelectActivity.class);
        startActivity(mainIntent);
        finish();
    }

}
