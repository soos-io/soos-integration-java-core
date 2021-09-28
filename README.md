# SOOS Integration Java Core

## Development
### Requirements
- Maven
- JDK 11

### Build
```bash
mvn clean compile assembly:single
```

**Note**: Copy the file `soos-ci-analysis-java` to the `soos` folder inside your project

### Run Locally
```bash
java -Dm=run_and_wait -Dof=fail_the_build -Ddte=soos -Dfte= -Dwd=./ -Darmw=300 -Darpi=10 -Dburi=https://dev-api.soos.io/api/ -Dscp=./ -Dpn="Java Test" soos/soos-ci-analysis-java.jar
```
