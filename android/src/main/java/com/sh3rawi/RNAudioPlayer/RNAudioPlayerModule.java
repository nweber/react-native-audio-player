package com.sh3rawi.RNAudioPlayer;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.Callback;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;

public class RNAudioPlayerModule extends ReactContextBaseJavaModule {
  ReactApplicationContext reactContext;
  MediaPlayer mp;

  public RNAudioPlayerModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNAudioPlayer";
  }

  @ReactMethod
  public void play(String audio) {
    mp = MediaPlayer.create(this.reactContext, Uri.fromFile(new File(audio)));
    mp.start();
    mp.setOnCompletionListener(new OnCompletionListener() {
      @Override
      public void onCompletion(MediaPlayer mp) {
        mp.reset();
        mp.release();
        mp = null;
      }
    });
  }

  @ReactMethod
  public void stop(){
    mp.stop();
  }
}
