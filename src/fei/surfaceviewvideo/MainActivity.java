package fei.surfaceviewvideo;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private SurfaceView mSurfaceView;
	private Button mBtnPlay;
	private MediaPlayer mMediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
		mBtnPlay = (Button) findViewById(R.id.btn_play);

		final SurfaceHolder surfaceHolder = mSurfaceView.getHolder();
		surfaceHolder.setFixedSize(100, 100);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		mMediaPlayer = new MediaPlayer();
		mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		
		
		mBtnPlay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				mMediaPlayer.setDisplay(surfaceHolder);
				try {
					mMediaPlayer.setDataSource("sdcard/video.3gp");
					mMediaPlayer.prepare();
					mMediaPlayer.start();
				} catch (Exception e) {
				}
			}
		});
	}

}
