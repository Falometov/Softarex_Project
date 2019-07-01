import DateModification from './date-modification/date-modification.filter';

const module = angular.module('frontend.filters', []);

module.filter('dateModification', DateModification);

export default module.name;
