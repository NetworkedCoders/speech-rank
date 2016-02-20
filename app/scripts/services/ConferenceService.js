(function () {
    'use strict';

    angular.module('speechRank').factory('ConferenceService', function ( $http, $q ) {
        
        var conferenceService = {};

        conferenceService.getConferences = function() {
            return $http({
                method: 'GET',
                url: '/json-data/conferences.json'
            });
        };

        conferenceService.getConference = function(conferenceId) {
            return $http({
                method: 'GET',
                data: conferenceId,
                url: '/json-data/conference' + conferenceId + '.json'
            });
        };

        return conferenceService;
    });
})();
