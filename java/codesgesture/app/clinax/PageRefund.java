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

public class PageRefund extends AppCompatActivity {
    TextView txpolicy;
    String s;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refund);
       // s=getString(R.string.con);
        txpolicy=findViewById(R.id.txpolicy);
        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title=findViewById(R.id.title);
        title.setText("Refund Policy");

        txpolicy.setText(Html.fromHtml("<b>Cancellation and Refund Policy</b>\n" +
                "In the event that, the Practitioner with whom User has booked a paid appointment via the Website, has not been able to meet the User, User will need to write to us at support@practo.com within five (5) days from the occurrence of such event; in which case, the entire consultation amount as mentioned on the Website will be refunded to the User within the next five (5) to six (6) business days in the original mode of payment done by the User while booking. In case where the User, does not show up for the appointment booked with a Practitioner, without cancelling the appointment beforehand, the amount will not be refunded, and treated as under Clause 3.4.6. However, where cancellation charges have been levied (as charged by the Practitioner/Practice), you would not be entitled to complete refund even if you have cancelled beforehand."));
       // BindPolicy();
    }

//    private void BindPolicy() {
//        ArrayList<NetParam> param;
//        param = new ArrayList<NetParam>();
//        CallJson jc = new CallJson(PageRefund.this);
//        jc.SendRequest(s,"get_refund", param, new JsonCallbacks() {
//            @Override
//            public void onPostSuceess(String json, String method) throws JSONException {
//                JSONObject obj = UserUtil.ConvertStringToJsonObject(json);
//                if (obj.length() != 0) {
//                   // txpolicy.setText(obj.getString("return_information"));
//                    txpolicy.setText(Html.fromHtml(obj.getString("return_information")));
//                }
//            }
//
//            @Override
//            public void onPostError(String msg) {
//
//            }
//        }, "", "Loading..");
//
//    }

}
