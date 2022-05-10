package patternCommand.commands;

import collectionClasses.CustomHashMap;
import patternCommand.Command;
import patternCommand.Document;

public class InfoCommand implements Command {
    @Override
    public void execute(String x) {
        CustomHashMap customHashMap = Document.getCustomHashMap();
        System.out.println(customHashMap.getClass());
        System.out.println("Initialisation time: " + customHashMap.getInitTime());
        System.out.println("Number of elements: " + customHashMap.size());
    }
}
