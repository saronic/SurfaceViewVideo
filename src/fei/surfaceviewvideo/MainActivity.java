package fei.surfaceviewvideo;

import java.io.IOException;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private SurfaceView mSurfaceView;
	private Button mBtnPlay;
	private MediaPlayer mMediaPlayer;
	private Button mBtnStop;
	private Button mBtnPause;
	private SurfaceHolder mSurfaceHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
		mBtnPlay = (Button) findViewById(R.id.btn_play);
		mBtnStop = (Button) findViewById(R.id.btn_stop);
		mBtnPause = (Button) findViewById(R.id.btn_pause);

		mSurfaceHolder = mSurfaceView.getHolder();
		//mSurfaceHolder.setFixedSize(100, 100);
		//mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		

		mBtnPlay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				start();
			}
		});
		
		mBtnPause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mMediaPlayer != null) {
					if (mBtnPause.getText().equals("pause")) {
						mMediaPlayer.pause();
						mBtnPause.setText("continue");
					} else {
						mMediaPlayer.start();
						mBtnPause.setText("pause");
					}
				}
				
			}
		});
		mBtnStop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stop();
				
			}
		});
	}

	private void stop() {
		
		if (mMediaPlayer != null) {
			mMediaPlayer.stop();
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}

	private void start() {
		stop();
		mMediaPlayer = new MediaPlayer();
		mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mMediaPlayer.setDisplay(mSurfaceHolder);
		try {
			mMediaPlayer.setDataSource("sdcard/video.3gp");
			mMediaPlayer.prepare();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mMediaPlayer.start();
	}

}
