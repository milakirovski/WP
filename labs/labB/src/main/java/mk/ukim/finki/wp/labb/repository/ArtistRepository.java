package mk.ukim.finki.wp.labb.repository;

import mk.ukim.finki.wp.labb.bootstrap.DataHolder;
import mk.ukim.finki.wp.labb.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ArtistRepository {

    public List<Artist> findAll(){
        return DataHolder.artistList;
    }

    public Optional<Artist> findById(Long id){
        return DataHolder.artistList.stream().filter(artist -> Objects.equals(artist.getId(), id)).findFirst();
    }
}
