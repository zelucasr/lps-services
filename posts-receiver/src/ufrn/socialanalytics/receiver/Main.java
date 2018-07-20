package ufrn.socialanalytics.receiver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ufrn.socialanalytics.receiver.util.Constants;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {

		// Initialize Firebase
		try {
			// [START initialize]
			FileInputStream serviceAccount = new FileInputStream(Constants.FIREBASE_JSON);
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl(Constants.FIREBASE_DATABASE_URL).build();
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
