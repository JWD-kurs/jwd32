## Lab 9 - Pretraživanje, paginacija, custom servisi

### Pretraživanje

Po pravilu, svi dodatni parametri koje je neophodno poslati REST servisu, šalju se preko HTTP request parametara. Npr., za zahtev:

```
HTTP GET http://www.restservice.com/api/examples?param1=value1&param2=value2
```

request parametri su param1 i param2 i imaju vrednosti value1 i value2. Naravno, parametra može biti više i ne moraju se zvati (čak se i ne savetuje) param1 i param2,
već nešto smisleno. Vodeći se ovim, pretraživanje aktivnosti po imenu (na WAFEPA aplikaciji) će biti implementirano slanjem request parametra **name**, 
npr. za pronalaženje aktivnosti čije ime sadrži reč "run":

```
HTTP GET http://localhost:8080/api/activities?name=run
```

(obratite pažnju da je osnovni poziv na REST isti, samo smo dodali request parametre na kraju)

**Pogrešne implementacije pretraživanja aktivnosti po imenu (preko REST servisa)**:

* HTTP GET http://localhost:8080/api/activities/run
* HTTP GET http://localhost:8080/api/activities/name/run
* HTTP POST http://localhost:8080/api/activities/find
* HTTP GET http://localhost:8080/api/find_activities?name=run
* HTTP GET http://localhost:8080/api/find_by_name/run
* itd...


AngularJS omogućava slanje request parametra kroz $http servis, tako što se u drugom argumentu poziva funkcije $http servisa 
postave željeni request parametri u posebno polje **params** (mora se tako zvati po AngularJS dokumentaciji). Primer:

```javascript
$http.get('http://www.restservice.com/api/examples', { params: {'param1': 'value1', 'param2': 'value2'}})
		.success(...)
		.error(...)
```

što će prouzrokovati request:

```
HTTP GET http://www.restservice.com/api/examples?param1=value1&param2=value2
```

U našem slučaju, ako hoćemo da nađemo sve aktivnosti koje u imenu sadrže **run**:

```javascript
$http.get('api/activities', { params: {'name': 'run'}})
		.success(...)
		.error(...)
```

---

### Paginacija

* Na stranici za prikaz svih aktivnosti napraviti dva dugmeta, Previous i Next, staviti ih ispod tabele, poravnati po desnoj strani.
* Omogućiti slanje parametra **page** na 'api/activities', default vrednost je 0.
* Onemogućiti klik na dugme Previous ako smo na prvoj strani.
* Onemogućiti klik na dugme Next ako smo na poslednjoj strani.


---


### Pravljenje custom servisa

U AngularJS, servis predstavlja objekat koji obavlja specifičan zadatak (npr. $log za logovanje, $http za komunikaciju preko HTTP protokola),
a povezuje se sa ostatkom aplikacije pomoćuje injekcije (dependency injection). Servisi enkapsuliraju kod koji se koristi u direktivama i kontrolerima.

AngularJS nudi ima veliki broj servisa, ali često bude potrebno praviti novi, svoj, custom servis. 
Pravljenjem custom servisa izbegava se dupliciranje koda i smanjuje se složenost programa.

Primer pravljenja custom servisa:

```javascript
app.service('myService', function() {
	
	this.myServiceFunction = function() {
		return 'some text';
	};
});
```

Primer injektovanja i pozivanja custom servisa:

```javascript
app.controller('MyControler', function($scope, myService) {
	
	$scope.myControllerFunction = function() {
		$scope.text = myService.myServiceFunction();
	};
});
```

Obratiti pažnju da se servis OBAVEZNO injektuje u kontroler u kojem se koristi kao parametar funkcije (function($scope, myService) { ... }).
Takođe, custom servisi nemaju $ na početku imena, za razliku od AngularJS ugrađenih servisa.


* Definisati custom servis "activityService" koji će enkapsulirati čitavu funkcionalnost vezanu za dobavljanje, brisanje i čuvanje aktivnosti preko REST servera.

----

### Domaći

* Omogućiti pretraživanje korisnika po imenu ILI prezimenu (dakle ukoliko je upit pretrage "pet"), treba pronaći korisnika koje se zove npr. "Petar",
ali i korisnika koji se preziva npr. "Petrović".
* Dodati novu stranicu "Filmovi", na kojoj je moguće pretraživati filmove po imenu i godini koristeći themoviedb.org API https://www.themoviedb.org/documentation/api .