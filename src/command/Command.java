package command;

/**
 * Defines a design pattern known as the command design pattern.
 * This interface is used to represent actions or commands that can be executed and undone in an application.
 */
public interface Command {

    void execute(); //Is responsible for carrying out the action or command that is represented. When this method is called, the associated action is performed.
    void undo(); //Is in charge of undoing the action performed by execute().
}