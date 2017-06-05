'use strict';

angular.module('mzpsApp').controller('TourneysController',
    ['MatchResultService', '$scope', '$http', 'urls',
        function (MatchResultService, $scope, $http, urls) {
            var ctrl = this;
            ctrl.tourneys = [];
            ctrl.category = "";
            ctrl.leagues = [];
            ctrl.currentLeague = {};
            ctrl.matches = [];
            ctrl.currenMatch = {};

            //TODO: rework for diffrent controller
            ctrl.selectCategory = function (category) {
                ctrl.category = category;
                ctrl.strictFilter = ctrl.category !== '';
                ctrl.matches.length = 0;
                ctrl.getTourneysForCategory();
                ctrl.getLeaguesForCategory();
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
            //TODO: refactor until here============

            ctrl.getTourneysForCategory = function(){
                $http.get(urls.TOURNEY_SERVICE_API, {params: {"category": ctrl.category}})
                    .then(
                        function (response) {
                            ctrl.tourneys = response.data;
                        },
                        function () {
                            console.error('Error while loading tourneys with category' + ctrl.category);
                        }
                    );
            };

            ctrl.getLeaguesForCategory = function(){
                $http.get(urls.TOURNEY_SERVICE_API + "/leagues", {params: {"category": ctrl.category}})
                    .then(
                        function (response) {
                            ctrl.leagues = response.data;
                            console.log(ctrl.leagues);
                        },
                        function () {
                            console.error('Error while loading leagues with category' + ctrl.category);
                        }
                    );
            };

            ctrl.selectLeague = function (league) {
                ctrl.currentLeague = league;
                $http.get(urls.TOURNEY_SERVICE_API + "/matches", {params: {"leagueId": league.id}})
                    .then(
                        function (response) {
                            ctrl.matches = response.data;
                        },
                        function () {
                            console.error('Error while loading leagues with category' + ctrl.category);
                        }
                    );
            };

            ctrl.setActiveMatch = function (match) {
              ctrl.activeMatch = match;
            };

            //Passing data to modal
            $('#matchModal').on('show.bs.modal', function(e) {
                console.log(e.relatedTarget);
                var $modal = $(this),
                    team1 = $(e.relatedTarget).attr("data-team1"),
                    team2 = $(e.relatedTarget).attr("data-team2");
                console.log(team1);
                console.log(team1);
                $modal.find('.team1').html(team1);
                $modal.find('.team2').html(team2);
            })

        }
    ]);

app.filter('filterForTourney', function() {
    return function( items, tId) {
        var filtered = [];

        if(tId === undefined || tId === ''){
            return items;
        }

        angular.forEach(items, function(item) {
            if(tId === item.tourney.id ||  item.tourney.id === ''){
                filtered.push(item);
            }
        });

        return filtered;
    };
});
