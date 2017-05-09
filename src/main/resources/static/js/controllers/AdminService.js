'use strict';

angular.module('mzpsApp').factory('AdminService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
            var factory = {
                loadAllTourneys: loadAllTourneys,
                getAllTourneys: getAllTourneys
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
        }
    ]);