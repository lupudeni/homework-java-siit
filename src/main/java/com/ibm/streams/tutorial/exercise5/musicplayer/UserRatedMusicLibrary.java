package com.ibm.streams.tutorial.exercise5.musicplayer;

import com.ibm.streams.tutorial.exercise5.thirdpartyplugin.UserRatedLocalFilesystemMusicLibrary;
import lombok.var;

public interface UserRatedMusicLibrary extends MusicLibrary {

    StarRating userRatingOf(Song song);

    static class StarRatingConverter {
        public Rating convert(StarRating starRating) {
            return new Rating(starRating.numberOfStars * 20);
        }
    }

    @Override
    default Rating ratingOf(Song song) {
        StarRatingConverter starRatingConverter = new StarRatingConverter();
        var userRatedLocalFilesystemMusicLibrary = new UserRatedLocalFilesystemMusicLibrary();
        StarRating starRating = userRatedLocalFilesystemMusicLibrary.userRatingOf(song);
        return starRatingConverter.convert(starRating);
    }
}
