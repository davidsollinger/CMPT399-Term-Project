package termproject;

public class NullPlayer extends Player {
    
    @Override
    public String getName() {
        return "";
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
