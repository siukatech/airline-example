package com.siukatech.airlineexample;

import com.github.rvesse.airline.annotations.Cli;
import com.github.rvesse.airline.help.Help;
import com.siukatech.airlineexample.cmd.LoggingCommand;

@Cli(name = "siukatech-cli"
        , description = "Airline Tutorial"
        , defaultCommand = Help.class
        , commands = {LoggingCommand.class, Help.class}
)
public class CommandLine {
    public static void main(String[] args) {
        com.github.rvesse.airline.Cli<Runnable> cli = new com.github.rvesse.airline.Cli<>(CommandLine.class);
        Runnable cmd = cli.parse(args);
        cmd.run();
    }
}
