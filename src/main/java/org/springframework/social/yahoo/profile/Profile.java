package org.springframework.social.yahoo.profile;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Profile implements Serializable {

    @JsonProperty("memberSince")
    private String memberSince;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("ageCategory")
    private String ageCategory;
    @JsonProperty("familyName")
    private String familyName;
    @JsonProperty("image")
    private Image image;
    @JsonProperty("timeZone")
    private String timeZone;
    @JsonProperty("emails")
    private List<Email> emails;
    @JsonProperty("givenName")
    private String givenName;
    @JsonProperty("lang")
    private String lang;
    @JsonProperty("guid")
    private String guid;
    @JsonProperty("profileUrl")
    private String profileUrl;
    @JsonProperty("isConnected")
    private String isConnected;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("bdRestricted")
    private String bdRestricted;

    public String getMemberSince ()
    {
        return memberSince;
    }

    public void setMemberSince (String memberSince)
    {
        this.memberSince = memberSince;
    }

    public String getNickname ()
    {
        return nickname;
    }

    public void setNickname (String nickname)
    {
        this.nickname = nickname;
    }

    public String getAgeCategory ()
    {
        return ageCategory;
    }

    public void setAgeCategory (String ageCategory)
    {
        this.ageCategory = ageCategory;
    }

    public String getFamilyName ()
    {
        return familyName;
    }

    public void setFamilyName (String familyName)
    {
        this.familyName = familyName;
    }

    public Image getImage ()
    {
        return image;
    }

    public void setImage (Image image)
    {
        this.image = image;
    }

    public String getTimeZone ()
    {
        return timeZone;
    }

    public void setTimeZone (String timeZone)
    {
        this.timeZone = timeZone;
    }

    public List<Email> getEmails(List<Email> emails)   {
        return emails;
    }

    public void setEmails(List<Email> emails)
    {
        this.emails = emails;
    }

    public String getGivenName ()
    {
        return givenName;
    }

    public void setGivenName (String givenName)
    {
        this.givenName = givenName;
    }

    public String getLang ()
    {
        return lang;
    }

    public void setLang (String lang)
    {
        this.lang = lang;
    }

    public String getGuid ()
    {
        return guid;
    }

    public void setGuid (String guid)
    {
        this.guid = guid;
    }

    public String getProfileUrl ()
    {
        return profileUrl;
    }

    public void setProfileUrl (String profileUrl)
    {
        this.profileUrl = profileUrl;
    }

    public String getIsConnected ()
    {
        return isConnected;
    }

    public void setIsConnected (String isConnected)
    {
        this.isConnected = isConnected;
    }

    public String getGender ()
    {
        return gender;
    }

    public void setGender (String gender)
    {
        this.gender = gender;
    }

    public String getBdRestricted ()
    {
        return bdRestricted;
    }

    public void setBdRestricted (String bdRestricted)
    {
        this.bdRestricted = bdRestricted;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Member Since: ").append(memberSince).append(", ");
        sb.append("Username: ").append(nickname).append(", ");
        sb.append("Age Category: ").append(ageCategory).append(", ");
        sb.append("Family Name: ").append(familyName).append(", ");
        sb.append("Image: ").append(image).append(", ");
        sb.append("Timezone: ").append(timeZone).append(", ");
        sb.append("List<Email>s: ").append(emails).append(", ");
        sb.append("Given Name: ").append(givenName).append(", ");
        sb.append("Lang: ").append(lang).append(", ");
        sb.append("GUID: ").append(guid).append(", ");
        sb.append("Profile Url: ").append(profileUrl).append(", ");
        sb.append("Connected: ").append(isConnected).append(", ");
        sb.append("Gender: ").append(gender).append(", ");
        sb.append("BD Restricted: ").append(bdRestricted);
        return sb.toString();
    }
}
