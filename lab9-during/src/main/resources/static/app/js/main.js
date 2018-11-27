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


wafepaApp.controller("recordsCtrl", function($scope, $http, $location){
	
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
	
	$scope.searchParams = {};
	$scope.searchParams.activity = "";
	$scope.searchParams.minDuration = "";
	$scope.searchParams.intensity = "";
	
	$scope.pageNum = 0;
	$scope.totalPages = 1
	
	var recordsUrl = "/api/records";
	
	var activitiesUrl = "/api/activities";
	var usersUrl = "/api/users";
	
	var getRecords = function(){
		
		var config = { params: {} };
		
		//Dakle, polja config.params objekta moraju da se zovu kako back-end ocekuje
		if($scope.searchParams.activity != ""){
			config.params.activityName = $scope.searchParams.activity;
		}
		
		if($scope.searchParams.minDuration != ""){
			config.params.minDuration = $scope.searchParams.minDuration;
		}
		
		if($scope.searchParams.intensity != ""){
			config.params.intensity = $scope.searchParams.intensity;
		}		
		
		config.params.pageNum = $scope.pageNum;
		
		$http.get(recordsUrl, config).then(
			function success(res){
				$scope.records = res.data;
				$scope.totalPages = res.headers("totalPages");
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
	
	$scope.goToEdit = function(id){
		$location.path("/records/edit/" + id);
	}
	
	$scope.doSearch = function(){
		//console.log($scope.searchParams);
		$scope.pageNum = 0;
		getRecords();
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum = $scope.pageNum + direction;
		getRecords();
	}
	
});


wafepaApp.controller("editRecordCtrl", function($scope, $http, $routeParams, $location){
	
	var recordUrl = "/api/records/" + $routeParams.id;
	var activitiesUrl = "/api/activities";
	var usersUrl = "/api/users";
	
	$scope.record = {};
	$scope.record.time = "";
	$scope.record.duration = "";
	$scope.record.intensity = "";
	$scope.record.userId = "";
	$scope.record.activityId = "";	
	
	
	$scope.activities = [];
	$scope.users = [];
	
	
	
	var getActivities = function(){
		$http.get(activitiesUrl).then(
			function success(res){
				$scope.activities = res.data;
				getUsers();
			},
			function error(){
				alert("Couldn't fetch activities");
			}
		);
	}
	
	var getUsers = function(){
		return $http.get(usersUrl).then(
			function success(res){
				$scope.users = res.data;
				getRecord();
			},
			function error(){
				alert("Couldn't fetch users.");
			}
		);
	}
	
	var getRecord = function(){
		$http.get(recordUrl).then(
			function success(res){
				$scope.record = res.data;
			},
			function error(){
				alert("Couldn't fetch record.");
			}
		);
	}
	
	getActivities();
	
	// Pogledati promise chaining kako bi se ovo odradilo na kraci nacin
	// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise/then#Chaining
	// https://javascript.info/promise-chaining
	
	$scope.doEdit = function(){
		$http.put(recordUrl, $scope.record).then(
			function success(){
				$location.path("/records");
			},
			function error(){
				alert("Something went wrong.");
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
		.when('/records/edit/:id', {
			templateUrl : '/app/html/edit-record.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);