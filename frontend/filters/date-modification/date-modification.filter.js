export default function Filter() {
  'ngInject';
  return function(input) {
    return new Date(input);
  }
}
