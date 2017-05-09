'use strict';

angular.module('mzpsApp').controller('AdminController',
    ['TourneyService', '$scope',
        function (TourneyService, $scope) {

            var ctrl = this;

            this.tourney = {};
            this.tourneys = [];

            this.submit = submit;
            this.createTourney = createTourney;
            this.updateTourney = updateTourney;
            this.getAllTourneys = getAllTourneys;
            this.reset = reset;

            this.successMessage = '';
            this.errorMessage = '';

            function submit() {
                console.log('Submitting');
                if (this.tourney.id === undefined || this.tourney.id === null) {
                    console.log('Saving New tourney', this.tourney);
                    createTourney(this.tourney);
                } else {
                    updateTourney(this.tourney, this.tourney.id);
                    console.log('tourney updated with id ', this.tourney.id);
                }
            }

            function createTourney(tourney) {
                console.log('About to create Tourney');
                TourneyService.createTourney(tourney)
                    .then(
                        function (response) {
                            console.log('Tourney created successfully');
                            ctrl.successMessage = 'Tourney created successfully';
                            ctrl.errorMessage='';
                            ctrl.done = true;
                            ctrl.Tourney={};
                            $scope.tourneyForm.$setPristine();
                        },
                        function (errResponse) {
                            console.error('Error while creating Tourney');
                            ctrl.errorMessage = 'Error while creating Tourney: ' + errResponse.data.errorMessage;
                            ctrl.successMessage='';
                        }
                    );
            }


            function updateTourney(Tourney, id){
                console.log('About to update Tourney');
                TourneyService.updateTourney(Tourney, id)
                    .then(
                        function (response){
                            console.log('Tourney updated successfully');
                            ctrl.successMessage='Tourney updated successfully';
                            ctrl.errorMessage='';
                            ctrl.done = true;
                            $scope.tourneyForm.$setPristine();
                        },
                        function(errResponse){
                            console.error('Error while updating Tourney');
                            ctrl.errorMessage='Error while updating Tourney '+errResponse.data;
                            ctrl.successMessage='';
                        }
                    );
            }

            function getAllTourneys() {
                return TourneyService.getAllTourneys();
            }

            function reset(){
                this.successMessage='';
                this.errorMessage='';
                this.tourney={};
                $scope.tourneyForm.$setPristine(); //reset Form
            }
        }
    ]);
