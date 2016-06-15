/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cutebird;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.cutebird.models.SoundModel;
import com.cutebird.viewcontrollers.BaseViewController;
import com.cutebird.views.RecyclerItemClickListener;

import java.util.ArrayList;

/**
 * Main screen that shows up when you launch Ringdroid. Handles selecting
 * an audio file or using an intent to record a new one, and then
 * launches RingdroidEditActivity from here.
 */
public class RingdroidSelectActivity extends BaseViewController {
    // Result codes
    private static final int REQUEST_CODE_EDIT = 1;
    private RecyclerView rvSounds;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // Inflate our UI from its XML layout description.
        setContentView(R.layout.media_select);
        rvSounds = (RecyclerView) findViewById(R.id.rvSounds);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvSounds.setLayoutManager(mLayoutManager);
        rvSounds.setItemAnimator(new DefaultItemAnimator());

        final ArrayList<SoundModel> soundModels = new ArrayList<>();
        SoundModel soundModel = new SoundModel();
        soundModel.setName("A");
        SoundModel soundModel2 = new SoundModel();
        soundModel2.setName("B");
        SoundModel soundModel3 = new SoundModel();
        soundModel3.setName("C");
        soundModels.add(soundModel);
        soundModels.add(soundModel2);
        soundModels.add(soundModel3);
        SoundDataAdapter dataAdapter = new SoundDataAdapter(this, soundModels);
        rvSounds.setAdapter(dataAdapter);
        //handle recycler view on item click
        rvSounds.addOnItemTouchListener(
                new RecyclerItemClickListener(RingdroidSelectActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(getApplicationContext(), soundModels.get(position).getName(), Toast.LENGTH_LONG).show();
                    }
                })
        );
        addNavigationDrawer();
        setTitle(R.string.main);
        setMenuIndex(R.string.main);
    }


    /**
     * Called with an Activity we started with an Intent returns.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent dataIntent) {
        if (requestCode != REQUEST_CODE_EDIT) {
            Toast.makeText(this, "Edit Amr", Toast.LENGTH_LONG).show();
            return;
        }

        if (resultCode != RESULT_OK) {
            return;
        }

        setResult(RESULT_OK, dataIntent);
        //finish();  // TODO(nfaralli): why would we want to quit the app here?
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.select_options, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // TODO(nfaralli): do we really need a "Show all audio" item now?
        //  menu.findItem(R.id.action_show_all_audio).setVisible(true);
        // menu.findItem(R.id.action_show_all_audio).setEnabled(!mShowAll);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_record:
                onRecord();
                return true;
            case R.id.action_edit_record:
                Toast.makeText(this, "Edit Sound FileName Amr", Toast.LENGTH_LONG).show();

                return true;
            default:
                return false;
        }
    }


    private void showFinalAlert(CharSequence message) {
        new AlertDialog.Builder(RingdroidSelectActivity.this)
                .setTitle(getResources().getText(R.string.alert_title_failure))
                .setMessage(message)
                .setPositiveButton(
                        R.string.alert_ok_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                finish();
                            }
                        })
                .setCancelable(false)
                .show();
    }

    private void onRecord() {
        try {
            Intent intent = new Intent(Intent.ACTION_EDIT, Uri.parse("record"));
            // intent.putExtra("was_get_content_intent", mWasGetContentIntent);
            intent.setClassName("com.cutebird", "com.cutebird.RingdroidEditActivity");
            startActivityForResult(intent, REQUEST_CODE_EDIT);
        } catch (Exception e) {
            Log.e("Ringdroid", "Couldn't start editor");
        }
    }

    private void startRingdroidEditor() {
        // Cursor c = mAdapter.getCursor();
        //  int dataIndex = c.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        String filename = "file name her amr";
        //String filename = c.getString(dataIndex);
        try {
            Intent intent = new Intent(Intent.ACTION_EDIT, Uri.parse(filename));
            //   intent.putExtra("was_get_content_intent", mWasGetContentIntent);
            intent.setClassName("com.cutebird", "com.cutebird.RingdroidEditActivity");
            startActivityForResult(intent, REQUEST_CODE_EDIT);
        } catch (Exception e) {
            Log.e("Ringdroid", "Couldn't start editor");
        }
    }

}
