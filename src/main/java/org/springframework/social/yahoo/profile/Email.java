package org.springframework.social.yahoo.profile;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Email implements Serializable {
    @JsonProperty("id")
    private String id;
    @JsonProperty("handle")
    private String handle;
    @JsonProperty("primary")
    private String primary;
    @JsonProperty("type")
    private String type;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getHandle ()
    {
        return handle;
    }

    public void setHandle (String handle)
    {
        this.handle = handle;
    }

    public String getPrimary ()
    {
        return primary;
    }

    public void setPrimary (String primary)
    {
        this.primary = primary;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(id).append(", ");
        sb.append("Handle: ").append(handle).append(", ");
        sb.append("Primary: ").append(primary).append(", ");
        sb.append("Type: ").append(type);
        return sb.toString();
    }
}
