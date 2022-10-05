package com.fatloss.commands;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;
// import java.util.logging.Logger;

// import org.apache.logging.log4j.LogManager;
import com.fatloss.exception.GenderParsingException;
import com.fatloss.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Option;

@Component
@CommandLine.Command(name = "MyRefactorCLI", mixinStandardHelpOptions = true, version = "myrefactor-cli-1.0", description = "CLI Project")
public class FatLossCommand implements Callable<Integer> {

    private static Logger logger = LogManager.getLogger(FatLossCommand.class);

    private static Pattern GENDER_PATTERN = Pattern.compile("^(m|male|f|female)$", Pattern.CASE_INSENSITIVE);

    @Option(names = {"-t", "--activity"})
    private boolean activity;

    @ArgGroup(exclusive = false)
    private BMROptions bmrOptions;

    private static class BMROptions {
        @Option(names = {"-a", "--age"}, description = "Age", required = true)
        private int age;

        @Option(names = {"-g", "--gender"}, description = "Male/Female", required = true)
        private String gender;

        @Option(names = {"-w", "--weight"}, description = "Weight in kg.", required = true)
        private double weight;

        @Option(names = {"-h", "--height"}, description = "Height in kg.", required = true)
        private double height;
    }

    private void validateGender() {
        if (this.bmrOptions != null && !GENDER_PATTERN.matcher(this.bmrOptions.gender).matches()) {
            throw new GenderParsingException(String.format("issues parsing %s gender", this.bmrOptions.gender));
        }
    }

    private static Gender toGender(final String gender) {
        return switch(gender.toUpperCase()) {
            case "F", "FEMALE" -> Gender.FEMALE;
            case "M", "MALE" -> Gender.MALE;
            default -> throw new GenderParsingException(String.format("issues parsing %s gender", gender));
        };
    }

    private static final String ACTIVITY_FACTOR_DESCRIPTION = """
            
            Sedentary (little or no exercise):\s
            	calories = BMR × 1.2;
                        
            Lightly active (light exercise/sports 1-3 days/week):\s
            	calories = BMR × 1.375;
                        
            Moderately active (moderate exercise/sports 3-5 days/week):\s
            	calories = BMR × 1.55;
                        
            Very active (hard exercise/sports 6-7 days a week):\s
            	calories = BMR × 1.725
                        
            Extra active (very hard exercise/sports & a physical job):\s
            	calories = BMR × 1.9.
            	
            """;

    private void printCaloriesSummary(
            final Equation equation
            , final ActivityFactor activityFactor
            , final String equationType
            , final EnergyOptions energyOptions
    ) {
        final double calories = new MaintenanceCalories(energyOptions, activityFactor).calories(equation);
        final double percent10 = calories * 0.1;
        final double percent15 = calories * 0.15;
        final double percent20 = calories * 0.2;

        System.out.printf("%22s %20s = %.1f calories\t(10%%=%.1f, 15%%=%.1f, 20%%=%.1f)\n",
                equationType,
                activityFactor,
                calories,
                calories - percent10,
                calories - percent15,
                calories - percent20
        );
    }

    @Override
    public Integer call() {
        if (this.activity) {
            logger.info(ACTIVITY_FACTOR_DESCRIPTION);

            return 0;
        }

        this.validateGender();

        if (this.bmrOptions == null) {
            return 0;
        }

        final EnergyOptions energyOptions = new EnergyOptions();
        energyOptions.setAge(this.bmrOptions.age);
        energyOptions.setGender(toGender(this.bmrOptions.gender));
        energyOptions.setHeight(this.bmrOptions.height);
        energyOptions.setWeight(this.bmrOptions.weight);

        final HarrisBenedictEquation harrisBenedictEquation = new HarrisBenedictEquation(energyOptions);
        final MifflinStJeorEquation mifflinStJeorEquation = new MifflinStJeorEquation(energyOptions);

        Arrays.stream(ActivityFactor.values()).forEach(activityFactor -> {
            printCaloriesSummary(harrisBenedictEquation, activityFactor, "Harris-Benedict", energyOptions);
            //System.out.println();
            printCaloriesSummary(mifflinStJeorEquation, activityFactor, "Mifflin St. Jeor", energyOptions);
            System.out.println();
        });

        return 0;
    }

}