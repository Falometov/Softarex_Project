export default class {

  constructor($state) {
    'ngInject';

    this.$state = $state;
  }

  addItem(key, value) {
    localStorage.setItem(key, value);
  }

  getItem(key) {
    return localStorage.getItem(key);
  }

  checkRole(role) {
    return role === localStorage.getItem('role');
  }

  checkAction(action) {
    return action === localStorage.getItem('action');
  }

  logout() {
    localStorage.clear();
    this.$state.go('login');
  }
  
  getAccessToken() {
    return localStorage.getItem('accessToken');
  }

  getLocale() {
    return localStorage.getItem('locale');
  }

}