package mk.ukim.finki.wp.labb.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.labb.model.Artist;
import mk.ukim.finki.wp.labb.model.Song;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataHolder {
    public static List<Artist> artistList = new ArrayList<Artist>();
    public static List<Song> songList = new ArrayList<Song>();

    @PostConstruct
    public void init(){
        Artist adele = new Artist(1000L, "Adele", "Atkins", "An English singer-songwriter, Adele is famous for her soulful voice and emotional ballads, with hits like Someone Like You and Hello.");
        Artist freddie = new Artist(1001L, "Freddie", "Mercury", "The lead vocalist of Queen, Freddie Mercury is remembered for his incredible vocal range, theatrical stage presence, and timeless songs like Bohemian Rhapsody.");
        Artist elvis = new Artist(1002L, "Elvis", "Presley", "An American singer and actor, Elvis Presley is known as the \"King of Rock and Roll\" and revolutionized music with his iconic voice and stage presence.");
        Artist sade = new Artist(1003L, "Sade", "Adu", "Is a British-Nigerian singer, songwriter, and actress, best known as the lead vocalist of the band Sade. She rose to fame in the 1980s with the release of their debut album Diamond Life, which featured the hit single Smooth Operator. Sade's music blends elements of soul, jazz, R&B, and pop, known for its smooth, sultry sound and her distinctive, emotive voice.");
        Artist ladyGaga = new Artist(1004L, "Lady", "Gaga", "is an American singer, songwriter, and actress, known for her provocative performances, boundary-pushing fashion, and powerful vocals.");
        artistList.add(freddie);
        artistList.add(adele);
        artistList.add(elvis);
        artistList.add(sade);
        artistList.add(ladyGaga);

        songList.add(new Song("track01", "Bohemian Rhapsody","Rock&Roll",1975,Collections.emptyList()));
        songList.add(new Song("track02", "Hello", "Pop", 2010, Collections.emptyList()));
        songList.add(new Song("track03","Suspicious minds", "Rock&Roll",1960,Collections.emptyList()));
        songList.add(new Song("track04", "Smooth Operator","R&B",1988, Collections.emptyList()));
        songList.add(new Song("track05", "Bad Romance","Pop",2009, Collections.emptyList()));
    }
}
