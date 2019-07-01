import iziToast from 'izitoast';
import alertify from 'alertifyjs';

export default class {

  constructor(LocalStorageService, CertificatesService, $state, $translate, $filter) {
    'ngInject';

    this.$filter = $filter;
    this.$translate = $translate;
    this.$state = $state;
    this.tags = [];
    this.CertificatesService = CertificatesService;
    this.LocalStorageService = LocalStorageService;
  }

  createCertificate() {
    this.certificate = this.CertificatesService.create({
      name: this.name,
      description: this.description,
      price: this.price,
      durationInDays: this.duration,
      tags: this.tags
    }, 
    () => {
      this.$state.go('certificates');
      this.showSuccessMessage(this.$filter('translate')('ADD_CERTIFICATE_MESSAGE'));
    }, 
    response => this.showErrorMessage(response.data.errorMessage));
  }

  addTag() {
    let tag = new Object();
    let flag = false;

    for (let index = 0; index < this.tags.length; index++) {
      if (this.tags[index].name === this.tagName) {
        flag = true;
        break;
      }
    }

    if (!flag) {
      tag.name = this.tagName;
      this.tags.push(tag);
    }

    this.tagName = null;
  }

  removeTag(tagName) {
    for (let index = 0; index < this.tags.length; index++) {
      if (this.tags[index].name === tagName) {
        this.tags.splice(index, 1);    
      }
    }

  }

  checkInputFields() {
    return !this.name || !this.description || !this.price || !this.duration;
  }

  showSuccessMessage(successMessage) {
    iziToast.success({
      title: 'Success',
      titleSize: 22,
      message: successMessage,
      messageSize: 17,
      position: 'topLeft',
      timeout: 5000
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

  checkFields() {
    if (!this.name && !this.description && !this.price && !this.duration && this.tags.length === 0) {
      this.$state.go('certificates');
    } else {
      alertify.confirm(this.$filter('translate')('UNSAVED_DATA'), () => this.$state.go('certificates'));
    }
  }

  $onInit() {
    alertify.defaults.glossary.title = '';
    alertify.defaults.theme.ok = 'btn btn-light';
    alertify.defaults.theme.cancel = 'btn btn-primary';
  }

  $onChanges() {
  }

  $onDestroy() {
  }
}
