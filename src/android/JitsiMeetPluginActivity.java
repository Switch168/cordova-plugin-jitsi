package com.cordova.plugin.jitsi;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import com.cordova.plugin.jitsi.JitsiPlugin;//.JITSI_PLUGIN_TAG
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.util.HashMap;

public class JitsiMeetPluginActivity extends JitsiMeetActivity {
    
    public static void launchActivity(Context context, JitsiMeetConferenceOptions options) {
        Intent intent = new Intent(context, JitsiMeetPluginActivity.class);
        intent.setAction("org.jitsi.meet.CONFERENCE");
        intent.putExtra("JitsiMeetConferenceOptions", options);
        context.startActivity(intent);

    }

    @Override
    public void onDestroy() {
        JitsiPluginModel.getInstance().changeState("onConferenceDestroyed");
        super.onDestroy();
    }

    @Override
    public void finish() {
        JitsiPluginModel.getInstance().changeState("onConferenceFinished");
        super.finish();
    }

    @Override
    public void onConferenceJoined(HashMap<String, Object> data) {
        JitsiPluginModel.getInstance().changeState("onConferenceJoined");
        super.onConferenceJoined(data);
    }

    @Override
    public void onConferenceTerminated(HashMap<String, Object> data) {
        JitsiPluginModel.getInstance().changeState("onConferenceTerminated");
        super.onConferenceTerminated(data);
    }

    @Override
    public void onConferenceWillJoin(HashMap<String, Object> data) {
        JitsiPluginModel.getInstance().changeState("onConferenceWillJoin");
        super.onConferenceWillJoin(data);
    }
    
    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        if (!isInPictureInPictureMode) {
            this.startActivity(new Intent(this, getClass())
                    .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
        }
    }
}
