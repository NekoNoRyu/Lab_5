package patternCommand.commands;

import patternCommand.Command;

public class HelpCommand implements Command {
    @Override
    public void execute(String x) {
        System.out.println("help...");
    }
}