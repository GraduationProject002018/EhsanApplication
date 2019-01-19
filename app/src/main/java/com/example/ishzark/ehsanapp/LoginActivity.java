package com.example.ishzark.ehsanapp;

import android.app.Activity;
import android.widget.Button;



public class LoginActivity extends Activity {
    private Button RegButton;

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Firebase.Auth.FirebaseAuth auth = Firebase.Auth.FirebaseAuth.DefaultInstance;





        auth.SignInWithCustomTokenAsync(custom_token).ContinueWith(task => {
        if (task.IsCanceled) {
            Debug.LogError("SignInWithCustomTokenAsync was canceled.");
            return;
        }
        if (task.IsFaulted) {
            Debug.LogError("SignInWithCustomTokenAsync encountered an error: " + task.Exception);
            return;
        }

        Firebase.Auth.FirebaseUser newUser = task.Result;
        Debug.LogFormat("User signed in successfully: {0} ({1})",
                newUser.DisplayName, newUser.UserId);
});

        Firebase.Auth.FirebaseUser user = auth.CurrentUser;
        if (user != null) {
            string name = user.DisplayName;
            string email = user.Email;
            System.Uri photo_url = user.PhotoUrl;
            // The user's Id, unique to the Firebase project.
            // Do NOT use this value to authenticate with your backend server, if you
            // have one; use User.TokenAsync() instead.
            string uid = user.UserId;
        }
    }
    */
}
