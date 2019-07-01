import iziToast from 'izitoast';

export default class {

  constructor($state, UserService, AuthentificationService, LocalStorageService, $translate) {
    'ngInject';

    this.LocalStorageService = LocalStorageService;
    this.AuthentificationService = AuthentificationService;
    this.UserService = UserService;
    this.$state = $state;
    this.$translate = $translate;
  }

  signUp() {
    this.UserService.create({
      username: this.username.trim(),
      password: this.password
    },
    response => {
      this.AuthentificationService.login({
        username: response.username,
        password: this.password
      },
      data => {
        let accessToken = data.access_token;

        this.LocalStorageService.addItem('accessToken', accessToken);
        this.LocalStorageService.addItem('userName', response.username);
        this.LocalStorageService.addItem('role', response.userRole);
        this.$state.go('certificates');
      })
    },
    response => this.showErrorMessage(response.data.errorMessage));
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
