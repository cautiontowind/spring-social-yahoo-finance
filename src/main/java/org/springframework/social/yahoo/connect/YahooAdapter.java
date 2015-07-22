package org.springframework.social.yahoo.connect;

import java.io.IOException;

import org.springframework.social.ApiException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.yahoo.profile.Profile;
import org.springframework.social.yahoo.api.Yahoo;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class YahooAdapter implements ApiAdapter<Yahoo> {

    public boolean test(Yahoo yahoo) {
        try {
            yahoo.profileDetailsOperations().profile();
            return true;
        } catch (ApiException | IOException e) {
        	e.printStackTrace();
            return false;
        }
    }

    @Override
    public void setConnectionValues(Yahoo yahoo, ConnectionValues values) {
        Profile profile;
		try {
			profile = yahoo.profileDetailsOperations().profile();
	        values.setProviderUserId(profile.getGuid());
	        values.setDisplayName(profile.getNickname());
	        values.setProfileUrl(profile.getProfileUrl());
	        values.setImageUrl(profile.getImage().getImageUrl());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @Override
    public UserProfile fetchUserProfile(Yahoo yahoo) {
     //   System.out.println("TODO : Implement fetchUserProfile");
        Profile profile;
		try {
			profile = yahoo.profileDetailsOperations().profile();
	        return new UserProfileBuilder().setName(profile.getGivenName()+" "+profile.getFamilyName())
	                .setUsername(profile.getNickname()).build();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

    }


    @Override
    public void updateStatus(final Yahoo yahoo, final String message) {
        throw new UnsupportedOperationException();
    }

}
