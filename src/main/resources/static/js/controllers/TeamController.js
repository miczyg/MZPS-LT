'use strict';

angular.module('mzpsApp').controller('TeamController',
    ['TeamService', '$scope',  function( TeamService, $scope) {

        this.team = {};
        this.teams=[];

        this.submit = submit;
        this.getAllTeams = getAllTeams;
        this.createTeam = createTeam;
        this.updateTeam = updateTeam;
        this.removeTeam = removeTeam;
        this.editTeam = editTeam;
        this.reset = reset;

        this.successMessage = '';
        this.errorMessage = '';
        this.done = false;
        this.tab = 1;
        this.categoryFilter = '';
        this.strictFilter = false;

        this.select = function(setTab) {
            this.tab = setTab;
            this.strictFilter = true;
            if (setTab === 2)
                this.categoryFilter = "Mlodzik";
            else if (setTab === 3)
                this.categoryFilter = "Mlodziczka";
            else if (setTab === 4)
                this.categoryFilter = "Kadet";
            else if (setTab === 5)
                this.categoryFilter = "Kadetka";
            else {
                this.strictFilter = false;
                this.categoryFilter = "";
            }
            };

        this.isSelected = function(checkTab) {
            return (this.tab === checkTab);
        };

        function submit() {
            console.log('Submitting');
            if (this.team.id === undefined || this.team.id === null) {
                console.log('Saving New Team', this.team);
                createTeam(this.team);
            } else {
                updateTeam(this.team, this.team.id);
                console.log('Team updated with id ', this.team.id);
            }
        }

        function createTeam(team) {
            console.log('About to create team');
            TeamService.createTeam(team)
                .then(
                    function (response) {
                        console.log('Team created successfully');
                        this.successMessage = 'Team created successfully';
                        this.errorMessage='';
                        this.done = true;
                        this.team={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Team');
                        this.errorMessage = 'Error while creating Team: ' + errResponse.data.errorMessage;
                        this.successMessage='';
                    }
                );
        }


        function updateTeam(team, id){
            console.log('About to update team');
            TeamService.updateTeam(team, id)
                .then(
                    function (response){
                        console.log('Team updated successfully');
                        this.successMessage='Team updated successfully';
                        this.errorMessage='';
                        this.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Team');
                        this.errorMessage='Error while updating Team '+errResponse.data;
                        this.successMessage='';
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
            this.successMessage='';
            this.errorMessage='';
            TeamService.getTeam(id).then(
                function (team) {
                    this.team = team;
                },
                function (errResponse) {
                    console.error('Error while removing team ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            this.successMessage='';
            this.errorMessage='';
            this.team={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);