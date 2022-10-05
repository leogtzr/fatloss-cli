package com.fatloss;

import com.fatloss.commands.FatLossCommand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;
import picocli.CommandLine.IFactory;

@SpringBootApplication
public class FatlossApplication implements CommandLineRunner, ExitCodeGenerator {

	// auto-configured to inject PicocliSpringFactory
	private final IFactory factory;
	private final FatLossCommand myCommand;
	private int exitCode;

	public FatlossApplication(final IFactory factory, final FatLossCommand myCommand) {
		this.factory = factory;
		this.myCommand = myCommand;
	}


	public static void main(final String[] args) {
		SpringApplication.run(FatlossApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {
		this.exitCode = new CommandLine(this.myCommand, this.factory).execute(args);
	}

	@Override
	public int getExitCode() {
		return exitCode;
	}

}
