var app = angular.module('mzpsApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/mzpsApp',
    TEAM_SERVICE_API : 'http://localhost:8080/mzpsApp/api/team/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'TeamController',
                controllerAs:'ctrl',
                resolve: {
                    teams: function ($q, TeamService) {
                        console.log('Load all teams');
                        var deferred = $q.defer();
                        TeamService.loadAllTeams().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

