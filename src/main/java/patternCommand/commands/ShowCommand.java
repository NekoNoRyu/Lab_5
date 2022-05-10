package patternCommand.commands;

import collectionClasses.StudyGroup;
import patternCommand.Command;
import patternCommand.Document;

import java.util.Map;

public class ShowCommand implements Command {
    @Override
    public void execute(String x) {
        for (Map.Entry<Long, StudyGroup> entry : Document.getCustomHashMap().entrySet()) {
            System.out.println(entry);
        }
    }
}
