'use strict';

angular.module('mzpsApp').controller('TeamController',
    ['TeamService', '$scope',  function( TeamService, $scope) {

        var self = this;
        self.team = {};
        self.teams=[];

        self.submit = submit;
        self.getAllTeams = getAllTeams;
        self.createTeam = createTeam;
        self.updateTeam = updateTeam;
        self.removeTeam = removeTeam;
        self.editTeam = editTeam;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        function submit() {
            console.log('Submitting');
            if (self.team.id === undefined || self.team.id === null) {
                console.log('Saving New Team', self.team);
                createTeam(self.team);
            } else {
                updateTeam(self.team, self.team.id);
                console.log('Team updated with id ', self.team.id);
            }
        }

        function createTeam(team) {
            console.log('About to create team');
            TeamService.createTeam(team)
                .then(
                    function (response) {
                        console.log('Team created successfully');
                        self.successMessage = 'Team created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.team={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Team');
                        self.errorMessage = 'Error while creating Team: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateTeam(team, id){
            console.log('About to update team');
            TeamService.updateTeam(team, id)
                .then(
                    function (response){
                        console.log('Team updated successfully');
                        self.successMessage='Team updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Team');
                        self.errorMessage='Error while updating Team '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeTeam(id){
            console.log('About to remove Team with id '+id);
            TeamService.removeTeam(id)
                .then(
                    function(){
                        console.log('Team '+ id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing team '+ id +', Error :' + errResponse.data);
                    }
                );
        }


        function getAllTeams(){
            return TeamService.getAllTeams();
        }

        function getCategory(category){
            return TeamService.getCategory(category);
        }

        function editTeam(id) {
            self.successMessage='';
            self.errorMessage='';
            TeamService.getTeam(id).then(
                function (team) {
                    self.team = team;
                },
                function (errResponse) {
                    console.error('Error while removing team ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.team={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);