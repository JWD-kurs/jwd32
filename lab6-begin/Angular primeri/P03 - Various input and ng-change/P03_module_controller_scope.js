var app = angular.module("example", []);

app.controller("ExampleController", function($scope){	
	
	
	$scope.model = {
		checkboxValue: true,
		radioValue: "green",
		numberValue: 5
	}

	$scope.printType = function(){
		console.log("The type of number input value is:");
		console.log(typeof $scope.model.numberValue);
	}

});
