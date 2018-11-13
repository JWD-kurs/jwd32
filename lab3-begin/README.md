## Lab 3 - DTO, komponente, parametri

----

### DTO (Data Transfer Object)

Data Transfer Object je objekat koji se koristi za enkapsulaciju podataka, 
i za njihovo slanje/prijem od strane jednog podsistema aplikacije ka drugom podsistemu.

DTO se najčešće koriste u višeslojnoj aplikaciji za transfer podataka između same aplikacije i UI sloja. Takođe se veoma dobro uklapaju u MVC šablon.

DTO su neophodni uvek kada se format podataka koji razmenjujemo sa spoljnim
aplikacijama razlikuje od formata entiteta koji interno koristimo u modelu.

### Komponente

Uvođenjem novih DTO klasa javlja se potreba za konverzijom iz DTO u interne entitete i obrnuto. Konverzija se može centralizovati u pomoćne klase koje implementiraju intertfejs `Converter` (`org.springframework.core.convert.converter.Converter`). Ove klase će biti markirane anotacijom `@Component`, kako bi Dependency Injection mehanizmom mogle biti lako povezivane sa drugim delovima aplikacije. 

* kreirati paket `jwd.wafepa.support`
* kreirati u njemu klase ActivityDTOToActivity i ActivityToActivityDTO
* implementirati convert metode ovih klasa
* anotirati klase kao `@Component`

----

* Napraviti paket `jwd.wafepa.web.dto`

* Napraviti klasu `ActivityDTO` u paketu `jwd.wafepa.web.dto`. Polja klase `ActivityDTO` treba da budu ista kao polja klase `Activity` (`Long id`, `String name`).

* Izmeniti `ApiActivityController` da ne koristi `Activity` za prenos podataka između UI sloja i aplikacije, već `ActivityDTO`. Koristiti konvertere.

* Testirati napravljeni REST web servis


----

### Parametri REST metoda


Po pravilu, svi dodatni parametri koje je neophodno poslati REST servisu, šalju se preko HTTP request parametara. Npr., za zahtev:

```
HTTP GET http://www.restservice.com/api/examples?param1=value1&param2=value2
```

request parametri su `param1` i `param2` i imaju vrednosti `value1` i `value2`. Naravno, parametara može biti više i ne moraju se zvati (čak se i ne savetuje) param1 i param2,
već imenom odražavaju svoju funkciju. Vodeći se ovim, pretraživanje aktivnosti po imenu (na WAFEPA aplikaciji) će biti implementirano slanjem request parametra **name**, 
npr. za pronalaženje aktivnosti čije ime sadrži reč "run":

```
HTTP GET http://localhost:8080/api/activities?name=run
```

(obratite pažnju da je osnovni poziv na REST isti, samo smo dodali request parametre na kraju)

**Pogrešne implementacije pretraživanja aktivnosti po imenu (preko REST servisa)**:

* `HTTP GET http://localhost:8080/api/activities/run`
* `HTTP GET http://localhost:8080/api/activities/name/run`
* `HTTP POST http://localhost:8080/api/activities/find`
* `HTTP GET http://localhost:8080/api/find_activities?name=run`
* `HTTP GET http://localhost:8080/api/find_by_name/run`
* itd...

Spring omogućava proširenje REST servisa ovakvim request parametrima. Sve što je potrebno uraditi je:

* Metodu kontrolera proširiti parametrom i anotirati ga sa `@RequestParam`.

* Dakle, u našem slučaju metodu getActivities (kontroler `ApiActivitiesController`) proširiti parametrom `String name` i anotirati ga sa `@RequestParam`.

Pored prenošenja parametara kroz URL, dodatni parametri mogu se prosleđivati i kroz zaglavlje zahteva. Tada se mogu preuzimati korišćenjem anotacije `@RequestHeader`.

----

### Maven standalone

* http://docs.spring.io/spring-boot/docs/1.3.3.RELEASE/maven-plugin/

* Zatvoriti Eclipse
* Otvoriti terminal u folderu gde se nalazi pom.xml projekta
* Pokrenuti `mvn spring-boot:run` i otići na stranicu projekta iz browser-a
* Zaustaviti projekat (Ctrl-C)
* Pokrenuti `mvn package` i pogledati sadržaj target foldera
* U pom.xml izmeniti `<packaging>war</packaging>` u `<packaging>jar</packaging>` i pokrenuti ponovo `mvn package`
* Iz target foldera pokrenuti jar
* Izmeniti testove tako da neki od njih ne prođe
* Pokušati ponovo sa `mvn package`

---

### Domaći zadatak

1. Napraviti UserDTO koji ne sadrži polje password i UserRegistrationDTO, koji sadrži polje password dva puta
1. Napraviti konvertore za nove DTO klase
1. Izmeniti ApiUserController tako da se koriste DTO klase 
1. Implementirati REST zahtev za pretragu korisnika po korisničkom imenu
