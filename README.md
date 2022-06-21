# glue-files

### How to run
#### Prerequisites:
- [JRE 11](https://www.oracle.com/cis/java/technologies/javase/jdk11-archive-downloads.html)

#### Steps:
1. Open terminal and `cd` to the repository root
2. `./mvnw clean package`
3. `java -jar target/glue-files-jar-with-dependencies.jar <sourceFolderPath> <resultFolderPath>`
    - \<_sourceFolderPath_\> example: `path-to-repo-root\temp` (absolute path)
    - \<_resultFolderPath_\> example: `path-to-repo-root\out` (absolute path)
    - Both folders must exist before you run the application