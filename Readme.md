# cv-github-security 
* Functionality: List all the repository present in the clairvoyant organization.

# Build

    maven clean install

# Run

Prerequisite

* Set the auth_token as environment variable.

For example 

    export auth_token = "Bearer actual_token"

Run below command

    java -jar cv-github-security-1.0-SNAPSHOT-jar-with-dependencies.jar

# Result
csv file which contains necessary information related to clairvoyant organization repositories.