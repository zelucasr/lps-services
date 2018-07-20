package ufrn.socialanalytics.receiver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


@Path("/teste")
public class Service {

	@Context
	ServletContext context;

	@GET
	@Produces("text/plain")
	public String getMethod() throws IOException, InterruptedException {

		// Initialize Firebase
		try {
			// [START initialize]
			FileInputStream serviceAccount = new FileInputStream(
					context.getRealPath("WEB-INF/socialanalytics-6b16b-firebase-adminsdk-3n1vk-87f9484673.json"));
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://socialanalytics-6b16b.firebaseio.com").build();
			FirebaseApp.initializeApp(options);
			// [END initialize]
		} catch (IOException e) {
			System.out.println("ERROR: invalid service account credentials. See README.");
			System.out.println(e.getMessage());

			System.exit(1);
		}
		
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("");

		DatabaseReference usersRef = ref.child("users");

		Map<String, User> users = new HashMap<>();
		users.put("alanisawesome", new User("June 23, 1912", "Alan Turing"));
		users.put("gracehop", new User("December 9, 1906", "Grace Hopper"));

		usersRef.setValueAsync(users);

		return "teste";
	}

	public static class User {

		public String date_of_birth;
		public String full_name;
		public String nickname;

		public User(String date_of_birth, String full_name) {
			// ...
		}

		public User(String date_of_birth, String full_name, String nickname) {
			// ...
		}

	}

}
