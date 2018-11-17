## Lab 5 - Veze objekata, paginacija, validacija

## ORM - Veze između objekata

Želimo da implementiramo mogućnost čuvanja više adresa za jednog korisnika. U našoj implementaciji, adrese jednog korisnika će biti nezavisne od adresa ostalih korisnika, bez obzira na to da li su u pitanju iste geografske adrese. Najpre ćemo ostvariti vezu klasa `User` i `Address` na nivou objektnog modela. 

* Klasi User dodajemo polje koje sadrži listu adresa tog korisnika:

```java 
private List<Address> addresses = new ArrayList<>();
```

* Klasi `Address` dodajemo polje koje govori kog korisnika je to adresa:

```java 
private User user;
```

* Generisati getere i setere

Neophodno je obezbediti da ukoliko se jedan kraj veze promeni, ta promena bude ažurirana i u entitetu na drugom kraju veze. Drugim rečima, održavanje veze je naš zadatak i ne obavlja se automatski. 

* U klasi `User` dodati metodu `addAddress()`

```java 
	public void addAddress(Address address){
		this.addresses.add(address);
		if(address.getUser()!=this){
			address.setUser(this);
		}
	}
```

* U klasi `Address` modifikovati metodu `setUser()`

```java 
	public void setUser(User user) {
		this.user = user;
		if(!user.getAddresses().contains(this)){
			user.getAddresses().add(this);
		}
	}
```

Sada možemo dodati anotacije koje nalažu JPA da mapira vezu između objekata.

* Na polje `addresses` klase `User` dodati anotaciju:
```java 
@OneToMany(mappedBy="user")
```
* Na polje `user` klase `Address` dodati anotaciju:
```java 
@ManyToOne(fetch=FetchType.EAGER)
```
* u application.properties ddl-auto podešavanje prebaciti na create
* Pokrenuti aplikaciju i ispratiti promene nastale na šemi baze

### REST mapiranje kompozicije

Adrese korisnika ćemo mapirati na pod-resurs resursa User, tj. na URL `/api/users/{userid}/addresses`

* Izmeniti `RequestMapping` klase `AddressController`
* Dodati u `AddressRepository` i `AddressService` metodu `findByUser(User user)`
* Izmeniti `RequestMapping` i tela njenih metoda, tako da se učitavaju adrese samo za određenog korisnika

----

## Paginacija

Aplikacije neretko sadrže više hiljada, pa i miliona instanci nekog entiteta. Neophodno je podržati da se u jednom zahtevu vrati samo deo tih instanci. Ovo se naziva paginacija.

* izmeniti `TestData` klasu tako da instancira 100 korisnika
* UserRepository sada treba da nasledi `PagingAndSortingRepository<T, ID>` umesto `JpaRepository<T, ID>`
* kontroler i servis korisnika je neophodno izmeniti tako da prima parametar `?page=` koji govori koju po redu stranicu želimo, kao i da vraća `Page<User>`

## Validacija

* dodati `@NotBlank` i `@Size(max=30)` anotacije na firstName polje `UserRegistrationDTO`
* dodati anotaciju `@Validated` na metodu koja obrađuje POST u UserController klasi
* dodati metodu u kontroler koja će obrađivati DataIntegrityViolationException


## Domaći zadatak

1. Ponoviti JavaScript i jQuery
