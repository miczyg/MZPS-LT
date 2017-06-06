'use strict';

angular.module('mzpsApp').controller('TourneysController',
    ['MatchResultService', '$scope', '$http', 'urls', '$q',
        function (MatchResultService, $scope, $http, urls, $q) {
            var ctrl = this;
            ctrl.tourneys = [];
            ctrl.category = "";
            ctrl.leagues = [];
            ctrl.currentLeague = {};
            ctrl.matches = [];
            ctrl.currenMatch = {};
            ctrl.leagueResults = [];
            ctrl.overallResults = {};

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

            ctrl.getTourneysForCategory = function () {
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

            ctrl.getLeaguesForCategory = function () {
                $http.get(urls.TOURNEY_SERVICE_API + "/leagues", {params: {"category": ctrl.category}})
                    .then(
                        function (response) {
                            ctrl.leagues = response.data;
                        },
                        function () {
                            console.error('Error while loading leagues with category' + ctrl.category);
                        }
                    );
            };

            ctrl.loadLeague = function (id) {
                var deferred = $q.defer();
                $http.get(urls.TOURNEY_SERVICE_API + "/league", {params: {"leagueId": id}})
                    .then(
                        function (response) {
                            ctrl.currentLeague = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading matches for league' + id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            };

            ctrl.loadMatches = function (leagueId) {
                var deferred = $q.defer();
                $http.get(urls.TOURNEY_SERVICE_API + "/matches", {params: {"leagueId": leagueId}})
                    .then(
                        function (response) {
                            ctrl.matches = response.data;
                            deferred.resolve(response);

                        },
                        function (errResponse) {
                            console.error('Error while loading matches for id' + leagueId);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            };

            ctrl.selectLeague = function (league) {
                ctrl.loadLeague(league.id).then(function () {
                    ctrl.loadMatches(league.id)
                        .then(function () {
                            ctrl.overallResults = ctrl.calculateResults(ctrl.getTeams(), ctrl.getMatches());
                        });
                });

                // ctrl.overallResults = ctrl.calculateResults(ctrl.getTeams(), ctrl.getMatches());

            };

            ctrl.setActiveMatch = function (match) {
                ctrl.activeMatch = match;
            };

            ctrl.submitMatchResult = function (match) {
                console.log('Updating Match with id ' + match.id);
                var deferred = $q.defer();
                $http.put(urls.TOURNEY_SERVICE_API + "match/" + match.id, match)
                    .then(
                        function (response) {
                            ctrl.loadMatches(ctrl.currentLeague.id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Team with id :' + match.id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise
                    .then(function () {
                        ctrl.overallResults = ctrl.calculateResults(ctrl.getTeams(), ctrl.getMatches());
                    });

            };

            ctrl.getStandings = function () {
                return ctrl.overallResults;
            };

            ctrl.getMatches = function () {
                return ctrl.matches;
            };

            ctrl.getLeagues = function () {
                return ctrl.leagues;
            };

            ctrl.getTeams = function () {
                return ctrl.currentLeague.teams;
            };

            ctrl.getTourneys = function () {
                return ctrl.tourneys;
            };

            ctrl.reformatPoints = function (match) {
                var team1res = [], team2res = [];
                angular.forEach(match.teamResults[0], function (value, key) {
                    team1res.push(value);
                });

                angular.forEach(match.teamResults[1], function (value, key) {
                    team2res.push(value);
                });

                return [team1res, team2res]
            };

            ctrl.calculateSets = function (pointResults) {
                var team1res = pointResults[0], team2res = pointResults[1];
                var team1Sets = 0, team2Sets = 0;
                for (var i = 0; i < team1res.length; i++) {
                    if (team1res[i] > team2res[i]) team1Sets += 1;
                    else if (team1res[i] < team2res[i]) team2Sets += 1;
                }
                return [team1Sets, team2Sets];
            };

            ctrl.displayResult = function (match) {
                var formattedPoints = ctrl.reformatPoints(match);
                var sets = ctrl.calculateSets(formattedPoints);
                return sets[0] + " : " + sets[1];
            };

            ctrl.calculateOverallPoints = function (selfResults, opponentResults) {
                var won = selfResults.reduce(add, 0);
                var lost = opponentResults.reduce(add, 0);
                return [won, lost];
            };

            ctrl.calculateResults = function (teams, matches) {
                var tourneyResults = {};

                //prepare results

                angular.forEach(teams, function (team) {
                    tourneyResults[team.id] = {
                        name: team.name,
                        pointsWon: 0,
                        pointsLost: 0,
                        setsWon: 0,
                        setsLost: 0,
                        tPoints: 0
                    };
                });

                //fill results
                angular.forEach(matches, function (match) {
                    var formattedPoints = ctrl.reformatPoints(match);
                    var points = ctrl.calculateOverallPoints(formattedPoints[0], formattedPoints[1]);
                    var sets = ctrl.calculateSets(formattedPoints);
                    tourneyResults[match.matchTeams[0].id].pointsWon += points[0];
                    tourneyResults[match.matchTeams[0].id].pointsLost += points[1];
                    tourneyResults[match.matchTeams[0].id].setsWon += sets[0];
                    tourneyResults[match.matchTeams[0].id].setsLost += sets[1];
                    var res = sets[0] - sets[1];
                    tourneyResults[match.matchTeams[0].id].tPoints +=
                        res === 2 ? 3 : res === 1 ? 2 : res === -1 ? 1 : 0;

                    tourneyResults[match.matchTeams[1].id].pointsWon += points[1];
                    tourneyResults[match.matchTeams[1].id].pointsLost += points[0];
                    tourneyResults[match.matchTeams[1].id].setsWon += sets[1];
                    tourneyResults[match.matchTeams[1].id].setsLost += sets[0];
                    res = sets[1] - sets[0];
                    tourneyResults[match.matchTeams[1].id].tPoints +=
                        res === 2 ? 3 : res === 1 ? 2 : res === -1 ? 1 : 0;

                });
                // console.log(tourneyResults);

                return tourneyResults;
            };

        }
    ]);


//some helpers
app.filter('filterForTourney', function () {
    return function (items, tId) {
        var filtered = [];

        if (tId === undefined || tId === '') {
            return items;
        }

        angular.forEach(items, function (item) {
            if (tId === item.tourney.id || item.tourney.id === '') {
                filtered.push(item);
            }
        });

        return filtered;
    };
});

function add(a, b) {
    return a + b;
}
