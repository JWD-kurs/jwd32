var app = angular.module("first_contact", []);
app.controller("firstController", function($scope){	
	
	
	$scope.people = [];
	
	var person1 = { name: "Marija", surname: "Maric", earnings: "123"};
	$scope.people.push(person1);
	
	var person2 = { name: "Marko", surname: "Markovic", earnings: "123"};
	$scope.people.push(person2);
	
	var person3 = { name: "Nikola", surname: "Nikolic", earnings: "123"};
	$scope.people.push(person3);
	
	$scope.boss = true;
	
});