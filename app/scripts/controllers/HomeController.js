(function () {
    'use strict';

    angular.module('speechRank').controller('HomeController', function( $scope, ConferenceService, toastr ) {
        console.log('HomeController loaded..');

        $scope.getConferences = function() {
            ConferenceService.getConferences()
                .then(function(response) {
                    console.log('response', response);
                    $scope.yearsArray = response.data;
                }).catch(function(error) {
                    toastr.error(error.data, 'Server Error!');
                });
        };

        $scope.importConference = function (newConfName, newConfYear, playlistLink) {
            if (newConfName !== '' && newConfYear !== '' && playlistLink !== '') {
                ConferenceService.importConference(newConfYear, newConfName, playlistLink)
                    .then(function (response) {
                        console.log('response', response);
                        console.log($scope.yearsArray.map(function (year) {
                            if (year.year == newConfYear) {
                                year.conferences.push(response.data);
                                console.log("Conference successfully imported!");
                            }
                        }));

                    }).catch(function (error) {
                    toastr.error(error.data, 'Server Error!');
                });
            } else {
                console.log('not enough info to import conference!');
            }
        };

        $scope.getConferences();
    });
})();
