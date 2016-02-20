(function () {
    'use strict';

    angular.module('speechRank', [
        'ui.router'
    ])
    .config(config)
    .run(run);

    function config( $urlRouterProvider, $stateProvider, $sceDelegateProvider ) {
        // redirection to home path
        $urlRouterProvider.when('', '/');

        // main template
        $stateProvider.state('main', {
            abstract: true,
            templateUrl: 'templates/partials/main.html'
        })

        // PUBLIC ROUTES
        // home page
        .state('main.home', {
            url: '/',
            templateUrl: 'templates/routes/home.html',
            controller: 'HomeController'
        })

        .state('main.conference', {
            url: '/conference/:id',
            templateUrl: 'templates/routes/conference.html',
            controller: 'ConferenceController'
        })

        // MISCELLANEOUS
        // 404
        // last state is 404 page, for all those states that are not defined, we redirect to 404
        .state('main.404', {
            url: '^*path',
            templateUrl: 'templates/routes/404.html'
        });

        $sceDelegateProvider.resourceUrlWhitelist([
            'self',
            'https://www.youtube.com/**',
            'http://www.youtube.com/**'
        ]);
    }

    function run() {

    }
})();