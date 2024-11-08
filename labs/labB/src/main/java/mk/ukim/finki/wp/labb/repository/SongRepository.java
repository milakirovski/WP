package mk.ukim.finki.wp.labb.repository;

import mk.ukim.finki.wp.labb.bootstrap.DataHolder;
import mk.ukim.finki.wp.labb.model.Artist;
import mk.ukim.finki.wp.labb.model.Song;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class SongRepository {

    public List<Song> findAll(){
        return DataHolder.songList;
    }

    public Song findByTrackId(String trackId){
        return findAll().stream().filter(song -> song.getTrackId().equals(trackId)).findFirst().orElse(null);
    }

    public Artist addArtistToSong(Artist artist, Song song){
        // Get the list of performers for the song
        List<Artist> artists = new ArrayList<>(song.getPerformers());

        // Add the new artist to the list
        artists.add(artist);

        // Update the song's performer list
        findByTrackId(song.getTrackId()).setPerformers(artists);

        return artist;
    }

}
