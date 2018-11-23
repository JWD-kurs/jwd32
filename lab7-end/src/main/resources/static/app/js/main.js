var wafepaApp = angular.module("wafepaApp", ['ngRoute']);

wafepaApp.controller("homeCtrl", function($scope){
	$scope.message = "Hello JWD 32!";
});


wafepaApp.controller("activitiesCtrl", function($scope, $http, $location){
	
	$scope.activities = [];
	
	var baseUrl = "/api/activities";
	
	var getActivities = function(){
		
		var promise = $http.get(baseUrl);
		
		promise.then(
			function success(res){
				$scope.activities = res.data;
			},
			function error(){
				alert("Unsuccessful fetch!")
			}
		);
		
		//console.log("Poruka");
	}
	
	getActivities();
	
	$scope.goToEdit = function(aid){
		$location.path("/activities/edit/" + aid);
	}
	
	$scope.goToAdd = function(){
		$location.path("/activities/add");
	}
	
});

wafepaApp.controller("editActivityCtrl", function($scope, $routeParams, $http, $location){
	
	//console.log($routeParams);
	
	$scope.activity = {};
	$scope.activity.name = "";
	
	var baseUrl = "/api/activities/" + $routeParams.aid;
	
	var getActivity = function(){
		
		var promise = $http.get(baseUrl);
		promise.then(
			function uspeh(odg){
				$scope.activity = odg.data;
			},
			function neuspeh(){
				console.log("Something went wrong!");
			}
		);
		
	}
	
	getActivity();
	
	$scope.doEdit = function(){
		
		$http.put(baseUrl, $scope.activity).then(
			function success(res){
				$location.path("/activities");
			},
			function error(){
				alert("Something went wrong!");
			}
		);
		
	}
	
});

wafepaApp.controller("addActivityCtrl", function($scope, $http, $location){
	
	var baseUrl = "/api/activities";
	
	$scope.activity = {};
	$scope.activity.name = "";
	
	$scope.doAdd = function(){
		$http.post(baseUrl, $scope.activity).then(
			function success(){
				$location.path("/activities");
			},
			function error(){
				alert("Couldn't add activity!");
			}
		)
	}
	
});


wafepaApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html',
			controller: 'homeCtrl'
		})
		.when('/activities', {
			templateUrl : '/app/html/activities.html'
		})
		.when('/activities/add', {
			templateUrl : '/app/html/add-activity.html'
		})
		.when('/activities/edit/:aid', {
			templateUrl : '/app/html/edit-activity.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);