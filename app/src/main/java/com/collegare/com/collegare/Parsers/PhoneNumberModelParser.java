import org.json.JSONException;
import org.json.JSONObject;

class PhoneNumberModelParser {


		public PhoneNumberModelParser() {
		}

		public PhoneNumberModel parsePhoneNumberModel(String json_object) {

			PhoneNumberModel local_model = null;
			try {
					JSONObject jsobj = new JSONObject(json_object);

					local_model = new PhoneNumberModel(jsobj.getInt("code") , jsobj.getString("location") , );
 			} 
			catch (JSONException e){

 				 e.printStackTrace();
			}

			return local_model;
		}
			
}