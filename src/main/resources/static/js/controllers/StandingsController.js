angular.module('mzpsApp').controller('StandingsController',
    ['$scope', "$location", '$http', '$q', 'urls', function ($scope, $location, $http, $q, urls) {

        var ctrl = this;
        ctrl.category = "";
        ctrl.teams = {};

        ctrl.selectCategory = function (category) {
            ctrl.category = category;
            ctrl.strictFilter = ctrl.category !== '';
            ctrl.getByCategory();
        };

        ctrl.isSelected = function (category) {
            return (ctrl.category === category);
        };

        ctrl.getCategory = function () {
            return ctrl.category;
        };

        ctrl.isCategoryStrict = function () {
            return ctrl.strictFilter;
        };

        ctrl.getByCategory = function () {
            $http.get(urls.STANDINGS_SERVICE_API, {params: {"category": ctrl.category}})
                .then(
                    function (response) {
                        ctrl.teams = response.data;
                    },
                    function () {
                        console.error('Error while loading teams with category' + ctrl.category);
                    }
                );
        };

        ctrl.getStandings = function(){
            return ctrl.teams;
        };
    }]);