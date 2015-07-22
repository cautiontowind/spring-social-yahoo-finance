package org.springframework.social.yahoo.profile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Image implements Serializable {
    @JsonProperty("height")
    private String height;
    @JsonProperty("imageUrl")
    private String imageUrl;
    @JsonProperty("width")
    private String width;
    @JsonProperty("size")
    private String size;

    public String getHeight ()
    {
        return height;
    }

    public void setHeight (String height)
    {
        this.height = height;
    }

    public String getImageUrl ()
    {
        return imageUrl;
    }

    public void setImageUrl (String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getWidth ()
    {
        return width;
    }

    public void setWidth (String width)
    {
        this.width = width;
    }

    public String getSize ()
    {
        return size;
    }

    public void setSize (String size)
    {
        this.size = size;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Height: ").append(height).append(", ");
        sb.append("Image Url: ").append(imageUrl).append(", ");
        sb.append("Width: ").append(width).append(", ");
        sb.append("Size: ").append(size);
        return sb.toString();
    }
}
