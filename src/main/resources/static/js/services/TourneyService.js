'use strict';

angular.module('mzpsApp').factory('TourneyService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
            var factory = {
                loadAllTourneys: loadAllTourneys,
                getAllTourneys: getAllTourneys,
                createTourney: createTourney,
                updateTourney: updateTourney
            };

            return factory;

            function loadAllTourneys() {
                console.log("fetching tourneys");
                var deferred = $q.defer();
                $http.get(urls.ADMIN_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all tourneys');
                            $localStorage.tourneys = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading tourneys');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllTourneys() {
                return $localStorage.tourneys;
            }

            function createTourney(tourney) {
                console.log('Creating Tourney');
                var deferred = $q.defer();
                $http.post(urls.ADMIN_SERVICE_API, tourney)
                    .then(
                        function (response) {
                            loadAllTourneys();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while creating Tourney : ' + errResponse.data.errorMessage);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateTourney(tourney, id) {
                console.log('Updating Tourney with id ' + id);
                var deferred = $q.defer();
                $http.put(urls.ADMIN_SERVICE_API + id, tourney)
                    .then(
                        function (response) {
                            loadAllTourneys();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Tourney with id :' + id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
        }
    ]);