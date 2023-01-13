# [SOOS Core SCA Java Library](https://soos.io/sca-product)

<img src="assets/SOOS_logo.png" style="margin-bottom: 10px;" width="350" alt="SOOS Icon">

SOOS is an independent software security company, located in Winooski, VT USA, building security software for your team. [SOOS, Software security, simplified](https://soos.io).

Use SOOS to scan your software for [vulnerabilities](https://app.soos.io/research/vulnerabilities) and [open source license](https://app.soos.io/research/licenses) issues with [SOOS Core SCA](https://soos.io/sca-product). [Generate SBOMs](https://kb.soos.io/help/generating-a-software-bill-of-materials-sbom). Govern your open source dependencies. Run the [SOOS DAST vulnerability scanner](https://soos.io/dast-product) against your web apps or APIs.

[Demo SOOS](https://app.soos.io/demo) or [Register for a Free Trial](https://app.soos.io/register).

If you maintain an Open Source project, sign up for the Free as in Beer [SOOS Community Edition](https://soos.io/products/community-edition).


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
