package patternCommand;

import patternCommand.commands.*;

import java.util.Scanner;

public class Application {
    static private Menu menu = new Menu();
    static private boolean isRun = true;

    static public void requestMenu(String command) throws InvalidCommandException {
        String[] splittedCommand = command.split(" ", 2);
        try {
            if (splittedCommand.length == 1) {
                menu.clickMenuItem(command, "");
            } else {
                menu.clickMenuItem(splittedCommand[0], splittedCommand[1]);
            }
        } catch (NullPointerException|IndexOutOfBoundsException e) {
            throw new InvalidCommandException(command + ": not a command");
        }
    }

    static public void run() {
        try {
            requestMenu("dev:load");
        } catch (InvalidCommandException ignored) {
            ignored.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        while (isRun) {
            System.out.println("Enter command");
            String command = scanner.nextLine();
            System.out.println("Execute command " + command);
            try {
                Application.requestMenu(command);
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static public void stop() {
        isRun = false;
    }


    static public void fillMenu() {
        menu.add("help", MenuItem.builder().command(new HelpCommand()).build());
        menu.add("dev:load", MenuItem.builder().command(new FileToCollectionCommand()).build());
        menu.add("insert", MenuItem.builder().command(new InteractiveInsertCommand()).build());
        menu.add("dev:insert", MenuItem.builder().command(new InsertCommand()).build());
        menu.add("info", MenuItem.builder().command(new InfoCommand()).build());
        menu.add("exit", MenuItem.builder().command(new ExitCommand()).build());
        menu.add("save", MenuItem.builder().command(new CollectionToFileCommand()).build());
        menu.add("execute_script", MenuItem.builder().command(new ExecuteScriptCommand()).build());
        menu.add("show", MenuItem.builder().command(new ShowCommand()).build());
        menu.add("update", MenuItem.builder().command(new InteractiveUpdateCommand()).build());
        menu.add("dev:update", MenuItem.builder().command(new UpdateCommand()).build());
        menu.add("remove_key", MenuItem.builder().command(new RemoveKeyCommand()).build());
        menu.add("clear", MenuItem.builder().command(new ClearCommand()).build());
        menu.add("remove_lower", MenuItem.builder().command(new InteractiveRemoveLowerCommand()).build());
        menu.add("dev:remove_lower", MenuItem.builder().command(new RemoveLowerCommand()).build());
        menu.add("replace_if_lower", MenuItem.builder().command(new InteractiveReplaceIfLowerCommand()).build());
        menu.add("dev:replace_if_lower", MenuItem.builder().command(new ReplaceIfLowerCommand()).build());
        menu.add("remove_greater_key", MenuItem.builder().command(new RemoveGreaterKeyCommand()).build());
        menu.add("remove_any_by_form_of_education", MenuItem.builder().command(new RemoveAnyByFOECommand()).build());
        menu.add("count_by_form_of_education", MenuItem.builder().command(new CountByFOECommand()).build());
        menu.add("filter_greater_than_students_count", MenuItem.builder().command(new FilterGreaterThanSCCommand()).build());
    }
}
