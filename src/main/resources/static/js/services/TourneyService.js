'use strict';

angular.module('mzpsApp').factory('TourneyService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
            var factory = {
                loadAllTourneys: loadAllTourneys,
                getTourney: getTourney,
                getAllTourneys: getAllTourneys,
                createTourney: createTourney,
                updateTourney: updateTourney,
                removeTourney: removeTourney
            };

            return factory;

            function loadAllTourneys() {
                console.log("fetching tourneys");
                var deferred = $q.defer();
                $http.get(urls.ADMIN_TOURNEY_SERVICE_API)
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

            function getTourney(id) {
                console.log('Fetching Tourney with id :' + id);
                var deferred = $q.defer();
                $http.get(urls.ADMIN_TOURNEY_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Tourney with id :' + id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading Tourney with id :' + id);
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
                $http.post(urls.ADMIN_TOURNEY_SERVICE_API, tourney)
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
                $http.put(urls.ADMIN_TOURNEY_SERVICE_API + id, tourney)
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

            function removeTourney(id) {
                console.log('Removing Tourney with id ' + id);
                var deferred = $q.defer();
                $http.delete(urls.ADMIN_TOURNEY_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllTourneys();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Tourney with id :' + id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
        }
    ]);