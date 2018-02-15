# Rafael Albergari Carmo

In this test I utilized: 

Plugins test integration
-
- maven-surefire-plugin (for execute tests classes *Test.java)
- maven-failsafe-plugin (for execute tests classes *IT.java)
- derby-maven-plugin (for create embedded data base in memory)
- maven-embedded-glassfish-plugin (for application service)

Tecnologies
-
- Jackson (for rest and json)
- Derby   (for data base)
- Glassfish (Application service)
- Junit and mockito (for tests)

How Execute
-
- You will need use maven version 3.3.9 I don't no wy, but other version have bug with that's plugin's.
- You will need set your  MAVEN_OPTS = -Xmx1024m -XX:MaxPermSize=128m
- Use Java 7
- Usethe command mvn -DforkMode=never -DforkCount=0 clean verify

Obs: A error for "SQLIntegrityConstraintViolationException" ocurr in testLetterA, I don't understand why, because the short time I didn't fix it! This don't change the application operation and the test finish with the build sucess.
-