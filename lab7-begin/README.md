## Lab 7 - AngularJS $http servis, REST

----

### Promise koncept

Promise koncept (koncept obećanja) je koncept kojim se osigurava eventualno izvršavanje potrebnog koda nakon asinhrone operacije.
Takva operacija se može završiti uspešno ili neuspešno, i promise koncept  nam nudi mogućnost da na različite ishode različito reagujemo.

Primer asinhrone operacije jeste komunikacija sa serverom. 

### $http servis

$http servis služi za komunikaciju sa HTTP serverom, u ovom slučaju REST serverom. Podržana je većina HTTP metoda, ali najčešće su get, put, post, delete.
Primeri korišćenja:

```javascript
$http.get('route') 									// poziva se GET route (route je putanja resursa ili kolekcije na REST servisu)
		.then(function successCallback(data) {			// ako je status 2xx (npr. 200 OK), u data se nalaze trazeni podaci
			// do something
		},function errorCallback(data) { 			// ako se dogodila greska, tj. ako status nije 2xx
			// handle error
		});
		
		
$http.post('route', { attrName : 'attrValue'})		// route je putanja kolekcije, drugi parametar je objekat koji se dodaje u kolekciju
		.then(...)
		
$http.put('route', { attrName : 'attrValue'})		// route je putanja resursa, drugi parametar je objekat predstavlja izmenjen resurs
		.then(...)
		
$http.delete('route')								// route je putanja resursa koji se briše
		.then(...)
		
```

Za više informacija o $http servisu pogledati AngularJS dokumentaciju [https://docs.angularjs.org/api/ng/service/$http](https://docs.angularjs.org/api/ng/service/$http)

---

* Implementirati prikaz aktivnosti na stranici /activities. Aktivnosti prikazati u tabeli.

* Implementirati brisanje jedne aktivnosti klikom na dugme Delete.

* Implementirati dodavanje nove aktivnosti na stranici /activities/add. Nakon uspešnog dodavanja aktivnosti,
korisnika redirektovati na /activities stranicu.

* Implementirati izmenu postojeće aktivnosti na stranici /activities/edit/:id. Nakon uspešne izmene aktivnosti,
korisnika redirektovati na /activities stranicu.

----

### $location servis

$location servis služi za programsko čitanje i menjanje URL adrese u address baru browsera.
Primer korišćenja:

```javascript
$location.path('/route'); 		// redirekcija korisnika na "/route" stranicu
```

Za više informacija o $location servisu pogledati AngularJS dokumentaciju [https://docs.angularjs.org/api/ng/service/$location](https://docs.angularjs.org/api/ng/service/$location)

----

### $routeParams servis

$routeParams servis služi za čitanje parametara rute. Parametri rute se definišu sa dvotačkom i imenom parametra (npr. :id).
Ukoliko neka ruta sadrži parametre rute, (npr. konkretno za stranicu za izmenu aktivnosti sa id=5 -> /#/activities/edit/5),
vrednost parametra rute se može dobiti kroz $routeParams objekat, koji je zapravo mapa (key-value), tako što se 
traži vrednost po ključu imena parametra (npr. za prethodni primer -> vrednost $routeParams.id će biti 5).

Za više informacija o $routeParams servisu pogledati AngularJS dokumentaciju [https://docs.angularjs.org/api/ngRoute/service/$routeParams](https://docs.angularjs.org/api/ngRoute/service/$routeParams)

----

### Domaći zadaci

* Uraditi za korisnike sve što je urađeno na času za aktivnosti
* Na stranici za dodavanje aktivnosti, onemogućiti klik na dugme "Save" ukoliko je polje za ime aktivnosti prazno.
* Na stranici za prikaz svih aktivnosti, dodati dugme "Reverse". Kada se klikne ovo dugme, obrnuti redosled prikazanih aktivnosti.
* Na stranici za prikaz svih aktivnosti, u koloni "Actions" dodati dugme "View" za svaku aktivnost. Kada se klikne ovo dugme,
otvara se stranica koja je vizuelno ista kao "add" i "edit" stranice, ali je onemogućeno menjanje imena aktivnost i ne postoji dugme "Save".
* Dodati novu stranicu "Countries" u kojoj će se prikazivati spisak zemalja - iskoristiti REST Countries API [http://restcountries.eu/](http://restcountries.eu/)
