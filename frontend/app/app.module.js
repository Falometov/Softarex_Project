console.log('application started...');

import angular from 'angular';
import resource from 'angular-resource';
import uiRouter from 'angular-ui-router';
import animate from 'angular-animate';
import AppComponent from './app.component';
import Components from '../components/components.module';
import Services from '../services/services.module';
import alertifyStyle from '../node_modules/alertifyjs/build/css/alertify.min.css';
import izitoast from '../node_modules/izitoast/dist/css/iziToast.min.css';
import Filters from '../filters/filters.module';
import translate from 'angular-translate';
import sanitize from 'angular-sanitize'
import {getEnLocalization, getRuLocalization} from './localization';

// create our app module and setup core dependencies
angular.module('app', [

  resource,
  uiRouter,
  animate,
  Components,
  Services,
  Filters,
  translate,
  sanitize

])

.config(($stateProvider, $urlRouterProvider, $translateProvider) => {
  'ngInject';

  $stateProvider
  
  .state({
    name: 'certificates',
    url: '/certificates',
    template: '<certificates/>'
  })

  .state({
    name: 'signup',
    url: '/signup',
    template: '<signup/>'
  })
  
  .state({
    name: 'login',
    url: '/login',
    template: '<authentification/>'  
  })
  
  .state({
    name: 'add',
    url: '/add',
    template: '<addcertificate/>'
  })
  
  .state({
    name: 'edit',
    url: '/edit',
    template: '<editcertificate/>'
  });

  // setup default route
  $urlRouterProvider.otherwise('/certificates');

  $translateProvider
  
  .translations('en', getEnLocalization())

  .translations('ru', getRuLocalization());

  $translateProvider.preferredLanguage('en');
  $translateProvider.useSanitizeValueStrategy('sanitizeParameters');

})

// set constants
.constant('DOMAIN_URL', 'http://localhost:8080')

.constant('CLIENT_ID', 'gift-certificates')

.constant('CLIENT_SECRET', 'secret')

// setup root component
.component('app', AppComponent)

.run(($http, LocalStorageService) => {
  'ngInject';
  $http.defaults.headers.common['Accept-Language'] = () => `${LocalStorageService.getLocale()}`;
});

