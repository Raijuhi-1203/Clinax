package codesgesture.app.clinax;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.clinax.Services.CallJson;
import codesgesture.app.clinax.Services.JsonCallbacks;
import codesgesture.app.clinax.Services.NetParam;
import codesgesture.app.clinax.Services.UserUtil;

public class PagePrivacy extends AppCompatActivity {
    TextView txpolicy;
    String s;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy);
        s=getString(R.string.con);
        txpolicy=findViewById(R.id.txpolicy);
        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title=findViewById(R.id.title);
        title.setText("Privacy Policy");

         BindPolicy();

    }

    private void BindPolicy() {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(PagePrivacy.this);
        jc.SendRequest(s,"get_privacy", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONObject obj = UserUtil.ConvertStringToJsonObject(json);
                if (obj.length() != 0) {
                   // txpolicy.setText(obj.getString("privacy_information"));
                    txpolicy.setText(Html.fromHtml(obj.getString("privacy_policy_content")));
                }
            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");

    }



}