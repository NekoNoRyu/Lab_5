package patternCommand.commands;

import collectionClasses.CustomHashMap;
import patternCommand.Command;
import patternCommand.Document;

public class ClearCommand implements Command {
    @Override
    public void execute(String x) {
        Document.setCustomHashMap(new CustomHashMap<>());
    }
}