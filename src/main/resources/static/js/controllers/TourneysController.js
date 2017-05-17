'use strict';

angular.module('mzpsApp').controller('TourneysController',
    ['MatchResultService', '$scope',
        function (MatchResultService, $scope) {
            //MOCKUP
            var ctrl = this;

            ctrl.mock_t1 = {
                id: 1,
                name: "I Turniej ligowy",
                category: "Mlodzik",
                teams: [
                    {name: "Wawel"},
                    {name: "Sparta"},
                    {name: "Dunajec"}
                ]
            };

            ctrl.mock_match = {
                teams: [ {name: "Wawel"},{name: "Dunajec"}],
                teams_results: [
                    {s1: 25, s2: 18, s3: 12},
                    {s1: 23, s2: 25, s3: 15}
                ]
            };



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
