'use strict';

angular.module('mzpsApp').controller('TeamController',
    ['TeamService', '$scope', function( TeamService, $scope) {

        var ctrl = this;

        ctrl.team = {};
        ctrl.teams=[];

        ctrl.submit = submit;
        ctrl.getAllTeams = getAllTeams;
        ctrl.createTeam = createTeam;
        ctrl.updateTeam = updateTeam;
        ctrl.removeTeam = removeTeam;
        ctrl.editTeam = editTeam;
        ctrl.reset = reset;

        ctrl.successMessage = '';
        ctrl.errorMessage = '';
        ctrl.done = false;

        ctrl.category = '';
        ctrl.strictFilter = false;

        ctrl.selectCategory = function(category) {
            ctrl.category = category;
            ctrl.strictFilter = ctrl.category !== '';
        };

        ctrl.isSelected = function(category) {
            return (ctrl.category === category);
        };

        ctrl.getCategory = function(){
            return ctrl.category;
        };

        ctrl.isCategoryStrict = function () {
            return ctrl.strictFilter;
        };

        function submit() {
            console.log('Submitting');
            if (ctrl.team.id === undefined || ctrl.team.id === null) {
                console.log('Saving New Team', ctrl.team);
                createTeam(ctrl.team);
            } else {
                updateTeam(ctrl.team, ctrl.team.id);
                console.log('Team updated with id ', ctrl.team.id);
            }
        }

        function createTeam(team) {
            console.log('About to create team');
            TeamService.createTeam(team)
                .then(
                    function (response) {
                        console.log('Team created successfully');
                        ctrl.successMessage = 'Team created successfully';
                        ctrl.errorMessage='';
                        ctrl.done = true;
                        ctrl.team={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Team');
                        ctrl.errorMessage = 'Error while creating Team: ' + errResponse.data.errorMessage;
                        ctrl.successMessage='';
                    }
                );
        }


        function updateTeam(team, id){
            console.log('About to update team');
            //fixes updating team that is in a league
            if(team.league) delete team.league;
            TeamService.updateTeam(team, id)
                .then(
                    function (response){
                        console.log('Team updated successfully');
                        ctrl.successMessage='Team updated successfully';
                        ctrl.errorMessage='';
                        ctrl.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Team');
                        ctrl.errorMessage='Error while updating Team '+errResponse.data;
                        ctrl.successMessage='';
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
            ctrl.successMessage='';
            ctrl.errorMessage='';
            TeamService.getTeam(id).then(
                function (team) {
                    team.category = team.categoryName;
                    team.phone = Number(team.phone);
                    ctrl.team = team;
                },
                function (errResponse) {
                    console.error('Error while removing team ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            ctrl.successMessage='';
            ctrl.errorMessage='';
            ctrl.team={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);