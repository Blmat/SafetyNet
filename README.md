Safetynet

To launch the app, you must launch the class

SafetynetApplication

Once the app is launched you can go you the endpoints via the addresses bellow:

http://localhost:8080/firestation?stationNumber= "ENTER station Number"

http://localhost:8080/fire?address= "ENTER address address"

http://localhost:8080/phoneAlert?firestation= "ENTER number station"

http://localhost:8080/personInfo?firstName="ENTER firstname"&lastName="ENTER lastname"

http://localhost:8080/flood/stations?stations="ENTER station number"

http://localhost:8080/communityEmail?city="ENTER city"

http://localhost:8080/childAlert?address=" ENTER address"

Or you can use swagger: http://localhost:8080/swagger-ui/index.html#/

To launch the test, you can use the terminal command:

mvn test

To generate the target folder, you can use the terminal command:

 "mvn verify"
 or 
 "mvn site" (for surefire report)
 or even 
 "mvn jacoco:report" (for a more accurate report with jacoco)

The Json file containing the data is located in the resources folder:

src/main/resources/data.json

The completed project is in the branch :

master
