package nl.ioost.recipes.facade;

public class RecipeTagNotFoundException extends RuntimeException {
    public RecipeTagNotFoundException(String message) {
        super(message);
    }
}
