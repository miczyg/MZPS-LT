'use strict';

angular.module('mzpsApp').controller('TourneysController',
    ['MatchResultService', '$scope',
        function (MatchResultService, $scope) {

            this.matchResult = {};
            this.matchResults = [];

            this.getAllMatchResults = getAllMatchResults;

            function getAllMatchResults() {
                return MatchResultService.getAllMatchResults();
            }
        }
    ]);
