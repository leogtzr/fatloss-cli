# fatloss-cli

fatloss-cli is a little simple to show what are your maintenance calories.
It will print maintenance calories using the _Harris-Benedict_ and the 
_Mifflin St. Jeor_ equations.

## Build
```bash
mvn package
```

## Run
```
java -jar target/fatloss-0.0.1-SNAPSHOT.jar <options>
```

### Options
```
-a, --age=<age>         Age
-g, --gender=<gender>   Male/Female
-h, --height=<height>   Height in kg.
-t, --activity
-w, --weight=<weight>   Weight in kg.
```

