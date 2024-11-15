package mk.ukim.finki.wp.lab.model.exceptions;

public class LocationNotFoundException extends Exception{
    public LocationNotFoundException(Long id) {
        super(String.format("Location %s not found", id));
    }
}
