package com.example.learning.controllers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/debug")
public class DebugController {

    @Autowired
    private Gson gson;

    @PostMapping("/sso")
    private ResponseEntity<?> sso(@RequestParam String authorizationToken) throws Exception {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(authorizationToken);

        String jsonString = gson.toJson(decodedToken.getClaims().get("firebase"));

        String subjectId = new JSONObject(jsonString)
                .getJSONObject("identities")
                .getJSONArray("apple.com")
                .get(0)
                .toString();

        return ResponseEntity.ok(subjectId);
    }
}
