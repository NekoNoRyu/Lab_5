package patternCommand;

import lombok.Builder;

@Builder
public class MenuItem {
    Command command;

    public void clicked(String x) {
        command.execute(x);
    }
}
