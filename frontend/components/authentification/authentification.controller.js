import iziToast from 'izitoast';

export default class {

  constructor(AuthentificationService, LocalStorageService, $state, $translate, $filter) {
    'ngInject';

    this.$filter = $filter;
    this.$translate = $translate;
    this.$state = $state;
    this.AuthentificationService = AuthentificationService;
    this.LocalStorageService = LocalStorageService;
  }

  sendLogin() {
    this.AuthentificationService.login({
      username: this.username,
      password: this.password
    },
    data => {
      let accessToken = data.access_token;
      let payload = atob(accessToken.split('.')[1]);
      let token = JSON.parse(payload);

      this.LocalStorageService.addItem('accessToken', accessToken);
      this.LocalStorageService.addItem('userName', token.user_name);
      this.LocalStorageService.addItem('role', token.authorities);
      this.$state.go('certificates');
    },
    () => {
      this.showErrorMessage(this.$filter('translate')('INCORRECT_DATA'));
      this.password = null;
    });
  }

  showErrorMessage(errorMessage) {
    iziToast.error({
      title: 'Error',
      titleSize: 22,
      message: errorMessage,
      messageSize: 17,
      position: 'topLeft',
      timeout: 5000
    });
  }

  changeLanguage(lang) {
    this.LocalStorageService.addItem('locale', lang);
    this.$translate.use(this.LocalStorageService.getLocale());
  }

  $onInit() {
  }

  $onChanges() {
  }

  $onDestroy() {
  }
}
