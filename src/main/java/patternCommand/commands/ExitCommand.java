package patternCommand.commands;

import patternCommand.Application;
import patternCommand.Command;

public class ExitCommand implements Command {
    @Override
    public void execute(String x) {
        Application.stop();
    }
}
