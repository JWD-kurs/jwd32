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
	
	$scope.doDelete = function(id){
		var promise = $http.delete(baseUrl + "/" + id);
		promise.then(
			function success(){
				getActivities();
			},
			function error(){
				alert("Something went wrong.");
			}
		);
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


wafepaApp.controller("recordsCtrl", function($scope, $http){
	
	$scope.records = [];
	
	$scope.activities = [];
	$scope.users = [];
	
	$scope.newRecord = {};
	$scope.newRecord.time = "";
	$scope.newRecord.duration = "";
	$scope.newRecord.intensity = "";
	$scope.newRecord.description = "";
	
	$scope.newRecord.userId = "";
	$scope.newRecord.activityId = "";
	
	
	var recordsUrl = "/api/records";
	
	var activitiesUrl = "/api/activities";
	var usersUrl = "/api/users";
	
	var getRecords = function(){
		$http.get(recordsUrl).then(
			function success(res){
				$scope.records = res.data;
			},
			function error(){
				alert("Something went wrong.");
			}
		);
	}
	
	getRecords();
	
	
	var getActivities = function(){
		$http.get(activitiesUrl).then(
			function success(res){
				$scope.activities = res.data;
			},
			function error(){
				alert("Something went wrong.");
			}
		);
	}
	
	var getUsers = function(){
		$http.get(usersUrl).then(
			function success(res){
				$scope.users = res.data;
			},
			function error(){
				alert("Something went wrong.");
			}
		);
	}
	
	getActivities();
	getUsers();
	
	$scope.doAdd = function(){
		//console.log($scope.newRecord);
		
		$http.post(recordsUrl, $scope.newRecord).then(
			function success(){
				getRecords();
				
				$scope.newRecord = {};
				$scope.newRecord.time = "";
				$scope.newRecord.duration = "";
				$scope.newRecord.intensity = "";
				$scope.newRecord.description = "";
				
				$scope.newRecord.userId = "";
				$scope.newRecord.activityId = "";
			},
			function error(){
				alert("Could not save the record!");
			}
		);
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
		.when('/records', {
			templateUrl : '/app/html/records.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);