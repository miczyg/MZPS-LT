'use strict';

angular.module('mzpsApp').factory('LeagueService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
            var factory = {
                loadAllLeagues: loadAllLeagues,
                getAllLeagues: getAllLeagues,
                createLeague: createLeague,
                updateLeague: updateLeague
            };

            return factory;

            function loadAllLeagues() {
                console.log("fetching leagues");
                var deferred = $q.defer();
                $http.get(urls.ADMIN_LEAGUE_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all leagues');
                            $localStorage.leagues = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading leagues');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllLeagues() {
                return $localStorage.leagues;
            }

            function createLeague(league) {
                console.log('Creating League');
                var deferred = $q.defer();
                $http.post(urls.ADMIN_LEAGUE_SERVICE_API, league)
                    .then(
                        function (response) {
                            loadAllLeagues();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while creating League : ' + errResponse.data.errorMessage);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateLeague(league, id) {
                console.log('Updating League with id ' + id);
                var deferred = $q.defer();
                $http.put(urls.ADMIN_LEAGUE_SERVICE_API + id, league)
                    .then(
                        function (response) {
                            loadAllLeagues();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating League with id :' + id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
        }
    ]);
