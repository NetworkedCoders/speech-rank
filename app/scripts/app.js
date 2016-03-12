(function () {
    'use strict';

    angular.module('speechRank', [
        'ui.router',
        'ngAnimate',
        'angular-loading-bar',
        'toastr'
    ])
    .config(config)
    //.run(run)
	.constant('REST_END_POINT','http://localhost:5050');

    function config( $urlRouterProvider, $stateProvider, $sceDelegateProvider, cfpLoadingBarProvider ) {
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
		    // Allow same origin resource loads.
		    'self',
		    // Allow loading from our assets domain.  Notice the difference between * and **.
		    'https://www.youtube.com/**',
		    'http://www.youtube.com/**',
		    'https://youtube.com/**',
		    'http://youtube.com'
	    ]);

        // loading bar setup
        cfpLoadingBarProvider = {
            includeBar: true,
            includeSpinner: true,
            latencyThreshold: 1500
        };
    }

    /*
    function run() {

    }
    */
})();
