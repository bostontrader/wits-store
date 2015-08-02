package com.fyrecloud.wits;

//import java.io.IOException;
//import java.net.ServerSocket;

//import org.apache.commons.cli.BasicParser;
//import org.apache.commons.cli.CommandLine;
//import org.apache.commons.cli.CommandLineParser;
//import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
//import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
//import org.apache.commons.cli.ParseException;
//import org.apache.commons.cli.UnrecognizedOptionException;

/**
 * 
 * @author Thomas Radloff bostontrader@gmail.com http://bostontrader.github.io/wits-store
 *
 * The purpose of this class is to communicate with WITS agents 
 * and maintain the WITS db.
 * 
 * More specifically: It will listen on TCP for incoming connections from WITS agents.
 * Upon connection, it will update the WITS db as necessary, given the incoming
 * message, and return a suitable reply, perhaps after consulting the db.
 *
 * General Usage: 
 * 
 * Executing this class w/o any command line arguments, or with --help as an argument, will 
 * produce a help screen.  It's not feasible to expect to be able to execute this class 
 * solely on default values because this class will at least need to know how to 
 * access the db, and this needs command line args.
 * 
 */

public class WITStore {

	/**
	 * An object to describe the available command line options.
	 */
	private static Options clOptions = null;

	/**
	 * An object to model the command line, after parsing.
	 */
	//private static CommandLine cmd = null;

	/**
	 * What port is the WITS store default port?
	 */
	//public static int WITSCMADefaultPort = 4444;

	/**
	 * What port is the WITS store listening on?
	 */
	//public static int listeningPort = WITSCMADefaultPort;


	public static void main(String[] args) {

		// 1. Build the model of command line options
		buildCLIOptions();

		// 2. If no command line arguments then print the help screen and exit.
		//    It's not feasible to expect to be able to execute this class solely on default
		//    values because this class will at least need to know how to access the
		//    database.
		//if (args.length < 1)
			//printHelp();

		// 3. Now parse the command line
		//try {
			//CommandLineParser parser = new BasicParser();
			//cmd = parser.parse( CLIOptions, args);
		//} catch(UnrecognizedOptionException e) {
			// 3.1. If an option is not recognized then print the
			// help screen and exit.
			//printHelp();
		//} catch(ParseException e) {
			//e.printStackTrace();
			//System.exit(-1);
		//}

		// 4. Determine which port this instance of WITStore should listen on
		//if (cmd.hasOption("port"))
			//listeningPort = Integer.parseInt(cmd.getOptionValue("port"));

		//boolean listening = true;
		//try {
			// 5. Establish the listening socket
			//ServerSocket serverSocket = null;
			//serverSocket = new ServerSocket(listeningPort);

			// 6. Anytime a request comes in, spawn a new
			// thread and deal with the associated request.
			//while (listening)
				//new ServerThread(serverSocket.accept()).start();
			//serverSocket.close();
		//} catch (IOException e) {
			//System.err.println("Could not listen on port: " + Integer.toString(listeningPort));
			//System.exit(-1);
		//}
	}

	/**
	 * The purpose of this method is to build a model of the accepted CLI Options
	 */
	private static void buildCLIOptions() {
		clOptions = new Options();
		clOptions.addOption(new Option("help", "Print this help message."));

		//OptionBuilder.withArgName( "port" );
		//OptionBuilder.hasArg();
		//OptionBuilder.withDescription("The port that WITSCMA will listen to.  Default = "+ Integer.toString(WITSCMADefaultPort));
		//CLIOptions.addOption(OptionBuilder.create("port"));
	}

	/**
	 * This method will print the help screen.
	 */
	//private static void printHelp() {
		//HelpFormatter formatter = new HelpFormatter();
		//formatter.printHelp( "WITStore", CLIOptions );
		//System.exit(0);
	//}

}
