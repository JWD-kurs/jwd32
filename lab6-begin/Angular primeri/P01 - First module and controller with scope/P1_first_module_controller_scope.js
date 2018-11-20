
 // metoda koja pravi novi modul. Prvi parametar -  naziv modula, drugi parametar su dependencies / zavisnosti, koje ce injektor ubaciti pre loadovanja samog modula.
angular.module("first_contact", []);
//moglo je i ovako -> var app = angular.module("first_contact", []); jer poziv ove metode vraća napravljeni modul

//angular.module("first_contact");   getter metoda - dovlaci modul pod datim imenom.
//da smo uveli promenljivu app, tu bi stajalo -> app.controller("firstController....
angular.module("first_contact").controller("firstController", function($scope){	
	
	$scope.name = "Marko";
});