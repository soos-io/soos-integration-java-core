# SOOS SCA: Java
## OSS Security for Everyone
The **SOOS SCA Java Library** is the most flexible way to run **SOOS** against your codebase to gain insights into your open source package risk. Run **locally** or on a **CI/CD
server**, using either synchronous or asynchronous mode.

## Supported Languages and Package Managers

*	[Node (NPM)](https://www.npmjs.com/)
*	[Python (pypi)](https://pypi.org/)
*	[.NET (NuGet)](https://www.nuget.org/)
*	[Ruby (Ruby Gems)](https://rubygems.org/)
*	[Java (Maven)](https://maven.apache.org/)

Our full list of supported manifest formats can be found [here](https://kb.soos.io/help/soos-languages-supported).

## Need an Account?
**Visit [soos.io](https://app.soos.io/register) to create your trial account.**

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
java -jar -Dm=run_and_wait -Dof=fail_the_build -Ddte=soos -Dfte= -Dwd=./ -Darmw=300 -Darpi=10 -Dburi=https://api.soos.io/api/ -Dscp=./ -Dpn="Java Test" soos/soos-sca.jar
```
