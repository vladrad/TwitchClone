package streamer.com.myapplication.http.models.streams;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vladi on 2/12/17.
 */

public class Channel { //Main Channel Object

    private boolean mature;
    private boolean partner;
    private String status;
    @SerializedName("broadcaster_language")
    private String broadcasterLanguage;
    @SerializedName("display_name")
    private String displayName;
    private String game;
    private String language;
    @SerializedName("_id")
    private long id;
    private String name;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    private long delay;
    private String logo;
    private String banner;
    @SerializedName("video_banner")
    private String videoBanner;
    private String background;
    @SerializedName("profile_banner")
    private String profileBanner;
    @SerializedName("profile_banner_background_color")
    private String profileBannerBackground;
    private String url;
    private long views;
    private long followers;

    public boolean isMature() {
        return mature;
    }

    public boolean isPartner() {
        return partner;
    }

    public String getStatus() {
        return status;
    }

    public String getBroadcasterLanguage() {
        return broadcasterLanguage;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getGame() {
        return game;
    }

    public String getLanguage() {
        return language;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public long getDelay() {
        return delay;
    }

    public String getLogo() {
        return logo;
    }

    public String getBanner() {
        return banner;
    }

    public String getVideoBanner() {
        return videoBanner;
    }

    public String getBackground() {
        return background;
    }

    public String getProfileBanner() {
        return profileBanner;
    }

    public String getProfileBannerBackground() {
        return profileBannerBackground;
    }

    public String getUrl() {
        return url;
    }

    public long getViews() {
        return views;
    }

    public long getFollowers() {
        return followers;
    }
}
