'use strict';

angular.module('mzpsApp').controller('TourneysController',
    ['TourneysService', '$scope',
        function (TourneysService, $scope) {

            this.matchResult = {};
            this.matchResults = [];

            this.getAllMatchResults = getAllMatchResults;

            function getAllMatchResults() {
                return TourneysService.getAllMatchResults();
            }
        }
    ]);
