var app = angular.module("first_contact", []);

app.controller("firstController", function($scope){	
	
	
	$scope.newName = "";
	$scope.newSurname = "";
	
	$scope.people = [];
	
	var person1 = { name: "Marija", surname: "Maric"};
	$scope.people.push(person1);
	
	var person2 = { name: "Marko", surname: "Markovic"};
	$scope.people.push(person2);
	
	var person3 = { name: "Nikola", surname: "Nikolic"};
	$scope.people.push(person3);
	
	$scope.addPerson = function(){
		
		var personNew = {};
		personNew.name = $scope.newName;
		personNew.surname = $scope.newSurname;
		
		$scope.people.push(personNew);

		$scope.newName = "";
		$scope.newSurname = "";
	};

});