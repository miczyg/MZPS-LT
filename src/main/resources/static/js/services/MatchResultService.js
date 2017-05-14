'use strict';

angular.module('mzpsApp').factory('MatchResultService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
            var factory = {
                loadAllMatchResults: loadAllMatchResults,
                getAllMatchResults: getAllMatchResults
            };

            return factory;

            function loadAllMatchResults() {
                console.log("fetching matches result");
                var deferred = $q.defer();
                $http.get(urls.TOURNEY_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all matches results');
                            $localStorage.matchResults = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading match results');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllMatchResults() {
                return $localStorage.matchResults;
            }
        }
    ]);
