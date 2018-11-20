var app = angular.module('first_contact', []);

//Parent controller za second controller
app.controller("firstController", function($scope){	
	
		$scope.name = "Marija";
		$scope.surname = "Maric";
					  
});

//Child controller za first controller
app.controller("secondController", function($scope){	
	
		$scope.name = "Marko";
});