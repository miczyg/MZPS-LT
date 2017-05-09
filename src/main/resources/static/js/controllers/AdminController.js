'use strict';

angular.module('mzpsApp').controller('AdminController',
    ['AdminService', '$scope',
        function (AdminService, $scope) {

            this.tourneys = {};

            this.getAllTourneys = getAllTourneys;

            this.successMessage = '';
            this.errorMessage = '';

            function getAllTourneys() {
                return AdminService.getAllTourneys();
            }
        }
    ]);
