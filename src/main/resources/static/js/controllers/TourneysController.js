'use strict';

angular.module('mzpsApp').controller('TourneysController',
    ['MatchResultService', '$scope', '$http', 'urls',
        function (MatchResultService, $scope, $http, urls) {
            var ctrl = this;
            ctrl.tourneys = [];
            ctrl.category = "";
            ctrl.leagues = [];

            //TODO: rework for diffrent controller
            ctrl.selectCategory = function (category) {
                ctrl.category = category;
                ctrl.strictFilter = ctrl.category !== '';
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

            //DATA MOCKUP


            ctrl.mock_t = {
                id: 1,
                name: "I turniej ligowy"
            };
            ctrl.t = [ctrl.mock_t];

            ctrl.mock_league = {
                id: 1,
                name: "I Liga",
                category: "Mlodzik",
                tourney: 1,
                teams: [
                    {name: "Wawel"},
                    {name: "Sparta"},
                    {name: "Dunajec"}
                ]
            };
            ctrl.leagues = [ctrl.mock_league];

            ctrl.mock_match = {
                id: 1,
                tourney: 1,
                teams: [ {name: "Wawel"},{name: "Dunajec"}],
                team_results: [5,6]
            };
            ctrl.matches = [ctrl.mock_match];

            ctrl.teams_results =  [
                {id: 1, s1: 0, s2: 0, s3: 0},
                {id: 2, s1: 0, s2: 0, s3: 0},
                {id: 3, s1: 0, s2: 0, s3: 0},
                {id: 4, s1: 0, s2: 0, s3: 0},
                {id: 5, s1: 25, s2: 18, s3: 12},
                {id: 6, s1: 23, s2: 25, s3: 15}
            ];
        //    MOCK END

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
