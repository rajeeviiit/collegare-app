import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import org.json.JSONArray;

class CopModelParser {

	SociallinksModelParser sociallinks_parser;
	CreatorModelParser creator_parser = new CreatorModelParser();
	VersionModelParser version_parser = new VersionModelParser();
	AdeesModelParser adees_parser = new AdeesModelParser();

	public CopModelParser() {
		sociallinks_parser = new SociallinksModelParser();
	}

	public CopModel parseCopModel(String json_object) {

		CopModel local_model = null;
		try {
			JSONObject jsobj = new JSONObject(json_object);

			ArrayList<SociallinksModel> sociallinkss = new ArrayList<>();
			JSONArray sociallinks_arr = jsobj.getJSONArray("sociallinks");

			for (int i = 0; i < sociallinks_arr.length(); i++) {

				sociallinkss.add(sociallinks_parser.parseSociallinksModel((String) sociallinks_arr.get(i)));

			}

			CreatorModel creator = creator_parser.parseCreatorModel(jsobj.getJSONObject("creator").toString());

			VersionModel version = version_parser.parseVersionModel(jsobj.getJSONObject("version").toString());

			AdeesModel adees = adees_parser.parseAdeesModel(jsobj.getJSONObject("adees").toString());

			local_model = new CopModel(jsobj.getString("location_name"), jsobj.getString("organizer_description"), jsobj.getString("organizer_name"), jsobj.getInt("speakers_ver"), jsobj.getInt("tracks_ver"), jsobj.getString("name"), jsobj.getInt("sponsors_ver"), jsobj.getString("description"), jsobj.getString("schedulepublished_on"), jsobj.getString("privacy"), sociallinkss, jsobj.getString("end_time"), creator, jsobj.getString("logo"), version, jsobj.getString("time_zone"), jsobj.getString("type"), jsobj.getString("start_time"), adees, jsobj.getString("topic"), jsobj.getString("state"), jsobj.getString("email"), jsobj.getInt("id"), );
		} catch (JSONException e) {

			e.printStackTrace();
			}

		return local_model;
		}
			
}