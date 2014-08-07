
package com.benjaminkiesling.zauberkugel;

import com.benjaminkiesling.zauberkugel.ShakeDetector.OnShakeListener;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity {

    private Zauberkugel mZauberkugel = new Zauberkugel();
    private TextView mAnswerLabel;
    private ImageView mCrystalBallImage;
    private SensorManager mSensorManager;
    private Sensor mAccelermometer;
    private ShakeDetector mShakeDetector;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        // Assign our View variables and assign them to Views on the Views from the layout file
        
        mAnswerLabel = (TextView) findViewById(R.id.textView1);
        mCrystalBallImage = (ImageView) findViewById(R.id.imageView1);
        
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelermometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(new OnShakeListener() {
            
            @Override
            public void onShake() {
                handleNewAnswer();
                
            }
        });
        

        

    }
    
    @Override
    public void onResume(){
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelermometer,
                SensorManager.SENSOR_DELAY_UI);
        
    }
    @Override
    public void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(mShakeDetector);
        
    }
    
    private void animateZauberkugel(){
        mCrystalBallImage.setImageResource(R.drawable.ball_animation);
        AnimationDrawable ballAnimation = (AnimationDrawable)mCrystalBallImage.getDrawable();
        if (ballAnimation.isRunning()){
            ballAnimation.stop();
            }
        ballAnimation.start();
        
    }
    
    private void animateAnswer(){
        AlphaAnimation fadeInAnimation = new AlphaAnimation(0, 1);
        fadeInAnimation.setDuration(1500);
        fadeInAnimation.setFillAfter(true);
        
        mAnswerLabel.setAnimation(fadeInAnimation);
    }
    
    private void playSound(){
        MediaPlayer player =  MediaPlayer.create(this, R.raw.crystal_ball);
        player.start();
        player.setOnCompletionListener(new OnCompletionListener() {
            
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                
            }
        });
    }
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void handleNewAnswer() {
        String answer = mZauberkugel.getAnswer();

        //Button was clicked, answerLabel gets updated
        mAnswerLabel.setText(answer);
        
        animateZauberkugel();
        animateAnswer();
        playSound();
    }
}
