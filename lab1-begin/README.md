## Lab 1 - Uvod u Maven i Spring Framework

### Spring framework
----------------


![Spring runtime](https://cloud.githubusercontent.com/assets/5716823/15799911/0f481b4a-2a6d-11e6-8628-c06eb8cb3764.png) 

Classical web - server side generated HTML:

![Server side generated HTML](https://cloud.githubusercontent.com/assets/5716823/15799916/0f5ce692-2a6d-11e6-8a07-7365753bc98d.png)


Data centric approach - server side generated data:

![Data Centric](https://cloud.githubusercontent.com/assets/5716823/15799914/0f5c1e1a-2a6d-11e6-8ee4-b0bf7e543599.png)


### Maven
-------------------------


*	[Build tool](http://maven.apache.org/users/index.html) - alat za konstrukciju Java programa

*	[Convention over configuration](http://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)

*	[POM](http://maven.apache.org/pom.html) - `pom.xml` je konfiguracioni fajl u kom je opisano sve ono što Maven treba da zna o određenom Java projektu

*	[Dependency management](http://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html) - automatsko upravljanje dependency-ima

*	[Build life-cycle](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html) - životni ciklus izgradnje programskog paketa

*	[IDE integration (m2e)](https://www.eclipse.org/m2e/) - integracija sa Eclipse radnim okruženjem


**Standard Directory Layout** - Maven koristi specifičnu strukturu direktorijuma unutar projekta i ova struktura MORA biti ispoštovana.

![Standard Directory Layout](http://core0.staticworld.net/images/idge/imported/article/jvw/2005/12/jw-1205-maven1-100156413-orig.gif)

----

### High Level Architecture
-----------------------


![HLA](https://cloud.githubusercontent.com/assets/5716823/15799912/0f59b56c-2a6d-11e6-936e-b3fc3d7a45ec.png)

----

### Hello world! web aplikacija


* Otvoriti Eclipse radno okruženje

* Napraviti novi Maven projekat i odabrati arhetip (archetype) projekta da bude `maven-archetype-webapp` (File -> New -> Maven project)

* Konfigurisati Maven (u `pom.xml`) da se koristi Java 7:

```xml
<build>
	<finalName>lab1</finalName>

	<plugins>
		<plugin>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-compiler-plugin</artifactId>
			<version>3.1</version>
			<configuration>
				<source>1.7</source>
				<target>1.7</target>
			</configuration>
		</plugin>
	</plugins>
</build>
```

* Uraditi update projekta (desni klik na projekat -> Maven -> Update project)


----

### Dodavanje modela

* Napraviti folder src/main/java

* Napraviti paket `jwd.wafepa.model`

* Napraviti klasu `Activity` unutar paketa za modele (polja klase: `Long id`, `String name`)

* Generisati getere i setere klase `Activity` pomoću Eclipse-a

----

### Dodavanje servisnog sloja

Servisni sloj se koristi za manipulaciju podacima. Te manipulacije najčešće podrazumevaju CRUD operacije.
U Spring Framework-u postoji *de facto* standard, odnosno konvencija strukture paketa, kao i davanju imena paketima i klasama za određene slojeve.
Za servisni sloj se pravi paket `service`. Ovaj paket će sadržati interfejse sa metodama koje će predstavljati željene operacije nad podacima.
Zatim se pravi paket `service.impl` koji sadrži klase sa implementacijama interfejsa servisa. Imena klasa u servisnom sloju uglavnom imaju sufiks "Service".

* Napraviti `ActivityService` interfejs, sa metodama za CRUD operacije: `findOne(Long id)`, `findAll()`, `save(Activity activity)`, `remove(Long id)`

* Napraviti `InMemoryActivityService` klasu, koja će biti in-memory implementacija `ActivityService` interfejsa

----

### Automatsko testiranje servisnog sloja

Automatsko testiranje je važan deo u razvojnom ciklusu softvera i predstavlja pisanje testova koji će testirati funkcionalnost određenih modula aplikacije.
Za automatsko jedinično testiranje u Javi koristi se JUnit.

* Dodati **JUnit** dependency u `pom.xml` (ukoliko već postoji, zameniti da verzija bude 4.11)

```xml
<dependency>
	<groupId>junit</groupId>
	<artifactId>junit</artifactId>
	<version>4.11</version>
</dependency>
```


* Napraviti klasu `InMemoryActivityServiceTest` unutar foldera `src/test/java`

* Dodati metode void `setUp()` i void `tearDown()` i anotirati ih sa `@Before` i `@After`, respektivno. Ove metode će služiti za inicijalizaciju podataka nad kojima će se testirati metode.
Metode anotirane sa `@Before` će se izvršiti pre svakog zasebnog testa, a metode anotirane sa `@After` nakon svakog zasebnog testa.
Ovim se osiguravamo da znamo stanje sistema i podataka pre i nakon svakog testa. Ukoliko bismo nešto želeli da izvršimo pre svih ili nakon svih testova, koristili bismo anotacije `@BeforeClass` i `@AfterClass`.

* Napisati testove korišćenjem tvrdnji (eng. assert), npr:


```java
@Test
public void testFindOne() {
	Activity activity = activityService.findOne(1L);
	Assert.assertNotNull(activity);
	Assert.assertEquals("Running", activity.getName());
}
```

* Anotirati testove sa @Test i pokrenuti testove (desni klik na klasu -> Run as -> JUnit test)

----

### Domaći zadatak

1. Dopuniti servisni sloj (ActivityService) metodama:
	- Activity findByName(String name) - treba da pronađe Activity objekat sa zadatim imenom
	- List<Activity> save(List<Activity> activities) - treba da snimi sve zadate Activity objekte
	- void remove(List<Long> ids) - treba da izbriše sve Activity objekte sa zadatim ID-evima
2. Napisati testove za nove metode servisnog sloja
