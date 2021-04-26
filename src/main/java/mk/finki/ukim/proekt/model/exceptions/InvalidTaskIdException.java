package mk.finki.ukim.proekt.model.exceptions;

public class InvalidTaskIdException extends RuntimeException{
    public InvalidTaskIdException(Long id){
        super(String.format("Task with id: %d, could not be found",id));
    }
}
