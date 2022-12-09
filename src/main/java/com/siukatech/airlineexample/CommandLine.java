package com.siukatech.airlineexample;

import com.github.rvesse.airline.annotations.Cli;
import com.github.rvesse.airline.help.Help;
import com.github.rvesse.airline.parser.ParseResult;
import com.github.rvesse.airline.parser.errors.ParseException;
import com.siukatech.airlineexample.cmd.DatabaseSetupCommand;
import com.siukatech.airlineexample.cmd.LoggingCommand;

import java.util.Arrays;

@Cli(name = "siukatech-cli"
        , description = "Airline Tutorial"
        , defaultCommand = Help.class
        , commands = {LoggingCommand.class
        , Help.class
        , DatabaseSetupCommand.class}
)
public class CommandLine {
    public static void main(String[] args) {
        com.github.rvesse.airline.Cli<Runnable> cli = new com.github.rvesse.airline.Cli<>(CommandLine.class);
        try {
            //Runnable cmd = cli.parse(args);
            //cmd.run();

            // https://rvesse.github.io/airline/guide/practise/exceptions.html
            // Parse with a result to allow us to inspect the results of parsing
            ParseResult<Runnable> result = cli.parseWithResult(args);
            if (result.wasSuccessful()) {
                // Parsed successfully, so just run the command and exit
                //System.exit(result.getCommand().run());
                System.exit(0);
            } else {
                // Parsing failed
                // Display errors and then the help information
                System.err.println(String.format("%d errors encountered:", result.getErrors().size()));
                int i = 1;
                for (ParseException e : result.getErrors()) {
                    System.err.println(String.format("Error %d: %s", i, e.getMessage()));
                    i++;
                }

                System.err.println();

                com.github.rvesse.airline.help.Help.<Runnable>help(cli.getMetadata(), Arrays.asList(args), System.err);
            }
        } catch (Exception e) {
            // ParseOptionIllegalValueException
            // Errors should be being collected so if anything is thrown it is unexpected
            System.err.println(String.format("Unexpected error: %s", e.getMessage()));
            //e.printStackTrace(System.err);
        }

        // If we got here we are exiting abnormally
        System.exit(1);
    }
}
