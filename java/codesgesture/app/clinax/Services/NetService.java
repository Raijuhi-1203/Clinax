package codesgesture.app.clinax.Services;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import java.util.ArrayList;

import codesgesture.app.clinax.Utils.Constants;


public class NetService {
    public static String invokeJSONWS(String MEthod, ArrayList<NetParam> keyvalues,String str) {
        String responseJSON = "";
        // Create request
        SoapObject request = new SoapObject(Constants.NAMESPACE, MEthod);
        if (keyvalues != null) {
            for (NetParam param : keyvalues) {
                PropertyInfo paramPI = new PropertyInfo();
                paramPI.setName(param.getKey());
                paramPI.setValue(param.getValue());
                request.addProperty(paramPI);
            }
        }

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = true;

        Element[] header = new Element[1];
        header[0] = new Element().createElement(Constants.SOAP_ACTION, "SoapHeaderClass");

        Element accountInfo = new Element().createElement(Constants.SOAP_ACTION, "usernm");
        header[0].addChild(Node.ELEMENT, accountInfo);

        Element apiKey = new Element().createElement(Constants.SOAP_ACTION, "pass");
        apiKey.addChild(Node.TEXT, "String");
        accountInfo.addChild(Node.ELEMENT, apiKey);


        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(str);
        try {
            // Invole web service
            androidHttpTransport.call(Constants.SOAP_ACTION + MEthod, envelope);
            // Get the response
             SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
          //  SoapObject response = (SoapObject) envelope.getResponse();
            // Assign it to static variable
            responseJSON = response.toString();
            // JSONObject readxml=UserUtil.readToJSON(responseJSON);

        } catch (Exception e) {
            e.printStackTrace();
        }
       return responseJSON;
    }




}
