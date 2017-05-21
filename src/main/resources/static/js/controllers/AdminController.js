'use strict';

angular.module('mzpsApp').controller('AdminController',
    ['TourneyService', 'LeagueService', '$scope',
        function (TourneyService, LeagueService, $scope) {

            var ctrl = this;

            this.tourney = {};
            this.tourneys = [];

            this.leaguePoints = [{place: 1}, {place: 2}, {place: 3}, {place: 4}];
            this.league = {leaguePoints: this.leaguePoints};
            this.leagues = [];


            this.submitTourney = submitTourney;
            this.submitLeague = submitLeague;
            this.createTourney = createTourney;
            this.createLeague = createLeague;
            this.updateTourney = updateTourney;
            this.updateLeague = updateLeague;
            this.removeTourney = removeTourney;
            this.editTourney = editTourney;
            this.getAllTourneys = getAllTourneys;
            this.getAllLeagues = getAllLeagues;
            this.resetTourneyForm = resetTourneyForm;
            this.resetLeagueForm = resetLeagueForm;
            this.addNewLeaguePointsChoice = addNewLeaguePointsChoice;
            this.removeLeaguePointsChoice = removeLeaguePointsChoice;

            this.tourneySuccessMessage = '';
            this.tourneyErrorMessage = '';
            this.leagueSuccessMessage = '';
            this.leagueErrorMessage = '';

            function submitTourney() {
                console.log('Submitting');
                if (this.tourney.id === undefined || this.tourney.id === null) {
                    console.log('Saving New tourney', this.tourney);
                    createTourney(this.tourney);
                } else {
                    updateTourney(this.tourney, this.tourney.id);
                    console.log('tourney updated with id ', this.tourney.id);
                }
            }

            function submitLeague() {
                console.log('Submitting');
                if (this.league.id === undefined || this.league.id === null) {
                    console.log('Saving New league', this.league);
                    createLeague(this.league);
                } else {
                    updateLeague(this.league, this.league.id);
                    console.log('league updated with id ', this.league.id);
                }
            }

            function createTourney(tourney) {
                console.log('About to create Tourney');
                TourneyService.createTourney(tourney)
                    .then(
                        function (response) {
                            console.log('Tourney created successfully');
                            ctrl.tourneySuccessMessage = 'Tourney created successfully';
                            ctrl.tourneyErrorMessage='';
                            ctrl.done = true;
                            ctrl.Tourney={};
                            $scope.tourneyForm.$setPristine();
                        },
                        function (errResponse) {
                            console.error('Error while creating Tourney');
                            ctrl.tourneyErrorMessage = 'Error while creating Tourney: ' + errResponse.data.errorMessage;
                            ctrl.successMessage='';
                        }
                    );
            }

            function createLeague(league) {
                console.log('About to create League');
                LeagueService.createLeague(league)
                    .then(
                        function (response) {
                            console.log('League created successfully');
                            ctrl.leagueSuccessMessage = 'League created successfully';
                            ctrl.leagueErrorMessage='';
                            ctrl.done = true;
                            ctrl.League={};
                            $scope.leagueForm.$setPristine();
                        },
                        function (errResponse) {
                            console.error('Error while creating League');
                            ctrl.leagueErrorMessage = 'Error while creating League: ' + errResponse.data.errorMessage;
                            ctrl.leagueSuccessMessage='';
                        }
                    );
            }


            function updateTourney(Tourney, id){
                console.log('About to update Tourney');
                TourneyService.updateTourney(Tourney, id)
                    .then(
                        function (response){
                            console.log('Tourney updated successfully');
                            ctrl.tourneySuccessMessage='Tourney updated successfully';
                            ctrl.tourneyErrorMessage='';
                            ctrl.done = true;
                            $scope.tourneyForm.$setPristine();
                        },
                        function(errResponse){
                            console.error('Error while updating Tourney');
                            ctrl.tourneyErrorMessage='Error while updating Tourney '+errResponse.data;
                            ctrl.successMessage='';
                        }
                    );
            }

            function updateLeague(League, id){
                console.log('About to update League');
                LeagueService.updateLeague(League, id)
                    .then(
                        function (response){
                            console.log('League updated successfully');
                            ctrl.leagueSuccessMessage='League updated successfully';
                            ctrl.leagueErrorMessage='';
                            ctrl.done = true;
                            $scope.leagueForm.$setPristine();
                        },
                        function(errResponse){
                            console.error('Error while updating League');
                            ctrl.leagueErrorMessage='Error while updating League '+errResponse.data;
                            ctrl.leagueSuccessMessage='';
                        }
                    );
            }

            function removeTourney(id){
                console.log('About to remove Tourney with id '+id);
                TourneyService.removeTourney(id)
                    .then(
                        function(){
                            console.log('Tourney '+ id + ' removed successfully');
                        },
                        function(errResponse){
                            console.error('Error while removing tourney '+ id +', Error :' + errResponse.data);
                        }
                    );
            }

            function editTourney(id) {
                this.tourneySuccessMessage='';
                this.tourneyErrorMessage='';
                TourneyService.getTourney(id).then(
                    function (tourney) {
                        //tourney.date = new Date(tourney.date);
                        tourney.category = tourney.categoryName;
                        ctrl.tourney = tourney;
                    },
                    function (errResponse) {
                        console.error('Error while loading tourney to form' + id + ', Error :' + errResponse.data);
                    }
                );
            }

            function getAllTourneys() {
                return TourneyService.getAllTourneys();
            }

            function getAllLeagues() {
                return LeagueService.getAllLeagues();
            }

            function resetTourneyForm(){
                this.tourneySuccessMessage='';
                this.tourneyErrorMessage='';
                this.tourney={};
                $scope.tourneyForm.$setPristine(); //reset Form
            }

            function resetLeagueForm(){
                this.leagueSuccessMessage='';
                this.leagueErrorMessage='';
                this.leaguePoints.forEach(function (part, index, theArray) {
                    theArray[index] = {place: index+1}
                });
                this.league = {leaguePoints: this.leaguePoints};
                $scope.leagueForm.$setPristine(); //reset Form
            }

            function addNewLeaguePointsChoice() {
                var newItemNo = this.leaguePoints.length+1;
                this.leaguePoints.push({'place':newItemNo});
            }
            function removeLeaguePointsChoice() {
                var lastItem = this.leaguePoints.length-1;
                this.leaguePoints.splice(lastItem);
            }
            $(document).ready(function(){
                var date_input=$('input[name="tourneyDate"]'); //our date input has the name "date"
                // var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
                var options={
                    format: 'dd/mm/yyyy',
                    // container: container,
                    todayHighlight: true,
                    autoclose: true,
                    locale: 'pl'
                };
                date_input.datepicker(options);
            })
        }
    ]);
