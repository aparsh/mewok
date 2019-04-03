package com.example.android.mewok;

import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    /** Handles playback of all the sound files */
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            if (mMediaPlayer != null) {
                // Regardless of the current state of the media player, release its resources
                // because we no longer need it.
                mMediaPlayer.release();

                // Set the media player back to null. For our code, we've decided that
                // setting the media player to null is an easy way to tell that the media player
                // is not configured to play an audio file at the moment.
                mMediaPlayer = null;
            }
        }
    };

    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener= new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int audiofocusChanged) {
            switch (audiofocusChanged) {
                case AudioManager.AUDIOFOCUS_LOSS:
                    mMediaPlayer.stop();
                    mMediaPlayer.release();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    mMediaPlayer.pause();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    mMediaPlayer.setVolume(1, 1);
                    break;
                case AudioManager.AUDIOFOCUS_GAIN:
                    mMediaPlayer.start();

            }
        }
    };


            @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        mAudioManager =(AudioManager) getSystemService(Context.AUDIO_SERVICE);


        final ArrayList<Word_Phrases> wordsp = new ArrayList<Word_Phrases>();
        wordsp.add(new Word_Phrases("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        wordsp.add(new Word_Phrases("What is your name?","tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        wordsp.add(new Word_Phrases("My name is...","oyaaset...",R.raw.phrase_my_name_is));
        wordsp.add(new Word_Phrases("How are you feeling?","michәksәs?",R.raw.phrase_how_are_you_feeling));
        wordsp.add(new Word_Phrases("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        wordsp.add(new Word_Phrases("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming));
        wordsp.add(new Word_Phrases("Yes, I’m coming.","hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        wordsp.add(new Word_Phrases("I’m coming.","әәnәm",R.raw.phrase_im_coming));
        wordsp.add(new Word_Phrases("Let’s go.","yoowutis",R.raw.phrase_lets_go));
        wordsp.add(new Word_Phrases("Come here.","әnni'nem",R.raw.phrase_come_here));

        PhrasesAdapter phrases_Adapter =
                new PhrasesAdapter(this, wordsp,R.color.categoryphrases);

        ListView listView1 = (ListView) findViewById(R.id.list);

        listView1.setAdapter(phrases_Adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mMediaPlayer != null) {
                    // Regardless of the current state of the media player, release its resources
                    // because we no longer need it.
                    mMediaPlayer.release();

                    // Set the media player back to null. For our code, we've decided that
                    // setting the media player to null is an easy way to tell that the media player
                    // is not configured to play an audio file at the moment.
                    mMediaPlayer = null;
                }
                Word_Phrases word=wordsp.get(position);
                int result=mAudioManager.requestAudioFocus(mAudioFocusChangeListener,AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioid1());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);


                }
                mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        getActionBar().setDisplayHomeAsUpEnabled(true);
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                            // Navigate up to the closest parent
                            .startActivities();
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMediaPlayer.release();

    }
}
