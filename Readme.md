

# Safetynet
**APIRest for emergencies services**

### To launch the app, you must launch the class

- SafetynetApplication

or use
- ```shell
  mvn spring-boot:run
  ```

                                                                                                            
**Once the app is launched you can go to the endpoints via the addresses bellow:**

- **GET{firestation}**</br>
 Get a list of people covered by the corresponding fire station.</br>
 The list must include: first name, last name, address, phone number.</br>
 It must provide a count of the number of adults and the number of children </br>
(any individual 18 years of age or younger).
 ```html
- http://localhost:8080/firestation?stationNumber=<station_number>
```
- **GET{fire}**</br>
  Get a list of inhabitants living at the given address as well as the number of the fire station.</br>
  The list should include the name, phone number, age and medical history </br>
  (medications, dosage and
  (medications, dosage and allergies) of each person.
```html
http://localhost:8080/fire?address=<address>
```
- **GET{phoneAlert}**</br>
  Get list of all phone number at station number.
```html
http://localhost:8080/phoneAlert?firestation=<firestation_number>
```
- **GET{personInfo}**</br>
  This url must return the name, address, age, email and medical history </br>
  (medications,dosage, allergies) of each inhabitant. </br> 
  If several people have the same name, they must all appear.
  all of them must appear.
```html
http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
```
- **GET{floodAlert}** </br>
  Get a list of all household at addresses station.</br>
  Give for each family home list, the persons details :</br>
  name, phone, age and medicals records: medication and allergie.
```html
http://localhost:8080/flood/stations?stations=<firestation_number>
```
- **GET{communityEmail}**
  Get list of all emails at city.</br>
```html
http://localhost:8080/communityEmail?city=<city>
```
- **GET{childAlert}**
  Get list of all children at address.</br>
  Each child contains a list with family adults
```html
http://localhost:8080/childAlert?address=<address>
```
### Or you can use swagger: http://localhost:8080/swagger-ui/index.html#/

**To launch the test, you can use the terminal command:**

```shell
mvn verify
```

## Reports
Maven site to get all reports:

- **SureFire Report** for all unit Tests.
- **Jacoco Report** for tests coverage.

Run build site, use command:

```shell
mvn site
```
Access file directory : `target/site`
Run the `index.html` in your web browser.


## Jacoco Coverage
Jacoco coverage is automatically done with tests
but for a more accurate coverage rate (excluding classes not useful </br> 
for the coverage rate), use:
``` shell
mvn jacoco:report
```

Access file directory : `target/site/jacoco/index.html`




***The Json file containing the data is located in the resources folder:***

- src/main/resources/data.json

**The completed project is in the branch :**

- master
