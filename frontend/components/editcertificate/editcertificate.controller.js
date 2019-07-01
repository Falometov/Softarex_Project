import alertify from 'alertifyjs';
import iziToast from 'izitoast';

export default class {

  constructor(LocalStorageService, CertificatesService, $state, $translate, $filter) {
    'ngInject';

    this.$filter = $filter;
    this.$translate = $translate;
    this.$state = $state;
    this.CertificatesService = CertificatesService;
    this.LocalStorageService = LocalStorageService;
  }

  updateCertificate() {
    this.certificate = this.CertificatesService.update({
      id: this.id,
      name: this.name,
      description: this.description,
      price: this.price,
      durationInDays: this.duration,
      tags: this.tags
    }, 
    () => {
      this.$state.go('certificates');
      this.showSuccessMessage(this.$filter('translate')('UPDATE_CERTIFICATE_MESSAGE'));
    },
    response => this.showErrorMessage(response.data.errorMessage));
  }

  removeCertificate() {
    alertify.confirm(this.$filter('translate')('DELETE_MESSAGE'), () => {
      this.CertificatesService.remove({
        id: this.id
      }, 
      () => {
        this.$state.go('certificates');
        this.showSuccessMessage(this.$filter('translate')('DELETE_SUCCESS_CERTIFICATE'));
      },
      response => this.showErrorMessage(response.data.errorMessage));
    })
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
    if (this.name === this.certificate.name && this.description === this.certificate.description 
        && this.price === this.certificate.price && this.duration === this.certificate.durationInDays) {
      this.$state.go('certificates');
    } else {
      alertify.confirm(this.$filter('translate')('UNSAVED_DATA'), () => this.$state.go('certificates'));
    }
  }

  $onInit() {
    alertify.defaults.glossary.title = '';
    alertify.defaults.theme.ok = 'btn btn-light';
    alertify.defaults.theme.cancel = 'btn btn-primary';

    this.certificate = JSON.parse(this.LocalStorageService.getItem('certificate'));
    this.id = this.certificate.id;
    this.name = this.certificate.name;
    this.description = this.certificate.description;
    this.price = this.certificate.price;
    this.duration = this.certificate.durationInDays;
    this.tags = this.certificate.tags;
  }

  $onChanges() {
  }

  $onDestroy() {
  }
}
