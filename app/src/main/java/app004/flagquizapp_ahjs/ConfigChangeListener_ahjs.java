package app004.flagquizapp_ahjs;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.widget.Toast;


import java.util.Set;

import app004.flagquizapp.R;

public class ConfigChangeListener_ahjs implements OnSharedPreferenceChangeListener {
    private MainActivity_ahjs mainActivityAhjs;

    public ConfigChangeListener_ahjs(MainActivity_ahjs mainActivityAhjs) {
        this.mainActivityAhjs = mainActivityAhjs;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        this.mainActivityAhjs.setPreferencesChanged(true);

        if (key.equals(this.mainActivityAhjs.getREGIONS())) {
            this.mainActivityAhjs.getQuizViewModel().setGuessRows(sharedPreferences.getString(
                    MainActivity_ahjs.CHOICES, null));
            this.mainActivityAhjs.getQuizFragment().resetQuiz();
        } else if (key.equals(this.mainActivityAhjs.getCHOICES())) {
            Set<String> regions = sharedPreferences.getStringSet(this.mainActivityAhjs.getREGIONS(),
                    null);
            if (regions != null && regions.size() > 0) {
                this.mainActivityAhjs.getQuizViewModel().setRegionsSet(regions);
                this.mainActivityAhjs.getQuizFragment().resetQuiz();
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                regions.add(this.mainActivityAhjs.getString(R.string.default_region));
                editor.putStringSet(this.mainActivityAhjs.getREGIONS(), regions);
                editor.apply();
                Toast.makeText(this.mainActivityAhjs, R.string.default_region_message,
                        Toast.LENGTH_LONG).show();
            }
        }

        Toast.makeText(this.mainActivityAhjs, R.string.restarting_quiz,
                Toast.LENGTH_SHORT).show();
    }
}
